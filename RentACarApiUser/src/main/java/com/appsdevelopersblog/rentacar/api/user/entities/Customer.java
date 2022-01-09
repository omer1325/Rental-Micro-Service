package com.appsdevelopersblog.rentacar.api.user.entities;


import javax.persistence.Entity;
import javax.persistence.Table;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "customers")
public class Customer extends UserEntity {

}
