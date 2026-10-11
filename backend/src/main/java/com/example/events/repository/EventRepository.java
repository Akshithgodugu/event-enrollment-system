package com.example.events.repository;import com.example.events.Event;import org.springframework.data.jpa.repository.JpaRepository;import java.util.List;
public interface EventRepository extends JpaRepository<Event,Long>{List<Event> findByStatusOrderByDateTimeAsc(String status);}
