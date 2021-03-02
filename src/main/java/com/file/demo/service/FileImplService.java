package com.file.demo.service;

import com.file.demo.dto.FileDto;
import com.file.demo.domain.FileDomain;
import com.file.demo.repository.FileRepository;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class FileImplService implements FileService {

    private final FileRepository fileRepository;

    public FileImplService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }
    /*this is for profile.jpg String originalFilename*/
    @Override
    public FileDomain upload(File originalFilename){
        //dto
        FileDto fileDto = new FileDto();
        fileDto.setFile(originalFilename.toString());
        //domain
        FileDomain fileDomain = new FileDomain();
        fileDomain.setFile(fileDto.getFile());


        return fileRepository.save(fileDomain);
    }
}
