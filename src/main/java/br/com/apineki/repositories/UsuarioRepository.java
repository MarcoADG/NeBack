package br.com.apineki.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.apineki.entities.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	Usuario findByLogin(String login);
}
