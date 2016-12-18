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
import org.springframework.web.bind.annotation.RequestParam;
import ru.techcoll.swords.domain.Player;
import ru.techcoll.swords.service.PlayerService;
import ru.techcoll.swords.web.form.PlayerRegisterForm;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;

@Controller
public class IndexController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private PlayerService playerService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model, Principal principal) {
        if (principal == null)
            return "index/index";

        Player player = playerService.getPlayerByEmail(principal.getName());
        model.addAttribute("player", player);
        return "index/main";
    }

    @RequestMapping(value = "profile", method = RequestMethod.GET)
    public String profile(Model model, Principal principal) {
        return "index/profile";
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout,
            Model model) {

        model.addAttribute("error", error != null);
        model.addAttribute("logout", logout != null);
        return "index/login";
    }

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String register(Model model) {
        model.addAttribute("form", new PlayerRegisterForm());
        return "index/register";
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String registerPostback(@ModelAttribute("form") @Valid PlayerRegisterForm form, Errors errors, Model model, HttpServletRequest request) {
        if (errors.hasErrors()) {
            return "index/register";
        }

        Player player = playerService.registerPlayer(
            form.getName(),
            form.getNickname(),
            form.getEmail(),
            form.getPassword()
        );

        try {
            request.login(form.getEmail(), form.getPassword());
        } catch (ServletException ex) {
            return "redirect:./login";
        }

        return "redirect:./";
    }

}
