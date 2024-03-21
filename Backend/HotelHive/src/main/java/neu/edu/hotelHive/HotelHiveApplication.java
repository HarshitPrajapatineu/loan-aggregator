package neu.edu.hotelHive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableEurekaClient
public class HotelHiveApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelHiveApplication.class, args);
	}

}
