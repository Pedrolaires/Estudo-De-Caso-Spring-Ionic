package br.pedro.cursomc.domain;

import java.io.Serializable;
import java.util.Objects;

public class Categoria implements Serializable {  // <- permite converter os objetos da classe para uma sequência de Bytes (para gravar em arquivos, etc)
	private static final long serialVersionUID = 1L; // /\


	private Integer id;
	private String nome;
	
	
	public Categoria() {
		super();
	}
	
	public Categoria(Integer id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	
	
	// usados para comparar objetos através do conteúdo em vez de comparar por ponteiro de memória.
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Categoria other = (Categoria) obj;
		return Objects.equals(id, other.id);
	}

	
	
	
	
	
	
}
