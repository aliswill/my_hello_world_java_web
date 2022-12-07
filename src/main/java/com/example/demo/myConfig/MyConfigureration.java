package com.example.demo.myConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.demo.Interceptor.Interceptor;
import com.example.demo.utils.MyJdbcTemplate;

@Configuration
public class MyConfigureration implements WebMvcConfigurer {

	
//	@Autowired MyJdbcTemplate myjdbctemplate;
////這樣就完成配置了? 這樣不行 要去參考原本JdbcTemplate是怎樣配置的 不然會少一些資料庫參數之類的
//	@Bean
//	public MyJdbcTemplate addMyJdbcTemplate() {
//		//MyJdbcTemplate myjdbctemplate = new MyJdbcTemplate();
//		return myjdbctemplate;
//	}
	
	
	//配置攔截器
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		//頁面路徑跟靜態資原路徑都是解析的那個，不管是用thymeleaf解析或原使靜態資原路徑
		registry.addInterceptor(new Interceptor()).addPathPatterns("/**").excludePathPatterns("/","/toRegister","/login","/css/**","javascript/**");
		//注意"/css/**" 前面要斜線 不然css會被攔掉
		//要攔的是網址不是路徑!!所以執行登入檢查對應的那個網址不能攔
		
	}
	
	


}
