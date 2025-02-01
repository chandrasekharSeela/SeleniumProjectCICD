package Util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ReturnDuplicates {

    public static <T> Set<T> returnDuplicates(T[] array) {
        Set<T> valley = new HashSet<>();
        return Arrays.stream(array).filter(e -> !valley.add(e)).collect(Collectors.toSet());
    }
}
