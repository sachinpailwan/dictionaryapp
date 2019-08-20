package com.pailsom.dictionary.service;

import com.pailsom.dictionary.dto.DictionaryWord;
import com.pailsom.dictionary.repository.DictionaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class DictionaryService {

    @Autowired
    private DictionaryRepository repository;

    public void uploadDictionaryData(Path filePath) throws IOException {
        try(Stream<String> lines = Files.lines(filePath))
        {
            lines.forEach(s -> {
                String word = s.split("=")[0];
                String meaningStr = s.split("=")[1];
                List<String> meaningList =  Arrays.asList(meaningStr.split(","))
                        .stream().collect(Collectors.toList());
                repository.put(word,meaningList);
            });
        }
    }

    public DictionaryWord search(String word) {
        return repository.search(word);
    }
}
