package com.d3banking.training.App;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication(scanBasePackages= {"com.d3banking.config", "com.d3banking.training.config"})
@EnableResourceServer
public class checkImageApi
{
    public static  void main(String[] args)
    {
        SpringApplication.run(checkImageApi.class,args);
    }
}
