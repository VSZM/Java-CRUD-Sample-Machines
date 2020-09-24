package com.haris.digital.homework.services;

import java.util.*;

import org.springframework.stereotype.Service;

import com.haris.digital.homework.dto.MachineDTO;

import lombok.RequiredArgsConstructor;

/**
 * Provides CRUD functionality to Machine entity
 */
public interface MachineCRUDService {

	/**
	 * Fetch all non-deleted Machines
	 */
	List<MachineDTO> fetchAll();

	/**
	 * Finds a single Machine.
	 * @param uuid The id of the Machine
	 * @return The Machine found
	 * @throws MissingResourceException If the Machine is not in the database or deleted.
	 */
	MachineDTO getByID(String uuid) throws MissingResourceException;

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
	 * @throws MissingResourceException If the Machine is not in the database or deleted.
	 */
	MachineDTO updateMachine(MachineDTO updateableMachine) throws IllegalArgumentException, MissingResourceException;

	/**
	 * Deletes the Machine.
	 * @param uuid The id of the Machine
	 * @throws MissingResourceException If the Machine is not in the database or deleted.
	 */
	void deleteMachine(String uuid) throws MissingResourceException;

}
