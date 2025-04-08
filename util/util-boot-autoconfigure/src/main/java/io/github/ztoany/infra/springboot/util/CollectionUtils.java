package io.github.ztoany.infra.springboot.util;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CollectionUtils {
    public static List<String> copyListString(List<String> src) {
        if(src == null) return new ArrayList<>();
        return new ArrayList<>(src);
    }

    public static <S, D> List<D> mapList(List<S> src, Function<? super S, ? extends D> mapper) {
        if(src == null) return new ArrayList<>();
        return src.stream().map(mapper).collect(Collectors.toList());
    }

    public static List<String> ignoreListUselessString(List<String> src) {
        var ret = CollectionUtils.copyListString(src);
        ret.removeIf(String::isBlank);
        return ret.stream().map(String::trim).collect(Collectors.toList());
    }
}
