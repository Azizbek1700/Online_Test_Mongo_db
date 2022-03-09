package uz.jl.ui.menuUI;

import uz.jl.configs.ApplicationContextHolder;
import uz.jl.security.SecurityHolder;
import uz.jl.utils.Print;

import java.util.Map;
import java.util.Objects;

/**
 * @author Axmadjonov Eliboy, Wed 9:35 PM,1/26/2022
 */
public class Menu {
    private static Map<String, String> menus;

    private static final SecurityHolder session = ApplicationContextHolder.getBean( SecurityHolder.class );

    public static void getMainMenu() {
        if (Objects.isNull( session.authUserSession )) {
            Print.println( "LOGIN - > login" );
            Print.println( "REGISTER - > register" );
        } else {
            Print.println( "Get -> 1" );
            Print.println( "GetAll -> 2" );
            Print.println( "Update -> 3" );
            Print.println( "Delete -> 4" );
            Print.println( "QuessionCreate -> 5" );
            Print.println( "Get -> 6" );
            Print.println( "GetAll -> 7" );
        }
        Print.println( "QUIT - > q.." );
    }
}
