package br.com.workflow.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import lombok.*;

@Entity
@Table(name = "USERS")
@Getter 
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq_gen")
    @SequenceGenerator(
        name = "user_seq_gen",
        sequenceName = "SQ_USER_ID",
        allocationSize = 1
    )
    private Integer id;
    
    @Column(name = "LOGIN", nullable = false, unique = true)
    private String login;

    @Column(name = "NAME", nullable = false)
    private String name;
}