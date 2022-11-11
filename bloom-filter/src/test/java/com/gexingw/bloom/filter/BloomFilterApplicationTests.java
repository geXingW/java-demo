package com.gexingw.bloom.filter;

import org.junit.jupiter.api.Test;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BloomFilterApplicationTests {

    @Autowired
    RedissonClient redissonClient;

    @Test
    void contextLoads() {
    }

    @Test
    void redissonBloomFilter(){
        RBloomFilter<Object> bloomFilter = redissonClient.getBloomFilter("product-ids");
//        bloomFilter.tryInit(100000, 0.01);

        int repeatCnt = 0;
        for (int i = 100000; i < 102000; i++) {
            if(bloomFilter.contains(i)){
                repeatCnt++;
            }
        }

        System.out.println("总数：" + 100000 + "，重复：" + repeatCnt);
    }
}
