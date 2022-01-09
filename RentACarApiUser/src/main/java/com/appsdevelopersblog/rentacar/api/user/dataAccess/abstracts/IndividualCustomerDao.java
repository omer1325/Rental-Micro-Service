package com.appsdevelopersblog.rentacar.api.user.dataAccess.abstracts;

import com.appsdevelopersblog.rentacar.api.user.entities.IndividualCustomer;
import org.springframework.data.jpa.repository.JpaRepository;



public interface IndividualCustomerDao extends JpaRepository<IndividualCustomer, String> {
	
	boolean existsByEmail(String email);
}
