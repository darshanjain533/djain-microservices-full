package com.djain.microservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.djain.microservice.model.Emp;

@Repository
public interface EmpRepository extends JpaRepository<Emp, Long>{

	List<Emp> getempdetails(String firstname);
}
