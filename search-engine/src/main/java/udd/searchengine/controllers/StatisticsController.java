package udd.searchengine.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import udd.searchengine.contracts.StatisticsService;
import udd.searchengine.contracts.dto.FormAccessLocationDTO;

@RestController
@RequestMapping(value = "api/statistics")
public class StatisticsController {

	@Autowired
	private StatisticsService statisticService;
	
	@PostMapping("/form-access")
	@CrossOrigin
	public ResponseEntity<?> logAccessLocation(@RequestBody FormAccessLocationDTO locationDTO){
		try {
			statisticService.logFormAccessLocation(locationDTO);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/form-access")
	@CrossOrigin
	public ResponseEntity<?> getStatistics(){
		try {
			
			return new ResponseEntity<>(statisticService.getStatistics(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
