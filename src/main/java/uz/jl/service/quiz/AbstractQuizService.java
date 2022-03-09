package uz.jl.service.quiz;

import uz.jl.mappers.BaseGenericMapper;
import uz.jl.repository.base.BaseGenericRepository;
import uz.jl.service.base.BaseGenericService;
import uz.jl.utils.validators.base.BaseGenericValidator;

/**
 * @author Botirov Najmiddin, Mon 09:54. 31/01/2022
 */
public class AbstractQuizService<R extends BaseGenericRepository , M extends BaseGenericMapper, V extends BaseGenericValidator> implements BaseGenericService {
    protected R repository;
    protected final M mapper;
    protected final V validator;

    public AbstractQuizService(R repository, M mapper, V validator) {
        this.repository = repository;
        this.mapper = mapper;
        this.validator = validator;
    }
}
