package uz.jl.service.auth;

import org.bson.types.ObjectId;
import uz.jl.dto.auth.UserCreateDto;
import uz.jl.dto.auth.UserDto;
import uz.jl.dto.auth.UserUpdateDto;
import uz.jl.entity.auth.User;
import uz.jl.enums.HttpStatus;
import uz.jl.exception.ApiRuntimeException;
import uz.jl.exception.CustomSQLException;
import uz.jl.mappers.auth.UserMapper;
import uz.jl.repository.auth.UserRepository;
import uz.jl.response.Data;
import uz.jl.response.ResponseEntity;
import uz.jl.security.SecurityHolder;
import uz.jl.service.base.BaseGenericCrudService;
import uz.jl.service.base.AbstractService;
import uz.jl.utils.validators.auth.UserValidator;

import java.util.List;

/**
 * @author Axmadjonov Eliboy, Wed 10:36 PM,1/26/2022
 */
public class UserService extends AbstractService<UserRepository, UserMapper, UserValidator> implements BaseGenericCrudService<UserDto, UserCreateDto, UserUpdateDto, ObjectId> {

    public UserService(Class<UserRepository> userRepository, Class<UserMapper> userMapper, Class<UserValidator> userValidator) {
        super( userRepository, userMapper, userValidator );
    }

    @Override
    public ResponseEntity<Data<ObjectId>> create(UserCreateDto dto) {
        try {

            validator.validOnCreate( dto );
            User user = mapper.fromCreateDto( dto );
            repository.findUsername( user );

            ObjectId objectId = repository.create( user );
            return new ResponseEntity<>( new Data<>( objectId ) );
        } catch (CustomSQLException | ApiRuntimeException e) {
            throw new ApiRuntimeException( "Invalid Data", HttpStatus.HTTP_404 );
        }
    }

    @Override
    public ResponseEntity<Data<UserDto>> get(ObjectId id) {
        try {

        User user = repository.get( id );
        validator.validOnGet(user);
       UserDto dto= mapper.toCreateDto( user ); // userdan dto yasab beryabdi
        return new ResponseEntity<>( new Data<>( dto ) );
        }
        catch (CustomSQLException | ApiRuntimeException e){
            throw new ApiRuntimeException( "User not found", HttpStatus.HTTP_404 );
        }
    }

    @Override
    public ResponseEntity<Data<List<UserDto>>> getAll( ) {
       try {
           validator.validOnGetAll( SecurityHolder.authUserSession );
           List<User> users = repository.getAll();
           List<UserDto> userList = mapper.toCreateDtoList( users );
           return new ResponseEntity<>( new Data<>( userList ,userList.size()) );
       }
       catch (ApiRuntimeException | CustomSQLException e){
           throw new ApiRuntimeException( "PERMISSION_DENIED",HttpStatus.HTTP_404 );
       }
    }

    @Override
    public ResponseEntity<Data<Boolean>> update(UserUpdateDto dto) {
        try {

        User user= repository.get( new ObjectId(dto.getId()) );
        UserUpdateDto userUpdateDto = validator.validOnUpdate( dto, user );
        Boolean bool = repository.update( userUpdateDto );
        return new ResponseEntity<>( new Data<>( bool ) );
        }
        catch (ApiRuntimeException | CustomSQLException e){
            throw new ApiRuntimeException( "User not found", HttpStatus.HTTP_404 );
        }
    }

    @Override
    public void delete(ObjectId id) {
        repository.delete( id );
    }


    public ResponseEntity<Data<User>> login(String userName, String password) {
        try {
            User user = repository.login( userName );
            validator.validOnLogin( user, password );
            SecurityHolder.setUser( user );

            return new ResponseEntity<>( new Data<>( user ) );
        } catch (ApiRuntimeException | CustomSQLException e) {
            throw new ApiRuntimeException( "User not found", HttpStatus.HTTP_404 );
        }
    }
}
