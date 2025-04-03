package com.themindfulmountains.business.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "property")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class) // Register JSONB type
public class Property {
    private boolean active;
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "property_id", updatable = false, nullable = false)
    private String propertyId;
    private String name;
    private String location;
    @Column(name = "contact_no")
    private String contactNo;
    @Column(name = "email_id")
    private String emailId;
    @Type(type = "jsonb") // Enable JSONB storage
    @Column(name = "image_urls", columnDefinition = "jsonb")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> imageUrls;
}
