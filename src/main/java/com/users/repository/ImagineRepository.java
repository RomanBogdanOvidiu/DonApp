package com.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.users.model.Imagine;

@Repository
public interface ImagineRepository extends JpaRepository<Imagine, Integer> {

}
