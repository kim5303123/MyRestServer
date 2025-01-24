package himedia.myrestserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages= {"himedia.myrestserver", 
								"learnbyteaching.todos" })
public class MyRestServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyRestServerApplication.class, args);
	}

}
