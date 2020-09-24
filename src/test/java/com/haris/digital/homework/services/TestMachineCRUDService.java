package com.haris.digital.homework.services;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.MissingResourceException;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import com.haris.digital.homework.TestWithSpringCtx;
import com.haris.digital.homework.dto.MachineDTO;


public class TestMachineCRUDService extends TestWithSpringCtx {


	@Test
	public void testFetchAllWorks(){
		var allNonDeleted = machineCRUDService.fetchAll();

		assertTrue(CollectionUtils.isNotEmpty(allNonDeleted));
		assertEquals(4, allNonDeleted.size());
	}
	
	@Test
	public void testGetByIdWorksNormally(){
		var machine = machineCRUDService.getByID(ID5);
		assertNotNull(machine);
		assertEquals("Test 5", machine.getName());
	}
	
	@Test
	public void testGetByIdFailsAsExpected(){
		assertThrows(MissingResourceException.class, () -> {
			machineCRUDService.getByID("Non Existent ID");
		});
		assertThrows(MissingResourceException.class, () -> {
			machineCRUDService.getByID(DELETED_ID);
		});
	}
	
	@Test
	public void testCreateMachineWorksNormally(){
		var now = LocalDateTime.now();

		var newMachine = new MachineDTO();
		newMachine.setName("New Machine");
		newMachine = machineCRUDService.createMachine(newMachine);
		assertNotNull(newMachine);
		assertTrue(StringUtils.isNotBlank(newMachine.getId()));
		assertEquals("New Machine", newMachine.getName());
		assertNotNull( newMachine.getCreatedAt());

		var machineEntity = machineRepository.findById(newMachine.getId());
		assertTrue(machineEntity.isPresent());
		assertTrue(machineEntity.get().getCreatedAt().isAfter(now));
		assertTrue(machineEntity.get().getUpdatedAt().isAfter(now));
	}

	@Test
	public void testCreateMachineFailsAsExpected(){
		assertThrows(IllegalArgumentException.class, () -> {
			var newMachine = new MachineDTO();
			machineCRUDService.createMachine(newMachine);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			var newMachine = new MachineDTO();
			newMachine.setName(" ");
			machineCRUDService.createMachine(newMachine);
		});
	}

	@Test
	public void testUpdateMachineWorksNormally(){
		var now = LocalDateTime.now();

		var machine = machineCRUDService.getByID(ID5);
		assertNotNull(machine);
		machine.setName("New Name");
		machine = machineCRUDService.updateMachine(machine);
		assertEquals("New Name", machine.getName());

		var machineEntity = machineRepository.findById(ID5);
		assertTrue(machineEntity.isPresent());
		assertEquals("New Name", machineEntity.get().getName());
		assertTrue(machineEntity.get().getUpdatedAt().isAfter(now));
	}

	@Test
	public void testUpdateMachineFailsAsExpected(){
		assertThrows(IllegalArgumentException.class, () -> {
			var machine = machineCRUDService.getByID(ID5);
			assertNotNull(machine);
			machine.setName(" ");
			machineCRUDService.updateMachine(machine);

		});
		assertThrows(IllegalArgumentException.class, () -> {
			var machine = machineCRUDService.getByID(ID5);
			assertNotNull(machine);
			machine.setName(null);
			machineCRUDService.updateMachine(machine);
		});
		assertThrows(MissingResourceException.class, () -> {
			var machine = new MachineDTO();
			machine.setName("New Name");
			machine.setId("Not an ID");
			machineCRUDService.updateMachine(machine);
		});
		assertThrows(MissingResourceException.class, () -> {
			var machine = machineCRUDService.getByID(DELETED_ID);
			assertNotNull(machine);
			machine.setName("New Name");
			machineCRUDService.updateMachine(machine);
		});
	}

	@Test
	public void testDeleteMachineWorksNormally(){
		machineCRUDService.deleteMachine(ID5);

		var machineEntity = machineRepository.findById(ID5);
		assertTrue(machineEntity.isPresent());
		assertTrue(machineEntity.get().getIsDeleted());
	}
}
