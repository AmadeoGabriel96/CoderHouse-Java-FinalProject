package com.MoyanoManchon.FinalProject.repository;

import com.MoyanoManchon.FinalProject.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Override
    Optional<Client> findById(Long aLong);

}
