package ch.hesge.cours634.exam;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class PersonEntity {

    @Id
    private String email;
    private String name;
    private String address;
    @Enumerated(EnumType.STRING)
    private Category category;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonEntity)) return false;
        PersonEntity that = (PersonEntity) o;
        return Objects.equals(email, that.email) &&
                Objects.equals(name, that.name) &&
                Objects.equals(address, that.address) &&
                category == that.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, name, address, category);
    }
}
