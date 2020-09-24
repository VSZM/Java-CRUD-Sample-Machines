package com.haris.digital.homework.entities;


import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.*;

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
	private Boolean isDeleted;

	@CreationTimestamp
	private LocalDateTime createdAt;
	@UpdateTimestamp
	private LocalDateTime updatedAt;


}
