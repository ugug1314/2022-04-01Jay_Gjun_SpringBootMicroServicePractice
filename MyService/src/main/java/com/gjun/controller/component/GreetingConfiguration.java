package com.gjun.controller.component;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gjun.domain.Greeting;

//Spring factory Configuation(當作一個合作約)
@Configuration
public class GreetingConfiguration {
    //空參數建構子
	public GreetingConfiguration() {
		System.out.println("Greeting 組態配置產生個體了.....");
	}
	
	//功能 生產一個Greeting物件 給前端控制器來用
	@Bean(name="gjunGreeting")  // bean的id可以給多個name唷，不給的話bean的名稱會是方法的名稱
	public Greeting createGreeting() {
		//建構一個個體物件Instance
		Greeting greeting=new Greeting();
		greeting.setWho("Eric");
		greeting.setMessage("世界和平");
		return greeting;
	}
	
}
