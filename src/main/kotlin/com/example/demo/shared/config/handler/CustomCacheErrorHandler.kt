package com.example.demo.shared.config.handler

import org.slf4j.LoggerFactory
import org.springframework.cache.Cache
import org.springframework.cache.interceptor.CacheErrorHandler


class CustomCacheErrorHandler : CacheErrorHandler {

    protected val logger = LoggerFactory.getLogger(this.javaClass)


    override fun handleCacheGetError(p0: java.lang.RuntimeException, p1: Cache, p2: Any) {
        logger.error("Error in REDIS GET operation for KEY: $p2", p0)
    }

    override fun handleCachePutError(p0: java.lang.RuntimeException, p1: Cache, p2: Any, p3: Any?) {
        logger.error("Error in REDIS PUT operation for KEY: $p2", p0)
    }

    override fun handleCacheEvictError(p0: java.lang.RuntimeException, p1: Cache, p2: Any) {
        logger.error("Error in REDIS EVIT operation for KEY: $p2", p0)
    }

    override fun handleCacheClearError(p0: java.lang.RuntimeException, p1: Cache) {
        logger.error("Error in CacheClear operation", p0)
    }
}