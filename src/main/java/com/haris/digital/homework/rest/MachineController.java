package com.haris.digital.homework.rest;

import java.util.List;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.haris.digital.homework.dto.MachineDTO;
import com.haris.digital.homework.services.MachineCRUDService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/machines")
public class MachineController {

	private final MachineCRUDService machineCRUDService;

	@GetMapping
	public ResponseEntity<List<MachineDTO>> getAllMachines(){ //TODO: Add pagination if needed
		var machines = machineCRUDService.fetchAll();
		if(machines.isEmpty()){
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity(machines, HttpStatus.OK);
	}


	@GetMapping("/{id}")
	public ResponseEntity<MachineDTO> getMachine(@PathVariable("id") String id) {
		var machine = machineCRUDService.getByID(id);
		return new ResponseEntity<>(machine, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<MachineDTO> createMachine(@RequestBody MachineDTO machineDTO) {
		var createdMachine = machineCRUDService.createMachine(machineDTO);
		return new ResponseEntity<>(createdMachine, HttpStatus.CREATED);
	}

	@PutMapping()
	public ResponseEntity<MachineDTO> updateMachine(@RequestBody MachineDTO machineDTO) {
		var updatedMachine = machineCRUDService.updateMachine(machineDTO);
		return new ResponseEntity<>(updatedMachine, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteMachine(@PathVariable("id") String id) {
		machineCRUDService.deleteMachine(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
