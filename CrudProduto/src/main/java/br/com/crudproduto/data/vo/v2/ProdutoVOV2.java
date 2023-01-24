package br.com.crudproduto.data.vo.v2;

import java.io.Serializable;

/*
A segunda VO do código.

uma VO contém os dados que o sistema deseja mostrar referente a uma entidade sem expor a mesma,
neste caso a VO é uma cópia dos dados que podem ser mostrados ao  usuário.

A vantagem de se utilizar VOs é a segurança, pois os dados reais do sistema não são expostos e assim
o usuário não pode acessa-los e manipula-los, ele apenas tem acesso a VO do dado que deseja ver.

Com as VOs também é possível versionar endPoints, como é  caso desta VO que foi criada para versionar o endPoint de create,
Veremos mais sobre versionamento de endPoints no createV2 de CrudProdutoController e no ProdutoService.
 */

public class ProdutoVOV2 implements Serializable {
	
	private static final long serialVersionUID = 1L;
	

	private Long id;
	
	private String nome;
	
	private Double valor;
	
	private String tipo;
	
	private String marca;
	
	public ProdutoVOV2() {
		
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

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}
	
	
	
	

}
