package ro.itschool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ro.itschool.entity.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}
