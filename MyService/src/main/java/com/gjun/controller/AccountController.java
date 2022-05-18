package com.gjun.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gjun.domain.Account;

//個人帳戶資料維護與查詢
//@CrossOrigin   //post、get、put可以，delete不行唷
@RestController
public class AccountController {
	@CrossOrigin(origins={"http://localhost:80","http://localhost:8080"})
		//查詢特定帳戶
		@GetMapping(path="/account/id/{accid}/rawdata",produces="application/json")
		public Account getAccount(@PathVariable("accid")String id) {
			//調用帳戶物件...
			Account acct=new Account();
			acct.setId(id);
			acct.setBalance(1000000);
			acct.setName("張三丰");
			acct.setBank("台灣銀行");
			return acct;
		}
		
		@PostMapping(path="/account/remove/id/{accid}/rawdata",produces="application/json")
		public Account removeAccount(@PathVariable("accid")String id) {
			//調用帳戶物件...
			Account acct=new Account();
			acct.setId(id);
			acct.setBalance(1000000);
			acct.setName("張三丰");
			acct.setBank("台灣銀行");
			return acct;
		}
}
