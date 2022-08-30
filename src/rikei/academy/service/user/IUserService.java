package rikei.academy.service.user;

import rikei.academy.model.User;
import rikei.academy.service.IGenericService;

public interface IUserService extends IGenericService<User> {
    boolean existByUsername(String username);
    boolean existsByEmail(String email);
    boolean checkLogin(String username,String password);

    User getCurrentUser();
    void saveCurrentUser(User user);
    User findByUsername(String username);

}
