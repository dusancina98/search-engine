package udd.searchengine.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import udd.searchengine.contracts.SearchService;
import udd.searchengine.contracts.dto.SimpleQueryWithOperatorDTO;

@RestController
@RequestMapping(value = "api/search")
public class SearchController {

	@Autowired
	private SearchService searchService;
	
	@PostMapping("/custom")
	@CrossOrigin
	public ResponseEntity<?> searchJobApplications(@RequestBody List<SimpleQueryWithOperatorDTO> queries){
		try {
	
			return new ResponseEntity<>(searchService.search(queries), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
