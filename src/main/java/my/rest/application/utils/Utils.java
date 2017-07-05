package my.rest.application.utils;

/**
 * @author abogaichuk
 */
public class Utils {
    public static boolean isNullOrEmpty(String s) {
        return s == null || s.isEmpty();
    }

    public static boolean isNotNullAndNotEmpty(String s) {
        return !isNullOrEmpty(s);
    }
}
