package com.pailsom.dictionary.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Node {
    private static final String NOT_FOUND = "Meaning not found in dictionary";
    private static int NUMBER_OF_CHARACTERS = 26;
    Node [] children = new Node[NUMBER_OF_CHARACTERS];
    List<String> meanings = new ArrayList<>();

    public static int getCharIndex(char e){
        return e-'a';
    }

    private Node getNode(int index){
        return children[index];
    }
    private void setNode(char c,Node node){
        children[getCharIndex(c)]=node;
    }
    public void add(String str,int index,List<String> meanings){
        if(index == str.length()-1){
            this.meanings.addAll(meanings);
            return;
        }
        char currCode = str.charAt(index);
        int charCode = getCharIndex(currCode);
        Node child = getNode(charCode);
        if(null == child){
            child = new Node();
            setNode(currCode,child);
        }
        child.add(str,index+1,meanings);
    }

    public List<String>  search(String str,int index){
        if(index == str.length()-1){
            return this.meanings;
        }
        char currCode = str.charAt(index);
        int charCode = getCharIndex(currCode);
        Node child = getNode(charCode);
        if(null == child){
            return Arrays.asList(NOT_FOUND);
        }
        return child.search(str,index+1);
    }
}
