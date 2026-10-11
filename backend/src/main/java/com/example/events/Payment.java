package com.example.events;
import jakarta.persistence.*;import java.math.BigDecimal;import java.time.LocalDateTime;
@Entity @Table(name="payments") public class Payment { @Id @GeneratedValue(strategy=GenerationType.IDENTITY) public Long id; @OneToOne(optional=false) public Enrollment enrollment; @Column(nullable=false,precision=10,scale=2) public BigDecimal amount; @Column(nullable=false) public String status="PENDING"; @Column(unique=true) public String reference; @Column(nullable=false) public LocalDateTime createdAt=LocalDateTime.now(); public Payment(){} }
