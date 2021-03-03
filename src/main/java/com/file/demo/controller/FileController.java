package com.file.demo.controller;

import com.file.demo.service.FileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@RestController
@RequestMapping("/api/file")
@CrossOrigin(origins= { "http://localhost:8081", }, maxAge=3000)
public class FileController {

    private final FileService fileService;
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping(value="/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        /*kinukuha nya yung file path kung saan dpt maiupload
        magsasave na folder*/
        File convertFile = new File("C:\\Users\\john%brix17\\Downloads\\uploaded\\"+file.getOriginalFilename());
        convertFile.createNewFile();
        FileOutputStream fout = new FileOutputStream(convertFile);
        fout.write(file.getBytes());

        System.out.println(file.getOriginalFilename().toString());
        System.out.println(file.getName());
        fout.close();
        return new ResponseEntity<>(fileService.upload(convertFile), HttpStatus.OK);
  /*      return new ResponseEntity<>("Successfully uploaded", HttpStatus.OK);*/
        /*return new ResponseEntity<>(fileService.upload(file.getOriginalFilename()), HttpStatus.OK);*/
    }

}
