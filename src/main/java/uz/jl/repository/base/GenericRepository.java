package uz.jl.repository.base;

import uz.jl.dto.BaseGenericDto;
import uz.jl.entity.base.BaseGenericEntity;
import uz.jl.response.Data;
import uz.jl.response.ResponseEntity;

import java.io.Serializable;
import java.util.List;

public interface GenericRepository<E extends BaseGenericEntity, K extends Serializable> {

    E get(K id);

    List<E> getAll();

}
