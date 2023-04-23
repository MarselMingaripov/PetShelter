package com.example.petshelter.service.impl;

import com.example.petshelter.entity.Cat;
import com.example.petshelter.entity.CatOwner;
import com.example.petshelter.entity.RoleSt;
import com.example.petshelter.entity.User;
import com.example.petshelter.exception.NotFoundInBdException;
import com.example.petshelter.exception.ValidationException;
import com.example.petshelter.repository.UserRepository;
import com.example.petshelter.service.UserService;
import com.example.petshelter.service.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ValidationService validationService;

    @Override
    public User createUser(User user) {
        if (!validationService.validatePhoneNumber(user.getPhoneNumber())) {
            throw new ValidationException(user.toString());
        }
        return userRepository.save(user);
    }

    @Override
    public User createUserFromTgB(String text) {
        String[] arr = text.split(" ");
        String phoneNumber = arr[0];
        Long tgId = Long.valueOf(arr[1]);
        System.out.println(text);
        if (!validationService.validatePhoneNumber(phoneNumber)) {
            throw new ValidationException("Error during phone number validation");
        }
        if (userRepository.existsByPhoneNumber(phoneNumber)) {
            User user = userRepository.findByPhoneNumber(phoneNumber).get();
            user.setTelegramId(tgId);
            return userRepository.save(user);
        } else {
            User user = new User(phoneNumber, tgId);
            return userRepository.save(user);
        }
    }

    @Override
    public User findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new NotFoundInBdException("Не найдено в базе данных");
        }
    }

    @Override
    public User updateById(Long id, User user) {
        if (userRepository.findById(id).isPresent()) {
            user.setId(id);
            return userRepository.save(user);
        } else {
            throw new NotFoundInBdException("Не найдено в базе данных");
        }
    }

    @Override
    public User deleteById(Long id) {
        User user = findById(id);
        userRepository.delete(user);
        return user;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByPhone(String phone) {
        if (userRepository.existsByPhoneNumber(phone)) {
            return userRepository.findByPhoneNumber(phone).get();
        } else {
            return null;
        }
    }

    @Override
    public User findByTelegramID(Long id) {
        Optional<User> user = userRepository.findByTelegramId(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new NotFoundInBdException("Не найдено в базе данных");
        }
    }

    @Override
    public String returnVolunteerTgId(){
        List<User> users = findAll();
        List<Long> volunteers = users.stream()
                .filter(x ->x.getRoleSt().equals(RoleSt.VOLUNTEER))
                .map(User::getTelegramId)
                .collect(Collectors.toList());
        String s = "";
        for (Long id : volunteers) {
            s = s + id + " ";
        }
        return s;
    }
}
