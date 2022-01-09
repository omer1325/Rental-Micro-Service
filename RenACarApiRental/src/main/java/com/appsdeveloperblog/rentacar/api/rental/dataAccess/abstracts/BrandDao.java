package com.appsdeveloperblog.rentacar.api.rental.dataAccess.abstracts;

import com.appsdeveloperblog.rentacar.api.rental.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandDao extends JpaRepository<Brand, Integer> {

}
