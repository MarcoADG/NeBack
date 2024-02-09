package br.com.apineki.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;

	@Column(name = "login", nullable = false, unique = true)
	private String login;

	@Column(name = "senha", nullable = false)
	private String senha;

	@OneToMany(mappedBy = "usuario")
	private List<AssociacaoSkill> associacaoSkill;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<AssociacaoSkill> getAssociacaoSkill() {
		return associacaoSkill;
	}

	public void setAssociacaoSkill(List<AssociacaoSkill> associacaoSkill) {
		this.associacaoSkill = associacaoSkill;
	}

	public Usuario(Integer id, String login, String senha, List<AssociacaoSkill> associacaoSkill) {
		super();
		this.id = id;
		this.login = login;
		this.senha = senha;
		this.associacaoSkill = associacaoSkill;
	}

	public Usuario() {
	}

}
