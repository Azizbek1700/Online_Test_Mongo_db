package uz.jl.ui.question;

import org.bson.types.ObjectId;
import uz.jl.dto.answer.AnswerCreateDto;
import uz.jl.dto.answer.AnswerUpdateDto;
import uz.jl.dto.question.QuestionCreateDto;
import uz.jl.dto.question.QuestionDto;
import uz.jl.dto.question.QuestionUpdateDto;
import uz.jl.entity.question.Question;
import uz.jl.entity.question.QuestionAnswer;
import uz.jl.enums.Category;
import uz.jl.enums.Level;
import uz.jl.exception.ApiRuntimeException;
import uz.jl.response.Data;
import uz.jl.response.ResponseEntity;
import uz.jl.service.question.QuestionService;
import uz.jl.ui.AbstractUI;
import uz.jl.ui.GenericCrudUI;
import uz.jl.utils.Color;
import uz.jl.utils.Input;
import uz.jl.utils.Print;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Botirov Najmiddin, Sun 23:58. 30/01/2022
 */
public class QuestionUI extends AbstractUI<QuestionService> implements GenericCrudUI<QuestionDto, QuestionCreateDto, QuestionUpdateDto, String>, IQuestionUI<AnswerCreateDto, AnswerUpdateDto> {

    public QuestionUI(QuestionService questionService) {
        super(questionService);
    }

    @Override
    public void create() {
        try {
            int j = 1;
            String category = null;
            for (Category category1 : Category.values()) {
                Print.println(Color.BLUE,(j++)+". "+category1.name());
            }
            String choice1 = Input.getStr("Enter category: ");
            if (choice1.equals("1")) category=Category.MATH.toString();
            if (choice1.equals("2")) category=Category.CHEMISTRY.toString();
            if (choice1.equals("3")) category=Category.GEOGRAPHY.toString();
            if (choice1.equals("4")) category=Category.ENGLISH.toString();

            String language = Input.getStr("Language: ");
            String title = Input.getStr("Title: ");
            Integer mark = Input.getNum("Mark: ");
            int i = 1;
            String level = null;
            for (Level value : Level.values()) {
                Print.println(Color.BLUE,(i++)+". "+value.name());
            }
            String choice = Input.getStr("Level: ");
            if (choice.equals("1")) level=Level.EASY.toString();
            if (choice.equals("2")) level=Level.MEDIUM.toString();
            if (choice.equals("3")) level=Level.HARD.toString();
            String option;
            List<QuestionAnswer> questionAnswerList = new ArrayList<>();
            do {
                ResponseEntity<Data<QuestionAnswer>> response = service.createAnswer(createAnswer());
                questionAnswerList.add(response.getBody().getData());
                option = Input.getStr("Do you want to continue? (yes/n..).. ");
            }
            while (option.equalsIgnoreCase("yes"));

            QuestionCreateDto question = new QuestionCreateDto(new ObjectId(), new Date(), title, questionAnswerList, level, language, category, mark, "");
            ResponseEntity<Data<ObjectId>> response = service.create(question);
            showResponse(Color.BLACK, response);
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
        try {
        String id = Input.getStr("_id: ");
        ResponseEntity<Data<QuestionDto>> response = service.get(new ObjectId(id));
        showResponse(response);

        } catch (ApiRuntimeException e) {
        showResponse(e);
        }

    }

    @Override
    public void getAll() {
            ResponseEntity<Data<List<QuestionDto>>> response = service.getAll( );
            showResponse(Color.GREEN, response );
    }
    @Override
    public AnswerCreateDto createAnswer() {
        String answer = Input.getStr("Enter answer: ");
        String right = Input.getStr("is_right(T/F): ");
        boolean r;
        r = right.toUpperCase().startsWith("T");
        return new AnswerCreateDto(answer, r);
    }

    @Override
    public void updateAnswer() {

    }

    @Override
    public void getAllAnswer() {

    }

    @Override
    public void getAnswer() {

    }

    @Override
    public void deleteAnswer() {

    }
}
