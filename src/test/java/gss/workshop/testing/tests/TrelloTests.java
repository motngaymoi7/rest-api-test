package gss.workshop.testing.tests;

import gss.workshop.testing.pojo.board.BoardCreationRes;
import gss.workshop.testing.pojo.card.CardCreationRes;
import gss.workshop.testing.pojo.card.CardUpdateRes;
import gss.workshop.testing.pojo.list.ListCreationRes;
import gss.workshop.testing.requests.RequestFactory;
import gss.workshop.testing.utils.ConvertUtils;
import gss.workshop.testing.utils.OtherUtils;
import gss.workshop.testing.utils.ValidationUtils;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

public class TrelloTests extends TestBase {

  @Test
  public void trelloWorkflowTest() {
    // 1. Create new board without default list
    String boardName = OtherUtils.randomBoardName();
    Response resBoardCreation = RequestFactory.createBoard(boardName, false);

    // VP. Validate status code
    ValidationUtils.validateStatusCode(resBoardCreation, HttpStatus.SC_OK);

    // VP. Validate a board is created: Board name and permission level
    BoardCreationRes board =
        ConvertUtils.convertRestResponseToPojo(resBoardCreation, BoardCreationRes.class);
    ValidationUtils.validateStringEqual(boardName, board.getName());
    ValidationUtils.validateStringEqual("private", board.getPrefs().getPermissionLevel());

    // -> Store board Id
    String boardId = board.getId();
    System.out.println(String.format("Board Id of the new Board: %s", boardId));
/*
    // 2. Create a TODO list
    Response resToDoList = RequestFactory.createList(boardId, "TODO");

    // VP. Validate status code
    ValidationUtils.validateStatusCode(resToDoList, HttpStatus.SC_OK);

    // VP. Validate a list is created: name of list, closed attribute
    ListCreationRes todoPojo = ConvertUtils.convertRestResponseToPojo(resToDoList, ListCreationRes.class);
    ValidationUtils.validateStringEqual("TODO", todoPojo.getName());

    //Store listID
    String todoIdList = todoPojo.getId();

    // VP. Validate the list was created inside the board: board Id
    ValidationUtils.validateStringEqual(boardId, todoPojo.getIdBoard());

    // 3. Create a DONE list
    Response resDoneList = RequestFactory.createList(boardId, "DONE");

    // VP. Validate status code
    ValidationUtils.validateStatusCode(resToDoList, HttpStatus.SC_OK);

    // VP. Validate a list is created: name of list, "closed" property
    ListCreationRes donePojo = ConvertUtils.convertRestResponseToPojo(resDoneList, ListCreationRes.class);
    ValidationUtils.validateStringEqual("DONE", donePojo.getName());

    //Store listID
    String doneIdList = donePojo.getId();

    // VP. Validate the list was created inside the board: board Id
    ValidationUtils.validateStringEqual(boardId, donePojo.getIdBoard());

    // 4. Create a new Card in TODO list
    String taskName = OtherUtils.randomTaskName();
    Response resCardCreation = RequestFactory.createCard(taskName, todoIdList);

    // VP. Validate status code
    ValidationUtils.validateStatusCode(resCardCreation, HttpStatus.SC_OK);
    CardCreationRes cardCreationPojo = ConvertUtils.convertRestResponseToPojo(resCardCreation, CardCreationRes.class);

    // Store the cardId
    String cardId = cardCreationPojo.getId();

    // VP. Validate a card is created: task name, list id, board id
    ValidationUtils.validateStringEqual(taskName, cardCreationPojo.getName());
    ValidationUtils.validateStringEqual(todoIdList, cardCreationPojo.getIdList());
    ValidationUtils.validateStringEqual(boardId, cardCreationPojo.getIdBoard());

    // VP. Validate the card should have no votes or attachments
    ValidationUtils.validateStringEqual("0", cardCreationPojo.getIdMembersVoted());
    ValidationUtils.validateStringEqual("", cardCreationPojo.getAttachments());

    // 5. Move the card to DONE list
    Response resUpdateCard = RequestFactory.updateCard(cardId, doneIdList);

    // VP. Validate status code
    ValidationUtils.validateStatusCode(resUpdateCard, HttpStatus.SC_OK);

    // VP. Validate the card should have new list: list id
    CardUpdateRes cardUpdatePojo = ConvertUtils.convertRestResponseToPojo(resUpdateCard, CardUpdateRes.class);

    // VP. Validate the card should preserve properties: name task, board Id, "closed" property
    ValidationUtils.validateStringEqual(taskName, cardUpdatePojo.getName());
    ValidationUtils.validateStringEqual(boardId, cardUpdatePojo.getIdBoard());

    // 6. Delete board
    Response resDeleteBoard = RequestFactory.deleteBoard(boardId);
*/
    // VP. Validate status code
//    ValidationUtils.validateStatusCode(resDeleteBoard, HttpStatus.SC_OK);
//    Response resGetBoard = RequestFactory.getBoardById(boardId);
//    ValidationUtils.validateStatusCode(resGetBoard, HttpStatus.SC_NOT_FOUND);
  }
}
