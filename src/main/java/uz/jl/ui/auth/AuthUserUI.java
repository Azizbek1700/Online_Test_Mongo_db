package uz.jl.ui.auth;

import org.bson.types.ObjectId;
import uz.jl.dto.auth.UserCreateDto;
import uz.jl.dto.auth.UserDto;
import uz.jl.dto.auth.UserUpdateDto;
import uz.jl.entity.auth.User;
import uz.jl.enums.Role;
import uz.jl.exception.ApiRuntimeException;
import uz.jl.response.Data;
import uz.jl.response.ResponseEntity;
import uz.jl.security.SecurityHolder;
import uz.jl.service.auth.UserService;
import uz.jl.ui.AbstractUI;
import uz.jl.ui.GenericCrudUI;
import uz.jl.utils.Color;
import uz.jl.utils.Input;
import uz.jl.utils.Print;

import java.util.List;

/**
 * @author Axmadjonov Eliboy, Wed 10:25 PM,1/26/2022
 */
public class AuthUserUI extends AbstractUI<UserService> implements GenericCrudUI<UserDto, UserCreateDto, UserUpdateDto, String> {
    public AuthUserUI(UserService service) {
        super(service);
    }

    @Override
    public void create() {
        try {
            String fullName = Input.getStr("Enter full name: ");
            String username = Input.getStr("username: ");
            String password = Input.getStr("password: ");
            UserCreateDto userCreateDto = new UserCreateDto(fullName, username, password);
            ResponseEntity<Data<ObjectId>> response = service.create(userCreateDto);
            showResponse(Color.GREEN, response);
        } catch (ApiRuntimeException e) {
            showResponse(e.getMessage());
        }
    }


    @Override
    public void get() {
        try {

            String id = Input.getStr("Enter Id : ");
            ResponseEntity<Data<UserDto>> response = service.get(new ObjectId(id));
            showResponse(Color.GREEN, response);
        } catch (ApiRuntimeException e) {
            showResponse(e.getMessage());
        }
    }

    @Override
    public void delete() {
        try {
            String id = Input.getStr("Enter ID: ");
            service.delete(new ObjectId(id));
        } catch (ApiRuntimeException e) {
            //showResponse( );
        }
    }

    @Override
    public void update() {
        try {

            String id = Input.getStr("ID:");
            String fullName = Input.getStr("FullName: ");
            String username = Input.getStr("Username: ");
            String language = Input.getStr("Language: ");
            if (SecurityHolder.authUserSession.getRole().equals(Role.ADMIN)) {
                Role role = Role.STUDENT;
                int i = 1;
                for (Role value : Role.values()) {
                    Print.println((i++) + "." + value.name());
                }

                String rol = Input.getStr("Enter role: ");
                if (rol.equals( "1" )) role = Role.ADMIN;
                if (rol.equals( "2" )) role = Role.TEACHER;
                if (rol.equals( "3" )) role = Role.STUDENT;

                ResponseEntity<Data<Boolean>> response = service.update(new UserUpdateDto(id, fullName, username, language, role));
                showResponse(Color.GREEN, response);

            }

            ResponseEntity<Data<Boolean>> response = service.update(new UserUpdateDto(id, fullName, username, language));
            showResponse(Color.GREEN, response);
        } catch (ApiRuntimeException e) {
            showResponse(e.getMessage());
        }
    }

    @Override
    public void getAll() {
        ResponseEntity<Data<List<UserDto>>> response = service.getAll();
        showResponse(Color.GREEN, response);
    }

    public void login() {
        try {

            String userName = Input.getStr("Enter user name; ");
            String password = Input.getStr("Enter password: ");
            ResponseEntity<Data<User>> response = service.login(userName, password);
            showResponse(Color.GREEN, response);
        } catch (ApiRuntimeException e) {
            showResponse(e.getMessage());
        }
    }


}
