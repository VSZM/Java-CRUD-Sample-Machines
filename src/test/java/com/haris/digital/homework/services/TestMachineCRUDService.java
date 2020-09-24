package com.haris.digital.homework.services;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Test;

import com.haris.digital.homework.TestWithSpringCtx;


public class TestMachineCRUDService extends TestWithSpringCtx {


	@Test
	public void testFetchAllWorks(){
		var allNonDeleted = machineCRUDService.fetchAll();

		assertTrue(CollectionUtils.isNotEmpty(allNonDeleted));
		assertEquals(4, allNonDeleted.size());
	}
}
