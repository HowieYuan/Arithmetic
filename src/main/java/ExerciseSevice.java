import org.apache.commons.math3.fraction.Fraction;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Created with IntelliJ IDEA
 *
 * @Author yuanhaoyue swithaoy@gmail.com
 * @Description 生成题目与答案逻辑业务类
 * @Date 2018-09-24
 * @Time 17:12
 */
@SuppressWarnings("unchecked")
class ExerciseSevice {
    private static boolean isNegative = false;

    /**
     * 生成题目与答案，并写入文件
     *
     * @param range     取值范围
     * @param questions 题目总数
     */
    static void generateExercise(int range, int questions) throws IOException {
        //生成题目
        ExerciseSevice.getQuestion(questions, range);
        BufferedWriter exerciseWriter = new BufferedWriter(new FileWriter(Main.EXERCISE_PATH));
        BufferedWriter answerWriter = new BufferedWriter(new FileWriter(Main.ANSWER_PATH));
        //清空文件
        exerciseWriter.flush();
        answerWriter.flush();
        //分别写入题目与答案于两个文件中
        int currentQuestion = 1;
        for (String question : Main.questionsMap.keySet()) {
            String answer = Main.questionsMap.get(question);
            exerciseWriter.write("第" + currentQuestion + "题：" + question + " =");
            exerciseWriter.newLine();
            answerWriter.write(currentQuestion + ". " + answer);
            answerWriter.newLine();
            currentQuestion++;
        }
        //结束，关闭流
        exerciseWriter.close();
        answerWriter.close();
    }

    /**
     * 随机生成题目与对应的答案, 存入 questionsMap
     */
    public static void getQuestion(int questionsSum, int range) {
        //循环随机生成题目
        while (Main.questionsMap.size() < questionsSum) {
            List questionList = new ArrayList();
            //该表达式是否包含括号
            boolean havebrackets = false;
            Random random = new Random();
            //随机生成该题目的运算符个数
            int operatorSum = random.nextInt(3) + 1;
            int currentOperatorNum = 0;
            while (currentOperatorNum < operatorSum) {
                List list = new ArrayList();
                //生成运算符
                String operator = Util.getOperator();
                //决定是否为分数
                if (random.nextBoolean()) {
                    //生成分数
                    Fraction fraction = Util.getFraction(range);
                    list.add(fraction);
                    if (havebrackets) {
                        list.add(")");
                    }
                    list.add(operator);
                } else {
                    //生成整数
                    int num = random.nextInt(range) + 1;
                    list.add(new Fraction(num, 1));
                    if (havebrackets) {
                        list.add(")");
                    }
                    list.add(operator);
                }
                currentOperatorNum++;
                //处理括号
                if (havebrackets) {
                    havebrackets = false;
                    questionList.addAll(list);
                    continue;
                }
                //如果是乘除法，则不考虑括号
                if ("×".equals(operator) || "÷".equals(operator) || operatorSum == 1) {
                    questionList.addAll(list);
                    continue;
                }
                //如果是加减法，决定是否存在括号
                if (random.nextBoolean()) {
                    list.add(0, "(");
                    havebrackets = true;
                }
                questionList.addAll(list);
            }
            if (random.nextBoolean()) {
                Fraction fraction = Util.getFraction(range);
                questionList.add(fraction);
            } else {
                int num = random.nextInt(range) + 1;
                questionList.add(new Fraction(num, 1));
            }
            if (havebrackets) {
                questionList.add(")");
            }
            //生成题目答案
            Fraction answer = getAnswer(new ArrayList(questionList));
            if (isNegative) {
                isNegative = false;
                continue;
            }
            //将题目与对应的答案存入 questionsMap
            Main.questionsMap.put(new Question(questionList).toString(), Util.getNum(answer).toString());
        }
    }

    /**
     * 生成题目答案
     *
     * @param questionList 题目
     * @return 若遇到负数的答案，则反转式子
     */
    static Fraction getAnswer(List questionList) {
        //优先处理括号里的表达式
        while (questionList.contains("(")) {
            int h1 = questionList.indexOf("(");
            int h2 = questionList.indexOf(")");
            //获得括号内的表达式
            List havebracketsList = questionList.subList(h1 + 1, h2);
            //安置计算出来的答案
            questionList.add(h2 + 1, getAnswer(havebracketsList));
            //移除原表达式，以进行下一步计算
            for (int i = 0; i < 5; i++) {
                questionList.remove(h1);
            }
        }
        //先计算乘除法
        while ((questionList.contains("×") || questionList.contains("÷")) && !isNegative) {
            int multiply = questionList.indexOf("×");
            int divide = questionList.indexOf("÷");
            if (multiply < divide && multiply != -1 || divide == -1) {
                //计算乘法
                questionList = calculate(questionList, multiply, Fraction::multiply);
            } else {
                //计算除法
                questionList = calculate(questionList, divide, Fraction::divide);
            }
        }
        //后计算加减法
        while ((questionList.contains("+") || questionList.contains("-")) && !isNegative) {
            int add = questionList.indexOf("+");
            int subtract = questionList.indexOf("-");
            if (add < subtract && add != -1 || subtract == -1) {
                //计算加法
                questionList = calculate(questionList, add, Fraction::add);
            } else {
                //计算减法
                questionList = calculate(questionList, subtract, Fraction::subtract);
            }
        }
        //返回答案
        return (Fraction) questionList.get(0);
    }

    /**
     * 计算题目其中一个表达式
     *
     * @param list               题目中的数字运算符序列
     * @param index              表达式中运算符所在位置
     * @param fractionArithmetic 四则运算计算函数接口
     */
    private static List calculate(List list, int index, FractionArithmetic fractionArithmetic) {
        //计算数值1
        Fraction fraction1 = (Fraction) list.get(index - 1);
        //计算数值2
        Fraction fraction2 = (Fraction) list.get(index + 1);
        //计算结果
        Fraction fraction = fractionArithmetic.arithmetic(fraction1, fraction2);
        //如果计算的结果是负数
        if (fraction.getNumerator() <= 0) {
            isNegative = true;
        }
        if (list.size() == 3) {
            return new ArrayList(Collections.singletonList(fraction));
        } else {
            list.remove(index);
            list.remove(index);
            list.remove(index - 1);
            list.add(index - 1, fraction);
            return list;
        }
    }
}