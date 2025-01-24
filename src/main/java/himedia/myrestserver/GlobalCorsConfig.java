package himedia.myrestserver;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class GlobalCorsConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
//		CORS 정책 설정
		registry.addMapping("/api/**")	//	/api로 시작되는 모든 요청에 CORS 정책 부여
		.allowedOrigins("*")			//	모든 도메인에 대해 허용
		.allowedMethods("GET","POST","PUT","DELETE")
		.allowedHeaders("*")			//	모든 헤더 허용
		.maxAge(3600);					//	CORS 캐싱 허용 시간 (초단위 : 3600초(1시간) )
	}

	
	
	
}
