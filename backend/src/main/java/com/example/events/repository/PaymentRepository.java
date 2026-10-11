package com.example.events.repository;import com.example.events.Payment;import org.springframework.data.jpa.repository.JpaRepository;import java.util.Optional;
public interface PaymentRepository extends JpaRepository<Payment,Long>{Optional<Payment> findByEnrollmentId(Long enrollmentId);long countByStatus(String status);}
