package com.tak.gateway.api.common.utile;

import com.tak.gateway.api.common.BaseConstants;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.regex.Pattern;

public class StringUtil extends StringUtils {
    public static String requestReplace(String paramValue, String gubun) {

        String result = "";

        if (paramValue != null) {

            paramValue = paramValue.replaceAll("<", "&lt;").replaceAll(">", "&gt;");

            paramValue = paramValue.replaceAll("\\*", "");
            paramValue = paramValue.replaceAll("\\?", "");
            paramValue = paramValue.replaceAll("\\[", "");
            paramValue = paramValue.replaceAll("\\{", "");
            paramValue = paramValue.replaceAll("\\(", "");
            paramValue = paramValue.replaceAll("\\)", "");
            paramValue = paramValue.replaceAll("\\^", "");
            paramValue = paramValue.replaceAll("\\$", "");
            paramValue = paramValue.replaceAll("'", "");
            paramValue = paramValue.replaceAll("@", "");
            paramValue = paramValue.replaceAll("%", "");
            paramValue = paramValue.replaceAll(";", "");
            paramValue = paramValue.replaceAll(":", "");
            paramValue = paramValue.replaceAll("-", "");
            paramValue = paramValue.replaceAll("#", "");
            paramValue = paramValue.replaceAll("--", "");
            paramValue = paramValue.replaceAll("-", "");
            paramValue = paramValue.replaceAll(",", "");

            if (gubun != "encodeData") {
                paramValue = paramValue.replaceAll("\\+", "");
                paramValue = paramValue.replaceAll("/", "");
                paramValue = paramValue.replaceAll("=", "");
            }

            result = paramValue;

        }
        return result;
    }

    public static boolean isEmpty(Object str) {
        return !isNotEmpty(str);
    }

    public static boolean isNotEmpty(Object str) {
        if (str == null) {
            return false;
        } else if (str.getClass() == String.class && "".equals(((String) str).trim())) {
            return false;
        } else if ("".equals(str.toString().trim())) {
            return false;
        } else {
            return true;
        }
    }

    public static String nvl(String str, String defaultStr) {
        if (str == null)
            return defaultStr;
        else
            return str;
    }

    public static String nvl(String str) {
        return nvl(str, "");
    }

    public static String getNow(String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Calendar         c1  = Calendar.getInstance();
        return sdf.format(c1.getTime());
    }

    public static String getNow() {
        return getNow(BaseConstants.DATE_FORMAT);
    }

    public static String rpad(Object str, int length, String def) {
        String        r   = str == null ? "" : str.toString();
        int           len = r.length();
        StringBuilder sb  = new StringBuilder();
        for (int i = 0; i < length; i++) {
            if (i < len) {
                sb.append(r.charAt(i));
            } else {
                sb.append(def);
            }
        }
        return sb.toString();
    }

    public static String getObjectKey() {
        return getObjectKey2();
    }

    public static String getObjectKey2() {
        String mil = String.valueOf(System.currentTimeMillis());

        StringBuilder sb = new StringBuilder();
        sb.append(mil).append(RandomStringUtils.randomNumeric(20 - mil.length()));

        return sb.toString();
    }

    public static String maskString(String str) {
        if (StringUtil.isBlank(str)) {
            return str;
        }
        final int strLength = str.length();
        if (strLength < 2) {
            return maskString(str, '*', 1, strLength - 1);
        } else {
            return maskString(str, '*', strLength / 2, strLength - (strLength / 2));
        }

    }

    public static String maskString(final String str, final char mask, final int fromIdx, final int count) {
        if (StringUtil.isBlank(str)) {
            return str;
        }
        final int strLength = str.length();
        //if (Character.isWhitespace(mask) || (1 > count) || (0 > fromIdx) || (strLength <= fromIdx + count - 1)) {
        if (String.valueOf(mask).isEmpty() || (1 > count) || (0 > fromIdx) || (strLength <= fromIdx + count - 1)) {
            return str;
        }

        final char[] chars = new char[count];
        Arrays.fill(chars, mask);

        final String prefix  = str.substring(0, fromIdx);
        final String body    = String.valueOf(chars);
        final String postfix = str.substring(fromIdx + count);

        return prefix + body + postfix;
    }

