package br.edu.ifrn.crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.edu.ifrn.crud.dominio.Equipamento;


public interface EquipamentoRepository extends JpaRepository<Equipamento, Integer> {
	
	@Query("select u from Equipamento u where u.ip like %:ip% " +
			"and u.setor like %:setor% ")
	List<Equipamento> findByIpAndSetor(@Param("ip")String ip,
							@Param("setor") String setor);
}
