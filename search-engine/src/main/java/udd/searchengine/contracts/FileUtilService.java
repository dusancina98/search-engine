package udd.searchengine.contracts;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;


public interface FileUtilService extends PdfUtil{
	
    static final String PATH_SEPARATOR = "/";
    
    static final String DEFAULT_EXTENSION = ".pdf";
    
    static final String REL_IMAGE_PATH = "./documents";
    
	String saveFileAndGetPath(MultipartFile multipartFile, String imageName, String imagesFolderPath) throws IOException;

}
