package ru.animal.world.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.animal.world.dto.UserDto;
import ru.animal.world.service.UserService;
import ru.animal.world.utils.City;

@Controller
public class RegistrationController {

    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public ModelAndView registration(Model model) {
        ModelAndView modelAndView = new ModelAndView("registration");
        modelAndView.addObject("request", new UserDto());
        modelAndView.addObject("cities", City.values());
        return modelAndView;
    }

    @PostMapping("registration")
    public ModelAndView addUser(@ModelAttribute("request") UserDto userDto) {
        UserDto userDtoView = userService.create(userDto);
        return userDtoView.getUserId() == null ? new ModelAndView("redirect:/registration") :
                                                 new ModelAndView("redirect:/login");
    }
}
