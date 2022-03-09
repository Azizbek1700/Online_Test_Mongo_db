package uz.jl.ui;

import uz.jl.configs.ApplicationContextHolder;
import uz.jl.dto.BaseGenericDto;
import uz.jl.dto.GenericDto;
import uz.jl.service.auth.UserService;

import java.io.Serializable;

/**
 * @author Axmadjonov Eliboy, Wed 9:29 PM,1/26/2022
 */
public interface GenericCrudUI<D extends GenericDto, CD extends BaseGenericDto, UD extends GenericDto, L extends Serializable> {
    public abstract void create();

    public abstract void delete();

    public abstract void update();

    public abstract void get();

    public abstract void getAll();
}
