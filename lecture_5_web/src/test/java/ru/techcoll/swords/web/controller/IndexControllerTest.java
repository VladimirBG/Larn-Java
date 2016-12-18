package ru.techcoll.swords.web.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.techcoll.swords.domain.Player;
import ru.techcoll.swords.service.PlayerService;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(IndexController.class)
public class IndexControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PlayerService playerService;


    @Test
    public void testIndex() throws Exception {

        mvc.perform(get("/"))
                .andExpect(view().name("index/index"));

    }

    @Test
    public void testRegisterPostback() throws Exception {

        final Player player1 = new Player();
        player1.setId(1L);
        player1.setName("John Doe");
        player1.setNickname("johnDoe");
        player1.setEmail("johnDoe@game.org");
        player1.setPassword("123456");

        doReturn(player1).when(playerService).registerPlayer("John Doe", "johnDoe", "johnDoe@game.org", "123456");

        mvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("name", "John Doe")
                .param("nickname", "johnDoe")
                .param("email", "johnDoe@game.org")
                .param("password", "123456")
                .param("passwordConfirmation", "123456")
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("./registered"));

        verify(playerService, atLeastOnce()).registerPlayer("John Doe", "johnDoe", "johnDoe@game.org", "123456");

    }

}