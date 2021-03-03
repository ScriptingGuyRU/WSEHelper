package com.wsespring.wse.service;

import com.wsespring.wse.model.Word;
import com.wsespring.wse.repository.WordRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


//@DirtiesContext
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = TestDataBaseConfig.class)
//@WebAppConfiguration
@SpringBootTest
public class ServiceImplTest {

//    @Resource
//    private EntityManagerFactory emf;
//    protected EntityManager em;

    @MockBean
    protected WordRepository repository;


//    @Before
//    public void setUp() throws Exception {
//        em = emf.createEntityManager();
//    }

    @Test
    public void saveWord() {
        Word newWord = new Word("Rus","Eng");
        Word saveWord = repository.save(newWord);
        Assert.assertEquals(newWord,saveWord);
    }

    @Test
    public void loadWordById() {
    }

    @Test
    public void loadAll() {
    }
}