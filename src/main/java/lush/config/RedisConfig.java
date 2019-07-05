package lush.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Redis 설정파일.
 *
 */
@Configuration
@EnableCaching
@EnableRedisRepositories
public class RedisConfig {
	// Redis Host
	@Value("${spring.redis.host}")
	private String redisHost;
	
	// Redis Port
	@Value("${spring.redis.port}")
	private int redisPort;
	
	/**
	 * Lettuce Client 사용
	 * @return
	 */
	@Bean
	public RedisConnectionFactory redisConnectionFactory() {
		// Redis Server 정보 셋팅
		return new LettuceConnectionFactory(redisHost, redisPort);
	}
	
	/**
	 * Redis Template 설정
	 * @return
	 */
	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
		
		// 내부 또는 외부의 Redis 와 연결
		redisTemplate.setConnectionFactory(redisConnectionFactory());
		
		// 문자를 깨지지 않고 받기 위한 직렬화 작업
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		
		return redisTemplate;
	}
}
