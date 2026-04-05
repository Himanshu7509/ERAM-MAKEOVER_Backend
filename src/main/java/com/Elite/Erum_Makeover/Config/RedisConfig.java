package com.Elite.Erum_Makeover.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

@Configuration
public class RedisConfig {

    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {

        String host = System.getenv("REDISHOST");
        int port = Integer.parseInt(System.getenv("REDISPORT"));
        String password = System.getenv("REDISPASSWORD");

        System.out.println("Connecting to Redis: " + host + ":" + port);

        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        config.setHostName(host);
        config.setPort(port);
        config.setPassword(password);

        // 🔥 IMPORTANT → enable SSL (Railway uses it internally sometimes)
        LettuceClientConfiguration clientConfig = LettuceClientConfiguration.builder()
                .useSsl()
                .build();

        return new LettuceConnectionFactory(config, clientConfig);
    }
}