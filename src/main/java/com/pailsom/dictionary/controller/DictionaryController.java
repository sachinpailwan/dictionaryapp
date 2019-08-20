package com.pailsom.dictionary.controller;

import com.pailsom.dictionary.dto.DictionaryWord;
import com.pailsom.dictionary.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class DictionaryController {
    public static String uploadDirectory = System.getProperty("user.dir")+"/uploads";


    @Autowired
    private DictionaryService service;

    @RequestMapping("/")
    public String uploadPage(){
        return "UploadView";
    }

    @RequestMapping(value = "/upload" ,method = RequestMethod.POST)
    public String upload(Model model, @RequestParam("file")MultipartFile file) throws IOException {
        Path filePath = Paths.get(uploadDirectory,file.getOriginalFilename());
        Files.write(filePath,file.getBytes());
        new Thread(()-> {
            try {
                service.uploadDictionaryData(filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
        model.addAttribute("msg","Successfully uploaded file :"+file.getOriginalFilename());
        return "UploadViewStatus";
    }

    @RequestMapping(value = "/search/{word}",method=RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public String search(Model model, @PathVariable("word") final String word){
        model.addAttribute("result",service.search(word));
        return "SearchResult";
    }
}
