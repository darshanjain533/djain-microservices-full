package com.djain.microservice.detail.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/detail")
public class DetailController {
	@Autowired
	private Environment env;
	
	@Value("${name.value.property : Djain}")  //Djain will be used if name.value.property is not found.
	private String myvar;
	
	@Value("${profile.message: defProfMsg}")  //Djain will be used if name.value.property is not found.
	private String profmsg;
	
	@Value("${java.home}")  //gets System Environment variable.
	private String javaHome;
	
	@Value("#{${student.hobbies}}") //to read map from property file
	private Map<String, List<String>> hobbies;
	
	@Value("#{systemProperties}") 	// System properties in a map
	private Map<String, String> properties;
	
	@Value("#{systemProperties['user.name']}")  //gets username from System propert Map
	private String userName;
	
	@Value("#{'${student.booksRead}'.split(',')}")
	private List<String> booksRead;
	
	private static final Logger logger = LoggerFactory.getLogger(DetailController.class);
	Log log = LogFactory.getLog(DetailController.class);
	
	
	@RequestMapping(value={"/employees","","/empdata"}, method=RequestMethod.GET)
	public String createEmployee(@RequestParam(defaultValue = "testdata", name = "id1") String id1, @RequestParam(required = false, name = "id2") String id2) {
		logger.info("inside create employee - logger");
		log.info("inside create employee - log");
		log.info("reading myvars:::"+myvar);
		log.info("java home iss:::"+javaHome);
		log.info("application.property value read is:::"+env.getProperty("name.value.property"));
		log.info(" Map is:::"+hobbies.get("indoor"));
		log.info(" list is:::"+booksRead.get(0));
		log.info(" loading from app-dev.prop is:::"+profmsg);
		log.info(" loading from app-dev.prop is:::"+env.getProperty("profile.message"));
		
		return "Return of test data with id1 as:::"+id1+" and id2 as:::"+id2;	}
}
