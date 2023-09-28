package com.doodle.application.order;

import com.doodle.application.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
    List<Order> findAllByCustomer(User customer);
}
