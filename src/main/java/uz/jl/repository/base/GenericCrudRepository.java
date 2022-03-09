package uz.jl.repository.base;

import uz.jl.dto.BaseGenericDto;
import uz.jl.dto.GenericDto;
import uz.jl.entity.base.BaseGenericEntity;
import uz.jl.response.Data;
import uz.jl.response.ResponseEntity;

import java.io.Serializable;

public interface GenericCrudRepository<D extends BaseGenericDto,
        E extends BaseGenericEntity, UD extends GenericDto, K extends Serializable>
        extends GenericRepository<E, K> {

    K create(E entity);

    Boolean update(UD dto);

    void delete(K id);
}
