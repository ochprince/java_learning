package com.ochprince.java_learning.utils;

import java.util.function.Consumer;

/**
 * File Util
 *
 * <p>Copyright OchPrince 2019 Co.,Ltd.</p>
 *
 * @author shilong.jiang
 * @version 1.0.0.RELEASE
 * @since CreatedAt 2019-07-04 14:55:27
 */
public class FileUtil {

    /**
     * Split and deal with long content string.
     *
     * @param longContent string
     * @param delimiter delimiter
     * @param dealFn function
     */
    public static void bigSplit(String longContent, String delimiter, Consumer<String> dealFn) {
        int index = 0;
        while (true) {
            int i = longContent.indexOf(delimiter, index + delimiter.length());
            if (i > 0) {
                dealFn.accept(longContent.substring(index, i));
                index = i;
            } else {
                dealFn.accept(longContent.substring(index));
                break;
            }
        }
    }
}
