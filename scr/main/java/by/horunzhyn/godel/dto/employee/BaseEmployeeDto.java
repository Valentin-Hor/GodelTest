package by.horunzhyn.godel.dto.employee;

import by.horunzhyn.godel.data.Gender;
import by.horunzhyn.godel.dto.BaseDto;

import java.time.LocalDate;

public abstract class BaseEmployeeDto extends BaseDto {

    private String firstName;
    private String lastName;
    private Gender gender;
    private LocalDate dateOfBirth;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
