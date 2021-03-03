package com.wsespring.wse.service;

import com.wsespring.wse.model.Word;
import com.wsespring.wse.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceImpl implements Servise {

    @Autowired
    WordRepository repository;

    @Override
    public Word saveWord(Word w) {
        return repository.save(w);
    }

    @Override
    public Word loadWordById(long id) {
        return repository.getOne(id);
    }

    @Override
    public List<Word> loadAll() {
        return repository.findAll();
    }
}
