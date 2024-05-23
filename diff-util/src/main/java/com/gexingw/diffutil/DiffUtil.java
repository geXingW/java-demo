package com.gexingw.diffutil;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author GeXingW
 */
public class DiffUtil {

    public static final String ADD = "ADD";

    public static final String UPDATE = "UPDATE";

    public static final String DELETE = "DELETE";

    @Data
    @Accessors(chain = true)
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DiffItem {

        private String type;

//        private

        private Object oldValue;

        private Object newValue;

    }

//    public static List<DiffItem> diff(Object origin, Object target) {
//
//    }

}
