package com.gjun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gjun.component.EmployeesGeneratedComponent;
import com.gjun.domain.Greeting;
//對外對內的控制器
//POJO(Plain Old Java Object)
@RestController
public class GreetingController {
	
	// 空參數建構子
     public GreetingController() {
    	 System.out.println("Greeting 控制器產生個體....");
     }
	//Property Injection(屬性注入)
	@Autowired
	private Greeting gjunGreeting;
	//屬性注入元件，用來產生人名
	@Autowired
	private EmployeesGeneratedComponent generate;
	
	//提供Action 回應打招呼訊息 前端base on http
	//需求揭露URL End Point/明確Request Method...
	//如果xxxMapping中指定其他的參數，則路徑也必須以path="xxx"的方式撰寫。
	@RequestMapping(path="/hello/rawdata",method= {RequestMethod.GET})
	public Greeting helloGreeting() {
		//操作被注入的Greeting物件
		return gjunGreeting;
	}
	//Method 參數注入 配合類型(要有具有可建構架構)
	@GetMapping("/hello/component")
	public Greeting helloInjection(Greeting gjunGreeting) {
		gjunGreeting.setWho(generate.generate()); //呼叫亂數產生人名的方法
		gjunGreeting.setMessage("Spring Boot awsome");
		return gjunGreeting;
	}
}
