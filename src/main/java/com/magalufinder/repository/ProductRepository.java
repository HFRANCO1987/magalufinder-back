

package com.magalufinder.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import com.magalufinder.models.Product;

public interface ProductRepository extends CrudRepository<Product, Long>{

	
	 @Query(value = " SELECT * FROM tab_product p\r\n" + 
	 		" INNER JOIN tab_product_stores ps ON ps.id_product = p.id\r\n" + 
	 		" INNER JOIN tab_stores s ON s.id = ps.id_store\r\n" + 
	 		" WHERE ps.id_store = :id", nativeQuery = true)
	 List<Product> findByProductByIdStore(@Param("id") Long id);

	@Query("select p from Product p where p.code = :product or p.description LIKE '%' || :product || '%'")
	List<Product> findByCodeByDescription(@Param("product") String product);
	
}
