package library.service;

import com.senla.training.library.entity.Role;
import com.senla.training.library.entity.User;

import java.util.List;
import java.util.Set;

public interface UserService {

    List<User> findAll();

    User findById(Integer id);

    User add(User user);

    User update(User user);

    User findByUsername(String userName);

    User setRoles(String userName, Set<Role> roles);

}
