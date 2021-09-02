package org.launchcode.srilc101.models.data;

import org.launchcode.srilc101.models.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Integer> {

    Product findByName(String name);

    List<Product> findAllByUserId(int userId);
}
