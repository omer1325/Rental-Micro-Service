package com.appsdevelopersblog.rentacar.api.user.dataAccess.abstracts;

import com.appsdevelopersblog.rentacar.api.user.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerDao extends JpaRepository<Customer, String>{

}
