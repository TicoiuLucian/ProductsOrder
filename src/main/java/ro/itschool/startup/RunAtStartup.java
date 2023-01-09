package ro.itschool.startup;

import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ro.itschool.entity.Order;
import ro.itschool.entity.Product;
import ro.itschool.entity.ShoppingCart;
import ro.itschool.entity.User;
import ro.itschool.repository.ProductRepository;
import ro.itschool.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class RunAtStartup {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {

        User user = new User();
        user.setAddress("My Address 1");
        user.setName("User name 1");
        user.setEmail("My email");

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setProducts(getProducts());
        shoppingCart.setUser(user);


        Order order = new Order();
        order.setOrderDate(LocalDateTime.now());
        order.setShoppingCart(shoppingCart);
        order.setUser(user);

        user.setCart(shoppingCart);
        user.addOrderToUser(order);

        userRepository.save(user);

        productRepository.save(new Product("product11", 15.99f));
        productRepository.save(new Product("product12", 15.99f));

    }

    private List<Product> getProducts() {
        return productRepository.saveAll(List.of(
                new Product("product1", 15.99f),
                new Product("product2", 25.99f),
                new Product("product3", 5.99f),
                new Product("product4", 965.99f),
                new Product("product5", 456.99f)
        ));

    }
}
