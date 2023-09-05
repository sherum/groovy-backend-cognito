package org.vfvt.story

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories
import org.springframework.web.client.RestTemplate
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@EnableMongoRepositories
@SpringBootApplication
class StoryApplication {

    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate()
    }

    @Bean
    WebConfiguration webConfiguration(){
        return new WebConfiguration()
    }



    static void main(String[] args) {
        SpringApplication.run(StoryApplication, args)
    }

}

class WebConfiguration implements WebMvcConfigurer {

    @Override
    void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedMethods("*");
    }
}