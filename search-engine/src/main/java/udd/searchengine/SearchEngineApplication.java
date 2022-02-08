package udd.searchengine;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SpringBootApplication
public class SearchEngineApplication implements ApplicationRunner {
	private static final Logger logger = LogManager.getLogger(SearchEngineApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(SearchEngineApplication.class, args);
	}
	
	@Override
	public void run(ApplicationArguments applicationArguments) throws Exception {

		logger.info("Info log");
		logger.warn("Hey, This is a warning!");
		logger.error("Oops! We have an Error. OK");
		logger.fatal("Damn! Fatal error. Please fix me.");
	}
}
