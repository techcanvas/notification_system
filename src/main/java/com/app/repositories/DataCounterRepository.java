package com.app.repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.app.entity.DataCounter;
@Repository
public interface DataCounterRepository extends CrudRepository<DataCounter, Integer> {

	public DataCounter findByClient(String client);


}
