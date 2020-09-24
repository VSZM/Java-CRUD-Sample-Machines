package com.haris.digital.homework.services;

import java.util.*;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.haris.digital.homework.dto.MachineDTO;
import com.haris.digital.homework.repository.MachineRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MachineCRUDServiceImpl implements MachineCRUDService{

	private final MachineRepository machineRepository;
	//private final ModelMapper modelMapper;


	@Override
	public List<MachineDTO> fetchAll() {
		return null;
	}

	@Override
	public MachineDTO getByID(String uuid) throws MissingResourceException {
		return null;
	}

	@Override
	public MachineDTO createMachine(MachineDTO newMachine) throws IllegalArgumentException {
		return null;
	}

	@Override
	public MachineDTO updateMachine(MachineDTO updateableMachine)
		throws IllegalArgumentException, MissingResourceException {
		return null;
	}

	@Override
	public void deleteMachine(String uuid) throws MissingResourceException {

	}
}
