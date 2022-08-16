package com.gjun.myservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//聲明多個Bean配置 可以觸發多個元件掃描
@SpringBootApplication
@ComponentScan("com.gjun") //掃描控制項配置 Compoent/Bean
public class MyServiceApplication {

	public static void main(String[] args) {
		//啟動服務(Engine--隱含注入服務與Middle ware)
		//啟動了@Configuration
		SpringApplication.run(MyServiceApplication.class, args);
	}

}
