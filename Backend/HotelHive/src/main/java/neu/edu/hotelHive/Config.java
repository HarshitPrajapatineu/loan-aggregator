package neu.edu.hotelHive;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Config {
 
    
    @Bean("eurekaTemplate")
    @LoadBalanced
     public RestTemplate eurekaTemplate() {
         HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
         httpRequestFactory.setConnectionRequestTimeout(4000);
         httpRequestFactory.setConnectTimeout(4000);
         httpRequestFactory.setReadTimeout(4000);
         return new RestTemplate(httpRequestFactory);
     }
}
