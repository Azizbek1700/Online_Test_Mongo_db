package uz.jl.mappers;

import uz.jl.dto.BaseGenericDto;
import uz.jl.dto.GenericDto;
import uz.jl.dto.auth.UserCreateDto;
import uz.jl.dto.auth.UserDto;
import uz.jl.dto.auth.UserUpdateDto;
import uz.jl.entity.auth.User;
import uz.jl.entity.base.BaseGenericEntity;

public abstract class GenericMapper<
        E extends BaseGenericEntity,
        D extends BaseGenericDto,
        CD extends BaseGenericDto,
        UD extends GenericDto> implements BaseGenericMapper {


    public abstract E fromCreateDto(CD dto);

    public abstract D toCreateDto(E user);

    public abstract UD fromUpdateDto(UD dto);
}
