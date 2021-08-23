package org.launchcode.britaneygroupa.models.data;

import org.launchcode.britaneygroupa.models.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {

    Product findByProductName(String productName);
}
