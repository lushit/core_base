package lush.config;

import lush.intercepter.Interceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {
	
	// 등록할 인터셉터
	@Autowired
	private Interceptor interceptor;
	
	// 인터셉터 등록 로직
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 제외할 접근 경로
		String[] excludePathPatterns = {"/login", "/logout", "/updatePassword", "/getAuthorization","/createMember","/createCustomer","/getMemberCheck","/getCodeName"};
		
		// 만들어둔 인터셉터를 등록해준다
		registry.addInterceptor(interceptor)
			// 허용할 접근 경로
			.addPathPatterns("/**")
			// 제외할 접근 경로
			.excludePathPatterns(excludePathPatterns)
			// 임시 제외접근 경로
			.excludePathPatterns("/common/**")
			.excludePathPatterns("/test");
		
	}
	
	// Resources 관련 처리
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// js 및 기타 인클루드 관련 등
		registry
			.addResourceHandler("/common/**")
			.addResourceLocations("/common/")
			.setCachePeriod(3600)
			.resourceChain(true)
			.addResolver(new PathResourceResolver());
	}
}
