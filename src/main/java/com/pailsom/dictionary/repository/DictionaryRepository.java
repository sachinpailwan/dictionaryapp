package com.pailsom.dictionary.repository;

import com.pailsom.dictionary.dto.DictionaryWord;
import com.pailsom.dictionary.dto.Node;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class DictionaryRepository {
    Node root = new Node();

    public void put(String word,List<String> meaningWordList){
        root.add(word,0,meaningWordList);
    }

    public DictionaryWord search(String word){
        DictionaryWord dictionaryWord = new DictionaryWord();
        dictionaryWord.setWord(word);
        dictionaryWord.setMeaningList(root.search(word,0));
        return dictionaryWord;
    }

}
