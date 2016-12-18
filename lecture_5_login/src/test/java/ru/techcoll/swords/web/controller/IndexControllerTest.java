package ru.techcoll.swords.web.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.techcoll.swords.domain.Player;
import ru.techcoll.swords.service.PlayerService;

import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class IndexControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @MockBean
    private PlayerService playerService;

    @Before
    public void startUp() {
        this.mvc = MockMvcBuilders
            .webAppContextSetup(context)
            .apply(springSecurity())
            .build();
    }

    private Player createPlayer1() {
        Player player1 = new Player();
        player1.setId(1L);
        player1.setName("John Doe");
        player1.setNickname("johnDoe");
        player1.setEmail("johnDoe@game.org");
        player1.setPassword("123456");
        return player1;
    }

    @Test
    public void testIndex() throws Exception {

        mvc.perform(get("/"))
            .andExpect(status().isOk())
            .andExpect(view().name("index/index"));

    }

    @Test
    @WithMockUser(value = "johnDoe@game.org")
    public void testIndexUser() throws Exception {

        Player player1 = createPlayer1();

        doReturn(player1).when(playerService).getPlayerByEmail("johnDoe@game.org");

        mvc.perform(get("/"))
            .andExpect(status().isOk())
            .andExpect(view().name("index/main"))
            .andExpect(model().attributeExists("player"))
            .andExpect(model().attribute("player", player1));

    }

    @Test
    public void testLogin() throws Exception {

        User user1 = new User(
            "johnDoe@game.org",
            "123456",
            Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
        );
        doReturn(user1).when(playerService).loadUserByUsername("johnDoe@game.org");

        RequestBuilder requestBuilder = formLogin()
            .user("johnDoe@game.org")
            .password("password");

        mvc.perform(requestBuilder)
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrlPattern("/login?error"));;

        verify(playerService, atLeastOnce()).loadUserByUsername("johnDoe@game.org");

    }

    @Test
    public void testRegisterPostback() throws Exception {

        final Player player1 = createPlayer1();

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
            .andExpect(redirectedUrl("./login"));

        verify(playerService, atLeastOnce()).registerPlayer("John Doe", "johnDoe", "johnDoe@game.org", "123456");

    }

}