package udd.searchengine.services.util.elasticsearch;

import java.util.Map;

public class PartsOfDay {
	public static final Map<String, Map<String, String>> values = Map.of(
		    "Jutro",  Map.of(
				    "gte", "2021-02-08T06:00:00.000",
				    "lt", "2021-02-08T12:00:00.000",
		    		"from", "06h",
		    		"to", "12h"),
		    "Podne", Map.of(
				    "gte", "2021-02-08T12:00:00.000",
				    "lt", "2021-02-08T17:00:00.000",
		    		"from", "12h",
		    		"to", "17h"),
		    "Veče", Map.of(
				    "gte", "2021-02-08T17:00:00.000",
				    "lt", "2021-02-08T21:00:00.000",
		    		"from", "17h",
		    		"to", "21h"),
		    "Noć", Map.of(
				    "gte", "2021-02-08T21:00:00.000",
				    "lt", "2021-02-08T23:59:00.000",
		    		"from", "21h",
		    		"to", "24h"),
		    "Zora", Map.of(
				    "gte", "2021-02-08T00:00:00.000",
				    "lt", "2021-02-08T06:00:00.000",
		    		"from", "00h",
		    		"to", "06h")

		);
}
