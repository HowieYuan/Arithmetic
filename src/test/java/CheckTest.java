import org.junit.Test;

import java.io.File;

/**
 * Created with IntelliJ IDEA
 *
 * @Author yuanhaoyue swithaoy@gmail.com
 * @Description
 * @Date 2018-09-24
 * @Time 22:16
 */
public class CheckTest {
    private final static String PROJECT_PATH = new File("").getAbsolutePath();
    private final static String EXERCISE_PATH = PROJECT_PATH + "\\src\\main\\resources\\exercise.txt";
    private final static String ANSWER_PATH = PROJECT_PATH + "\\src\\main\\resources\\answers.txt";

    @Test
    public void main() throws Exception {
        CheckSevice.checkExercise(EXERCISE_PATH, ANSWER_PATH);
    }
}
