package uz.jl.dto.auth;

import lombok.*;
import uz.jl.dto.BaseGenericDto;
import uz.jl.enums.Role;

/**
 * @author Axmadjonov Eliboy, Wed 8:55 PM,1/26/2022
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserCreateDto implements BaseGenericDto {
    private String full_name;
    private String user_name;
    private String password;
    private Role role;

    public UserCreateDto(String fullName, String username, String password) {
        this.full_name = fullName;
        this.user_name = username;
        this.password = password;
    }
}


