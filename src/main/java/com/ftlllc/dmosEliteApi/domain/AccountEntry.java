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
@Table(name = "account_entry")
@NamedQuery(name = "AccountEntry.findAll", query="select a from AccountEntry a")
public class AccountEntry
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer accountEntryId;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "status")
    private String status;
    // ^^ todo I don't like this... i want status to be an enum

    @Column(name = "order_id")
    private BigInteger orderId;

    @Column(name = "group_id")
    private BigInteger groupId;

    @Column(name = "create_date")
    private LocalDate createDate;

    @ManyToOne
    @JoinColumn(name="rental_booth_id")
    private RentalBooth rentalBooth;
}
