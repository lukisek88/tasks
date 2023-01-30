package com.crud.tasks.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TrelloConfigTest {

    @Autowired
    private TrelloConfig trelloConfig;

    @Test
    public void shuoldFetchTrelloConfigMethod() {
        //Given

        //when

        //Then
        assertNotNull(trelloConfig.getTrelloApiEndpoint());
        assertNotNull(trelloConfig.getTrelloAppKey());
        assertNotNull(trelloConfig.getTrelloToken());

    }

}