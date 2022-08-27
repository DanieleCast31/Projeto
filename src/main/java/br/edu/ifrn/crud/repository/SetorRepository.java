package br.edu.ifrn.crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.edu.ifrn.crud.dominio.Setor;

public interface SetorRepository extends JpaRepository<Setor, Integer>{
	
	@Query("select p from Setor p where p.nome like %:nome% ")
	List<Setor> findByNome(@Param("nome") String nome);
}
