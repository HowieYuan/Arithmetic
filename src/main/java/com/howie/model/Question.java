package com.howie.model;

import com.howie.util.Util;
import org.apache.commons.math3.fraction.Fraction;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 *
 * @Author yuanhaoyue swithaoy@gmail.com
 * @Description
 * @Date 2018-09-27
 * @Time 20:00
 */
public class Question {
    //按计算顺序记录每步的答案
    private List<Fraction> answerList;
    //按计算顺序记录每个操作符
    private List<String> operatorList;

    private List<String> questionList;

    private String answer;

    public Question() {
    }

    public Question(List<Fraction> answerList, List<String> operatorList,
                    List<String> questionList, String answer) {
        this.answerList = answerList;
        this.operatorList = operatorList;
        this.questionList = questionList;
        this.answer = answer;
    }

    public List<Fraction> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<Fraction> answerList) {
        this.answerList = answerList;
    }

    public List<String> getOperatorList() {
        return operatorList;
    }

    public void setOperatorList(List<String> operatorList) {
        this.operatorList = operatorList;
    }

    public List<String> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<String> questionList) {
        this.questionList = questionList;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Question)) {
            return false;
        }
        Question that = (Question) o;
        return (answerList != null ? answerList.equals(that.answerList) : that.answerList == null)
                && (operatorList != null ? operatorList.equals(that.operatorList) : that.operatorList == null)
                && questionList.size() == that.getQuestionList().size()
                && questionList.containsAll(that.getQuestionList());
    }

    @Override
    public int hashCode() {
        int result = answerList != null ? answerList.hashCode() : 0;
        result = 31 * result + (operatorList != null ? operatorList.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Object o : questionList) {
            if (o instanceof Fraction) {
                s.append(Util.getNum((Fraction) o)).append(" ");
            } else {
                s.append(o).append(" ");
            }
        }
        return s.toString();
    }
}
