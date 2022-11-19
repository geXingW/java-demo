package com.gexingw.mybatis.util;

import com.github.pagehelper.PageInfo;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: GeXingW
 * @date: 2022/11/19
 * @time: 19:37
 */
public class PageUtil {
    public static Map<String, Object> format(PageInfo<?> pageInfo){
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("records", pageInfo.getList());
        result.put("total", pageInfo.getTotal());
        result.put("size", pageInfo.getSize());
        result.put("page", pageInfo.getPageNum());

        return result;
    }
}
