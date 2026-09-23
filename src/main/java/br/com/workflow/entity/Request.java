package br.com.workflow.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.*;

@Entity 
@Table(name = "REQUEST")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Request {

    @Id 
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "request_seq_gen")
    @SequenceGenerator(
        name = "request_seq_gen",
        sequenceName = "SQ_REQUEST_ID",
        allocationSize = 1
    )
    private Integer id;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "DESCRIPTION")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", nullable = false)
    private RequestStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "CREATEDBY", 
        nullable = false,
        referencedColumnName = "LOGIN"
    )
    private User createdBy;

    @Column(name = "CREATEDAT", nullable = false)
    private LocalDateTime createdAt;

    @ManyToMany (fetch = FetchType.LAZY)
    @JoinTable(
        name = "REQUESTUSER",
        joinColumns = @JoinColumn(name = "REQUESTID"),
        inverseJoinColumns = @JoinColumn(name = "USERID") 
    )
    @Builder.Default
    private List<User> additionalRequesters = new ArrayList<>();

    @PrePersist
    public void prePresist(){
        if(this.createdAt == null){
            this.createdAt = LocalDateTime.now();
        }
        if(this.status == null){
            this.status = RequestStatus.CREATED;
        }
    }
}