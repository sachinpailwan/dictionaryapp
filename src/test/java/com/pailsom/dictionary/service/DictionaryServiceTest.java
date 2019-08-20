package com.pailsom.dictionary.service;

import com.pailsom.dictionary.dto.DictionaryWord;
import com.pailsom.dictionary.repository.DictionaryRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class DictionaryServiceTest {

    @Autowired
    private DictionaryService service;

    @Autowired
    private DictionaryRepository repository;

    @Value("classpath::dictionary.txt")
    private File dictionaryFile;

    @Before
    public void setup() throws IOException{
        service.uploadDictionaryData(Paths.get("src","test","resouces","dictionary.txt"));
    }

    @Test
    public void search() throws IOException {
        DictionaryWord word = repository.search("vain");
        Assert.assertNotNull(word.getMeaningList());
        Assert.assertTrue(word.getMeaningList().contains("unproductive of success"));
    }

    @Test
    public void searchNotFound() throws IOException {
        DictionaryWord word = repository.search("vainTr");
        Assert.assertNotNull(word.getMeaningList());
        Assert.assertTrue(word.getMeaningList().contains("Meaning not found in dictionary"));
    }
}
