package com.gjun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gjun.controller.component.EmployeesGeneratedComponent;
import com.gjun.domain.Greeting;

//對外對內的控制器
//POJO(Plain Old Java Object
@RestController
public class GreetingController {
	public GreetingController() {
		//測試Controller是否會依Spring Boot啟動而產生個體
		System.out.println("GreetingController 控制器產生個體....");
	}
      //Property Injection(屬性注入)
	 @Autowired
	 private Greeting gjunGreeting;  //直接使用註冊在組態檔的bean，自動注入
	 
	 //屬性注入元件
	 @Autowired
	 private EmployeesGeneratedComponent generate;
	 
	  //提供Action 回應打招呼訊息 前端base on http
	  //需要揭露URL End Point 及明確Request Method...
	 @RequestMapping(path="/hello/rawdata" ,method= {RequestMethod.GET})
	public Greeting helloGreeting() {
		//操作被注入的Greeting物件
		return gjunGreeting;
	}
	 
	 //Method 參數注入 配合類型(該類型需具有建構架構不可直接用介面)
	 @GetMapping("/hello/component") 
	 //這裡的注入不同於上的Autowired注入的是組態檔設定的Greeting bean，這裡只是注入為空的Greeting類別的物件
	 public Greeting helloInjection(Greeting gjunGreeting) {
		 gjunGreeting.setWho(generate.generate());
		 gjunGreeting.setMessage("世界和平");
		 return gjunGreeting;
	 }
}
