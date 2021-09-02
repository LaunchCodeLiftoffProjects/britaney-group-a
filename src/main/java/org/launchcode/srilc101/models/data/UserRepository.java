package org.launchcode.srilc101.models.data;

import org.launchcode.srilc101.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUserName(String userName);

}
