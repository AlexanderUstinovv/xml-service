package com.sberbank.xmlservice.config;

import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.sql.SQLException;

@Configuration
public class H2Config {
    @PostConstruct
    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server h2Server() throws SQLException {
        return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", port);
    }

    @Value("${spring.h2.port}")
    private String port;
}
