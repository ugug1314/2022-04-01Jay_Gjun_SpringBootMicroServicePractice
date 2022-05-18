package com.gjun.middleware;
//攔截Basic類別的Authorization 進行驗證

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import java.util.Base64;
//import java.util.Base64.Decoder;
import org.apache.commons.codec.binary.Base64;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
//@Order(2)
public class TokenFilter implements Filter{

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		 System.out.println("2. Token 攔截起來");
		 HttpServletRequest request=(HttpServletRequest) req;
		 HttpServletResponse response=(HttpServletResponse) resp;
		 //參照出Header Authorization
		 String token=request.getHeader("Authorization"); //Basic Token是Base64編碼
		 
		 if(token!=null) {
		 //切割字串 basic xxxxxx ->post man傳來的前面會有basic
			 String[] items=token.split(" ");
		//建立Base64的物件 
	    Base64 base64 = new Base64();	
	     //Base64 decode 我的寫法
		String decodeString=new String(base64.decode(items[1]));
		//老師寫法
		/*Base64 獲取一個decode物件
		Base64.Decoder decorder=Base64.getDecoder();
		byte[] decodeByte=decorder.decode(items[1].getBytes("UTF-8"));
		var txtString=new String(decodeByte,"UTF-8");*/
		System.out.println(decodeString);
		
		//切割取出使用者帳號與密碼
		String[] data=decodeString.split(":");
		String userid=data[0];
		String password=data[1];
		//進行合法驗證 Database authorization 
		chain.doFilter(request, response);
		 }
	}
    @Bean   
	public FilterRegistrationBean<TokenFilter> loggingFilter(){
		//建構FilterRegistrationBean物件
    
		FilterRegistrationBean<TokenFilter> register=
				new FilterRegistrationBean<>();
		//設定Filter
		register.setFilter(new TokenFilter());
		//設定URL，
		register.addUrlPatterns("/iot/*");
		register.addUrlPatterns("/acct/*");
		register.setOrder(2);
		return register;
	}
}
