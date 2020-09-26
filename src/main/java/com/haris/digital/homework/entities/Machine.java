package com.haris.digital.homework.entities;


import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import com.haris.digital.homework.dto.MachineDTO;

import lombok.*;

@Entity
@Table(name = "MACHINE")
@Builder
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
public class Machine implements Serializable {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(
		name = "UUID",
		strategy = "org.hibernate.id.UUIDGenerator"
	)
	@EqualsAndHashCode.Include()
	@Column(name = "id", updatable = false, nullable = false)
	private String id;
	@Column(name = "name", nullable = false)
	private String name;
	private Boolean isDeleted = false;

	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;


	@PrePersist
	protected void onCreate() {
		createdAt = LocalDateTime.now();
		updatedAt = LocalDateTime.now();
	}

	@PreUpdate
	protected void onUpdate() {
		updatedAt = LocalDateTime.now();
	}

	public static Machine fromDTO(MachineDTO dto){
		Machine m = new Machine();
		m.setName(dto.getName());

		return m;
	}



}
