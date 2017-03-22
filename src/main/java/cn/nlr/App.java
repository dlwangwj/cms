package cn.nlr;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;


@SpringBootApplication
public class App extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(App.class);
	}
	//@Bean
//	public EmbeddedServletContainerCustomizer containerCustomizer() {
//
//	   return (container -> {
//	        ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/401.html");
//	        ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/404.html");
//	        ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500.html");
//
//	        container.addErrorPages(error401Page, error404Page, error500Page);
//	   });
//	}
    public static void main( String[] args )
    {
    	 SpringApplication application = new SpringApplication(App.class);
         application.setBannerMode(Banner.Mode.OFF); 
         application.run(args); 
         System.out.println( "run App!" );
    }
}
