package com.haris.digital.homework.services;

import java.util.*;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.haris.digital.homework.dto.MachineDTO;
import com.haris.digital.homework.entities.Machine;
import com.haris.digital.homework.exceptions.MissingMachineException;
import com.haris.digital.homework.repository.MachineRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class MachineCRUDServiceImpl implements MachineCRUDService{

	private final MachineRepository machineRepository;


	@Override
	public List<MachineDTO> fetchAll() {
		var allMachines = machineRepository.findByIsDeletedOrderByUpdatedAtDesc(false);
		return allMachines.stream()
					.map(MachineDTO::fromEntity)
					.collect(Collectors.toList());
	}

	@Override
	public MachineDTO getByID(String id) throws MissingMachineException {
		if(id == null){
			throw new MissingMachineException(String.format("No machine found by the ID: |%s|", id));
		}
		var machine = machineRepository.findById(id);

		if(!machine.isPresent()){
			throw new MissingMachineException(String.format("No machine found by the ID: |%s|", id));
		}
		if(machine.get().getIsDeleted()){
			log.warn("Attempted access of deleted machine. ID: |{}|", id);
			throw new MissingMachineException(String.format("No machine found by the ID: |%s|", id));
		}

		return MachineDTO.fromEntity(machine.get());
	}

	@Override
	public MachineDTO createMachine(MachineDTO newMachine) throws IllegalArgumentException {
		if(StringUtils.isBlank(newMachine.getName())){
			log.warn("Attempted machine creation with incorrect name: |{}|", newMachine.getName());
			throw new IllegalArgumentException("Missing name");
		}

		var machineEntity = Machine.fromDTO(newMachine);
		var savedMachine = machineRepository.save(machineEntity);
		log.info("Created new Machine |{}|", savedMachine);
		return MachineDTO.fromEntity(savedMachine);
	}

	@Override
	public MachineDTO updateMachine(MachineDTO updateableMachine)
		throws IllegalArgumentException, MissingResourceException {
		if(StringUtils.isBlank(updateableMachine.getName())){
			log.warn("Attempted machine update to incorrect name: |{}|", updateableMachine.getName());
			throw new IllegalArgumentException("Missing name");
		}

		var machineEntity = machineRepository.findById(updateableMachine.getId());
		if(!machineEntity.isPresent() || machineEntity.get().getIsDeleted()){
			if(machineEntity.isPresent()){
				log.warn("Attempted update of deleted machine. ID: |{}|", updateableMachine.getId());
			}

			throw new MissingMachineException(String.format("No machine found by the ID: |%s|", updateableMachine.getId()));
		}

		machineEntity.get().setName(updateableMachine.getName());
		var savedMachine = machineRepository.save(machineEntity.get());
		var savedMachineDTO = MachineDTO.fromEntity(savedMachine);
		log.info("Updated Machine from |{}}| to |{}|", updateableMachine, savedMachineDTO);
		return savedMachineDTO;
	}

	@Override
	public void deleteMachine(String id) throws MissingResourceException {
		var machineEntity = machineRepository.findById(id);
		if(!machineEntity.isPresent()){


			throw new MissingMachineException(String.format("No machine found by the ID: |%s|", id));
		}

		if(machineEntity.get().getIsDeleted()){
			log.warn("Attempted delete of already deleted machine. Ignoring. ID: |{}|", id);
			return;
		}

		machineEntity.get().setIsDeleted(true);
		machineRepository.save(machineEntity.get());
		log.info("Soft-deleted of machine |{}|", id);
	}
}
