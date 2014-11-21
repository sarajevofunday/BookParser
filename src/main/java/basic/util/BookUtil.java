package basic.util;

/**
 * @author aisajbegovic
 * @since 21.11.2014
 */
public class BookUtil {
    private BookUtil() {}

    public static String getBookNameOnly(String fullBookFileName) {
        return fullBookFileName.replaceFirst("[.][^.]+$", "");
    }
}
