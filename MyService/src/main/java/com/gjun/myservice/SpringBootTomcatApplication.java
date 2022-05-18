package com.gjun.myservice;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
@SpringBootApplication
@ComponentScan("com.gjun")
  //建立SpringBoot初始化類別
public class SpringBootTomcatApplication extends SpringBootServletInitializer{

}
