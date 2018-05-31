package babinski.maciej.spring.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller  //to jest to samo co komponent , Spring tym zarzada nie my
@RequestMapping("/")

public class IndexController {


    @RequestMapping("/")  // wysyła zapytanie o wszystkie metody , mówi pod jakim adresem sie znajduje
    public String index() {
        return "index";
    }


    @GetMapping("/login")
    public String login(Principal principal) {
        return principal == null ? "login" : "redirect:/";
    }
}
