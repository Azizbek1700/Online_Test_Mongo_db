package uz.jl.service.question;

import org.bson.types.ObjectId;
import uz.jl.dto.answer.AnswerCreateDto;
import uz.jl.dto.question.QuestionCreateDto;
import uz.jl.dto.question.QuestionDto;
import uz.jl.dto.question.QuestionUpdateDto;
import uz.jl.entity.question.Question;
import uz.jl.entity.question.QuestionAnswer;
import uz.jl.enums.HttpStatus;
import uz.jl.exception.ApiRuntimeException;
import uz.jl.exception.CustomSQLException;
import uz.jl.mappers.question.QuestionMapper;
import uz.jl.repository.question.QuestionRepository;
import uz.jl.response.Data;
import uz.jl.response.ResponseEntity;
import uz.jl.security.SecurityHolder;
import uz.jl.service.base.AbstractService;
import uz.jl.service.base.BaseGenericCrudService;
import uz.jl.utils.validators.question.QuestionValidator;

import java.util.List;

/**
 * @author Botirov Najmiddin, Mon 00:08. 31/01/2022
 */
public class QuestionService extends AbstractService<QuestionRepository, QuestionMapper, QuestionValidator> implements BaseGenericCrudService<QuestionDto, QuestionCreateDto, QuestionUpdateDto, ObjectId> {

    public QuestionService(Class<QuestionRepository> questionRepository, Class<QuestionMapper> questionMapper, Class<QuestionValidator> questionValidator) {
        super(questionRepository, questionMapper, questionValidator);
    }


    @Override
    public ResponseEntity<Data<ObjectId>> create(QuestionCreateDto dto) {
        try {
            validator.validaOnCreate(dto);
            Question question = mapper.fromCreateDto(dto);
            ObjectId id = repository.create(question);
            return new ResponseEntity<>(new Data<>(id));
        } catch (CustomSQLException e) {
            throw new ApiRuntimeException("Gahaha", HttpStatus.HTTP_STATUS_CODE_NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Data<QuestionDto>> get(ObjectId id) {
        try {
            Question question = repository.get(id);
            validator.validOnGetForQuestion(question);
            QuestionDto dto = mapper.toCreateDto(question);
            return new ResponseEntity<>(new Data<>(dto));
        } catch (CustomSQLException | ApiRuntimeException e) {
            throw new ApiRuntimeException("User not found", HttpStatus.HTTP_404);
        }
    }

    @Override
    public ResponseEntity<Data<List<QuestionDto>>> getAll() {
        try {
            validator.validOnGetAll(SecurityHolder.authUserSession);
            List<Question> question = repository.getAll();
            List<QuestionDto> questionList = mapper.toCreateDtoList(question);
            return new ResponseEntity<>(new Data<>(questionList, questionList.size()));
        } catch (ApiRuntimeException | CustomSQLException e) {
            throw new ApiRuntimeException("PERMISSION_DENIED", HttpStatus.HTTP_404);
        }

    }

    @Override
    public ResponseEntity<Data<Boolean>> update(QuestionUpdateDto dto) {
        return null;
    }

    @Override
    public void delete(ObjectId id) {

    }

    public ResponseEntity<Data<QuestionAnswer>> createAnswer(AnswerCreateDto answer) {
        try {
            validator.validOnCreateAnswer(answer);
            QuestionAnswer questionAnswer = mapper.fromCreateDtoForAnswer(answer);
            return new ResponseEntity<>(new Data<>(questionAnswer));
        } catch (ApiRuntimeException e) {
            throw new ApiRuntimeException("ANSWER_NOT_FOUND", HttpStatus.HTTP_404);
        }
    }
}
