package com.anishan.dome.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        //大多数情况，都是选用<String, Object>
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);

        // 使用JSON的序列化对象，对数据key和value进行序列化转换
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
        //ObjectMapper是Jackson的一个工作类，顾名思义他的作用是将JSON映射到Java对象即反序列化，或将Java对象映射到JSON即序列化
        ObjectMapper mapper = new ObjectMapper();
        // 设置序列化时的可见性，第一个参数是选择序列化哪些属性，比如时序列化setter?还是filed?h第二个参数是选择哪些修饰符权限的属性来序列化，比如private或者public，这里的any是指对所有权限修饰的属性都可见(可序列化)
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        // 设置出现故障即错误的类型，第一个是指验证程序，此时的参数为无需验证，其他参数可以查看源码了解(作者还在啃源码中)，第二是指该类不能为final修饰，否则将会报错
        mapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL);

        jackson2JsonRedisSerializer.setObjectMapper(mapper);
        // 设置RedisTemplate模板的序列化方式为jacksonSeial
        template.setDefaultSerializer(jackson2JsonRedisSerializer);
        return template;
    }


}
