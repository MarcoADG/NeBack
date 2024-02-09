package br.com.apineki.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.apineki.entities.AssociacaoSkill;



public interface AssociacaoSkillRepository extends JpaRepository<AssociacaoSkill, Integer> {

	@Query("SELECT a FROM AssociacaoSkill a WHERE a.usuario.id = :usuarioId")
    List<AssociacaoSkill> findByUsuarioId(@Param("usuarioId") Integer usuarioId);
}
