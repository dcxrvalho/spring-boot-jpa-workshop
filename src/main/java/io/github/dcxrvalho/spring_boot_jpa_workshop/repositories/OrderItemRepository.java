package io.github.dcxrvalho.spring_boot_jpa_workshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.dcxrvalho.spring_boot_jpa_workshop.entities.OrderItem;
import io.github.dcxrvalho.spring_boot_jpa_workshop.entities.pk.OrderItemPK;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {

}
