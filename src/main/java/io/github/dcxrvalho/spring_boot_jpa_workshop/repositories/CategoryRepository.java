package io.github.dcxrvalho.spring_boot_jpa_workshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.dcxrvalho.spring_boot_jpa_workshop.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	
}
