package com.howie.sevice;

import com.howie.Main;
import com.howie.util.Util;
import org.apache.commons.math3.fraction.Fraction;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created with IntelliJ IDEA
 *
 * @Author yuanhaoyue swithaoy@gmail.com
 * @Description 检查题目与答案正误逻辑业务类
 * @Date 2018-09-24
 * @Time 17:07
 */
@SuppressWarnings("unchecked")
public class CheckSevice {
    //正确题目个数
    private static int correct = 0;
    //错误题目个数
    private static int wrong = 0;
    //正确题目列表
    private static List<Integer> correctList = new ArrayList<>();
    //错误题目列表
    private static List<Integer> wrongList = new ArrayList<>();

    /**
     * 比对题目与答案的正确与否
     *
     * @param exercisePath 题目文件路径
     * @param answerPath   答案文件路径
     */
    public static void checkExercise(String exercisePath, String answerPath) throws IOException {
        BufferedReader exerciseReader = new BufferedReader(new FileReader(exercisePath));
        List<String> operatorList = new ArrayList<>(Arrays.asList("+", "-", "×", "÷", "(", ")"));
        //读取题目文件，并生成题目的答案
        List<Fraction> realAnswerList = exerciseReader.lines()
                .map(s -> s.substring(s.indexOf("：") + 1, s.indexOf("=")).trim())
                .map(s -> s.split("\\s+"))
                .map(array -> {
                    List questionList = new ArrayList();
                    for (String s : array) {
                        //转换为分数
                        if (!operatorList.contains(s)) {
                            questionList.add(Util.getFraction(s));
                        } else {
                            questionList.add(s);
                        }
                    }
                    return questionList;
                })
                .map(questionList -> ExerciseSevice.getAnswer(questionList))
                .collect(toList());
        //读取答案文件
        BufferedReader answerReader = new BufferedReader(new FileReader(answerPath));
        List<Fraction> answerList = answerReader.lines()
                .map(s -> s.substring(s.indexOf(".") + 1).trim())
                .map(Util::getFraction)
                .collect(toList());
        //比对答案与正确答案，生成结果
        getCheckResult(realAnswerList, answerList);
        //将结果写入文件中
        BufferedWriter gradeWriter = new BufferedWriter(new FileWriter(Main.GRADE_PATH));
        gradeWriter.flush();
        gradeWriter.write("Correct: " + correct + " " + correctList);
        gradeWriter.newLine();
        gradeWriter.write("Wrong: " + wrong + " " + wrongList);
        //结束，关闭流
        gradeWriter.close();
    }

    /**
     * 比对答案
     *
     * @param realAnswerList 正确答案
     * @param answerList     输入的答案
     */
    private static void getCheckResult(List<Fraction> realAnswerList, List<Fraction> answerList) {
        for (int i = 0; i < realAnswerList.size(); i++) {
            //题目正确
            if (realAnswerList.get(i).equals(answerList.get(i))) {
                correct++;
                correctList.add(i + 1);
            } else {
                //题目错误
                wrong++;
                wrongList.add(i + 1);
            }
        }
    }
}
