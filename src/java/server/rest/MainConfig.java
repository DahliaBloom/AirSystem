package server.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import server.service.MainCollection;

@Configuration

public class MainConfig {
    @Bean
    public MainCollection getCollection() {
        MainCollection mainCollection = new MainCollection();
        mainCollection.setup();
        return mainCollection;
    }

}