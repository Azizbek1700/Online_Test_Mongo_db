package uz.jl.security;

import lombok.Getter;
import lombok.Setter;
import uz.jl.entity.auth.User;

/**
 * @author Axmadjonov Eliboy, Wed 9:40 PM,1/26/2022
 */
@Getter
@Setter
public class SecurityHolder implements Audit {
    public static User authUserSession;

    public static void setUser(User authUser) {
        authUserSession = authUser;
    }

    public static void killUser() {
        authUserSession = null;
    }


}
