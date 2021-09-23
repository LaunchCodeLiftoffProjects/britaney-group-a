package org.launchcode.britaneygroupa.models.data;

import org.launchcode.britaneygroupa.models.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Integer> {

    Product findByName(String name);

    List<Product> findAllByUserId(int userId);

    List<Product> findAllByDateOfExpiry(Date dateOfExpiry);

    Product deleteById(int id);
}
