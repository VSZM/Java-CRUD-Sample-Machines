package com.haris.digital.homework;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ContextConfiguration;

import com.haris.digital.homework.config.TestConfig;
import com.haris.digital.homework.services.MachineCRUDService;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = {TestConfig.class})
public class TestWithSpringCtx {

	@Autowired
	protected MachineCRUDService machineCRUDService;


}
