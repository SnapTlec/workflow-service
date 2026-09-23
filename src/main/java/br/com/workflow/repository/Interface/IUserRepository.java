package br.com.workflow.repository.Interface;

import java.util.List;
import java.util.Optional;

import br.com.workflow.entity.User;

public interface IUserRepository {
    User save(User user);
    Optional<User> findById(Integer id);
    List<User> findAllById(List<Integer> ids);
    Optional<User> findByLogin(String login);
    List<User> findAll();
}
