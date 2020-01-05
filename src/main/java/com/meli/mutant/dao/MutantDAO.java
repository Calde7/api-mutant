package com.meli.mutant.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.meli.mutant.model.Mutant;

public interface MutantDAO extends JpaRepository<Mutant, Long> {

    @Query("SELECT COUNT(m) FROM Mutant m WHERE m.isMutant= :type")
    Long getCount(Boolean type);

}
