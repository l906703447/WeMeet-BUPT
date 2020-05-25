package com.wemeet.wemeet.util;

/**
 * @author xieziwei99
 * 2020-05-13
 */
public class MathUtil {

    /**
     * 不精确，依据是 1 度大约是这么些千米
     *
     * @param meter 单位是米
     * @return 单位是度
     */
    public static double meterToLatitude(double meter, double curLat) {
        curLat = curLat * Math.PI / 180;
        double kilo = meter / 1000;
        return kilo / (111.320 * Math.cos(curLat));
    }

    /**
     * 不精确，依据是 1 度大约是这么些千米
     *
     * @param meter 单位是米
     * @return 单位是度
     */
    public static double meterToLongitude(double meter) {
        double kilo = meter / 1000;
        return kilo / 110.574;
    }
}
