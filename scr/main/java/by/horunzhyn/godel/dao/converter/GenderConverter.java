package by.horunzhyn.godel.dao.converter;

import by.horunzhyn.godel.data.Gender;

import javax.persistence.AttributeConverter;


public class GenderConverter implements AttributeConverter<Gender, String> {

    @Override
    public String convertToDatabaseColumn(Gender attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.name();
    }

    @Override
    public Gender convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        return Gender.valueOf(dbData);
    }
}
