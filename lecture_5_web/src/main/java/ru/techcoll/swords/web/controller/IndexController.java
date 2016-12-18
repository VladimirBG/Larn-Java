package ru.techcoll.swords.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.techcoll.swords.domain.Player;
import ru.techcoll.swords.service.PlayerService;
import ru.techcoll.swords.web.form.PlayerRegisterForm;

import javax.validation.Valid;

@Controller
public class IndexController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private PlayerService playerService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        final Player player = new Player();
        player.setName("Player1");

        model.addAttribute("player", player);
        return "index/index";
    }

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String register(Model model) {
        model.addAttribute("form", new PlayerRegisterForm());
        return "index/register";
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String registerPostback(@ModelAttribute("form") @Valid PlayerRegisterForm form, Errors errors, Model model) {
        if (errors.hasErrors()) {
            return "index/register";
        }

        Player player = playerService.registerPlayer(
            form.getName(),
            form.getNickname(),
            form.getEmail(),
            form.getPassword()
        );
        model.addAttribute("player", player);

        return "redirect:./registered";
    }

    @RequestMapping(value = "registered", method = RequestMethod.GET)
    public String registered(Model model) {
        return "index/registerSuccess";
    }

}
