package com.themindfulmountains.business.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "query_itinerary")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QueryItinerary {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "itinerary_id")
    private String itineraryId;

    @Column(name = "query_pdf_link")
    private String queryPdfLink;

    @Column(name = "no_of_adults")
    private Integer noOfAdults;

    @Column(name = "no_of_childs")
    private Integer noOfChilds;

    @Column(name = "b2b_total_cost")
    private BigDecimal b2bTotalItineraryCost;

    @Column(name = "b2c_total_cost")
    private BigDecimal b2cTotalItineraryCost;

    /* ================= RELATIONS ================= */

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "query", cascade = CascadeType.ALL)
    private List<DayItinerary> dayItineraryList;
}
