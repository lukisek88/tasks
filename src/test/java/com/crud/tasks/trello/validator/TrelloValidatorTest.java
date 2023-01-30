package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class TrelloValidatorTest {

    @InjectMocks
    TrelloValidator trelloValidator;

    @Test
    public void shouldValidateTrelloBoard() {
        //Given
        TrelloList trelloList1 = new TrelloList("1", "line 1", true);
        TrelloList trelloList2 = new TrelloList("2", "line 2", false);
        TrelloList trelloList3 = new TrelloList("3", "line 3", true);
        TrelloList trelloList4 = new TrelloList("4", "line 4", false);
        TrelloList trelloList5 = new TrelloList("5", "line 5", true);
        TrelloList trelloList6 = new TrelloList("6", "line 6", false);
        List<TrelloList> trelloLists1 = Arrays.asList(trelloList1, trelloList2);
        List<TrelloList> trelloLists2 = Arrays.asList(trelloList3, trelloList4);
        List<TrelloList> trelloLists3 = Arrays.asList(trelloList5, trelloList6);
        TrelloBoard trelloBoard1 = new TrelloBoard("1", "test", trelloLists1);
        TrelloBoard trelloBoard2 = new TrelloBoard("2", "anything", trelloLists2);
        TrelloBoard trelloBoard3 = new TrelloBoard("3", "something", trelloLists3);
        List<TrelloBoard> trelloBoardList = Arrays.asList(trelloBoard1, trelloBoard2, trelloBoard3);

        //When
        List<TrelloBoard> retrievedTrelloBoardList = trelloValidator.validateTrelloBoards(trelloBoardList);

        //Then
       assertEquals(2, retrievedTrelloBoardList.size());
    }
}