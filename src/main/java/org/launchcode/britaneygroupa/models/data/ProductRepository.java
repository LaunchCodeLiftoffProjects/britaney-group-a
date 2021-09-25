package org.launchcode.britaneygroupa.models.data;

import org.launchcode.britaneygroupa.models.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Integer> {

    Product findByName(String name);

    List<Product> findAllByUserId(int userId);

    List<Product> findAllByDateOfExpiryAndNotifiedIsNull(Date dateOfExpiry);

    Product deleteById(int id);

    //NOTE: The SQL query here is broken because it can't compare dates effectively with the timestamp. Will try to figure that out soon
    //@Query (value = "Select * From product Where (date_of_expiry - sysdate) in (0, 1, 14, 30) Order By user_ID", nativeQuery = true)
    //List<Product> findExpiringItems();
}
