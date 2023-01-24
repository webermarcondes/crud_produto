package br.com.crudproduto.data.vo.v1;

import java.io.Serializable;


/*
A primeira VO do código.

uma VO contém os dados que o sistema deseja mostrar referente a uma entidade sem expor a mesma,
neste caso a VO é uma cópia dos dados que podem ser mostrados ao  usuário.

A vantagem de se utilizar VOs é a segurança, pois os dados reais do sistema não são expostos e assim
o usuário não pode acessa-los e manipula-los, ele apenas tem acesso a VO do dado que deseja ver.

Com as VOs também é posível versionar o endPoints, veremos mais na VO2 de produto e no createV2 do CrudProdutoController e
do ProdutoService.
 */

public class ProdutoVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	

	private Long id;
	
	private String nome;
	
	private Double valor;
	
	private String tipo;
	
	public ProdutoVO() {
		
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
