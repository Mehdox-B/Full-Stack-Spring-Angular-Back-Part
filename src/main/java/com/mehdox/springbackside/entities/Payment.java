package com.mehdox.springbackside.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Getter
@Setter
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private LocalDate date;
    private double Amount;
    private PaymentType paymentType;
    private PaymentStatus paymentStatus = PaymentStatus.CREATED ;
    private String file;
    @ManyToOne
    private Student students;
}
