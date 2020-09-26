package com.haris.digital.homework;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ContextConfiguration;

import com.haris.digital.homework.config.TestConfig;
import com.haris.digital.homework.repository.MachineRepository;
import com.haris.digital.homework.services.MachineCRUDService;

@Transactional
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = {TestConfig.class})
public class TestWithSpringCtx {

	protected String ID5 = "50112233-4455-6677-8899-aabbccddeeff";
	protected String DELETED_ID = "30112233-4455-6677-8899-aabbccddeeff";


	@Autowired
	protected MachineCRUDService machineCRUDService;
	@Autowired
	protected MachineRepository machineRepository;
	@Autowired
	protected EntityManager entityManager;

}
