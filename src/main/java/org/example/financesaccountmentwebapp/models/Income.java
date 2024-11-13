package org.example.financesaccountmentwebapp.models;

import jakarta.persistence.*;

@Entity
@Table(name="income")
public class Income {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



}
