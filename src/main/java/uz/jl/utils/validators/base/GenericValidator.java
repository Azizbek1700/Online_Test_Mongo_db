package uz.jl.utils.validators.base;

import uz.jl.configs.ApplicationContextHolder;
import uz.jl.dto.BaseGenericDto;
import uz.jl.dto.GenericDto;
import uz.jl.entity.base.BaseGenericEntity;
import uz.jl.security.Audit;
import uz.jl.security.SecurityHolder;
import uz.jl.utils.BaseUtils;

import java.io.Serializable;

/**
 * Info about this class
 *
 * @param <CD>
 * @param <UD>
 * @param <K>
 */
public abstract class GenericValidator<S extends Audit, CD extends BaseGenericDto, UD extends GenericDto, E extends BaseGenericEntity, K extends Serializable> implements BaseGenericValidator {
    protected BaseUtils utils = ApplicationContextHolder.getBean( BaseUtils.class );
    protected SecurityHolder session = ApplicationContextHolder.getBean( SecurityHolder.class );

    /**
     * @param id
     */
    protected abstract void validKey(K id) throws IllegalArgumentException;

    /**
     * Info for this method
     *
     * @param dto
     */
    protected abstract void validOnCreate(CD dto) throws IllegalArgumentException;

    /**
     * @param dto
     */
    protected abstract UD validOnUpdate(UD dto,E entity) throws IllegalArgumentException;

}
