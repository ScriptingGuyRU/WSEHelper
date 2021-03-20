package com.wsespring.wse.service;

import com.wsespring.wse.model.Word;
import com.wsespring.wse.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Service
public class ServiceImpl implements Servise {

    @PersistenceContext
    private EntityManager em;

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

    @Override
    public List<Word> loadCont(Long count) {
        List<Long> allIds = repository.findAllIds();

        Set<Long> ids = randomByIds(count, allIds);
        return repository.findWordsByIds(ids);
    }

    private Set<Long> randomByIds(Long count, List<Long> allIds) {
        Set<Long> set = new HashSet<>();

        Random random = new Random();
        int randomInt;
        int i = 0;
        while (i < count) {
            randomInt = random.nextInt(Math.toIntExact(count));
            if (set.add(allIds.get(randomInt))) {
                i++;
            }
        }

        return set;
    }
}
