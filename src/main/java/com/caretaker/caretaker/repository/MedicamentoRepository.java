package com.caretaker.caretaker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.caretaker.caretaker.model.Medicamento;

@Repository
public interface MedicamentoRepository extends JpaRepository<Medicamento, Long>{

}
