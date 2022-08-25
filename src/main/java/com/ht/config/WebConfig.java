package com.ht.config;


import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] {RootConfig.class, SecurityConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {ServletConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
	
	
	@Override
	protected void customizeRegistration(ServletRegistration.Dynamic registration) {
		// url 경로가 없으면 예외 처리
		registration.setInitParameter("throwExceptionIfNoHandlerFound","true");
		
		// 파일 업로드를 위한 MultipartConfig
		MultipartConfigElement muConfigElement = new MultipartConfigElement("C:\\storage\\temp",20971520, 41943040, 20971520);
		registration.setMultipartConfig(muConfigElement);
	}
	
	// 웹에서 등록할 때 한글 utf-8 허용 필터
	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);
		//return super.getServletFilters();
		return new Filter[] {characterEncodingFilter};
	}

	
}
