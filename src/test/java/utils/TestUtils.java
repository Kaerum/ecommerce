package utils;

import java.util.List;

public class TestUtils {
    public static boolean listEquals(List<?> list1, List<?> list2) {
        return list1.size() == list2.size() && list1.containsAll(list2);
    }
}
