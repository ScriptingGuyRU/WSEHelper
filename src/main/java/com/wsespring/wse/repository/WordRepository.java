package com.wsespring.wse.repository;

import com.wsespring.wse.model.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface WordRepository extends JpaRepository<Word,Long> {

    @Query(value = "select * from word where id in :ids", nativeQuery = true)
    List<Word> findWordsByIds(@Param("ids")Set ids);

    @Query(value = "select w.id from word w", nativeQuery = true)
    List<Long> findAllIds();
}
