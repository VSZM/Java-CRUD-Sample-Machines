package com.haris.digital.homework.dto;


import java.time.LocalDateTime;

import com.haris.digital.homework.entities.Machine;

import lombok.*;

/**
 * DTO for Machine. Let's assume the last update date is secret so the DTO makes sense :)
 */
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class MachineDTO {

	@EqualsAndHashCode.Include()
	private String id;
	private String name;
	private LocalDateTime createdAt;

	public static MachineDTO fromEntity(Machine entity){
		MachineDTO m = new MachineDTO();
		m.setId(entity.getId());
		m.setName(entity.getName());
		m.setCreatedAt(entity.getCreatedAt());
		return m;
	}
}
