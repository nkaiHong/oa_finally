package org.fkjava.layout;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@ComponentScan("org.fkjava")
@EnableJpaRepositories
//继承之后自己的login页面跳不了，会出现一个新的login页面
//会按照configure里面的设置来跳转，所有我们要重写
public class SecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer{

	// 配置基于HTTP的安全控制
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()// 验证请求
		// 登录页面的地址和其他的静态页面都不要权限
		// /*表示目录下的任何地址，但是不包括子目录
		// /** 则连同子目录一起匹配
		.antMatchers("/security/login", "/css/**", "/js/**", "/webjars/**", "/static/**")//
		.permitAll()// 不做访问判断
		.anyRequest()// 所有请求
		.authenticated()// 授权以后才能访问
		.and()// 并且
		.formLogin()// 使用表单进行登录
		.loginPage("/security/login")// 登录页面的位置，默认是/login
		// 此页面不需要有对应的JSP，而且也不需要有对应代码，只要URL
		// 这个URL是Spring Security使用的，用来接收请求参数、调用Spring Security的鉴权模块
		.loginProcessingUrl("/security/do-login")// 处理登录请求的URL
		.usernameParameter("loginName")// 登录名的参数名
		.passwordParameter("password")// 密码的参数名称
		// .and().httpBasic()// 也可以基于HTTP的标准验证方法（弹出对话框）
		//.and().httpBasic()// 也可以基于HTTP的标准验证方法（弹出对话框）;
		.and().csrf()// 激活防跨站攻击功能
		;
	}
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// 系统默认自动把静态文件的根目录，映射到/public、/static、/resources里面。
	}
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// 动态注册URL和视图的映射关闭，解决控制器里面几乎没有代码的问题。
		registry.addViewController("security/login")
		.setViewName("security/login");
	}
	public static void main(String[] args) {
		SpringApplication.run(SecurityConfig.class, args);
	}
}
