package by.horunzhyn.godel.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "department")
public class Department extends BaseEntity implements Serializable {

    @Column(name = "title")
    private String title;

    public Department(String title) {
        this.title = title;
    }

    public Department() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
