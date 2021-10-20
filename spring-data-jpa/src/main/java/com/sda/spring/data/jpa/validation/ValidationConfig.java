package com.sda.spring.data.jpa.validation;

import com.sda.spring.data.jpa.validation.dto.UserReadDto;
import com.sda.spring.data.jpa.validation.dto.UserWriteDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Configuration
public class ValidationConfig {

    private static final Logger log = LoggerFactory.getLogger(ValidationConfig.class);

    @Autowired
    private UserService userService;

    @Bean
    public CommandLineRunner validationData() {
        return strings -> {
            testProgrammaticValidations();
            testService();
        };
    }

    private void testProgrammaticValidations() {
        UserWriteDto validDto = createValidUser();
        isValid(validDto);

        UserWriteDto invalidDto = createInValidUser();
        isValid(invalidDto);
    }

    private UserWriteDto createValidUser() {
        UserWriteDto dto = new UserWriteDto();
        dto.setName("jon snow");
        dto.setEmail("jonsnow@gmail.com");
        dto.setAge(30);
        dto.setConsented(true);
        dto.setAboutMe("I'm a watcher on the wall");
        return dto;
    }

    private UserWriteDto createInValidUser() {
        UserWriteDto dto = new UserWriteDto();
        dto.setName("ana");
        dto.setEmail("jonsnowgmail.com");
        dto.setAge(10);
        dto.setConsented(false);
        dto.setAboutMe(":X");
        return dto;
    }

    // programmatic validation
    private boolean isValid(UserWriteDto dto) {
        // get validation violations programmatically
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<UserWriteDto>> constraintViolations = validator.validate(dto);

        if (!constraintViolations.isEmpty()) {
            log.error("validating user: {}", dto);

            constraintViolations
                    .forEach(violation -> log.error("violation: {}", violation.getMessage()));
            return false;
        }
        return true;
    }

    private void testService() {
        // create user
        UserWriteDto dto = createValidUser();

        // crud
        UserReadDto savedUserDto = userService.save(dto);

        List<UserReadDto> userDtos = userService.findAll();

        UserReadDto userDto = userService.findById(savedUserDto.getId());

        UserWriteDto updateData = new UserWriteDto();
        updateData.setName("john snow");
        updateData.setEmail("johnsnow@gmail.com");
        updateData.setAge(35);
        updateData.setConsented(true);
        updateData.setAboutMe("I like to hunt wildlings");

        UserReadDto updatedDto = userService.update(userDto.getId(), updateData);

        userService.delete(updatedDto.getId());
    }
}
