package com.mehdox.springbackside.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Student {
    @Id
    private String Id;
    @Column(unique = true)
    private String code;
    private String  firstName;
    private String  lastName;
    private String  email;
    private String  picture;
}
