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
    public static void main( String[] args )
    {
    	 SpringApplication application = new SpringApplication(App.class);
         application.setBannerMode(Banner.Mode.OFF); 
         application.run(args); 
         System.out.println( "run App!" );
    }
}
