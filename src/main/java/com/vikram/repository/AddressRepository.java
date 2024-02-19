package com.vikram.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vikram.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{

}
