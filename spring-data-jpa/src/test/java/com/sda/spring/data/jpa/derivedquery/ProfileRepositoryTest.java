package com.sda.spring.data.jpa.derivedquery;

import com.sda.spring.data.jpa.derivedquerry.DerivedQueryApplication;
import com.sda.spring.data.jpa.derivedquerry.Profile;
import com.sda.spring.data.jpa.derivedquerry.ProfileRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = DerivedQueryApplication.class)
class ProfileRepositoryTest {

    private final String PROFILE_NAME_PAUL = "paul";
    private final String PROFILE_NAME_ALINA = "alina";
    private final ZonedDateTime BIRTHDATE = ZonedDateTime.now();

    @Autowired
    ProfileRepository profileRepository;

    @BeforeEach
    void setUp() {
        // clear db after each test
        profileRepository.deleteAll();
        Profile profile1 = new Profile(PROFILE_NAME_PAUL, 25, BIRTHDATE, true);
        Profile profile2 = new Profile(PROFILE_NAME_PAUL, 20, BIRTHDATE, false);
        Profile profile3 = new Profile(PROFILE_NAME_ALINA, 20, BIRTHDATE, true);
        Profile profile4 = new Profile(null, 30, BIRTHDATE, false);

        profileRepository.saveAll(Arrays.asList(profile1, profile2, profile3, profile4));
    }

    @Test
    void whenFindByName_thenReturnCorrectResult() {
        assertThat(profileRepository.findByName(PROFILE_NAME_PAUL)).hasSize(2);
    }

    @Test
    void whenFindByNameEndingWith_thenReturnCorrectResult() {
        assertThat(profileRepository.findByNameEndingWith("a")).hasSize(1);
    }

    @Test
    void whenDeleteProfileIsActive_thenReturnCorrectResult() {
        assertThat(profileRepository.deleteByActive(true)).hasSize(0);
    }

    // sample test method
    // givenProfile_whenFindByName_thenReturnProfile
    // shouldFindByName
    @Test
    void whenMethod_thenReturnResult() {
        // given
        // create profile
        Profile profile = new Profile(PROFILE_NAME_ALINA, 45, ZonedDateTime.now(), true);
        profileRepository.save(profile);

        // when
        // find
        List<Profile> actualList = profileRepository.findByName(profile.getName());

        // then
        // check not null / id
        // actual is equal to expected
        String actual = actualList.get(0).getName();
        String expected = PROFILE_NAME_ALINA;
        assertThat(actual).isEqualTo(expected);
    }
}