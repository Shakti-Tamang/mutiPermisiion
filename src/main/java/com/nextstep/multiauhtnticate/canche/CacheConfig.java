package com.nextstep.multiauhtnticate.canche;

import com.nextstep.multiauhtnticate.Model.AddBook;
import net.sf.ehcache.Cache;
import net.sf.ehcache.config.CacheConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public CacheManager cacheManager() {
        // Create the CacheManager with Ehcache 2.x configuration
        net.sf.ehcache.CacheManager cacheManager = net.sf.ehcache.CacheManager.create();

        // Create the cache configuration for the AddBook cache
        CacheConfiguration cacheConfig = new CacheConfiguration("addBookCache", 100)
                .memoryStoreEvictionPolicy("LRU")
                .eternal(false)
                .timeToLiveSeconds(600) // 10 minutes
                .timeToIdleSeconds(300); // 5 minutes

        // Add the cache configuration to the CacheManager
        cacheManager.addCache(new Cache(cacheConfig));

        // Return the CacheManager (Spring CacheManager will wrap this)
        return new org.springframework.cache.ehcache.EhCacheCacheManager(cacheManager);
    }
}
