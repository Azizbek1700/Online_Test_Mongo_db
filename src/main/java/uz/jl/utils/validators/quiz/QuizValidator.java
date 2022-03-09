package uz.jl.utils.validators.quiz;

import uz.jl.dto.quiz.QuizCreateDto;
import uz.jl.dto.quiz.QuizUpdateDto;
import uz.jl.entity.Quiz;
import uz.jl.security.SecurityHolder;
import uz.jl.utils.validators.base.GenericValidator;

public class QuizValidator extends GenericValidator<SecurityHolder, QuizCreateDto, QuizUpdateDto, Quiz,String> {
    @Override
    protected void validKey(String id) throws IllegalArgumentException {

    }

    @Override
    public void validOnCreate(QuizCreateDto dto) throws IllegalArgumentException {

    }

    @Override
    protected QuizUpdateDto validOnUpdate(QuizUpdateDto dto, Quiz entity) throws IllegalArgumentException {
        return null;
    }


}
