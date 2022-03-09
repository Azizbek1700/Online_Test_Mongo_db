package uz.jl;

import uz.jl.configs.ApplicationContextHolder;
import uz.jl.ui.auth.AuthUserUI;
import uz.jl.ui.menuUI.Menu;
import uz.jl.ui.question.QuestionUI;
import uz.jl.ui.quiz.QuizUI;
import uz.jl.utils.Color;
import uz.jl.utils.Input;
import uz.jl.utils.Print;

import java.util.Locale;

public class Application {
    public static void main(String[] args) {
        run();
    }

    private static void run() {
        AuthUserUI authUserUI = ApplicationContextHolder.getBean(AuthUserUI.class);
        QuestionUI questionUI = ApplicationContextHolder.getBean(QuestionUI.class);
        QuizUI quizUI = ApplicationContextHolder.getBean(QuizUI.class);
        Menu.getMainMenu();
        String choice = Input.getStr("?: ");
        switch (choice.toUpperCase(Locale.ROOT)) {
            case "REGISTER" -> authUserUI.create();
            case "LOGIN" -> authUserUI.login();
            case "1" -> authUserUI.get();
            case "2" -> authUserUI.getAll();
            case "3" -> authUserUI.update();
            case "4" -> authUserUI.delete();

            case "5" -> questionUI.create();
            case "6" -> questionUI.get();
            case "7" -> questionUI.getAll();

            case "8" -> quizUI.create();
            default -> Print.println(Color.RED, " Wrong choice!");
        }
        run();
    }

}
