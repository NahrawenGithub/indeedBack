package com.backendspringboot.backend.controller;

import com.backendspringboot.backend.dao.DBFileService;
import com.backendspringboot.backend.entity.DBFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Arrays;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController()
public class FileController {
    private static final Logger logger = LoggerFactory.getLogger(FileController.class);
    @Autowired
    private DBFileService dbFileService;
    @PostMapping("/uploadFile")
    public  Object uploadFile(@RequestParam("file")MultipartFile file){
        DBFile dbFile = dbFileService.storeFile(file);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(dbFile.getId().toString())
                .toUriString();
        return ResponseEntity.status(HttpStatus.CREATED).body(dbFile);
    }
    @GetMapping("/downloadFile/{fileId}")
    public Object downloadFile(@PathVariable("fileId") String fileId){
        DBFile dbFile = dbFileService.getFile(fileId);
        return ResponseEntity.status(HttpStatus.OK).body(dbFile);
    }

}