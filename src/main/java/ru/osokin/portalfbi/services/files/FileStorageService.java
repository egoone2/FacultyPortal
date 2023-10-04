package ru.osokin.portalfbi.services.files;

import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.osokin.portalfbi.exceptions.FileStorageException;
import ru.osokin.portalfbi.models.ServerFile;
import ru.osokin.portalfbi.repositories.FileRepository;
import ru.osokin.portalfbi.security.UserDetailsImpl;


import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileStorageService {

    private final FileRepository fileRepository;
    private final PreviewPngService previewPngService;
    @Value("${upload.path}")
    private String uploadPath;




    public ServerFile storeFile(MultipartFile file) {
        ServerFile fileEntity = new ServerFile();
        if (!file.isEmpty()) {
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file.getOriginalFilename();

            try {
                File uploadedFile = new File(uploadPath + "/" + resultFileName);
                file.transferTo(uploadedFile);

                fileEntity = getFileEntity(uploadedFile);
                fileRepository.save(fileEntity);

//                previewPngService.makePreviewPng(uploadedFile);
//                uploadedFile.setReadable(true);
            } catch (IOException e) {
                throw new FileStorageException("Cannot store file!");
            }

            return fileEntity;
        }
        return fileEntity;
    }

    private ServerFile getFileEntity(File file) {
        ServerFile serverFile = new ServerFile();
        serverFile.setName(file.getName());
        serverFile.setSizeInKb(getSizeInKB(file));
        return serverFile;
    }

    private int getSizeInKB(File file) {
        return (int) ((FileUtils.sizeOf(file) / 1024));
    }
}
