package br.com.crudproduto.services;


import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.crudproduto.data.vo.v1.ProdutoVO;
import br.com.crudproduto.data.vo.v2.ProdutoVOV2;
import br.com.crudproduto.exceptions.NotNumericException;
import br.com.crudproduto.exceptions.ResourceNotFoundException;
import br.com.crudproduto.mapper.DozerMapper;
import br.com.crudproduto.mapper.custom.ProdutoMapper;
import br.com.crudproduto.model.Produto;
import br.com.crudproduto.repository.ProdutoRepository;
import br.com.crudproduto.verifications.numericValidator;


/*
Uma classe de Service contém toda a interação com o banco, validações, verificações
e tratamento de excecções

A mesma possui a anotação @Service pois é injetada no CrudProdutoController que recebe as requisições
dos clientes e manda para que o service processe.
 */
@Service
public class ProdutoServices {
	
	
	Logger logger = Logger.getLogger(ProdutoServices.class.toString());
	
	@Autowired
	ProdutoRepository repository;
	
	@Autowired
	ProdutoMapper mapper = new ProdutoMapper();
	
	public List<ProdutoVO> findAll() {
		
		logger.info("buscando todos os produtos cadastrados");
		
		return DozerMapper.parseListObjects(repository.findAll(), ProdutoVO.class); 
	}
	
	public ProdutoVO findById(String id) {
		
		if (!numericValidator.isNumeric(id)) {
			throw new NotNumericException("Informe um valor númerico pro ID");
		}
		
		logger.info("buscando o produto de id " + id);
		
		Produto produto = repository.findById(Long.parseLong(id)).orElseThrow(() -> new ResourceNotFoundException("Não existe nenhum recurso com este ID"));
		
		return DozerMapper.parseObject(produto, ProdutoVO.class);
	
	}
	
	public List<ProdutoVO> findByTipo(String tipo) {
		
		return DozerMapper.parseListObjects(repository.findBytipo(tipo), ProdutoVO.class);
	}
	
	public List<ProdutoVO> findByValor(String valor_min, String valor_max) {
		
		List<Produto> produtos = new ArrayList<>();
		
		if (numericValidator.isNumeric(valor_min) && valor_max.equals("none")) {
			valor_max = valor_min;
		}
		
		else if (!numericValidator.isNumeric(valor_min) || !numericValidator.isNumeric(valor_max)) {
			throw new NotNumericException("informe apenas valores númericos");
		}
		
		produtos = repository.findByValor(Double.parseDouble(valor_min), Double.parseDouble(valor_max));
		
		return DozerMapper.parseListObjects(produtos, ProdutoVO.class);
				
	}
	
	public ProdutoVO create(ProdutoVO produto) {
		
		logger.info("criando um produto....");
		
		repository.save(DozerMapper.parseObject(produto, Produto.class));
		
		return produto;
	}
	
	public ProdutoVOV2 createV2(ProdutoVOV2 produto) {
		/*
		 endPoint versionado acessado pelo CrudProdutoController
		 
		 a vantagem do versionamento é fazer com que duas versões de um mesmo
		 endPoint fiquem ativas ao mesmo tempo, até que todos os usuários da versão antiga consigam
		 migrar pra nova.
		 */
		logger.info("criando um produto com a VO2");
		
		repository.save(mapper.convertVoToEntity(produto));
		
		return produto;
	}
	
	
	
	public ProdutoVO update(ProdutoVO produto) {
		
		Produto p1 = repository.findById(produto.getId()).orElseThrow(() -> new ResourceNotFoundException("Nenhum recurso encontrado para o ID informado!"));
	
		p1.setNome(produto.getNome());
		p1.setValor(produto.getValor());
		p1.setTipo(produto.getTipo());
		
		repository.save(p1);
		
		return produto;
	}
	
	public void delete(String id) {
		
		if (!numericValidator.isNumeric(id)) {
			throw new NotNumericException("Informe um valor númerico pro ID");
		}
		
		logger.info("apagando um produto.....");
		
		Produto p1 = repository.findById(Long.parseLong(id)).orElseThrow(() -> new ResourceNotFoundException("Nenhum recurso encontrado para o ID informado!"));
		
		repository.delete(p1);
	}

}

