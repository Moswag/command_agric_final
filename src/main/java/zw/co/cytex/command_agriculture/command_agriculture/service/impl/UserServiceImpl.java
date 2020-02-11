package zw.co.cytex.command_agriculture.command_agriculture.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import zw.co.cytex.command_agriculture.command_agriculture.model.User;
import zw.co.cytex.command_agriculture.command_agriculture.model.UserDto;
import zw.co.cytex.command_agriculture.command_agriculture.repositories.UserRepository;
import zw.co.cytex.command_agriculture.command_agriculture.service.UserService;
import zw.co.cytex.command_agriculture.command_agriculture.util.Constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static zw.co.cytex.command_agriculture.command_agriculture.util.Constants.STATUS_ACTIVE;

/**
 * @author : Webster Moswa
 * @since : 15/01/2020, Wed
 * email: webstermoswa11@gmail.com
 * mobile: 0771407147
 **/

@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private UserRepository userDao;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority());
    }

    private List<SimpleGrantedAuthority> getAuthority() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        userDao.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public void delete(Long id) {
        userDao.deleteById(id);
    }

    @Override
    public User findOne(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public User findById(Long id) {
        Optional<User> optionalUser = userDao.findById(id);
        return optionalUser.orElse(null);
    }

    @Override
    public UserDto update(UserDto userDto) {
        User user = findById(userDto.getId());
        if(user != null) {
            BeanUtils.copyProperties(userDto, user, "password", "username");
            userDao.save(user);
        }
        return userDto;
    }

    @Override
    public User save(UserDto user) {
        User newUser = new User();
        newUser.setName(user.getName());
        newUser.setSurname(user.getSurname());
        newUser.setEmail(user.getEmail());
        newUser.setUsername((user.getName().substring(0,1)+user.getSurname()).toLowerCase());
        newUser.setFirstTime(true);
        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        newUser.setStatus(STATUS_ACTIVE);
        newUser.setAccess(user.getAccess());
        return userDao.save(newUser);
    }
}
