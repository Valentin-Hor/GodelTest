package by.horunzhyn.godel.dto;

import by.horunzhyn.godel.entity.BaseEntity;

public abstract class BaseDtoMapper<E extends BaseEntity, Dto extends BaseDto> {

    protected abstract Dto createDto();

    protected abstract void fillDto(Dto dto, E entity);

    protected abstract E createEntity();

    protected abstract void fillEntity(E entity, Dto dto);

    public Dto mapEntityToDto(E entity) {
        if (entity == null) {
            return null;
        }
        Dto dto = createDto();
        dto.setId(entity.getId());
        fillDto(dto, entity);
        return dto;
    }

    public E mapDtoToEntity(Dto dto) {
        if (dto == null) {
            return null;
        }
        E entity = createEntity();
        fillEntity(entity, dto);
        return entity;
    }

}
