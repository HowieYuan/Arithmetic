package com.howie.sevice;

import org.apache.commons.math3.fraction.Fraction;

/**
 * Created with IntelliJ IDEA
 *
 * @Author yuanhaoyue swithaoy@gmail.com
 * @Description 四则运算函数接口
 * @Date 2018-09-17
 * @Time 23:48
 */
@FunctionalInterface
public interface FractionArithmetic {
    Fraction arithmetic(Fraction fraction1, Fraction fraction2);
}
