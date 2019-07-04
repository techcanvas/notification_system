package com.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entity.PackInfo;
@Repository
public interface PackInfoRepository extends JpaRepository<PackInfo, Integer> {

}
