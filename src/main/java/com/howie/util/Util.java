package com.howie.util;

import org.apache.commons.math3.fraction.Fraction;

import java.util.Random;

/**
 * Created with IntelliJ IDEA
 *
 * @Author yuanhaoyue swithaoy@gmail.com
 * @Description 处理符号处理工具类
 * @Date 2018-09-24
 * @Time 17:10
 */
public class Util {
    private final static String[] OPERATOR = {"+", "×", "÷", "-"};

    /**
     * 将数值转换为分数
     */
    public static Fraction getFraction(String num) {
        //带分数
        if (num.contains("'")) {
            int index1 = num.indexOf("'");
            int index2 = num.indexOf("/");
            return new Fraction(Integer.valueOf(num.substring(0, index1)) *
                    Integer.valueOf(num.substring(index2 + 1, num.length())) +
                    Integer.valueOf(num.substring(index1 + 1, index2)),
                    Integer.valueOf(num.substring(index2 + 1, num.length())));
            //分数
        } else if (num.contains("/")) {
            int index = num.indexOf("/");
            return new Fraction(Integer.valueOf(num.substring(0, index)), Integer.valueOf(num.substring(index + 1)));
            //整数
        } else {
            return new Fraction(Integer.valueOf(num));
        }
    }

    /**
     * 生成真分数/带分数
     *
     * @param range 取值范围
     */
    public static Fraction getFraction(int range) {
        Random random = new Random();
        //分母
        int denominator = random.nextInt(9) + 2;
        //分子
        int numerator = random.nextInt(denominator - 1) + 1;
        int commonFactor = 1;
        //约分
        for (int i = 1; i <= numerator; i++) {
            if (denominator % i == 0 && numerator % i == 0) {
                commonFactor = i;
            }
        }
        denominator = denominator / commonFactor;
        numerator = numerator / commonFactor;
        //带分数与真分数
        if (random.nextBoolean()) {
            int integer = random.nextInt(range) + 1;
            return new Fraction(integer * denominator + numerator, denominator);
        } else {
            return new Fraction(numerator, denominator);
        }
    }

    /**
     * 生产四则运算符
     */
    public static String getOperator() {
        return OPERATOR[(int) (Math.random() * 4)];
    }

    /**
     * 将 Fraction 类型格式化
     */
    public static Object getNum(Fraction fraction) {
        int numerator = fraction.getNumerator();
        int denominator = fraction.getDenominator();
        int integer;
        if (denominator == 1) {
            return numerator;
        } else if (numerator < denominator) {
            return numerator + "/" + denominator;
        } else {
            integer = numerator / denominator;
            numerator = numerator - integer * denominator;
            return integer + "'" + numerator + "/" + denominator;
        }
    }
}
