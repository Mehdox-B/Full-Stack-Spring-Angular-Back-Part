package com.mehdox.springbackside.dto;

import com.mehdox.springbackside.entities.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NewPaymentDTO {
    private double amount;
    private PaymentType paymentType;
    private LocalDate localDate;
    private String studentCode;
}
