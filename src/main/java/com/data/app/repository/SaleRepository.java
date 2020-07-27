package com.data.app.repository;

import com.data.app.entity.Sale;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SaleRepository extends CrudRepository<Sale, Long> {
	List<Sale> findByTitle(String title);

	List<Sale> findDistinctByCategory(String category);

	List<Sale> findByTitleAndCategory(String title, String category);
}
