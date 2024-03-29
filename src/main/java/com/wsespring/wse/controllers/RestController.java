package com.wsespring.wse.controllers;

import com.wsespring.wse.model.Word;
import com.wsespring.wse.service.Servise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {

    @Autowired
    Servise servise;

    @PostMapping("/addWordByAddNewModal")
    public ResponseEntity<Word> addNewWord(@RequestBody Word word) {
        servise.saveWord(word);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/getAll/{count}")
    public ResponseEntity<List<Word>> getAllWithCount(@PathVariable("count") Long count) {
        List<Word> list = null;
        if (count != null) {
            try {
                list = servise.loadCont(count);
            } catch (RuntimeException e) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            list = servise.loadAll();
        }
        return !list.isEmpty() || list != null ?
                new ResponseEntity<>(list, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping("/getAll")
    public ResponseEntity<List<Word>> getAll() {
        List<Word> list = servise.loadAll();
        return !list.isEmpty() || list != null ?
                new ResponseEntity<>(list, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
