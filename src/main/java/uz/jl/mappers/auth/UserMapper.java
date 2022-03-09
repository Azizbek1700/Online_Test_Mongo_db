package uz.jl.mappers.auth;

import org.bson.types.ObjectId;
import uz.jl.dto.auth.UserCreateDto;
import uz.jl.dto.auth.UserDto;
import uz.jl.dto.auth.UserUpdateDto;
import uz.jl.entity.auth.User;
import uz.jl.mappers.GenericMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Axmadjonov Eliboy, Wed 9:00 PM,1/26/2022
 */
public class UserMapper extends GenericMapper<User, UserDto, UserCreateDto, UserUpdateDto> {
    @Override
    public User fromCreateDto(UserCreateDto dto) {
        return new User( new ObjectId(), dto.getFull_name(), dto.getUser_name(), dto.getPassword(), dto.getRole() );
    }

    @Override
    public UserDto toCreateDto(User user) {
        return new UserDto( user.getId(), user.getFullName(), user.getUserName(), user.getQuizzes(), user.getCurrentQuiz(), user.getLanguage(), user.getRole(), user.getStatus() );
    }

    @Override
    public UserUpdateDto fromUpdateDto(UserUpdateDto dto) {
        return null;
    }

    public List<UserDto> toCreateDtoList(List<User> users) {

        List<UserDto> userDtoList = new ArrayList<>();

        for (User user : users) {
            userDtoList.add( new UserDto( user.getId(),
                    user.getFullName(),
                    user.getUserName(),
                    user.getQuizzes(),
                    user.getCurrentQuiz(),
                    user.getLanguage(),
                    user.getRole(),
                    user.getStatus() ) );
        }
        return userDtoList;
    }
}
