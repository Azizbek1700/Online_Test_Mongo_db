package uz.jl.mappers.quiz;

import uz.jl.dto.BaseGenericDto;
import uz.jl.dto.GenericDto;
import uz.jl.dto.quiz.QuizCreateDto;
import uz.jl.dto.quiz.QuizDto;
import uz.jl.dto.quiz.QuizUpdateDto;
import uz.jl.entity.Quiz;
import uz.jl.entity.base.BaseGenericEntity;
import uz.jl.mappers.GenericMapper;

import javax.swing.text.html.parser.Entity;
import java.util.Locale;

public class QuizMapper extends GenericMapper<Quiz, QuizDto, QuizCreateDto, QuizUpdateDto> {

    @Override
    public Quiz fromCreateDto(QuizCreateDto dto) {
        Quiz quiz = new Quiz();
        quiz.setCategory( dto.getCategory() );
        quiz.setLevel( dto.getLevel() );
        quiz.setDuration( dto.getDuration() );
        quiz.setMark( dto.getMark() );
        quiz.setQuestions( dto.getQuestions() );
        return quiz;
    }

    @Override
    public QuizDto toCreateDto(Quiz user) {
        return null;
    }

    @Override
    public QuizUpdateDto fromUpdateDto(QuizUpdateDto dto) {

        return null;
    }
}
