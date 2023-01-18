package br.com.crudproduto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.crudproduto.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	
	List<Produto> findBytipo(String tipo);
	
	@Query(value="select * from produto where valor >= :valor_min and valor <= :valor_max", nativeQuery=true)
	List<Produto> findByValor(@Param("valor_min") Double valor_min, @Param("valor_max") Double valor_max);
}
