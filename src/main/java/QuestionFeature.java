import org.apache.commons.math3.fraction.Fraction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 *
 * @Author yuanhaoyue swithaoy@gmail.com
 * @Description
 * @Date 2018-09-27
 * @Time 20:00
 */
public class QuestionFeature {
    //按计算顺序记录每步的答案
    private List<Fraction> answerList = new ArrayList<>();
    //按计算顺序记录每个操作符
    private List<String> operatorList = new ArrayList<>();

    public QuestionFeature() {
    }

    public QuestionFeature(List<Fraction> answerList, List<String> operatorList) {
        this.answerList = answerList;
        this.operatorList = operatorList;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof QuestionFeature)) {
            return false;
        }
        QuestionFeature that = (QuestionFeature) o;
        return (answerList != null ? answerList.equals(that.answerList) : that.answerList == null) && (operatorList != null ? operatorList.equals(that.operatorList) : that.operatorList == null);
    }

    @Override
    public int hashCode() {
        int result = answerList != null ? answerList.hashCode() : 0;
        result = 31 * result + (operatorList != null ? operatorList.hashCode() : 0);
        return result;
    }
}
