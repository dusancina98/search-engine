package udd.searchengine.services;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import udd.searchengine.contracts.FileUtilService;

@Service
public class FileServiceImpl implements FileUtilService{

	@Override
	public String saveFileAndGetPath(MultipartFile multipartFile, String fileName, String pathToFolder) throws IOException {
		
		if(multipartFile != null) 
			saveFile(REL_IMAGE_PATH + pathToFolder, fileName + DEFAULT_EXTENSION, multipartFile);
		
		return getImagePath(fileName, pathToFolder);
	}
	
	public String getImagePath(String fileName, String pathToFolder) {
		return pathToFolder + PATH_SEPARATOR + fileName + DEFAULT_EXTENSION;
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

	@Override
	public String extractTextFromPdf(String pathToFile) {
		try {
			PDDocument pdDoc = Loader.loadPDF(new File(REL_IMAGE_PATH + pathToFile));
			//PDDocument pdDoc = PDDocument.load(file);
			PDFTextStripper pdfStripper = new PDFTextStripper();
			return pdfStripper.getText(pdDoc);
		} catch (IOException e) {
			System.out.println("Greksa pri konvertovanju dokumenta u pdf");
		}
		return null;
//		try {
//			File f = new File(REL_IMAGE_PATH + pathToFile);
//			PDFParser parser = new PDFParser(new RandomAccessFile(f, "r"));
//			parser.parse();			
//			COSDocument cosDoc = parser.getDocument();
//			PDFTextStripper pdfStripper = new PDFTextStripper();
//			PDDocument pdDoc = new PDDocument(cosDoc);
//			return pdfStripper.getText(pdDoc);
//			//return parsedText;
//		} catch (FileNotFoundException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		return null;
//		try (PDDocument document = PDDocument.load(new File(REL_IMAGE_PATH + pathToFile))) {
//
//            if (!document.isEncrypted()) {
//
//                PDFTextStripper tStripper = new PDFTextStripper();
//                return tStripper.getText(document);
//               
//
//            }
//        } catch (IOException e){
//            System.err.println("Exception while trying to read pdf document - " + e);
//        }
//        return null;

//		try {
//
//			System.out.println(pathToFile);
//			//PDDocument pdDoc = Loader.loadPDF(new File(REL_IMAGE_PATH + pathToFile));
//			File file = new File(REL_IMAGE_PATH + pathToFile);
//			PDDocument pdDoc = PDDocument.load(file);
//			PDFTextStripper pdfStripper = new PDFTextStripper();
//			return pdfStripper.getText(pdDoc);
////			File file = new File(pathToFile);
////			PDFParser parser = new PDFParser(new RandomAccessFile(file, "r"));
////			parser.parse();
////			PDFTextStripper textStripper = new PDFTextStripper();
////			String text = textStripper.getText(parser.getPDDocument());
////			return text;
//		} catch (IOException e) {
//			System.out.println("Greksa pri konvertovanju dokumenta u pdf");
//		}
//		return null;
	}
}
