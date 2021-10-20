package com.sda.spring.data.jpa.validation.dto;

import com.sda.spring.data.jpa.validation.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    // convert entities to dtos
    public List<UserReadDto> toDto(List<User> entities) {
        List<UserReadDto> resultList = new ArrayList<>();
        // iterate list of entities
        for (User entity : entities) {
            // convert entity to dto
            UserReadDto dto = new UserReadDto();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setEmail(entity.getEmail());

            resultList.add(dto);
        }
        return resultList;
    }

    public List<UserReadDto> toDtoEasy(List<User> entities) {
        return entities.stream()                                // Stream<User>
                // get user and transform in anything else
                .map((entity) -> toDto(entity))                 // List<UserReadDto>
                .collect(Collectors.toList());
    }

    public List<UserReadDto> toDtoEasiest(List<User> entities) {
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public UserReadDto toDto(User entity) {
        UserReadDto dto = new UserReadDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());
        return dto;
    }

    public User toEntity(UserWriteDto dto) {
        User entity = new User();
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setAge(dto.getAge());
        entity.setConsented(dto.getConsented());
        entity.setAboutMe(dto.getAboutMe());
        return entity;
    }

    public User toEntity(User userToUpdate, UserWriteDto updateData) {
        userToUpdate.setName(updateData.getName());
        userToUpdate.setEmail(updateData.getEmail());
        userToUpdate.setAge(updateData.getAge());
        userToUpdate.setConsented(updateData.getConsented());
        userToUpdate.setAboutMe(updateData.getAboutMe());
        return userToUpdate;
    }
}
