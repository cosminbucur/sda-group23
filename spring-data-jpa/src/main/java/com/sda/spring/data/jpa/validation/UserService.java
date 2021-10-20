package com.sda.spring.data.jpa.validation;

import com.sda.spring.data.jpa.validation.dto.UserMapper;
import com.sda.spring.data.jpa.validation.dto.UserReadDto;
import com.sda.spring.data.jpa.validation.dto.UserWriteDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

// evaluate the constraint annotations on method parameters
// validate service or controller, persistence layer validation is too late
@Validated
@Service
public class UserService {
    
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    // crud

    public UserReadDto save(@Valid UserWriteDto writeDto) {
        log.info("saving user: {}", writeDto);

        User entity = userMapper.toEntity(writeDto);
        User savedEntity = userRepository.save(entity);
        return userMapper.toDto(savedEntity);
    }

    public List<UserReadDto> findAll() {
        log.info("find all users");

        return userRepository.findAll().stream()
                .map(user -> userMapper.toDto(user))
                .collect(Collectors.toList());
    }

    public UserReadDto findById(Long id) {
        log.info("finding user: {}", id);

        return userRepository.findById(id)
                .map(user -> userMapper.toDto(user))
                .orElseThrow(() -> new NotFoundException("user not found"));
    }

    public UserReadDto update(Long id, UserWriteDto updateData) {
        log.info("updating user: {} with data: {}", id, updateData);

        // find by id
        return userRepository.findById(id)
            // update user with new data
            .map(userToUpdate -> userMapper.toEntity(userToUpdate, updateData))
            // save entity
            .map(updatedUser -> userRepository.save(updatedUser))
            // convert to dto
            .map(savedUser -> userMapper.toDto(savedUser))
            // exception
            .orElseThrow(() -> new NotFoundException("user not found"));
    }

    public void updateDto(Long id, UserWriteDto updateData) {
        User foundUser = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("user not found"));
        userMapper.toEntity(foundUser, updateData);
        userRepository.save(foundUser);
        userMapper.toDto(foundUser);
    }

    public void delete(Long id) {
        log.info("deleting user: {}", id);

        userRepository.deleteById(id);
    }
}
