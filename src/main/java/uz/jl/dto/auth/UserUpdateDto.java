package uz.jl.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonProperty;
import uz.jl.dto.GenericDto;
import uz.jl.enums.Language;
import uz.jl.enums.Role;

/**
 * @author Axmadjonov Eliboy, Wed 8:57 PM,1/26/2022
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateDto extends GenericDto {
    @BsonProperty(value = "full_name")
    private String fullName;
    private String username;
    String language;
    Role role;

    public UserUpdateDto(String id, String fullName, String username, String language) {
        super(id);
        this.fullName = fullName;
        this.username = username;
        this.language = language;
    }

    public UserUpdateDto(String id, String fullName, String username, String language, Role role) {
        super(id);
        this.fullName = fullName;
        this.username = username;
        this.language = language;
        this.role = role;
    }
}
