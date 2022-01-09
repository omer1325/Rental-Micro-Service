package com.appsdevelopersblog.rentacar.api.user.dataAccess.abstracts;

import com.appsdevelopersblog.rentacar.api.user.entities.CorporateCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CorporateCustomerDao extends JpaRepository<CorporateCustomer, String> {
	
	boolean existsByEmail(String email);
	boolean existsByTaxNumber(String taxNumber);

}
