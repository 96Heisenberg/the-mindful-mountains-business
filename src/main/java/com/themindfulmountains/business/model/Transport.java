package com.themindfulmountains.business.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "transport")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transport {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String transportId;

    private String name;
    private String description;
    private String contactNo;
    private String bookingVia;

    private BigDecimal b2cTariff;
    private BigDecimal b2bTariff;
}
