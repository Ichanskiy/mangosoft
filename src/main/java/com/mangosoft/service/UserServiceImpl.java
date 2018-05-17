package com.mangosoft.service;

import com.mangosoft.entity.BaseObject;
import com.mangosoft.entity.User;
import com.mangosoft.repository.RoleRepository;
import com.mangosoft.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public Page<User> getUsersByPageAndCount(int page, int size) {
        return userRepository.findAll( PageRequest.of(page, size,  Sort.Direction.DESC, BaseObject.ID_FIELD));
    }

    @Override
    public User updateUser(User user) {
        return saveUser(user);
    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    @Override
    public boolean comparePassword(String oldPassword, User user) {
        return bCryptPasswordEncoder.matches(oldPassword, user.getPassword());
    }
}
