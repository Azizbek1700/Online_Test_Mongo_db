package uz.jl.ui.quiz;

import org.bson.types.ObjectId;
import uz.jl.dto.quiz.QuizCreateDto;
import uz.jl.dto.quiz.QuizDto;
import uz.jl.dto.quiz.QuizUpdateDto;
import uz.jl.entity.question.Question;
import uz.jl.entity.question.QuestionAnswer;
import uz.jl.enums.Category;
import uz.jl.enums.Language;
import uz.jl.enums.Level;
import uz.jl.exception.ApiRuntimeException;
import uz.jl.response.Data;
import uz.jl.response.ResponseEntity;
import uz.jl.service.quiz.QuizService;
import uz.jl.ui.AbstractUI;
import uz.jl.ui.GenericCrudUI;
import uz.jl.utils.Color;
import uz.jl.utils.Input;
import uz.jl.utils.Print;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static uz.jl.enums.Category.getC;
import static uz.jl.enums.Language.getLan;
import static uz.jl.enums.Level.getLevels;

/**
 * @author Botirov Najmiddin, Mon 09:59. 31/01/2022
 */
public class QuizUI extends AbstractUI<QuizService> implements GenericCrudUI<QuizDto, QuizCreateDto, QuizUpdateDto, ObjectId> {


    public QuizUI(QuizService service) {
        super(service);
    }

    @Override
    public void create() {
        try {
            getLan();
            String language = Input.getStr("LANGUAGE: ");

            Language language1 = Language.getLanguage(language);
            getC();
            String category = Input.getStr("CATEGORY: ");
            Category category1 = Category.getCategory(category);

            getLevels();
            String level = Input.getStr("Level: ");
            Level level1 = Level.getLevel(level);

            Integer duration = Input.getNum("Duration: ");
            Integer size = Input.getNum("Size: ");
            QuizCreateDto quizCreateDto = new QuizCreateDto();
            quizCreateDto.setCategory(category1);
            quizCreateDto.setDuration(duration);
            quizCreateDto.setLevel(level1);
            quizCreateDto.setSize(size);
            ResponseEntity<Data<List<Question>>> response = service.creates(quizCreateDto);
            List<Question> questions = response.getBody().getData();
            Map<Integer, String> ques = new HashMap<>();
            int i = 1;
            for (Question question : questions) {
                Print.println(Color.PURPLE, i + ". " + question.getTitle());
                int rigtAnswer = 0;
                int j = 1;
                for (QuestionAnswer answer : question.getAnswers()) {
                    Print.println(j + ")  " + answer.getAnswer());
                    if (answer.isRight())
                        rigtAnswer = j;
                    j++;
                }
                int choice = Input.getNum("Enter javob: ");
                Print.println("------------------next-----------------------");

                if (rigtAnswer == choice) {
                    ques.put(i++, "✅");
                } else {
                    ques.put(i++, "❌");
                }

            }
            for (Map.Entry<Integer, String> entry : ques.entrySet()) {
                Print.println(entry.getKey() + "/" + entry.getValue());
            }
        } catch (ApiRuntimeException e) {
            showResponse(e.getMessage());
        }

    }

    @Override
    public void delete() {

    }

    @Override
    public void update() {

    }

    @Override
    public void get() {

    }

    @Override
    public void getAll() {

    }
}
