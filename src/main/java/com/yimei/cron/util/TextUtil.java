package com.yimei.cron.util;

import java.util.regex.Pattern;

/**
 * Created by xiangyang on 16/7/28.
 */
public class TextUtil {

    public  static boolean isValidMobileNum(String securephone) {
        return (null != securephone) && Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$").matcher(securephone).matches();
    }
}
