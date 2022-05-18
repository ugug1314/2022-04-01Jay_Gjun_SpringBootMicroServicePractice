package com.gjun.controller.component;

import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Component;

//抽取現有員工的元件
@Component
public class EmployeesGeneratedComponent {
     private List<String> employees;
   //建構子進行初始化
	public EmployeesGeneratedComponent() {
		System.out.println("員工物件選擇器物件產生");
		employees=List.of("張三豐","張無忌","張翠珊","張泰山");
		
	}
     //動態抽取員工字串
	//Instance Method
	public String generate() {
		//建構隨機物件
		var r=new Random();
		int value=r.nextInt(employees.size());
		return employees.get(value);
	}
	
}
