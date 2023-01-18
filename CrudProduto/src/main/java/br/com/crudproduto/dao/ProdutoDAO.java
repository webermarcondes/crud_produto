package br.com.crudproduto.dao;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.crudproduto.exceptions.ResourceNotFoundException;
import br.com.crudproduto.model.Produto;
import br.com.crudproduto.repository.ProdutoRepository;


@Service
public class ProdutoDAO {
	
	@Autowired
	ProdutoRepository repository;
	
	public List<Produto> buscarTodos() {
		
		return repository.findAll(); 
	}
	
	public Produto buscaPorId(Long id) {
		
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Não existe nenhum recurso com este ID"));
	
	}
	
	public List<Produto> buscaPorTipo(String tipo) {
		
		return repository.findBytipo(tipo);
	}
	
	public List<Produto> buscaporValor(Double menorValor, Double maiorValor) {
		
		return repository.findByValor(menorValor, maiorValor);
	}
	
	public Produto criar(Produto produto) {
		
		
		return repository.save(produto);
	}
	
	
	
	public Produto atualizar(Produto produto) {
		
		Produto p1 = repository.findById(produto.getId()).orElseThrow(() -> new ResourceNotFoundException("Nenhum recurso encontrado para o ID informado!"));
	
		p1.setNome(produto.getNome());
		p1.setValor(produto.getValor());
		p1.setTipo(produto.getTipo());
		
		return repository.save(p1);
	}
	
	public void apagar(Long id) {
		
		Produto p1 = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Nenhum recurso encontrado para o ID informado!"));
		
		repository.delete(p1);
	}

}

