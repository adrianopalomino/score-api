package com.adrianopalomino.scoreapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adrianopalomino.scoreapi.domain.AnaliseCredito;

@Repository
public interface AnaliseCreditoRepository extends JpaRepository<AnaliseCredito, Long> {

}
