package com.example.demo.myConfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.demo.Interceptor.Interceptor;

@Configuration
public class MyConfigureration implements WebMvcConfigurer {

	//配置攔截器
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		//頁面路徑跟靜態資原路徑都是解析的那個，不管是用thymeleaf解析或原使靜態資原路徑
		registry.addInterceptor(new Interceptor()).addPathPatterns("/**").excludePathPatterns("/","/login","css/**","javascript/**");
		//要攔的是網址不是路徑!!所以執行登入檢查對應的那個網址不能攔
		
	}

}
