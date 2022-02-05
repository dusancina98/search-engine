package udd.searchengine.entities.elasticsearch;

public enum SearchType {
	regular,
	fuzzy,
	phrase,
	range,
	prefix,
	match,
	geospatial
}
