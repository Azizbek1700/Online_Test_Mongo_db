package uz.jl.entity.auth;

import lombok.*;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;
import uz.jl.entity.Quiz;
import uz.jl.entity.base.Auditable;
import uz.jl.enums.Language;
import uz.jl.enums.Role;
import uz.jl.enums.Status;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder(builderMethodName = "childBuilder")
public class User extends Auditable {

    @BsonProperty(value = "full_name")
    private String fullName;

    @BsonProperty(value = "user_name")
    private String userName;

    private String password;
    private Language language = Language.EN;
    private Role role;
    private Status status = Status.NO_ACTIVE;
    private List<Quiz> quizzes;
    private Quiz currentQuiz;

    public User(ObjectId id, String fullName, String userName, String password, Role role) {
        super( id );
        this.fullName = fullName;
        this.userName = userName;
        this.password = password;
        this.role = role;
    }
}
