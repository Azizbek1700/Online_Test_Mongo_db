package uz.jl.mappers.question;

import org.bson.types.ObjectId;
import uz.jl.dto.answer.AnswerCreateDto;
import uz.jl.dto.auth.UserCreateDto;
import uz.jl.dto.auth.UserUpdateDto;
import uz.jl.dto.question.QuestionCreateDto;
import uz.jl.dto.question.QuestionDto;
import uz.jl.dto.question.QuestionUpdateDto;
import uz.jl.entity.auth.User;
import uz.jl.entity.question.Question;
import uz.jl.entity.question.QuestionAnswer;
import uz.jl.mappers.GenericMapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Botirov Najmiddin, Mon 00:03. 31/01/2022
 */
public class QuestionMapper extends GenericMapper<Question, QuestionDto, QuestionCreateDto, QuestionUpdateDto> {
    @Override
    public Question fromCreateDto(QuestionCreateDto dto) {
        return new Question(dto.getTitle(), dto.getAnswers(), dto.getLevel(), dto.getLanguage(), dto.getCategory(), dto.getCreatedBy(), dto.getMark());
    }

    @Override
    public QuestionDto toCreateDto(Question question) {
        return new QuestionDto(question.getId().toString(), question.getCreatedAt(), question.getTitle(), question.getAnswers(), question.getLevel(), question.getLanguage(), question.getCategory(), question.getMark());
    }

    @Override
    public QuestionUpdateDto fromUpdateDto(QuestionUpdateDto dto) {
        return null;
    }

    public QuestionAnswer fromCreateDtoForAnswer(AnswerCreateDto answer) {
        return new QuestionAnswer(new ObjectId(), new Date(), answer.getAnswer(), answer.isRight());
    }

    public List<QuestionDto> toCreateDtoList(List<Question> question) {
        List<QuestionDto> questionDtoList = new ArrayList<>();
        for (Question question1 : question) {
            questionDtoList.add(new QuestionDto(question1.getId().toString(),
                    question1.getCreatedAt(),
                    question1.getTitle(),
                    question1.getAnswers(),
                    question1.getLevel(),
                    question1.getLanguage(),
                    question1.getCategory(),
                    question1.getMark()));
        }
        return questionDtoList;
    }
}

