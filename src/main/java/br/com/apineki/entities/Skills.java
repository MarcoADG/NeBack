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
@Table(name = "skills")
public class Skills {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;

	@Column(name = "imagem", nullable = false)
	private String imagem;
	
	@Column(name = "nome", nullable = false)
	private String nome;
	
	@Column(name = "descricao", nullable = false)
	private String descricao;
	
	@OneToMany(mappedBy = "skills")
	private List<AssociacaoSkill> associacaoSkill;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<AssociacaoSkill> getAssociacaoSkill() {
		return associacaoSkill;
	}

	public void setAssociacaoSkill(List<AssociacaoSkill> associacaoSkill) {
		this.associacaoSkill = associacaoSkill;
	}

	public Skills(Integer id, String imagem, String nome, String descricao, List<AssociacaoSkill> associacaoSkill) {
		super();
		this.id = id;
		this.imagem = imagem;
		this.nome = nome;
		this.descricao = descricao;
		this.associacaoSkill = associacaoSkill;
	}

	public Skills() {

	}

}
