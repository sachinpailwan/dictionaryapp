package com.pailsom.dictionary.dto;

import java.util.List;

public class DictionaryWord {
    private String word;
    private List<String> meaningList;

    public DictionaryWord(String word, List<String> meaningList){
        this.word =word;
        this.meaningList = meaningList;
    }

    public DictionaryWord(){
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public List<String> getMeaningList() {
        return meaningList;
    }

    public void setMeaningList(List<String> meaningList) {
        this.meaningList = meaningList;
    }
}
