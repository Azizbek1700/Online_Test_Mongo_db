package uz.jl.service.base;

import uz.jl.dto.BaseGenericDto;
import uz.jl.dto.GenericDto;
import uz.jl.dto.auth.UserDto;
import uz.jl.response.Data;
import uz.jl.response.ResponseEntity;

import java.io.Serializable;
import java.util.List;

/**
 * @author Axmadjonov Eliboy, Wed 10:27 PM,1/26/2022
 */

/**
 * @param <D>
 * @param <CD>
 * @param <UD>
 * @param <K>
 */
public interface BaseGenericCrudService<D extends BaseGenericDto, CD extends BaseGenericDto, UD extends GenericDto, K extends Serializable> {
    ResponseEntity<Data<K>> create(CD dto);

    ResponseEntity<Data<D>> get(K id);

    ResponseEntity<Data<List<D>>> getAll();

    ResponseEntity<Data<Boolean>> update(UD dto);

    void delete(K id);
}
