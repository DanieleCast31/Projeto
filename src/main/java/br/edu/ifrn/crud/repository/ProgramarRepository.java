package br.edu.ifrn.crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.edu.ifrn.crud.dominio.Programar;


public interface ProgramarRepository extends JpaRepository<Programar, Integer>{
	
	@Query("select p from Programar p where p.setor like %:setor% ")
	List<Programar> findBySetor(@Param("setor")String setor);
}