    /**
     * String을 int형으로
     * @param value
     * @return
     */
    public static int parseInt(String value) {
        return parseInt(value, 0);
    }

    /**
     * Object를 int형으로
     * Object가 null이면 defaultValue return
     * @param value
     * @param defaultValue
     * @return
     */
    public static int parseInt(String value, int defaultValue) {
        String valueStr = defaultString(value, String.valueOf(defaultValue));
        return Integer.parseInt(valueStr);
    }

    /**
     * String을 long형으로
     * defaultValue는 0이다.
     * @param value
     * @return
     */
    public static long parseLong(String value) {
        return parseLong(value, 0);
    }

    /**
     * String을 long형으로
     * 잘못된 데이타 일시 return은 defaultValue
     * @param value
     * @return
     */
    public static long parseLong(String value, long defaultValue) {
        long returnValue = 0;
        if (isEmpty(value)) {
            returnValue = defaultValue;
        } else if (!isNumeric(value)) {
            returnValue = defaultValue;
        } else {
            returnValue = Long.parseLong(value);
        }
        return returnValue;
    }

    /**
     * String을 long형으로
     * defaultValue는 0이다.
     * @param value
     * @return
     */
    public static double parseDouble(String value) {
        return parseDouble(value, 0);
    }

    /**
     * String을 long형으로
     * 잘못된 데이타 일시 return은 defaultValue
     * @param value
     * @return
     */
    public static double parseDouble(String value, double defaultValue) {
        double returnValue = 0;
        if (isEmpty(value)) {
            returnValue = defaultValue;
        } else if (!isNumeric(value)) {
            returnValue = defaultValue;
        } else {
            returnValue = Double.parseDouble(value);
        }
        return returnValue;
    }

    /**
     * It converts integer type to String ( 27 -> '27')
     *
     * <pre>
     * StringUtil.integer2string(14) 	= '14'
     * </pre>
     *
     * @param integer
     *            integer type
     * @return String string representation of a number
     * @deprecated Use @link {@link NumberUtil#intToString(int)}
     */
    @Deprecated
    public static String integer2string(int integer) {
        return ("" + integer);
    }

    /**
     * It converts the string representation of a number to integer type (eg.
     * '27' -> 27)
     *
     * <pre>
     * StringUtil.string2integer('14') 	= 14
     * </pre>
     *
     * @param str
     *            string representation of a number
     * @return integer integer type of string
     * @deprecated Use @link {@link NumberUtil#stringToInt(String)}
     */
    @Deprecated
    public static int string2integer(String str) {
        int ret = Integer.parseInt(str.trim());

        return ret;
    }

    public static long hexToNumeric(String hexString) {
        long returnValue = 0;
        if (StringUtils.isNoneBlank(hexString)) {
            try {
                returnValue = Long.parseLong(hexString, 16);
            } catch (Exception ex) {

            }
        }
        return returnValue;
    }

    public static String numericToHex(long numeric) {
        String returnValue = "";
        if (numeric > 0) {
            returnValue = Long.toHexString(numeric);
        }
        return returnValue.toUpperCase();
    }

    public static String phoneFormat(String src) {
        String mobTelNo = src;

        if (mobTelNo != null) {
            // 일단 기존 - 전부 제거
            mobTelNo = mobTelNo.replaceAll(Pattern.quote("-"), "");

            if (mobTelNo.length() == 11) {
                // 010-1234-1234
                mobTelNo = mobTelNo.substring(0, 3) + "-" + mobTelNo.substring(3, 7) + "-" + mobTelNo.substring(7);

            } else if (mobTelNo.length() == 8) {
                // 1588-1234
                mobTelNo = mobTelNo.substring(0, 4) + "-" + mobTelNo.substring(4);
            } else if (mobTelNo.length() > 5) {
                if (mobTelNo.startsWith("02")) { // 서울은 02-123-1234
                    mobTelNo = mobTelNo.substring(0, 2) + "-" + mobTelNo.substring(2, 5) + "-" + mobTelNo.substring(5);
                } else { // 그외는 012-123-1345
                    mobTelNo = mobTelNo.substring(0, 3) + "-" + mobTelNo.substring(3, 6) + "-" + mobTelNo.substring(6);
                }
            }

        }

        return mobTelNo;
    }
}
