package com.mehdox.springbackside.dto;

import com.mehdox.springbackside.entities.PaymentStatus;
import com.mehdox.springbackside.entities.PaymentType;
import com.mehdox.springbackside.entities.Student;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Getter
@Setter
public class PaymentDTO {
    private Long ID;
    private LocalDate date;
    private double Amount;
    private PaymentType paymentType;
    private PaymentStatus paymentStatus = PaymentStatus.CREATED ;
}
