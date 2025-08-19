package io.github.dcxrvalho.spring_boot_jpa_workshop.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import io.github.dcxrvalho.spring_boot_jpa_workshop.entities.Category;
import io.github.dcxrvalho.spring_boot_jpa_workshop.entities.Product;
import io.github.dcxrvalho.spring_boot_jpa_workshop.repositories.CategoryRepository;
import io.github.dcxrvalho.spring_boot_jpa_workshop.repositories.ProductRepository;
import io.github.dcxrvalho.spring_boot_jpa_workshop.services.exceptions.DatabaseException;
import io.github.dcxrvalho.spring_boot_jpa_workshop.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository repository;
	
	@Autowired
	private ProductRepository productRepository;
	
	public List<Category> findAll() {
		return repository.findAll();
	}
	
	public Category findById(Long id) {
		Category obj = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		return obj;
	}
	
	public Category insert(Category obj) {
		return repository.save(obj);
	}
	
	public void delete(Long id) {
		try {
			Category categoryToDelete = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
			List<Product> products = productRepository.findByCategories(categoryToDelete);
			for (Product product : products) {
	            product.getCategories().remove(categoryToDelete);
	            productRepository.save(product);
	        }
			repository.deleteById(id);
		} catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch(DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
			}
	}
		
	public Category update(Long id, Category obj) {
		try {
			Category entity = repository.getReferenceById(id);
			updateData(entity, obj);
			return repository.save(entity);
		} catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(Category entity, Category obj) {
		entity.setName(obj.getName());
	}
}
