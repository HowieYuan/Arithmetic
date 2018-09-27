import java.io.*;
import java.util.*;

/**
 * Created with IntelliJ IDEA
 *
 * @Author yuanhaoyue swithaoy@gmail.com
 * @Description 启动类
 * @Date 2018-09-22
 * @Time 21:03
 */
public class Main {
    private final static String PROJECT_PATH = new File("").getAbsolutePath();
    //各个文件的路径
    final static String EXERCISE_PATH = PROJECT_PATH + "\\src\\main\\resources\\exercise.txt";
    final static String ANSWER_PATH = PROJECT_PATH + "\\src\\main\\resources\\answers.txt";
    final static String GRADE_PATH = PROJECT_PATH + "\\src\\main\\resources\\grade.txt";

    /*
    -e C:\Users\Administrator\Desktop\Arithmetic\src\main\resources\exercise.txt -a C:\Users\Administrator\Desktop\Arithmetic\src\main\resources\answers.txt
     */

    public static void main(String[] args) throws IOException {
        int questions, range;
        //匹配命令 -n -r
        if (Arrays.stream(args).anyMatch("-n"::equals) && Arrays.stream(args).anyMatch("-r"::equals)) {
            questions = Integer.valueOf(args[1]);
            range = Integer.valueOf(args[3]) - 1;
            //生成题目
            ExerciseSevice.generateExercise(range, questions);
            System.out.println("生成题目完毕！");
            //匹配命令 -e -a
        } else if (Arrays.stream(args).anyMatch("-e"::equals) && Arrays.stream(args).anyMatch("-a"::equals)) {
            //核对答案
            CheckSevice.checkExercise(args[1], args[3]);
            System.out.println("结果生成成功！");
        } else {
            System.out.println("错误命令！");
        }
    }
}
