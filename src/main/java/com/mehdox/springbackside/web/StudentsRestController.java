package com.mehdox.springbackside.web;

import com.mehdox.springbackside.entities.Payment;
import com.mehdox.springbackside.entities.PaymentStatus;
import com.mehdox.springbackside.entities.PaymentType;
import com.mehdox.springbackside.entities.Student;
import com.mehdox.springbackside.repository.PaymentsRepository;
import com.mehdox.springbackside.repository.StudentRepository;
import com.mehdox.springbackside.service.PaymentService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin("http://localhost:4200")
public class StudentsRestController {
    private PaymentsRepository paymentsRepository;
    private StudentRepository studentRepository;
    private PaymentService paymentService;
    public StudentsRestController(StudentRepository studentRepository,PaymentsRepository paymentsRepository,PaymentService paymentService){
        this.studentRepository = studentRepository;
        this.paymentsRepository = paymentsRepository;
        this.paymentService = paymentService;
    }
    @GetMapping("/payments")
    public List<Payment> getAllPayments(){
        return paymentsRepository.findAll();
    }
    @GetMapping("/payments/{payment_id}")
    public Payment getPaymentsByID(@PathVariable Long payment_id){
        return paymentsRepository.findById(payment_id).get();
    }
    @GetMapping("/students")
    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }
    @GetMapping("/students/{student_id}")
    public Student getStudentByID(@PathVariable int student_id){
        return studentRepository.findById(student_id).get();
    }
    @GetMapping("/students/{student_code}/payments")
    public List<Payment> getPaymentsByStudentCode(@PathVariable String student_code){
        return paymentsRepository.findByStudentsCode(student_code);
    }
    @PostMapping(value = "/payments",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Payment savePayment(@RequestParam MultipartFile file,@RequestParam double amount,@RequestParam String student_code,@RequestParam PaymentType paymentType){
        Payment savedPayment = paymentService.savePayment(file,amount,student_code,paymentType);
        return savedPayment;
    }
    @GetMapping(value = "/paymentFile/{payment_ID}",produces = MediaType.APPLICATION_PDF_VALUE)
    public byte[] getPaymentFile(@PathVariable Long payment_ID) throws IOException{
       return paymentService.getPaymentFile(payment_ID);
    }
    @PutMapping("/payment/updateStatus/{paymentsID}")
    public Payment updatePaymentsState(@RequestParam PaymentStatus paymentStatus,@PathVariable Long paymentsID) {
        return  paymentService.updatePaymentsState(paymentStatus,paymentsID);
    }
}
