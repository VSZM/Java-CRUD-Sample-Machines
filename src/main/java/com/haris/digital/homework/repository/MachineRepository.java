package com.haris.digital.homework.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.haris.digital.homework.entities.Machine;

@Repository
public interface MachineRepository extends CrudRepository<Machine, String> {

	List<Machine> findByIsDeletedOrderByUpdatedAtDesc(boolean isDeleted);

}
