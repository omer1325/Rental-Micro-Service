package com.appsdeveloperblog.rentacar.api.rental.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="cars")
public class Car {

    @Id

    @Column(name="car_id")
    private String carId;
    @Column(name="model_year")
    private String modelYear;
    @Column(name="daily_price")
    private double dailyPrice;
    @Column(name="description")
    private String description;

    @ManyToOne
    @JoinColumn(name="brand_id")
    private Brand brand;

    @ManyToOne
    @JoinColumn(name="color_id")
    private Color color;
}
