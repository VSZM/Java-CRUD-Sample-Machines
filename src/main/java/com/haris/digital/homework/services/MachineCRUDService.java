package com.haris.digital.homework.services;

import java.util.List;

import com.haris.digital.homework.dto.MachineDTO;
import com.haris.digital.homework.exceptions.MissingMachineException;

/**
 * Provides CRUD functionality to Machine entity
 */
public interface MachineCRUDService {

	/**
	 * Fetch all non-deleted Machines ordered by last modified first
	 */
	List<MachineDTO> fetchAll();

	/**
	 * Finds a single Machine.
	 * @param id The id of the Machine
	 * @return The Machine found
	 * @throws MissingMachineException If the Machine is not in the database or deleted.
	 */
	MachineDTO getByID(String id) throws MissingMachineException;

	/**
	 * Creates a new machine.
	 * @param newMachine The machine to be created. Attributes other then name are ignored.
	 * @return The newly created machine with all of it's fields populated
	 * @throws IllegalArgumentException If the name attribute is blank.
	 */
	MachineDTO createMachine(MachineDTO newMachine) throws IllegalArgumentException;

	/**
	 * Updates the Machine. Practically the name can be updated only.
	 * @param updateableMachine
	 * @return The updated machine.
	 * @throws IllegalArgumentException If the name attribute is blank.
	 * @throws MissingMachineException If the Machine is not in the database or deleted.
	 */
	MachineDTO updateMachine(MachineDTO updateableMachine) throws IllegalArgumentException, MissingMachineException;

	/**
	 * Deletes the Machine.
	 * @param id The id of the Machine
	 * @throws MissingMachineException If the Machine is not in the database or deleted.
	 */
	void deleteMachine(String id) throws MissingMachineException;

}
