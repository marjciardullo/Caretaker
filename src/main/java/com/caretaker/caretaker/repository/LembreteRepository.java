package com.caretaker.caretaker.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.caretaker.caretaker.model.Lembrete;

@Repository
public interface LembreteRepository extends JpaRepository<Lembrete, Long>{
	
	@Query(value = "select * from tb_lembrete" 
			+"  Where hr_lembrete = ?1 AND dt_lembrete = ?2 AND"
			+" medicamento_pk_id = ?3", nativeQuery = true)
	Optional<Lembrete> findByIds(String hora, String dt, Long medicamento);
	
	@Modifying
	@Query(value = "delete from tb_lembrete" 
			+"  where hr_lembrete = ?1 AND dt_lembrete = ?2 AND "
			+"  medicamento_pk_id = ?3", nativeQuery = true)
	void deleteByIds(String hora, String dt, Long medicamento);
}
