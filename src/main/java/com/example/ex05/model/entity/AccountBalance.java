package com.example.ex05.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class AccountBalance {
    @Id
    private String username;
    private Double cashAvailable;
}