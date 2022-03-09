package uz.jl.utils.validators.question;

import uz.jl.dto.answer.AnswerCreateDto;
import uz.jl.dto.auth.UserCreateDto;
import uz.jl.dto.auth.UserUpdateDto;
import uz.jl.dto.question.QuestionCreateDto;
import uz.jl.dto.question.QuestionUpdateDto;
import uz.jl.entity.auth.User;
import uz.jl.entity.question.Question;
import uz.jl.entity.question.QuestionAnswer;
import uz.jl.enums.HttpStatus;
import uz.jl.enums.Role;
import uz.jl.exception.ApiRuntimeException;
import uz.jl.security.SecurityHolder;
import uz.jl.utils.validators.base.GenericValidator;

import java.util.Objects;

/**
 * @author Botirov Najmiddin, Mon 00:11. 31/01/2022
 */
public class QuestionValidator extends GenericValidator<SecurityHolder, QuestionCreateDto, QuestionUpdateDto, Question, String> {

    @Override
    protected void validKey(String id) throws IllegalArgumentException {

    }

    @Override
    protected void validOnCreate(QuestionCreateDto dto) throws IllegalArgumentException {

    }

    @Override
    protected QuestionUpdateDto validOnUpdate(QuestionUpdateDto dto, Question question) throws IllegalArgumentException {
        return null;
    }

    public void validOnCreateAnswer(AnswerCreateDto answerCreateDto) {
        if (Objects.isNull(answerCreateDto)) {
            throw new ApiRuntimeException("Answer wrong data", HttpStatus.HTTP_STATUS_CODE_NOT_FOUND);
        }
    }

    public void validaOnCreate(QuestionCreateDto dto) {
//        if (!SecurityHolder.authUserSession.getRole().equals(Role.ADMIN) ) {
//            throw new ApiRuntimeException("BUNAQA ROL YOQ SENDA UKA",HttpStatus.HTTP_404);
//        }
//        else if (!SecurityHolder.authUserSession.getRole().equals(Role.TEACHER) ) {
//            throw new ApiRuntimeException("BUNAQA ROL YOQ SENDA UKA",HttpStatus.HTTP_404);
//        }
        if (Objects.isNull(dto)) {
            throw new ApiRuntimeException("NOT_FOUND", HttpStatus.HTTP_404);
        }
        if (Objects.isNull(dto.getLanguage()) ||
                Objects.isNull(dto.getAnswers()) ||
                Objects.isNull(dto.getCategory()) ||
                Objects.isNull(dto.getTitle()) ||
                Objects.isNull(dto.getLevel()) ||
                dto.getMark() == 0) {
            throw new ApiRuntimeException("NOT_FOUND", HttpStatus.HTTP_404);
        }
        int count = 0;
        for (QuestionAnswer answer : dto.getAnswers()) {
            if (answer.isRight()) count++;
        }
        if (count > 1) {
            throw new ApiRuntimeException("1 ta tog`ri javob boladi aka",HttpStatus.HTTP_404);
        }
    }

    public QuestionUpdateDto validOnUpdateForQuestion(QuestionUpdateDto dto) {
        if (Objects.isNull(dto)) throw new ApiRuntimeException("Exeption", HttpStatus.HTTP_404);
        return dto;
    }

    public void validOnGetForQuestion(Question question) {
        if (Objects.isNull(question)) {
            throw new ApiRuntimeException("Not found", HttpStatus.HTTP_404);
        }
    }

    public void validOnGetAll(User authUserSession) {
        if (Objects.isNull( authUserSession )){
            throw new ApiRuntimeException( "SESSION_NULL",HttpStatus.HTTP_404 );
        }
        if (!authUserSession.getRole().equals( Role.ADMIN )){
            throw new ApiRuntimeException( "PERMISSION_DENIED",
                    HttpStatus.HTTP_404 );
        }
    }
}
