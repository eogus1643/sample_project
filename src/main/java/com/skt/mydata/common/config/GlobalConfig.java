package com.skt.mydata.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.navercorp.lucy.security.xss.servletfilter.XssEscapeServletFilter;

@Configuration
public class GlobalConfig implements WebMvcConfigurer {

	@Value("${filePath.path}")
    private String filePath;

	@Autowired
	CommInterceptor commInterceptor;

	// /포워드
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController( "/" ).setViewName( "forward:/main" );
//		registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
	}

	//XSS 설정
	@Bean
	public FilterRegistrationBean<XssEscapeServletFilter> filterRegistrationBean() {
		FilterRegistrationBean<XssEscapeServletFilter> filterRegistration = new FilterRegistrationBean<>();
		filterRegistration.setFilter(new XssEscapeServletFilter());
		filterRegistration.setOrder(1);
		filterRegistration.addUrlPatterns("/*");
		return filterRegistration;
	}

	//정적 Resource설정
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/");
		registry.addResourceHandler("/fonts/**").addResourceLocations("classpath:/static/fonts/");
		registry.addResourceHandler("/img/**").addResourceLocations("classpath:/static/img/");
		registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/");
		registry.addResourceHandler("/filePath/**").addResourceLocations("file:///"+filePath);
	}

	//Interceptor 설정
	@Override
	public void addInterceptors (InterceptorRegistry registry) {
		registry.addInterceptor(commInterceptor)
		.excludePathPatterns(
				"/css/**", "/fonts/**", "/img/**", "/js/**"
				,"/mydata/pass/home/error"
				, "/filePath/**"
			);
	}


}
