package com.mehdox.springbackside;

import com.mehdox.springbackside.entities.PaymentStatus;
import com.mehdox.springbackside.entities.PaymentType;
import com.mehdox.springbackside.entities.Payment;
import com.mehdox.springbackside.entities.Student;
import com.mehdox.springbackside.repository.PaymentsRepository;
import com.mehdox.springbackside.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

@SpringBootApplication
public class SpringBackSideApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBackSideApplication.class, args);
	}
	@Bean
	CommandLineRunner commandLineRunner(StudentRepository studentRepository, PaymentsRepository paymentsRepository){
		return args -> {
			PaymentType[] paymentTypes = PaymentType.values();
			Random random = new Random();
			//Students Manual Creation
			studentRepository.save(Student.builder().ID(UUID.randomUUID().toString())
					.firstName("Mehdox").lastName("B.").code("1234").email("mehdiben.tech@gmail.com").build());
			studentRepository.save(Student.builder().ID(UUID.randomUUID().toString())
					.firstName("Mehdox00").lastName("B.").code("5678").email("mehdiben.tech@gmail.com").build());
			studentRepository.save(Student.builder().ID(UUID.randomUUID().toString())
					.firstName("Mehdox11").lastName("B.").code("0987").email("mehdiben.tech@gmail.com").build());
			//Student's Payments Manual Creation
			studentRepository.findAll().forEach(st -> {
				for (int index = 1;index<20;index++){
					int i = random.nextInt(paymentTypes.length);
					DecimalFormat decimalFormat = new DecimalFormat("0.00");
					Payment payments = Payment.builder()
							.Amount(Double.parseDouble(decimalFormat.format(10000.00 + Math.random()*1000)))
							.paymentType(paymentTypes[i])
							.paymentStatus(PaymentStatus.CREATED)
							.date(LocalDate.now())
							.file(UUID.randomUUID().toString())
							.students(st)
							.build();
					paymentsRepository.save(payments);
				}
			});
		};
	}
}
