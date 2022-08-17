package com.djain.microservice.controller;


import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.djain.microservice.model.Emp;
import com.djain.microservice.repository.EmpRepository;
import com.djain.microservice.service.EmpService;

@RestController
@RequestMapping("/emprest")
public class EmpController {
	
	@Autowired
	EmpService empServ;
	
	@Autowired
	EmpRepository empRepo;
	
	Log log = LogFactory.getLog("EmpController");
	
	@RequestMapping(value={"/create",""}, method=RequestMethod.POST)
	public Emp createEmp(@RequestBody Emp emp) throws Exception {
	    return empServ.createEmp(emp);
	}

	@RequestMapping(value={"/read",""}, method=RequestMethod.GET)  // or just give @GetMapping("/rest)
	public List<Emp> readEmps() {
		log.info("emp controller get called");
	    return empServ.getEmployees();
	}
	
	@RequestMapping(value="/update/{empId}", method=RequestMethod.PUT)
	public Emp updateEmp(@PathVariable(value = "empId") Long id, @RequestBody Emp empDetails) {
	    return empServ.updateEmployee(id, empDetails);
	}
	
	@RequestMapping(value="/delete/{empId}", method=RequestMethod.DELETE)
	public void deleteEmps(@PathVariable(value = "empId") Long id) {
	    empServ.deleteEmployee(id);
	}
	
	@GetMapping("/empdetails/{val}")
	public List<Emp> retrievedetails(@PathVariable String val) {
		log.info("inside exchange 1");
		List<Emp> ls = empRepo.getempdetails(val);
		return ls;
	}
}
