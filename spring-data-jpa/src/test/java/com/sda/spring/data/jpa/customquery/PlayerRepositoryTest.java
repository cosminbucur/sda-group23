package com.sda.spring.data.jpa.customquery;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.ZonedDateTime;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = CustomQueryApplication.class)
class PlayerRepositoryTest {

    private final String PLAYER_NAME_PAUL = "paul";
    private final String PLAYER_NAME_ALINA = "alina";
    private final ZonedDateTime BIRTHDATE = ZonedDateTime.now();

    @Autowired
    PlayerRepository playerRepository;

    @BeforeEach
    void setUp() {
        // clear db after each test
        playerRepository.deleteAll();
        Player player1 = new Player(PLAYER_NAME_PAUL, 25, BIRTHDATE, true);
        Player player2 = new Player(PLAYER_NAME_PAUL, 20, BIRTHDATE, false);
        Player player3 = new Player(PLAYER_NAME_ALINA, 20, BIRTHDATE, true);
        Player player4 = new Player(null, 30, BIRTHDATE, false);

        playerRepository.saveAll(Arrays.asList(player1, player2, player3, player4));
    }

    @Test
    void whenFindByNameLike_thenReturnCorrectResult() {
        assertThat(playerRepository.findByNameLike("%ul%")).hasSize(2);
    }

}