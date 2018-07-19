package com.hm.iou.tools;

import java.math.BigDecimal;

public class MoneyFormatUtil {

    private static final char[] UNIT = {'亿', '拾', '佰', '仟', '万', '拾', '佰', '仟'};
    private static final char[] CHAINIESFIGURE2 = {'零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖'};

    //整数部分的转换
    public static String toChineseCharI(String intString) throws NumberFormatException {

        //用来存放转换后的大写数字,因为是StringBuffer类型,所以顺便把没有转换
        //的数字倒序排列一下,省一个变量.
        StringBuffer ChineseCharI = new StringBuffer(intString);
        //倒序的数字,便于同单位合并
        String rintString = ChineseCharI.reverse().toString();
        //清空一下
        ChineseCharI.delete(0, ChineseCharI.length());
        //单位索引
        int unitIndex = 0;
        //数字长度
        int intStringLen = intString.length();
        //一位由字符转换的数字
        int intStringnumber = 0;
        //补0
        boolean addZero = false;
        boolean flagZero = false;
        for (int i = 0; i < intStringLen; i++) {
            //按单位长度循环索引
            unitIndex = i % UNIT.length;

            //异常检查
            if (!Character.isDigit(rintString.charAt(i))) {
                throw new NumberFormatException("数字中含有非法字符");
            }
            intStringnumber = Character.digit(rintString.charAt(i), 10);

            //如果当前位是0,开启补0继续循环直到不是0的位
            if (intStringnumber == 0) {
                addZero = true;
                if (i != 0 && i % 4 == 0) {
                    if (addZero && ChineseCharI.length() != 0) {
                        ChineseCharI.append(CHAINIESFIGURE2[0]);
                        addZero = false;
                    }
                    flagZero = true;
                    ChineseCharI.append(UNIT[unitIndex]);
                }
            } else {
                //若当前位不是第一位且补0开启
                if (addZero && ChineseCharI.length() != 0 && !flagZero) {
                    ChineseCharI.append(CHAINIESFIGURE2[0]);
                }
                flagZero = false;
                //插入单位
                //个位数后面不需 要单位
                if (i > 0) {
                    System.out.println(i);
                    ChineseCharI.append(UNIT[unitIndex]);
                }

                //插入大写数字
                ChineseCharI.append(CHAINIESFIGURE2[intStringnumber]);
                //补0关闭           
                addZero = false;
            }
        }

        //异常处理
        if (ChineseCharI.length() == 0) {
            return "零";
        }
        return ChineseCharI.reverse().toString();

    }


    /**
     * 金额为分的格式
     */
    public static final String CURRENCY_FEN_REGEX = "\\-?[0-9]+";

    /**
     * 将分为单位的转换为元并返回金额格式的字符串 （除100）
     *
     * @param amount
     * @return
     * @throws Exception
     */
    public static String changeF2Y(long amount) throws Exception {
        Long amountLong = new Long(amount);
        if (!amountLong.toString().matches(CURRENCY_FEN_REGEX)) {
            throw new Exception("金额格式有误");
        }

        int flag = 0;
        String amString = amountLong.toString();
        if (amString.charAt(0) == '-') {
            flag = 1;
            amString = amString.substring(1);
        }
        StringBuffer result = new StringBuffer();
        if (amString.length() == 1) {
            result.append("0.0").append(amString);
        } else if (amString.length() == 2) {
            result.append("0.").append(amString);
        } else {
            String intString = amString.substring(0, amString.length() - 2);
            for (int i = 1; i <= intString.length(); i++) {
                if ((i - 1) % 3 == 0 && i != 1) {
                    result.append(",");
                }
                result.append(intString.substring(intString.length() - i, intString.length() - i + 1));
            }
            result.reverse().append(".").append(amString.substring(amString.length() - 2));
        }
        if (flag == 1) {
            return "-" + result.toString();
        } else {
            return result.toString();
        }
    }

    /**
     * 将分为单位的转换为元 （除100）
     *
     * @param amount
     * @return
     * @throws Exception
     */
    public static String changeF2Y(String amount) throws Exception {
        if (!amount.matches(CURRENCY_FEN_REGEX)) {
            throw new Exception("金额格式有误");
        }
        return BigDecimal.valueOf(Long.valueOf(amount)).divide(new BigDecimal(100)).toString();
    }

    /**
     * 将元为单位的转换为分 （乘100）
     *
     * @param amount
     * @return
     */
    public static String changeY2F(long amount) {
        return BigDecimal.valueOf(amount).multiply(new BigDecimal(100)).toString();
    }

    /**
     * 将元为单位的转换为分 替换小数点，支持以逗号区分的金额
     *
     * @param amount
     * @return
     */
    public static String changeY2F(String amount) {
        String currency = amount.replaceAll("\\$|\\￥|\\,", "");  //处理包含, ￥ 或者$的金额
        int index = currency.indexOf(".");
        int length = currency.length();
        Long amLong = 0l;
        if (index == -1) {
            amLong = Long.valueOf(currency + "00");
        } else if (length - index >= 3) {
            amLong = Long.valueOf((currency.substring(0, index + 3)).replace(".", ""));
        } else if (length - index == 2) {
            amLong = Long.valueOf((currency.substring(0, index + 2)).replace(".", "") + 0);
        } else {
            amLong = Long.valueOf((currency.substring(0, index + 1)).replace(".", "") + "00");
        }
        return amLong.toString();
    }


}
