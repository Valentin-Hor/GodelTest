package by.horunzhyn.godel.service;

import by.horunzhyn.godel.dao.BaseRepository;
import by.horunzhyn.godel.entity.BaseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public abstract class BaseServiceImpl<
        E extends BaseEntity,
        R extends BaseRepository<E>> implements BaseService<E> {

    protected abstract R getRepository();

    @Override
    public void delete(Long id) {
        getRepository().deleteById(id);
    }

    @Override
    public void delete(E entity) {
        getRepository().delete(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<E> findOne(Long id) {
        return getRepository().findById(id);
    }


    @Override
    @Transactional(readOnly = true)
    public List<E> findAllById(List<Long> ids) {
        return getRepository().findAllById(ids);
    }

    @Override
    public E save(E entity) {
        return getRepository().saveAndFlush(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<E> findAll() {
        return getRepository().findAll();
    }

}
