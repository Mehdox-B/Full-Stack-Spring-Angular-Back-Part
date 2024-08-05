package com.mehdox.springbackside.service;

import com.mehdox.springbackside.dto.NewPaymentDTO;
import com.mehdox.springbackside.entities.Payment;
import com.mehdox.springbackside.entities.PaymentStatus;
import com.mehdox.springbackside.entities.PaymentType;
import com.mehdox.springbackside.entities.Student;
import com.mehdox.springbackside.repository.PaymentsRepository;
import com.mehdox.springbackside.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.UUID;

@Service
@Transactional
public class PaymentService {
    private PaymentsRepository paymentsRepository;
    private StudentRepository studentRepository;
    public PaymentService(PaymentsRepository paymentsRepository,StudentRepository studentRepository){
        this.paymentsRepository = paymentsRepository;
        this.studentRepository = studentRepository;
    }
    public Payment savePayment(MultipartFile file , NewPaymentDTO newPaymentDTO){
        Path path = Paths.get(System.getProperty("user.home"),"students-app-files","payments");
        if(!Files.exists(path)){
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String fileID = UUID.randomUUID().toString();
        Path filePath = Paths.get(System.getProperty("user.home"),"students-app-files","payments",fileID+".pdf");
        try{
            Files.copy(file.getInputStream(),filePath);
        }
        catch(IOException exception){
            exception.printStackTrace();
        }
        Student student = studentRepository.findByCode(newPaymentDTO.getStudentCode());
        Payment payment = Payment.builder()
                .paymentStatus(PaymentStatus.CREATED)
                .date(LocalDate.now())
                .Amount(newPaymentDTO.getAmount())
                .file(filePath.toUri().toString())
                .students(student)
                .paymentType(newPaymentDTO.getPaymentType())
                .build();
        Payment savedPayment = paymentsRepository.save(payment);
        return  savedPayment;
    }
    public Payment updatePaymentsState(PaymentStatus paymentStatus,Long paymentsID) {
        Payment currentPayment = paymentsRepository.findById(paymentsID).get();
        currentPayment.setPaymentStatus(paymentStatus);
        return paymentsRepository.save(currentPayment);
    }
    public byte[] getPaymentFile(Long payment_ID) throws IOException{
        Payment payment = paymentsRepository.findById(payment_ID).get();
        String filePath = payment.getFile();
        return Files.readAllBytes(Path.of(URI.create(filePath)));
    }
}
