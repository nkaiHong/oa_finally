package org.fkjava.layout;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.DispatcherType;

import org.sitemesh.config.ConfigurableSiteMeshFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@ComponentScan("org.fkjava")
@EnableJpaRepositories
//使用页面装饰器，在这里配
public class LayoutConfig implements WebMvcConfigurer{

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		WebMvcConfigurer.super.addInterceptors(registry);
	}
	
	//在Spring boot 里面增加自定义的过滤器
	//只需要的bean类型为FilterRegistrationBean，那么就会自动作为一个过滤器增加到容器里面，
	
	@Bean
	public FilterRegistrationBean<ConfigurableSiteMeshFilter> siteMethFilter(){
		
		ConfigurableSiteMeshFilter filter = new ConfigurableSiteMeshFilter();
		
		FilterRegistrationBean<ConfigurableSiteMeshFilter> bean = new FilterRegistrationBean<>();
		//这种过滤，是拦截器，Spring boot里面，所有的静态文件都不会进入Spring MVC中
		//所有根本无法拦截到js，css文件
		bean.addUrlPatterns("/*"); //拦截所有的url
		bean.setFilter(filter);//把过滤器添加到Spring里面
		bean.setAsyncSupported(true);//激活异步servlet请求支持
		bean.setEnabled(true);//激活使用
		
		//只处理来自浏览器的请求和错误的转，Forward，include都不处理
		bean.setDispatcherTypes(DispatcherType.REQUEST,DispatcherType.ERROR);
		
		//初始化过滤的参数
		Map<String,String> initParameters = new HashMap<>();
	 
		// /*使用main.jsp来装饰	
		// /admin/* 使用admin.jsp来装饰 main为带横幅，菜单的布局     simple为不带横幅，菜单的布局
		initParameters.put("decoratorMappings", "/*=/WEB-INF/layouts/main.jsp\n/security/login=/WEB-INF/layouts/simple.jsp");
		//排除某些路径不要装饰
		//initParameters.put("exclude", "/identity/role,/identity/menu");
		bean.setInitParameters(initParameters);
		return bean;
	}
	public static void main(String[] args) {
		SpringApplication.run(LayoutConfig.class, args);
	}
}

