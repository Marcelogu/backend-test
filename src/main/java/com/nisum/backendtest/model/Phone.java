package com.nisum.backendtest.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="phone")
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private Integer countryCode;
    private Integer cityCode;
    private Integer number;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
