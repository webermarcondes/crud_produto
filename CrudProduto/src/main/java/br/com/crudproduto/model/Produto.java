package br.com.crudproduto.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/*
Classe de modelo de um dado que é salvo em banco.

O que as anotações abaixo são?:

@Entity: indica que a classe produto será uma tabela do banco;

@Table(name) define o nome personalizado da tabela (seu uso é opcional, 
caso não seja usada o nome da classe é considerado);

@Id: indica que o atributo será a chave primária da tabela;

@GeneratedValue(strategy): especifica a estratégia de geração do código do Id;

@Column: indica que o atributo da classe será armazenado em uma coluna da tabela;

 */
@Entity
@Table(name="produto")
public class Produto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) // nesse caso a geração é sequencial;
	private Long id;
	
	@Column(nullable=false, length=80)  //nullable true define que o campo na tabela é obrigatório (não nulo
	private String nome;                // já nullable false define que o campo tem seu preenchimento opcional.
	
	@Column(nullable=false)
	private Double valor;
	
	@Column(nullable=false, length=80)
	private String tipo;
	
	public Produto() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	

}
