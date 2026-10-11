package com.example.events.repository;import com.example.events.Enrollment;import org.springframework.data.jpa.repository.JpaRepository;import java.util.List;
public interface EnrollmentRepository extends JpaRepository<Enrollment,Long>{boolean existsByUserIdAndEventId(Long userId,Long eventId);List<Enrollment> findByUserIdOrderByCreatedAtDesc(Long userId);}
