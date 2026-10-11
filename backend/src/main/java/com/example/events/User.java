package com.example.events;
import jakarta.persistence.*;
@Entity @Table(name="users") public class User { @Id @GeneratedValue(strategy=GenerationType.IDENTITY) public Long id; @Column(nullable=false) public String fullName; @Column(nullable=false,unique=true) public String email; @Column(nullable=false) public String phone; @Column(nullable=false) public String password; @Column(nullable=false) public String role="USER"; public User(){} }
