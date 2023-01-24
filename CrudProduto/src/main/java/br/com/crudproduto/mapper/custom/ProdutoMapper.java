package br.com.crudproduto.mapper.custom;

import org.springframework.stereotype.Service;

import br.com.crudproduto.data.vo.v2.ProdutoVOV2;
import br.com.crudproduto.model.Produto;

/*
Faz a mesma função do Dozer, só que este Mapper foi construido manualmente para fazer
a conversão


a anotação @Service indica que a classe ProdutoMapper pode fazer parte de um processo de injeção de
dependências, caso a mesma classe seja utilizada em outra classe junto da anotação
@Autowired esta será injetada em tempo de execução.


"injetar" significa que a classe será inicializada automaticamente sem a necessidade de se utilizar o new.
 */
@Service
public class ProdutoMapper {
	
	
	public Produto convertVoToEntity(ProdutoVOV2 produto) {
		
		var entity = new Produto();
		entity.setId(produto.getId());
		entity.setNome(produto.getNome());
		entity.setTipo(produto.getTipo());
		entity.setValor(produto.getValor());
		
		return entity;
	}
	
	public ProdutoVOV2 convertEntityToVO(Produto produto) {
		
		var entity = new ProdutoVOV2();
		entity.setId(produto.getId());
		entity.setNome(produto.getNome());
		entity.setTipo(produto.getTipo());
		entity.setValor(produto.getValor());
		entity.setMarca("nike");
		
		return entity;
	}
	

}
