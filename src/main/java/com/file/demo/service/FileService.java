package com.file.demo.service;

import com.file.demo.domain.FileDomain;

import java.io.File;

public interface FileService {
    FileDomain upload(String originalFilename);
}
