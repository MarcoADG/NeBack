package br.com.apineki.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.apineki.entities.Usuario;
import br.com.apineki.repositories.UsuarioRepository;

@Service
public class UsuarioDetalheService implements UserDetailsService {

	@Autowired
	UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByLogin(username);
		if (usuario == null) {
			throw new RuntimeException();
		}
		return new UsuarioDetalhe(usuario);
	}

}
