//package com.Elite.Erum_Makeover.Config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
//import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
//import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
//
//@Configuration
//public class RedisConfig {
//    @Bean
//    public LettuceConnectionFactory redisConnectionFactory() {
//
//        String host = System.getenv("REDISHOST");
//        String portStr = System.getenv("REDISPORT");
//        String password = System.getenv("REDISPASSWORD");
//
//        System.out.println("HOST: " + host);
//        System.out.println("PORT: " + portStr);
//        System.out.println("PASS: " + password);
//        int port = Integer.parseInt(portStr);
//
//        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
//        config.setHostName(host);
//        config.setPort(port);
//        config.setPassword(password);
//
//        // 🔥 MOST IMPORTANT LINE (THIS FIXES YOUR ERROR)
//        LettuceClientConfiguration clientConfig = LettuceClientConfiguration.builder()
//                .useSsl()   // ✅ MUST ENABLE
//                .build();
//
//        return new LettuceConnectionFactory(config, clientConfig);
//    }
//}