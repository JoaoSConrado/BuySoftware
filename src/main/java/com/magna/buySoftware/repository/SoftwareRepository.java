package com.magna.buySoftware.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.magna.buySoftware.entity.SoftwareEntity;

@Repository
public interface SoftwareRepository extends JpaRepository<SoftwareEntity, Long> {

}
