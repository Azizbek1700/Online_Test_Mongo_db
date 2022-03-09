package uz.jl.repository.question;

import com.mongodb.BasicDBObject;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Filters;
import org.bson.types.ObjectId;
import uz.jl.dto.auth.UserDto;
import uz.jl.dto.auth.UserUpdateDto;
import uz.jl.dto.question.QuestionDto;
import uz.jl.dto.question.QuestionUpdateDto;
import uz.jl.dto.quiz.QuizCreateDto;
import uz.jl.entity.auth.User;
import uz.jl.entity.question.Question;
import uz.jl.repository.base.AbstractRepository;
import uz.jl.repository.base.GenericCrudRepository;

import java.util.*;

/**
 * @author Botirov Najmiddin, Mon 00:06. 31/01/2022
 */
public class QuestionRepository extends AbstractRepository<Question>
        implements GenericCrudRepository<QuestionDto, Question, QuestionUpdateDto, ObjectId> {

    public QuestionRepository(Class<Question> clazz) {
        super(clazz);
    }

    @Override
    public ObjectId create(Question question) {
        collection.insertOne(question);
        return question.getId();
    }

    @Override
    public Boolean update(QuestionUpdateDto dto) {
        return null;
    }

    @Override
    public void delete(ObjectId id) {

    }

    @Override
    public Question get(ObjectId id) {
        return collection.find(Filters.eq("_id", id)).first();
    }

    @Override
    public List<Question> getAll() {
            List<Question> queastions = new ArrayList<>();
            FindIterable<Question> iterDoc = collection.find();
            for (Question question : iterDoc) {
                queastions.add(question);
            }
            return queastions;
    }
    public List<Question>getQuiz(QuizCreateDto dto){
        Map<String, Object> mapMatch = new HashMap<>();
        mapMatch.put("category", dto.getCategory().name());
        mapMatch.put("level", dto.getLevel().name());

        Map<String, Object> mapSimple = new HashMap<>();
        mapSimple.put("size", dto.getSize() );

        BasicDBObject basicDBMatch = new BasicDBObject(mapMatch);
        BasicDBObject basicDBOSimple = new BasicDBObject(mapSimple);
        BasicDBObject queryMatch = new BasicDBObject("$match", basicDBMatch);
        BasicDBObject querySimple = new BasicDBObject("$sample", basicDBOSimple);

        AggregateIterable<Question> dtos = collection.aggregate( Arrays.asList(queryMatch, querySimple));
        List<Question> questions = new ArrayList<>();
        dtos.spliterator().forEachRemaining(questions::add);
        return questions;
    }
}
