package com.gjun.controller.component;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.gjun.domain.AppInformation;

//佈署可以重覆使用的Spring Bean(元件Component)
@Configuration
public class EntityConfiguration {
	public EntityConfiguration() {
		System.out.println("Entity組態物件建構...");
	}
	// 生命週期??->Spring預設注入使用的都是同一個bean元件
   @Bean("information")
   //@Scope("singleton")  //預設就是singleton,多執行緒時要注意是否會有衝突
   @Scope(value=ConfigurableBeanFactory.SCOPE_PROTOTYPE)  // 另一種寫法，並使用prototype
	public AppInformation getInformation() {
		// 建構物件(Instance)
		AppInformation infor=new AppInformation();
		infor.setAppName("財務系統");
		infor.setAddress("台北市公園路");
		infor.setCopyRight("版權所有");
		return infor;
	}
}
