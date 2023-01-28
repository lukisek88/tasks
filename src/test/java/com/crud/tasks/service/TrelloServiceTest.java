package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBadges;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TrelloServiceTest {
    @InjectMocks
    private TrelloService trelloService;

    @Mock
    private TrelloClient trelloClient;

    @Mock
    private SimpleEmailService emailService;

    @Mock
    private AdminConfig adminConfig;

    @Test
    public void fetchTrelloBoardsEmptyListTest() {
        //Given
        when(trelloClient.getTrelloBoards()).thenReturn(new ArrayList<>());

        //When
        List<TrelloBoardDto> trelloBoardDtos = trelloService.fetchTrelloBoards();

        //Then
        assertNotNull(trelloBoardDtos);
        assertEquals(0, trelloBoardDtos.size());
    }
    @Test
    public void createTrelloCardTest() {
        //Given

        CreatedTrelloCardDto createdCard = new CreatedTrelloCardDto("1", "card", "com/org",new TrelloBadges());
        TrelloCardDto card = new TrelloCardDto("card", "description", "pos", "1");

        when(trelloClient.createNewCard(card)).thenReturn(createdCard);
        when(adminConfig.getAdminMail()).thenReturn("mail@mail.com");

        //When
        CreatedTrelloCardDto createdTrelloCardDto = trelloService.createTrelloCard(card);

        //Then
        assertEquals(createdCard.getId(), createdTrelloCardDto.getId());
        assertEquals(createdCard.getName(), createdTrelloCardDto.getName());
        assertEquals(createdCard.getShortUrl(), createdTrelloCardDto.getShortUrl());
    }


}