import org.junit.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA
 *
 * @Author yuanhaoyue swithaoy@gmail.com
 * @Description
 * @Date 2018-09-24
 * @Time 21:55
 */
public class ExerciseTest {
    static Map<Integer, Integer> map = new HashMap<>();
    static {
        map.put(10000, 10);
        map.put(1000, 5);
        map.put(10, 5);
        map.put(20, 10);
        map.put(30, 20);
        map.put(50, 10);
        map.put(100, 10);
        map.put(200, 10);
        map.put(300, 3);
        map.put(120, 5);
    }

    @Test
    public void main() throws Exception {
        for (int questionSum : map.keySet()) {
            //生成10000条题目，范围为10
            ExerciseSevice.getQuestion(questionSum, map.get(questionSum));
            int currentQuestion = 1;
            for (Question question : ExerciseSevice.questionsList) {
                String answer = question.getAnswer();
                System.out.println("第" + currentQuestion + "题：" + question.toString() + " = " + answer);
                currentQuestion++;
            }
        }

    }
}
