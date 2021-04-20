package TDMeasurement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication
public class TdMeasurementApplication {

	public static void main(String[] args) {
		SpringApplication.run(TdMeasurementApplication.class, args);
	}

	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		//Single file maximum KB, MB
		factory.setMaxFileSize(DataSize.parse("100MB"));
		/// Set the total size of the total uploaded data
		factory.setMaxRequestSize(DataSize.parse("500MB"));
		return factory.createMultipartConfig();
	}
}
