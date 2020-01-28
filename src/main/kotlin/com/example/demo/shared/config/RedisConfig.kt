package com.example.demo.shared.config

import com.example.demo.shared.config.handler.CustomCacheErrorHandler
import org.springframework.core.env.Environment;
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.connection.RedisPassword
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.cache.RedisCacheManager
import org.springframework.data.redis.cache.RedisCacheConfiguration
import java.time.Duration
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration
import io.lettuce.core.ClientOptions
import io.lettuce.core.SocketOptions
import io.lettuce.core.resource.ClientResources
import org.springframework.cache.annotation.CachingConfigurerSupport
import org.springframework.cache.interceptor.CacheErrorHandler


@Configuration
@EnableCaching
@PropertySource("classpath:application.properties")
class RedisConfig (private val env: Environment) : CachingConfigurerSupport() {

    override fun errorHandler(): CacheErrorHandler {
        return CustomCacheErrorHandler()
    }

    @Bean
    fun redisConnectionFactory() : LettuceConnectionFactory{

        val socketOptions = SocketOptions.builder()
                .connectTimeout(Duration.ofMillis(300)).build()
        val clientOptions = ClientOptions.builder()
                .socketOptions(socketOptions)
                .autoReconnect(true)
                .suspendReconnectOnProtocolFailure(true)
                .cancelCommandsOnReconnectFailure(true)
                .disconnectedBehavior(ClientOptions.DisconnectedBehavior.REJECT_COMMANDS)
                .build()

        val clientConfig = LettuceClientConfiguration.builder()
                .commandTimeout(Duration.ofMillis(300))
                .clientOptions(clientOptions).build()

        val redisConf = RedisStandaloneConfiguration()
        redisConf.hostName = env.getProperty("spring.redis.host").toString()
        redisConf.port = Integer.parseInt(env.getProperty("spring.redis.port"))
        redisConf.password = RedisPassword.of(env.getProperty("spring.redis.password"))
        return LettuceConnectionFactory(redisConf, clientConfig)
    }

    @Bean
    fun cacheConfiguration(): RedisCacheConfiguration {
        val loader = this.javaClass.classLoader
        val jdkSerializer = JdkSerializationRedisSerializer(loader)
        val pair = SerializationPair.fromSerializer(jdkSerializer)
        return RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofSeconds(300))
                .serializeValuesWith(pair)
                .disableCachingNullValues()
    }

    @Bean
    fun cacheRedisManager(): RedisCacheManager {
        return RedisCacheManager.builder(redisConnectionFactory())
                .cacheDefaults(cacheConfiguration())
                .transactionAware()
                .build()
    }

}