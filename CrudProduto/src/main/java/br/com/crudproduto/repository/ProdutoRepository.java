package br.com.crudproduto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.crudproduto.model.Produto;

/*
Interface para conexão com o banco de dados, ao estender JpaRepository as operações
básicas já são carregadas.

Caso seja necessário criar operações especificas é só adiciona-las a interface criada.
 */


public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	
	/*
	 Consulta personalizada para busca por tipo, o findBy especifica o que será feito
	 e o tipo se refere ao atributo que será adicionado ao select para  a busca.
	 
	 existem outros nomes de métodos especificos que especificam a consulta a ser feita, de uma olhada na internet para saber mais.
	 */
	List<Produto> findBytipo(String tipo);
	
	/*
	Consulta personalizada definida pela anotação @Query
	
	Podemos apenas especificar a consulta utilizando o JPQL ou especificar um value como uma
	query de sql comum e um nativeQuery=true para especificar que se trata de sql comum.
	
	Nesse caso o nome do método da classe não precisa conter palavras que remetam a operação realizada para que o mesmo funcione.
	 */
	@Query(value="select * from produto where valor >= :valor_min and valor <= :valor_max", nativeQuery=true)
	List<Produto> findByValor(@Param("valor_min") Double valor_min, @Param("valor_max") Double valor_max);
}
