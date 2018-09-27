import org.apache.commons.math3.fraction.Fraction;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 *
 * @Author yuanhaoyue swithaoy@gmail.com
 * @Description 题目实体类
 * @Date 2018-09-22
 * @Time 21:09
 */
public class Question {
    private List<String> list;

    public Question(List<String> list) {
        this.list = list;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Object o : list) {
            if (o instanceof Fraction) {
                s.append(Util.getNum((Fraction) o)).append(" ");
            } else {
                s.append(o).append(" ");
            }
        }
        return s.toString();
    }

}
