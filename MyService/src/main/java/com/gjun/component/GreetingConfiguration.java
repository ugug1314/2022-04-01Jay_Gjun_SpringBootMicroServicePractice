package com.gjun.component;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gjun.domain.Greeting;

//Spring factory Configuration(當作一個合約)
@Configuration
public class GreetingConfiguration {
     //空參數建構子(監控物件是否有建立了)
	public GreetingConfiguration() {
		System.out.println("Greeting 組態配置產生個體了....");
	}
	@Bean(name="gjunGreeting")  //於Spring啟動了就建立元件
	//功能 生產一個Greeting物件 給前端控制器用
	public Greeting createGreeting() {
		// 建構一個 個體物件Instance
		Greeting greeting=new Greeting();
		greeting.setWho("Eric");
		greeting.setMessage("世界和平");
		return greeting;
	}
}
