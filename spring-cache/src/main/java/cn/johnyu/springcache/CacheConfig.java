package cn.johnyu.springcache;

import net.sf.ehcache.CacheManager;
//import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableCaching //使用缓存服务，对@Cacheable进行处理
public class CacheConfig {
//    @Bean
//    public CacheManager cacheManager(){
//        return new ConcurrentMapCacheManager();
//    }


    @Bean
    public EhCacheCacheManager cacheCacheManager(CacheManager cm){
        return new EhCacheCacheManager(cm);
    }
    @Bean
    public EhCacheManagerFactoryBean ehcache(){
        EhCacheManagerFactoryBean factoryBean=new EhCacheManagerFactoryBean();
        factoryBean.setConfigLocation(new ClassPathResource("ehcache.xml"));
        return factoryBean;
    }


}
