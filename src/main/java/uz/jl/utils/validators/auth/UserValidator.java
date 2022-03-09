package uz.jl.utils.validators.auth;

import org.bson.types.ObjectId;
import uz.jl.dto.auth.UserCreateDto;
import uz.jl.dto.auth.UserUpdateDto;
import uz.jl.entity.auth.User;
import uz.jl.enums.HttpStatus;
import uz.jl.enums.Role;
import uz.jl.exception.ApiRuntimeException;
import uz.jl.security.SecurityHolder;
import uz.jl.utils.validators.base.GenericValidator;

import java.util.Objects;

/**
 * @author Axmadjonov Eliboy, Wed 9:03 PM,1/26/2022
 */
public class UserValidator extends GenericValidator<SecurityHolder, UserCreateDto, UserUpdateDto,User, String> {
    @Override
    protected void validKey(String id) throws IllegalArgumentException {

    }

    @Override
    public void validOnCreate(UserCreateDto dto) {
        if (Objects.isNull(dto)) {
            throw new ApiRuntimeException("Invalid data", HttpStatus.HTTP_502);
        }

        if (Objects.isNull(dto.getUser_name())) {
            throw new ApiRuntimeException("Invalid data",HttpStatus.HTTP_502);
        }

        if (Objects.isNull(dto.getFull_name())) {
            throw new ApiRuntimeException("Invalid data",HttpStatus.HTTP_502);
        }

        if (Objects.isNull(dto.getPassword())) {
            throw new ApiRuntimeException("Invalid data",HttpStatus.HTTP_502);
        }
        if (Objects.isNull( SecurityHolder.authUserSession)) {
            dto.setRole( Role.STUDENT);
        }

        else if (SecurityHolder.authUserSession.getRole().equals(Role.ADMIN)) {
            dto.setRole(Role.TEACHER);
        }

    }


    @Override
    public UserUpdateDto validOnUpdate(UserUpdateDto dto, User user) throws IllegalArgumentException {

        if (Objects.isNull( dto )) {
            throw new ApiRuntimeException( "Wrong data",HttpStatus.HTTP_404 );
        }
        if (Objects.isNull( dto.getId() )) {
            throw new ApiRuntimeException( "Id is null",HttpStatus.HTTP_404 );
        }
        if (!dto.getId().equals( SecurityHolder.authUserSession.getId() ) &&! SecurityHolder.authUserSession.getRole().equals( Role.ADMIN )) {
            throw new ApiRuntimeException( "Permission denied",HttpStatus.HTTP_404 );
        }
        if (Objects.isNull( user )) {
            throw new ApiRuntimeException( "User not found 1",HttpStatus.HTTP_404 );
        }

        if (Objects.isNull( dto.getFullName() )) {
            dto.setFullName( user.getFullName() );
        }
        if (Objects.isNull( dto.getUsername() )) {
            dto.setUsername( user.getUserName() );
        }
        if (Objects.isNull( dto.getLanguage() )) {
            dto.setLanguage( user.getLanguage().toString() );
        }
        if (Objects.isNull( dto.getRole() )) {
            dto.setRole( user.getRole() );
        }

        return dto;

    }

    public void validOnLogin(User user , String password) {
        if (Objects.isNull(user) || !user.getPassword().equals(password)){
            throw new ApiRuntimeException("USER_NOT_FOUND",HttpStatus.HTTP_405);
        }
    }

    public void validOnDelete(ObjectId id, ObjectId sessionId) {
        if (Objects.isNull(id) || Objects.isNull(sessionId)) {
            throw  new ApiRuntimeException("USER_NOT_FOUND",HttpStatus.HTTP_405);
        }
    }

    public void validOnGet(User user) {
        if (Objects.isNull( user )) {
            throw new ApiRuntimeException( "USER_NOT_FOUND",HttpStatus.HTTP_404 );
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
