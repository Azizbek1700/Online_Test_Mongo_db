package uz.jl.repository.auth;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Filters;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import uz.jl.dto.auth.UserDto;
import uz.jl.dto.auth.UserUpdateDto;
import uz.jl.entity.auth.User;
import uz.jl.enums.HttpStatus;
import uz.jl.exception.CustomSQLException;
import uz.jl.repository.base.AbstractRepository;
import uz.jl.repository.base.GenericCrudRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class UserRepository extends AbstractRepository<User>
        implements GenericCrudRepository<UserDto, User, UserUpdateDto, ObjectId> {

    public UserRepository(Class<User> clazz) {
        super( clazz );
    }

    @Override
    public ObjectId create(User entity) {

        collection.insertOne( entity );
        return entity.getId();
    }

    public void findUsername(User entity) {
        User user1 = collection.find( Filters.eq( "user_name", entity.getUserName() ) ).first();

        if (Objects.nonNull( user1 )) {
            throw new CustomSQLException( "Username already taken", HttpStatus.HTTP_405, "ANYTHING" );
        }
    }

    @Override
    public Boolean update(UserUpdateDto dto) {
        Bson filter = Filters.eq("_id", new ObjectId(dto.getId()));
        BasicDBObject query = new BasicDBObject();
        ObjectMapper mapper = new ObjectMapper();
        HashMap<String, Object> map = mapper.convertValue(dto, new TypeReference<>(){});
        query.append("$set", new BasicDBObject(map));
        collection.updateOne(filter, query);
        return true;
    }

    @Override
    public void delete(ObjectId id) {
       // validator.validOnDelete(id,session.authUserSession.getId());
    }

    @Override
    public User get(ObjectId id) {

        return collection.find( Filters.eq( "_id", id ) ).first();

    }

    @Override
    public List<User> getAll() {
       // return collection.find();
        List<User> userList = new ArrayList<>();
        FindIterable<User> iterDoc = collection.find();
        for (User user : iterDoc) {
            userList.add(user);
        }
        return userList;
    }

    public User login(String userName) {

       return collection.find( Filters.eq( "user_name", userName ) ).first();

    }
}