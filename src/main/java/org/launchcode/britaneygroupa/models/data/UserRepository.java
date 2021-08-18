package org.launchcode.britaneygroupa.models.data;

import org.launchcode.britaneygroupa.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUsername(String username);

}
