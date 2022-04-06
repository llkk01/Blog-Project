package config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import env.Logins;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages="com.stone")
public class MvcConfig implements WebMvcConfigurer {

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp("/WEB-INF/views/",".jsp");
	}
	
	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/img/**").addResourceLocations("/img/");
	}
	   
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new Logins())
		        .addPathPatterns("/**")
		        .excludePathPatterns("/login")	
		        
		        .excludePathPatterns("/main")
		        
		        .excludePathPatterns("/memberAdd")	
		        .excludePathPatterns("/id")	
		        //.excludePathPatterns("/memberList")	
		        //.excludePathPatterns("/memberDetail")
		        .excludePathPatterns("/member/*")
		        
		        //.excludePathPatterns("/boardAdd")
		        .excludePathPatterns("/boardUpdate/")
		        .excludePathPatterns("/boardDelete/")
		        .excludePathPatterns("/boardList")
		        
		        .excludePathPatterns("/visitorUpdate/")
		        .excludePathPatterns("/visitorDelete/")
		        .excludePathPatterns("/visitorList")
		        
		       
				.excludePathPatterns("/img/*");
	}
	
}
