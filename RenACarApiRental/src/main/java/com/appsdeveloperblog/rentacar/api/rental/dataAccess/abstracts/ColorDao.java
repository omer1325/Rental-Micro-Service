package com.appsdeveloperblog.rentacar.api.rental.dataAccess.abstracts;

import com.appsdeveloperblog.rentacar.api.rental.entities.Color;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColorDao extends JpaRepository<Color, Integer>{

}
