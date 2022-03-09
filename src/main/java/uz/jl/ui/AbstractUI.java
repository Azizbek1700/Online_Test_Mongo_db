package uz.jl.ui;

import com.google.gson.GsonBuilder;
import lombok.Getter;
import lombok.Setter;
import uz.jl.service.base.BaseGenericService;
import uz.jl.utils.Color;
import uz.jl.utils.Print;

/**
 * @author Botirov Najmiddin, Thu 09:20. 27/01/2022
 */
@Getter
@Setter

public abstract class AbstractUI<S extends BaseGenericService> {
    protected final S service;

    protected AbstractUI(S service) {
        this.service = service;
    }
    protected <T> void showResponse(T response) {
        showResponse( Color.RED, response);
    }

    protected <T> void showResponse(String color, T response) {
        Print.println(color, new GsonBuilder().serializeNulls().setPrettyPrinting().create().toJson(response));
    }
}
