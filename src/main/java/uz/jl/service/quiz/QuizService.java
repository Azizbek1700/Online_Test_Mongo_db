package uz.jl.service.quiz;

import org.bson.types.ObjectId;
import uz.jl.dto.question.QuestionUpdateDto;
import uz.jl.dto.quiz.QuizCreateDto;
import uz.jl.dto.quiz.QuizDto;
import uz.jl.dto.quiz.QuizUpdateDto;
import uz.jl.entity.Quiz;
import uz.jl.entity.question.Question;
import uz.jl.enums.HttpStatus;
import uz.jl.exception.ApiRuntimeException;
import uz.jl.exception.CustomSQLException;
import uz.jl.mappers.quiz.QuizMapper;
import uz.jl.repository.quiz.QuizRepository;
import uz.jl.response.Data;
import uz.jl.response.ResponseEntity;
import uz.jl.service.base.BaseGenericCrudService;
import uz.jl.utils.validators.quiz.QuizValidator;

import java.util.List;

/**
 * @author Botirov Najmiddin, Mon 09:52. 31/01/2022
 */
public class QuizService extends AbstractQuizService<QuizRepository, QuizMapper, QuizValidator> implements BaseGenericCrudService<QuizDto, QuizCreateDto, QuizUpdateDto, ObjectId> {

    public QuizService(QuizRepository repository, QuizMapper mapper, QuizValidator validator) {
        super( repository, mapper, validator );
    }

    @Override
    public ResponseEntity<Data<ObjectId>> create(QuizCreateDto dto) {
        try {
            validator.validOnCreate(dto);
//            Quiz quiz =  mapper.fromCreateDto( dto );
            List<Question> questions = repository.create( dto );
//            List<Question> questions = repository.create( quiz );
            return null;
        } catch (ApiRuntimeException | CustomSQLException e) {
            throw new ApiRuntimeException( "NOT_FOUND", HttpStatus.HTTP_404 );
        }
    }
    public ResponseEntity<Data<List<Question>>> creates(QuizCreateDto dto) {
        try {
            validator.validOnCreate(dto);
//            Quiz quiz =  mapper.fromCreateDto( dto );
            List<Question> questions = repository.create( dto );
//            List<Question> questions = repository.create( quiz );
            return new ResponseEntity<>(new Data<>( questions ));
        } catch (ApiRuntimeException | CustomSQLException e) {
            throw new ApiRuntimeException( "NOT_FOUND", HttpStatus.HTTP_404 );
        }
    }

    @Override
    public ResponseEntity<Data<QuizDto>> get(ObjectId id) {
        return null;
    }

    @Override
    public ResponseEntity<Data<List<QuizDto>>> getAll() {
        return null;
    }

    @Override
    public ResponseEntity<Data<Boolean>> update(QuizUpdateDto dto) {
        return null;
    }

    @Override
    public void delete(ObjectId id) {

    }
}
