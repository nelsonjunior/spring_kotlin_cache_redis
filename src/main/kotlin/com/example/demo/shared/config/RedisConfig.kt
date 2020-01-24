package com.example.demo.shared.config

import org.springframework.core.env.Environment;
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.connection.RedisPassword
import org.apache.tomcat.jni.SSL.setPassword
import org.springframework.data.redis.connection.RedisStandaloneConfiguration



@Configuration
@EnableCaching
@PropertySource("classpath:application.properties")
class RedisConfig (private val env: Environment) {

    fun redisConnectionFactory() : LettuceConnectionFactory{
        val redisConf = RedisStandaloneConfiguration()
        redisConf.hostName = env.getProperty("spring.redis.host").toString()
        redisConf.port = Integer.parseInt(env.getProperty("spring.redis.port"))
        redisConf.password = RedisPassword.of(env.getProperty("spring.redis.password"))
        return LettuceConnectionFactory(redisConf)
    }
}