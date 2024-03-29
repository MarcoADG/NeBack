package br.com.apineki.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.apineki.entities.Usuario;

public class UsuarioDetalhe implements UserDetails {
	
	
	private Usuario usuario;

	public UsuarioDetalhe(Usuario usuario){
		this.usuario= usuario;
	}
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> lista = new ArrayList<>();
		lista.add(new SimpleGrantedAuthority("ADMIN"));
		return lista;
	}

	public Integer getId() {
		return usuario.getId();
	}
	
	@Override
	public String getPassword() {
		return usuario.getSenha();
	}

	@Override
	public String getUsername() {
		return usuario.getLogin();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
