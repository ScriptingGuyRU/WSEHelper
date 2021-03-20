package com.wsespring.wse.service;

import com.wsespring.wse.model.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

public interface Servise {
    Word saveWord(Word w);

    Word loadWordById(long id);

    List<Word> loadAll();

    List<Word> loadCont(Long count);

}
