package ru.osokin.portalfbi.services.files;


import ij.IJ;
import ij.ImagePlus;
import ij.process.ImageProcessor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
@Service
public class PreviewPngService {

    @Value("${previews.path}")
    private String previewsFilePath;
    @Value("${upload.path}")
    private String uploadPath;

    public void makePreviewPng(File uploadedFile) throws IOException {
        ImagePlus image = IJ.openImage(uploadedFile.getAbsolutePath());
        image.show();
        ImageProcessor imageProcessor = image
                .getProcessor()
                .resize(167, 167, true);
        image.setProcessor(imageProcessor);
        String path = previewsFilePath + "\\" + uploadedFile.getName();
        IJ.saveAs(image, getFileExtension(uploadedFile) ,path);
    }

    private String getFileExtension(File file) {
        return FilenameUtils.getExtension(file.getName());
    }

}
