package by.horunzhyn.godel.dto;

public abstract class BaseDto {
    private Long id;

    public BaseDto(Long id) {
        this.id = id;
    }

    public BaseDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
