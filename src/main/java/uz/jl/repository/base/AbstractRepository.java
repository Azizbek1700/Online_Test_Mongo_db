package uz.jl.repository.base;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import uz.jl.configs.ApplicationContextHolder;
import uz.jl.entity.base.BaseGenericEntity;
import uz.jl.security.SecurityHolder;

public abstract class AbstractRepository<E extends BaseGenericEntity> implements BaseGenericRepository{

    protected MongoCollection<E> collection;
    protected SecurityHolder session = ApplicationContextHolder.getBean(SecurityHolder.class);
    protected AbstractRepository(Class<E> clazz) {

        MongoDatabase db = ApplicationContextHolder.getBean(MongoDatabase.class);
        this.collection = db.getCollection(clazz.getSimpleName(), clazz);
    }
}
