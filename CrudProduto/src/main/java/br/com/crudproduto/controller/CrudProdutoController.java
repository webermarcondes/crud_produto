package br.com.crudproduto.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.crudproduto.dao.ProdutoDAO;
import br.com.crudproduto.exceptions.NotNumericException;
import br.com.crudproduto.model.Produto;
import br.com.crudproduto.verifications.numericValidator;

@RestController
@RequestMapping(value="/produtos")
public class CrudProdutoController {
	
	
	Logger logger = Logger.getLogger(CrudProdutoController.class.toString());
	

	@Autowired
	ProdutoDAO produtoDAO;
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Produto> buscarTodos() {
		
		logger.info("buscando todos os produtos cadastrados");
		
		return produtoDAO.buscarTodos();
	}
	
	@GetMapping(value="/buscaporid/{id}")
	public Produto buscarPorID(@PathVariable(value="id") String id) {
		
		if (!numericValidator.isNumeric(id)) {
			throw new NotNumericException("Informe um valor númerico pro ID");
		}
		
		logger.info("buscando o produto de id " + id);
		
		return produtoDAO.buscaPorId(Long.parseLong(id));
	}
	
	@GetMapping(value="/buscaportipo/{tipo}",
				produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Produto> buscaPorTipo(@PathVariable(value="tipo") String tipo) {
		return produtoDAO.buscaPorTipo(tipo);
	}
	
	@GetMapping(value="/buscaporvalor/{valor_min}/{valor_max}",
				produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Produto> buscaPorValor(@PathVariable(value="valor_min") String valor_min,
										@PathVariable(value="valor_max") String valor_max) {
		
		if (!numericValidator.isNumeric(valor_min) || !numericValidator.isNumeric(valor_max)) {
			throw new NotNumericException("informe apenas valores númericos");
		}
		
		return produtoDAO.buscaporValor(Double.parseDouble(valor_min), Double.parseDouble(valor_max));
	}
	
	@PostMapping(value="/criar",
				consumes=MediaType.APPLICATION_JSON_VALUE,
				produces=MediaType.APPLICATION_JSON_VALUE)
	public Produto criarProduto(@RequestBody Produto produto) {
		
		logger.info("criando um produto....");
		
		return produtoDAO.criar(produto);
	}
	
	@PutMapping(value="/atualizar",
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public Produto atualizarProduto(@RequestBody Produto produto) {
		
		logger.info("atualizando dados de um produto.....");
		
		return produtoDAO.atualizar(produto);
}
	@DeleteMapping(value="/apagar/{id}")
	public ResponseEntity<?> apagarProduto(@PathVariable(value="id") String id) {
		
		if (!numericValidator.isNumeric(id)) {
			throw new NotNumericException("Informe um valor númerico pro ID");
		}
		
		
		logger.info("apagando um produto.....");
		
		produtoDAO.apagar(Long.parseLong(id));
		
		return ResponseEntity.noContent().build();	
		}
	
}
