package net.thjang.blog.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = { "net.thjang.blog.dao",
        "net.thjang.blog.service"})
@Import({ DBConfig.class})
public class ApplicationConfig {



}
