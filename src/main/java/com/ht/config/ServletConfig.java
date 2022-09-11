package com.ht.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.ht.interceptor.AdminInterceptor;
import com.ht.interceptor.CartInterceptor;
import com.ht.interceptor.LoginInterceptor;

@EnableWebMvc //설정이 완료된 여러 스프링 빈을 추가해준다.
@ComponentScan(basePackages = {"com.ht.*"})

public class ServletConfig implements WebMvcConfigurer {
	
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		
		InternalResourceViewResolver bean = new InternalResourceViewResolver();
		bean.setViewClass(JstlView.class);
		bean.setPrefix("/WEB-INF/views/");
		bean.setSuffix(".jsp");
		registry.viewResolver(bean);
	}
	
/*
 * 내용의 의미는 /resources/** 말대로 /resources/js, 
 * /resources/css 와 같은 경로로 명시하는 경우에 
* /resources/ 경로로 해석하겠다 라는 의미로 보시면 됩니다. 
 
 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
		
	}
	
	@Bean
	public MultipartResolver multipartResolver() {
		StandardServletMultipartResolver resolver = new StandardServletMultipartResolver();
		return resolver;
	}
	
	//Interceptor 
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/member/login");
        registry.addInterceptor(new AdminInterceptor()).addPathPatterns("/admin/**");
        registry.addInterceptor(new CartInterceptor()).addPathPatterns("/cart/**").excludePathPatterns("/cart/add");
    }

}
