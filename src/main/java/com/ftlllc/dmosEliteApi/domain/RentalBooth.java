package com.ftlllc.dmosEliteApi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "rental_booth")
//@NamedQuery(name = "RentalBooth.findAll", query="select r from RentalBooth r")
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
    private Boolean paid;

    @Column(name = "order_id")
    private Integer orderId;

    @JsonIgnore
    @OneToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name="order_id", referencedColumnName = "order_id", insertable=false, updatable=false)
    private AnomalyMap anomalyMap;

    @Column(name = "group_id")
    private BigInteger groupId;

    @Column(name = "create_date")
    private LocalDate createDate;

    @Column(name = "anomaly_score")
    private BigDecimal anomalyScore;

    @JsonIgnore
    @OneToMany(mappedBy="rentalBooth")
    private List<AccountEntry> accountEntries;
}

