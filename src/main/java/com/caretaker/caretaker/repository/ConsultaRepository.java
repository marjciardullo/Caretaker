package com.caretaker.caretaker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.caretaker.caretaker.model.Consulta;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long>{

	@Query(value = "select * from tb_consulta" 
			+"  Where usuario_pk_id = ?1", nativeQuery = true)
	List<Consulta> findByUser(Long id);
}
