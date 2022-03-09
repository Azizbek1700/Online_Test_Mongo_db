package uz.jl.repository.quiz;

import com.mongodb.BasicDBObject;
import com.mongodb.client.AggregateIterable;
import org.bson.types.ObjectId;
import uz.jl.configs.ApplicationContextHolder;
import uz.jl.dto.quiz.QuizCreateDto;
import uz.jl.dto.quiz.QuizDto;
import uz.jl.dto.quiz.QuizUpdateDto;
import uz.jl.entity.Quiz;
import uz.jl.entity.question.Question;
import uz.jl.repository.base.AbstractRepository;
import uz.jl.repository.base.BaseGenericRepository;
import uz.jl.repository.base.GenericCrudRepository;
import uz.jl.repository.question.QuestionRepository;

import java.util.*;

public class QuizRepository extends AbstractRepository<Quiz> implements BaseGenericRepository, GenericCrudRepository<QuizDto, Quiz, QuizUpdateDto, ObjectId> {

    public QuizRepository(Class<Quiz> clazz) {
        super(clazz);
    }

    @Override
    public ObjectId create(Quiz entity) {
        return null;
    }

    public List<Question> create(QuizCreateDto dto){
        return ApplicationContextHolder.getBean( QuestionRepository.class ).getQuiz( dto );
    }
    @Override
    public Boolean update(QuizUpdateDto dto) {
        return null;
    }

    @Override
    public void delete(ObjectId id) {
    }

    @Override
    public Quiz get(ObjectId id) {
        return null;
    }

    @Override
    public List<Quiz> getAll() {
        return null;
    }
}
