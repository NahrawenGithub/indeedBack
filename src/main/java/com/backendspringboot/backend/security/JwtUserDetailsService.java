package com.backendspringboot.backend.security;

import com.backendspringboot.backend.dto.UserDto;
import com.backendspringboot.backend.entity.Users;
import com.backendspringboot.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userDao;
    @Autowired
    private PasswordEncoder bcryptEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userDao.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }

    public Users getUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userDao.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return user;
    }

    public String getRole(String username) throws RoleNotFoundException {
        Users user = getUserByUsername(username);
        return user.getRole();
    }

    public List<Users> getAllFournie() throws Exception {
        List<Users> usersList = userDao.findAllByRole("fournie");
        if (usersList == null) {
            throw new Exception("No fournie Found!");
        }
        return usersList;
    }

    public Users saveUser(Users user) {
        return userDao.save(user);
    }

    public Users updateUser(UserDto user) {
        Users updateUser = userDao.findByUsername(user.getUsername());
        updateUser.setFullName(user.getFullName());
        updateUser.setTelephone(user.getTelephone());
        updateUser.setUsername(user.getUsername());
        updateUser.setAddresse(user.getAddresse());
        return userDao.save(updateUser);
    }

    public Users save(UserDto user) {
        Users newUser = new Users();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        newUser.setRole(user.getRole());
        newUser.setFullName(user.getFullName());
        newUser.setTelephone(user.getTelephone());
        return userDao.save(newUser);
    }
}

