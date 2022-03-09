package uz.jl.service.base;

import uz.jl.configs.ApplicationContextHolder;
import uz.jl.mappers.BaseGenericMapper;
import uz.jl.repository.base.BaseGenericRepository;
import uz.jl.utils.validators.base.BaseGenericValidator;

/**
 * @author Axmadjonov Eliboy, Wed 9:32 PM,1/26/2022
 */
public abstract class AbstractService<R extends BaseGenericRepository, M extends BaseGenericMapper,V extends BaseGenericValidator> implements BaseGenericService{
    protected final R repository;
    protected final M mapper;
    protected final V validator;

    protected AbstractService(Class<R> repository, Class<M> mapper, Class<V> validator) {
        this.repository = ApplicationContextHolder.getBean(repository);
        this.mapper = ApplicationContextHolder.getBean(mapper);
        this.validator = ApplicationContextHolder.getBean(validator);
   }

}
