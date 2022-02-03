package udd.searchengine.services;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import udd.searchengine.contracts.FileUtilService;

@Service
public class FileServiceImpl implements FileUtilService{

	@Override
	public String saveFileAndGetPath(MultipartFile multipartFile, String imageName, String imagesFolderPath) throws IOException {
		
		if(multipartFile != null) 
			saveFile(REL_IMAGE_PATH + imagesFolderPath, imageName + DEFAULT_EXTENSION, multipartFile);
		
		return getImagePath(imageName, imagesFolderPath);
	}
	
	public String getImagePath(String imageName, String imagesFolderPath) {
		return imagesFolderPath + PATH_SEPARATOR + imageName + DEFAULT_EXTENSION;
	}
	
	private void saveFile(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException {
        Path uploadPath = Paths.get(uploadDir);
         
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
         
        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {        
            throw new IOException("Could not save file: " + fileName, ioe);
        }      
    }
}
