package io.github.dcxrvalho.spring_boot_jpa_workshop.services;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import io.github.dcxrvalho.spring_boot_jpa_workshop.entities.Order;
import io.github.dcxrvalho.spring_boot_jpa_workshop.entities.User;
import io.github.dcxrvalho.spring_boot_jpa_workshop.entities.enums.OrderStatus;
import io.github.dcxrvalho.spring_boot_jpa_workshop.repositories.OrderRepository;
import io.github.dcxrvalho.spring_boot_jpa_workshop.repositories.UserRepository;
import io.github.dcxrvalho.spring_boot_jpa_workshop.services.exceptions.DatabaseException;
import io.github.dcxrvalho.spring_boot_jpa_workshop.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;

	@Service
	public class OrderService {
		
		@Autowired
		private OrderRepository repository;
		
		@Autowired
		private UserRepository userRepository;
		
		public List<Order> findAll() {
			return repository.findAll();
		}
		
		public Order findById(Long id) {
			Order obj = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
			return obj;
		}
		
		public Order insert(Order obj) {
			User client = userRepository.findById(obj.getClient().getId())
					.orElseThrow(() -> new ResourceNotFoundException(obj.getClient().getId()));
			obj.setClient(client);
			obj.setMoment(Instant.now());
			obj.setOrderStatus(OrderStatus.WAITING_PAYMENT);
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
			
		public Order update(Long id, Order obj) {
			try {
				Order entity = repository.getReferenceById(id);
				updateData(entity, obj);
				return repository.save(entity);
			} catch(EntityNotFoundException e) {
				throw new ResourceNotFoundException(id);
			}
		}

		private void updateData(Order entity, Order obj) {
			if (obj.getOrderStatus() != null) {
				entity.setOrderStatus(obj.getOrderStatus());
			}
		}
}
