package com.gjun.component;

import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class EmployeesGeneratedComponent {
  private List<String> employees;
  
  //建構子初始化
   public EmployeesGeneratedComponent() {
	   System.out.println("員工物件選擇器物件產生....");
	   employees=List.of(
			   "小杰","小美","小明","小丸子"
			   );
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
