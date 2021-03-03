package com.wsespring.wse.controllers;

import com.wsespring.wse.model.Word;
import com.wsespring.wse.service.Servise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping()
public class MainController {

    @Autowired
    Servise servise;

    @GetMapping()
    public String mainPageGet() {
        return "main";
    }

    @PostMapping()
    public String mainPagePost() {
        return "main";
    }


    @GetMapping(value = "add-word")
    public ModelAndView addWordGet() {
        return new ModelAndView("new-word");
    }

    @PostMapping(value = "add-word")
    public String addWordPost(@RequestParam(value = "eng") String eng, @RequestParam(value = "rus") String rus) {
        servise.saveWord(new Word(eng,rus));
        return "redirect:/";
    }

    @GetMapping(value = "check-word")
    public ModelAndView checkWordGet() {
        ModelAndView mav = new ModelAndView("checkWord");
        Word word = servise.loadWordById(new Long(1));
        mav.addObject("word", word);
        return mav;
    }
}
