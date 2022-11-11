package com.gexingw.bloom.filter.controller;

import com.gexingw.common.util.R;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: GeXingW
 * @date: 2022/11/10
 * @time: 22:09
 */
@RestController
@RequestMapping("bloom-filter")
public class BloomFilterController {

    @Autowired
    RedissonClient redissonClient;

    /**
     * Guava的布隆过滤器
     *
     * @return
     */
    @GetMapping("guava")
    public R guava() {
        BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(), 100000, 0.02);

        for (int i = 0; i < 100000; i++) {
            bloomFilter.put(i);
        }

        int repeatCnt = 0;
        for (int i = 100000; i < 200000; i++) {
            if (bloomFilter.mightContain(i)) {
                repeatCnt++;
            }
        }

        System.out.println("总数：" + 100000 + "，重复：" + repeatCnt);
        return R.ok();
    }

    @GetMapping("redisson")
    public R redission() {
        RBloomFilter<Object> bloomFilter = redissonClient.getBloomFilter("product-ids");
        bloomFilter.tryInit(100000, 0.01);

//        for (int i = 0; i < 100000; i++) {
//            bloomFilter.add(i);
//        }

        int repeatCnt = 0;
        for (int i = 100000; i < 200000; i++) {
            if(bloomFilter.contains(i)){
                repeatCnt++;
            }
        }

        System.out.println("总数：" + 100000 + "，重复：" + repeatCnt);

        return R.ok();
    }


}
