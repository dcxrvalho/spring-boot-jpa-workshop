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
public class ProductService {
	
	@Autowired
	private ProductRepository repository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<Product> findAll() {
		return repository.findAll();
	}
	
	public Product findById(Long id) {
		Product obj = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		return obj;
	}
	
	public Product insert(Product obj) {
		return repository.save(obj);
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch(DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
			}
	}
		
	public Product update(Long id, Product obj) {
		try {
			Product entity = repository.getReferenceById(id);
			updateData(entity, obj);
			return repository.save(entity);
		} catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
		
	}

	private void updateData(Product entity, Product obj) {
		entity.setName(obj.getName());
		entity.setDescription(obj.getDescription());
		entity.setPrice(obj.getPrice());
		entity.setImgUrl(obj.getImgUrl());
		entity.getCategories().clear();
		for (Category cat : obj.getCategories()) {
			Category category = categoryRepository.findById(cat.getId()).orElseThrow(() -> new ResourceNotFoundException(cat.getId()));
			entity.getCategories().add(category);
		}
	}
}
