package com.djain.microservice.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import com.djain.microservice.model.Emp;
import com.djain.microservice.repository.EmpRepository;

@Service
public class EmpService {

	@Autowired
	EmpRepository empRepo;
	
	@Autowired
	JdbcTemplate jdbcT;
	NamedParameterJdbcTemplate jdbcNT;
	
	@PersistenceContext
	EntityManager em;
	
	Log log = LogFactory.getLog(getClass());
	
	//create employee
	public Emp createEmp(Emp empl) {
		try {
			log.info("Wait Started");
			Thread.sleep(10000); // for async implementation
			log.info("Wait ended");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return empRepo.save(empl);
	}
	
	//READ
	public List<Emp> getEmployees() {
	    return empRepo.findAll();
	}

	//UPDATE
	public Emp updateEmployee(Long empId, Emp employeeDetails) {
		Emp emp = empRepo.findById(empId).get();
		emp.setFirstName(employeeDetails.getFirstName());
		emp.setLastName(employeeDetails.getLastName());
		emp.setEmailId(employeeDetails.getEmailId());
		
		return empRepo.save(emp);				
	}

	//DELETE
	public void deleteEmployee(Long empId) {
	    empRepo.deleteById(empId);
	}
	
}
