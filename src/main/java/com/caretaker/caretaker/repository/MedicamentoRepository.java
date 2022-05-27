package com.caretaker.caretaker.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.caretaker.caretaker.model.Medicamento;

@Repository
public interface MedicamentoRepository extends JpaRepository<Medicamento, Long>{
	@Query(value = "select * from tb_medicamento" 
			+"  Where pk_id= ?1 AND usuario_pk_id = ?2 ", nativeQuery = true)
	Optional<Medicamento> findByIds(Long medicamento, Long usuario);
	
	@Query(value = "delete from tb_medicamento" 
			+"  Where pk_id= ?1 AND usuario_pk_id = ?2 ", nativeQuery = true)
	void deleteByIds(Long medicamento, Long usuario);
	
	@Query(value = "select * from tb_medicamento" 
			+"  Where usuario_pk_id = ?1 ", nativeQuery = true)
	List<Medicamento> findByUser(Long usuario);
	
	
}
