package ru.osokin.portalfbi.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.osokin.portalfbi.exceptions.FileStorageException;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileStorageService {
    @Value("${upload.path}")
    private String uploadPath;


    public String storeFile(MultipartFile file) {
        if (!file.isEmpty()) {
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file.getOriginalFilename();

            try {
                file.transferTo(new File(uploadPath + "/" + resultFileName));
            } catch (IOException e) {
                throw new FileStorageException("Cannot store file!");
            }

            return resultFileName;
        }
        return "";
    }
}
