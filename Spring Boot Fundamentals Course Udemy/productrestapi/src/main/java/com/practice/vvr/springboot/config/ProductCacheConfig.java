package com.practice.vvr.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hazelcast.config.Config;
import com.hazelcast.config.EvictionConfig;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.MaxSizePolicy;
@Configuration
public class ProductCacheConfig {
	
	@Bean
	public Config cacheConfig() {
		return new Config()
				.setInstanceName("hazel-instance")
				.addMapConfig( new MapConfig()
						.setName("product-cache")
						.setTimeToLiveSeconds(3000)
						.setEvictionConfig(new EvictionConfig()
								.setEvictionPolicy(EvictionPolicy.LRU)
								.setMaxSizePolicy(MaxSizePolicy.FREE_HEAP_SIZE)
								.setSize(200)
								)
						);
	}
	
}
