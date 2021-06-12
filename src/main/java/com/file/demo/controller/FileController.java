package com.file.demo.controller;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;
import com.dropbox.core.v2.users.DbxUserUsersRequests;
import com.dropbox.core.v2.users.FullAccount;
import com.file.demo.service.FileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;
import java.util.Objects;

@RestController
@RequestMapping("/api/file")
@CrossOrigin(origins = {"http://localhost:8082",}, maxAge = 3000)
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    private static final String ACCESS_TOKEN = "VguaSnkJNOIAAAAAAAAAAUWxbjvrETaBBWd9ES_xzxzG1yCxaQyMPSPeZqpuvbhA";


    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity uploadFile(@RequestParam("file") MultipartFile file) throws IOException, DbxException {

        upload(file);
        System.out.println("WORKING");
        File convertFile = new File("C:\\Users\\john brix17\\Downloads\\uploaded\\" + file.getOriginalFilename());
        convertFile.createNewFile();
        FileOutputStream fout = new FileOutputStream(convertFile);
        fout.write(file.getBytes());


        System.out.println(file.getOriginalFilename().toString());
        System.out.println(file.getName());
        fout.close();

        String fileUpload = "/" + file.getOriginalFilename();
        return new ResponseEntity(fileService.upload(fileUpload), HttpStatus.OK);
    }

    private void upload(MultipartFile file) throws DbxException {
        // Upload "test.txt" to Dropbox
        try (InputStream in = new FileInputStream("C:\\Users\\john brix17\\Pictures\\profile\\"+file.getOriginalFilename())) {
            DbxRequestConfig config;
            config = new DbxRequestConfig("dropbox/spring-boot-file-upload");
            DbxClientV2 client;
            client = new DbxClientV2(config, ACCESS_TOKEN);
            FullAccount account;
            DbxUserUsersRequests r1 = client.users();
            account = r1.getCurrentAccount();
            System.out.println(account.getName().getDisplayName());

            FileMetadata metadata = client.files().uploadBuilder("/images/"+file.getOriginalFilename())
                    .uploadAndFinish(in);
            System.out.println("SUccessfully upload!");

        } catch (IOException | DbxException e) {
            e.printStackTrace();
        }
    }
}

