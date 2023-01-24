package com.example.cameljpa.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Builder
@Entity // 1
@NoArgsConstructor
@Getter
@AllArgsConstructor
@Setter
@Table(name = "camel") // 2
public class Camel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private int age;
    private Gender gender;
    @OneToMany
    private List<Guardian> guardians;


    public void setGuardians(Guardian guardian) {
        guardians.add(guardian);
    }
}
