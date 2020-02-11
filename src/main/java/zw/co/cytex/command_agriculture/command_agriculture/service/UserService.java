package zw.co.cytex.command_agriculture.command_agriculture.service;

import zw.co.cytex.command_agriculture.command_agriculture.model.User;
import zw.co.cytex.command_agriculture.command_agriculture.model.UserDto;

import java.util.List;

/**
 * @author : Webster Moswa
 * @since : 15/01/2020, Wed
 * email: webstermoswa11@gmail.com
 * mobile: 0771407147
 **/


public interface UserService {
    User save(UserDto user);
    List<User> findAll();

    void delete(Long id);

    User findOne(String username);

    User findById(Long id);

    UserDto update(UserDto userDto);
}
