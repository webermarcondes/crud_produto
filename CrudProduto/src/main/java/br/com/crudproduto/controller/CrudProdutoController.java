package br.com.crudproduto.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.crudproduto.data.vo.v1.ProdutoVO;
import br.com.crudproduto.data.vo.v2.ProdutoVOV2;
import br.com.crudproduto.services.ProdutoServices;


/*
 A classe Controller contém todos os métodos da API, ela recebe as requisições para
 para os métodos especificos e retorna (ou não) um conteúdo ou excecão.
 
 a anotação @RestController torna a classe um controller
 */

@RestController

/*
 A anotação @RequestMapping mapeia uma classe/método para que este seja utilizado caso o valor passado
 no campo value seja identificado na URL fornecida pelo usuário
 
 Campos que @RequestMapping tem alem do value:
 
 
 method: especifica para qual verbo HTTP a requisição foi mapeada;
 consumes: especifica o conteúdo que a requisição consome para realizar suas operações;
 produces: especifica o conteúdo que a requisição retorna (se tudo der certo) após finalizar suas operações;
 */

@RequestMapping(value="/produtos")
public class CrudProdutoController {
	
	@Autowired  //AutoWired está relacionado com injeçao de dependências, veja mais na classe ProdutoServices
	ProdutoServices service;
	
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE) //GetMapping é semelhante a um @RequestMapping com o valor do method se referindo ao verbo GET. 
	public List<ProdutoVO> findAll() {
				
		return service.findAll();
	}
	
	@GetMapping(value="/findbyid/{id}")
	public ProdutoVO findById(@PathVariable(value="id") String id) {
				
		return service.findById(id);
	}
	
	@GetMapping(value="/findbytipo/{tipo}",
				produces=MediaType.APPLICATION_JSON_VALUE)
	public List<ProdutoVO> findByTipo(@PathVariable(value="tipo") String tipo) {
		
		return service.findByTipo(tipo);
	}
	
	@GetMapping(value="/findbyvalor/{valor_min}",
				produces=MediaType.APPLICATION_JSON_VALUE)
	public List<ProdutoVO> findByValor(@PathVariable(value="valor_min") String valor_min,
									@RequestParam(value="valor_max", defaultValue="none") String valor_max) {
	
		return service.findByValor(valor_min, valor_max);
		
	}
	
	@PostMapping(value="/create",    //@PostMapping é semelhante a @RequestMapping com o campo method se referindo ao verbo POST.
				consumes=MediaType.APPLICATION_JSON_VALUE,
				produces=MediaType.APPLICATION_JSON_VALUE)
	public ProdutoVO createProduto(@RequestBody ProdutoVO produto) {
		
		
		
		return service.create(produto);
	}
	
	@PostMapping(value="/createv2",
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ProdutoVOV2 createProduto(@RequestBody ProdutoVOV2 produto) {
		/*
		 endPoint versionado.
		 
		 a vantagem do versionamento é fazer com que duas versões de um mesmo
		 endPoint fiquem ativas ao mesmo tempo, até que todos os usuários da versão antiga consigam
		 migrar pra nova.*/
		
		return service.createV2(produto);
	}
	
	@PutMapping(value="/update", //@PutMapping é semelhante a @RequestMapping com o campo method se referindo ao verbo PUT.
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ProdutoVO atualizarProduto(@RequestBody ProdutoVO produto) {
		
		return service.update(produto);
}
	@DeleteMapping(value="/delete/{id}")  //@DeleteMapping é semelhante a @RequestMapping com o campo method se referindo verbo delete.
	public ResponseEntity<?> deleteProduto(@PathVariable(value="id") String id) {
		 //classe  deleteProduto com retorno de código padrão 204(No content) para operações de delete.
			
		service.delete(id); 
		
		return ResponseEntity.noContent().build();	  
		}
	
}
