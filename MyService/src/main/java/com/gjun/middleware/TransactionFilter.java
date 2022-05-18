package com.gjun.middleware;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class TransactionFilter implements Filter{
	 public TransactionFilter() {
		 System.out.println("Transaction Filter部署起來");
	 }
	
	
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		
		 System.out.println("1.Transaction Filter 攔截起來....");
		 //轉換Http
		 HttpServletRequest request=(HttpServletRequest) req;
		 HttpServletResponse response=(HttpServletResponse) resp;
		 //操作Cookie 操作Session 操作Header 必須用Http
		 //判斷是否傳遞一把apikey
		   String apikey=request.getHeader("apikey");
		   System.out.println(request.getRequestURI());
		   System.out.println(apikey);
		   //將請求往下傳
		   chain.doFilter(request, response);
		
	}

}
