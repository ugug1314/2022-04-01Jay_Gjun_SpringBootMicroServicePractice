package com.gjun.myservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.gjun.domain.Greeting;

//定義Spring Configuration(隨著應用系統啟動 並進行初始化配置)
@Configuration
@ComponentScan
public class ApplicationConfig {
 
	public ApplicationConfig() {
		System.out.println("Spring 組態佈署...");
	}
	//宣告一些可以重覆使用的元件(Spring Bean) Greeting(@Bean Spring Bean)
	@Bean("greetings") //指定Bean的名稱
	public Greeting createGreeting() {
		Greeting greeting=new Greeting();
		//注入資料
		greeting.setWho("Jay");
		greeting.setMessage("Jay say Hello");
		return greeting;
	}
}
