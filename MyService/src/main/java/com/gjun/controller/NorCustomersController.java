package com.gjun.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gjun.domain.Customers;
import com.gjun.domain.Message;



//客戶服務控制器(RESTful)
@RestController
public class NorCustomersController {
	//屬性注入Property Injection JdbcTemplate
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	//Action(Method) 調出客戶所有資料 Request Method-GET
	//Produces-Response Header Content-Type:application/json (MIME)
	@GetMapping(path="/nor/customers/all/rawdata",produces="application/json")
	public List<Customers> AllCustomers() {
		DataSource datasource=jdbcTemplate.getDataSource();
		System.out.println(datasource.toString());
		//下SQL語法
		String sql="select CustomerID,CompanyName,Address,Phone,Country from Customers";
			List<Customers> result=jdbcTemplate.query(sql,
					//匿名類別(callback 找到每一筆Fetching 逐筆整理成封裝JavaBean物件)
					/*new RowMapper<Customers>() {
						
						//逐筆
						@Override
						public Customers mapRow(ResultSet rs, int rowNum) throws SQLException {
							//不要針對ResultSet next
							Customers customers=new Customers();
							customers.setCustomerId(rs.getString("CustomerID"));
							customers.setCompanyName(rs.getString("CompanyName"));
							customers.setAddress(rs.getString("Address"));
							customers.setPhone(rs.getString("Phone"));
							customers.setCountry(rs.getString("Country"));
							
							return customers;
						}
				
			}*/
			   //Lambda
			   (rs,num)->{
				   Customers customers=new Customers();
					customers.setCustomerId(rs.getString("CustomerID"));
					customers.setCompanyName(rs.getString("CompanyName"));
					customers.setAddress(rs.getString("Address"));
					customers.setPhone(rs.getString("Phone"));
					customers.setCountry(rs.getString("Country"));
					
					return customers;
			   }
					); 
		return result;
	}
  
	
	//採購path傳遞客戶編號 進行單筆查詢
	@GetMapping(path="/nor/customers/id/{cid}/rawdata",produces = "application/json")
	public ResponseEntity<Object> customersById(@PathVariable("cid")String customerId) {
		//使用注入JdbcTemplate進行查詢
		try {
		Customers result=jdbcTemplate.queryForObject("select CustomerID,CompanyName,Address,Phone,"
				+ "Country From Customers Where CustomerID=?",
				// 查詢結果callback function point傳遞
				(rs,num)->{
					Customers customers=new Customers();
					customers.setCustomerId(rs.getString("CustomerID"));
					customers.setCompanyName(rs.getString("CompanyName"));
					customers.setAddress(rs.getString("Address"));
					customers.setPhone(rs.getString("Phone"));
					customers.setCountry(rs.getString("Country"));
					
					return customers;
				} ,
				new Object[] {customerId}   //customerId是對應sql語法中的?
				
				);
		 //建構ResponseEntity
		return new ResponseEntity<>(result,HttpStatus.OK);
		}catch(DataAccessException e) {
			Message msg=new Message();
			msg.setCode(400);  //客製化訊息清單代號
			msg.setMsg("查無此客戶");
			//建構ResponseEntity 封裝序列化Entity 與 Http Status Code
			//ResponseEntity可用來回應不同型別訊息的資料。
			return new ResponseEntity<>(msg,HttpStatus.BAD_REQUEST);
		}
	}
	
	
	
	//客戶新增(Http Body-rawdata-JSON) /Http Request Method-POST
	@PostMapping(path="/nor/customers/add",consumes="application/json",produces="application/json")
	//comsumes Request Headeer Content-Type:application/json
	public  ResponseEntity<Message> customersAdd(@RequestBody Customers customers) {
		// 透過DAO進行資料新增作業
		String sql="Insert into Customers(CustomerID,CompanyName,Address,Phone,Country) values(?,?,?,?,?)";
		Message msg=new Message();
		//使用Message類別來設定要回傳的訊息，加上ResponseEntity可設定http的回傳狀態
		ResponseEntity<Message> response=null;
		try {
		int affect=jdbcTemplate.update(sql,
				   customers.getCustomerId(),
				   customers.getCompanyName(),
				   customers.getAddress(),
				   customers.getPhone(),
				   customers.getCountry());
		msg.setCode(200);
		msg.setMsg("客戶新增成功");
		response=new ResponseEntity<Message>(msg,HttpStatus.OK);
		}catch(DataAccessException ex) {
			msg.setCode(400);
			msg.setMsg("客戶新增失敗");
			response=new ResponseEntity<Message>(msg,HttpStatus.BAD_REQUEST);
		}
		//新增狀態回應message內容
		return response;
	}
}
