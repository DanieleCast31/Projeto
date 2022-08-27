package br.edu.ifrn.crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.edu.ifrn.crud.dominio.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Integer>{
	
	@Query("select p from Cidade p where p.nome like %:nome% ")
	List<Cidade> findByNome(@Param("nome") String nome);
}
