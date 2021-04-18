package com.petke.budgeting.repository;

import com.petke.budgeting.model.Registry;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface RegistryRepository extends CrudRepository<Registry, String> {

    List<Registry> findByUserId(String userId);
    Registry findByIdAndUserId(String id, String userId);

}
