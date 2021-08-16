package com.ftlllc.dmosEliteApi.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "rental_booth")
@NamedQuery(name = "RentalBooth.findAll", query="select r from RentalBooth r")
public class RentalBooth
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer rentalBoothId;

    @Column(name = "rental")
    private String rental;

    @Column(name = "fee")
    private BigDecimal fee;
    // ^^ todo do i want bigDecimal

    @Column(name = "paid")
    private String paid;

    @Column(name = "order_id")
    private BigInteger orderId;

    @Column(name = "group_id")
    private BigInteger groupId;

    @Column(name = "create_date")
    private LocalDate crate_date;

}

