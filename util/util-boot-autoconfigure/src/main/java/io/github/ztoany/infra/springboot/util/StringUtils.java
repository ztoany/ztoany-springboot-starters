package io.github.ztoany.infra.springboot.util;

public class StringUtils {
    public static String ifNullOrBlankToEmptyAndTrim(String str) {
        var ret = org.springframework.util.StringUtils.hasText(str) ? str : "";
        return ret.trim();
    }
}
