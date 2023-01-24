package com.example.cameljpa.model;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Entity // 1
@NoArgsConstructor
@Getter
@AllArgsConstructor
@Setter
@Table(name = "guardian")
public class Guardian {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String lastName;
    private String email;





    public Guardian(String name, String email, String lastName) {
        this.name = name;
        this.email = email;
        this.lastName = lastName;
    }



    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return "Guardian{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }


}
