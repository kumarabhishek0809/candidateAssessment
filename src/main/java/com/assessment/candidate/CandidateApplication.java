package com.assessment.candidate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import static java.util.Arrays.asList;

@SpringBootApplication
@EnableCaching
@EnableScheduling
public class CandidateApplication {

    public static final String CANDIDATE_CACHE = "CANDIDATE_CACHE";

    public static void main(String[] args) {
        SpringApplication.run(CandidateApplication.class, args);
    }

    @Bean
    public CacheManager cacheManager() {
        ConcurrentMapCache candidateCache = new ConcurrentMapCache(CANDIDATE_CACHE);
        SimpleCacheManager manager = new SimpleCacheManager();
        manager.setCaches(asList(candidateCache));
        return manager;
    }

    @CacheEvict(allEntries = true, value = {CANDIDATE_CACHE})
    @Scheduled(fixedDelay = 2 * 60 * 1000, initialDelay = 500)
    public void candidateCacheEvict() {
        System.out.println("Flush Cache");
    }

}
