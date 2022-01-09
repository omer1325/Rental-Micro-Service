package com.appsdeveloperblog.rentacar.api.rental.dataAccess.abstracts;

import java.util.List;

import com.appsdeveloperblog.rentacar.api.rental.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarDao extends JpaRepository<Car, String>{
}
