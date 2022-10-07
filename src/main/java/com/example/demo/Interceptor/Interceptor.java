package com.example.demo.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
//檢查是否有登入
//攔截器 寫完需要配置=>去哪配置?

@Component
public class Interceptor implements HandlerInterceptor {

	@Override
	//目標方法執行之前
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		Object loginuser = request.getSession().getAttribute("loginUser");
		//未登入，回到登入頁面
		if(loginuser==null) {
			request.setAttribute("msg", "使用者尚未登入");
			request.getRequestDispatcher("/").forward(request, response);
			return false;
		}
		//已登入
		else {
			request.setAttribute("msg", "使用者已經登入");//DEBUG
			return true;//放行請求
		}
		
		//return HandlerInterceptor.super.preHandle(request, response, handler);
	}



}
