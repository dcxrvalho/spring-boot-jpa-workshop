package io.github.dcxrvalho.spring_boot_jpa_workshop.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.dcxrvalho.spring_boot_jpa_workshop.entities.Category;
import io.github.dcxrvalho.spring_boot_jpa_workshop.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	List<Product> findByCategories(Category category);
}
