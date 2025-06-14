package repository;

import orders.Order;

public interface OrderRepository {
    boolean save(Order order);
}