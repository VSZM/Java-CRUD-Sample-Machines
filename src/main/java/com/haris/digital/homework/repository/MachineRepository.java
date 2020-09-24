package com.haris.digital.homework.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.haris.digital.homework.entities.Machine;

@Repository
public interface MachineRepository extends CrudRepository<Machine, String> {


}
