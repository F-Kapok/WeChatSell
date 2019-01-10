package com.fans.uitls;

import java.math.BigDecimal;

/**
 * @ClassName BigDecimalUtil
 * @Description:
 * @Author fan
 * @Date 2019-01-11 00:36
 * @Version 1.0
 **/
public class BigDecimalUtil {

    public static boolean equals(Double v1, Double v2) {
        BigDecimal o1 = new BigDecimal(v1.toString());
        BigDecimal o2 = new BigDecimal(v2.toString());
        return o1.compareTo(o2) == 0;
    }
}
