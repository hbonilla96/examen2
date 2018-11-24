package com.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.Repo.RepoCafe;
import com.modelos.Cafe;

@Controller
public class CafeController {

    @Autowired
    RepoCafe repo;

    /**
     * Get coffee type information from the form.
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/cafe")
    public String saveCoffee(Model model) {
        model.addAttribute("cafe", new Cafe());
        return "cafe";
    }


    /**
     * Save coffee type to database.
     *
     * @param coffee
     * @return
     */
    @RequestMapping(value = "cafe", method = RequestMethod.POST)
    public String insertCoffee(@ModelAttribute("coffee") Cafe cafe) {
        repo.save(cafe);
        return "redirect:/";
    }
}
