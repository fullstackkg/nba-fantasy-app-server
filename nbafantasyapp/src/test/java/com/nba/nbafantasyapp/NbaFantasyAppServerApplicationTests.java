package com.nba.nbafantasyapp;

import com.nba.nbafantasyapp.player.PlayerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.assertj.core.api.Assertions.assertThat;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootTest
class NbaFantasyAppServerApplicationTests {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private PlayerRepository playerRepository;

    @Test
    void givenDatabaseWhenLoadedGetDataSourceAndProduct() throws SQLException {
        assertThat(dataSource.getClass().getName()).isEqualTo("com.zaxxer.hikari.HikariDataSource");
        assertThat(dataSource.getConnection().getMetaData().getDatabaseProductName()).isEqualTo("MySQL");
    }

    @Test
    void givenDataAvailableWhenLoadFirstPageThenGetFiveRecords() {
        Pageable pageable = PageRequest.of(0, 5);
        assertThat(playerRepository.findAll(pageable)).hasSize(5);
        assertThat(pageable.getPageNumber()).isEqualTo(0);

        Pageable nextPageable = pageable.next();
        assertThat(playerRepository.findAll(nextPageable)).hasSize(5);
        assertThat(nextPageable.getPageNumber()).isEqualTo(1);
    }
}
