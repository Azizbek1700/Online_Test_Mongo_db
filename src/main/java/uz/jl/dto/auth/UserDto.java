package uz.jl.dto.auth;

import lombok.*;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;
import uz.jl.dto.GenericDto;
import uz.jl.entity.Quiz;
import uz.jl.enums.Language;
import uz.jl.enums.Role;
import uz.jl.enums.Status;

import java.util.List;
import java.util.Stack;

/**
 * @author Axmadjonov Eliboy, Wed 10:37 PM,1/26/2022
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserDto extends GenericDto {
    @BsonProperty(value = "full_name")
    private String fullName;
    @BsonProperty(value = "user_name")
    private String username;
    private List<Quiz> quizzes;
    private Quiz currentQuiz;
    private Language language;
    private Role role;
    private Status status;

    public UserDto(ObjectId id, String fullName, String username, List<Quiz> quizzes, Quiz currentQuiz, Language language, Role role, Status status) {
        super(id.toString());
        this.fullName = fullName;
        this.username = username;
        this.quizzes = quizzes;
        this.currentQuiz = currentQuiz;
        this.language = language;
        this.role = role;
        this.status = status;
    }
}
