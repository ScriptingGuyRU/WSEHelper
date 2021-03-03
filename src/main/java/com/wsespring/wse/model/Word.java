package com.wsespring.wse.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "WORD")
public class Word {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "rus", length = 100, nullable = false)
    private String rus;

    @Column(name = "eng", length = 100, nullable = false)
    private String eng;

    public Word(String eng, String rus) {
        this.rus = rus;
        this.eng = eng;
    }

    public Word() {
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRus() {
        return rus;
    }

    public void setRus(String rus) {
        this.rus = rus;
    }

    public String getEng() {
        return eng;
    }

    public void setEng(String eng) {
        this.eng = eng;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word = (Word) o;
        return id == word.id &&
                Objects.equals(rus, word.rus) &&
                Objects.equals(eng, word.eng);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rus, eng);
    }
}
