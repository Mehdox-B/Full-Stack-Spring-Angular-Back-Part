package com.mehdox.springbackside.repository;

import com.mehdox.springbackside.entities.PaymentStatus;
import com.mehdox.springbackside.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface PaymentsRepository extends JpaRepository<Payment,Long> {
    List<Payment> findByStudentsCode(String student_code);
    List<Payment> findByPaymentStatus(PaymentStatus paymentStatus);
}
