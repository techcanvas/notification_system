package com.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entity.DataCounter;
import com.app.entity.PacksDefaultLimit;


@Repository
public interface PacksDefaultLimitRepository extends JpaRepository<PacksDefaultLimit, Integer> {
	public PacksDefaultLimit findByPackid(Long packid);
}
