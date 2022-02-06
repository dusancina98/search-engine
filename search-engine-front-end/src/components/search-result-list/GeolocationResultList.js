import { useSelector } from "react-redux";
import { makeGeolocationSearchResults } from "../../store/job-application/selector";
import SearchResultItem from "./SearchResultItem";

const GeolocationResultList = () => {
	const geolocationSearchResults = useSelector(makeGeolocationSearchResults());

	return (
		<>
			{geolocationSearchResults.map((searchResult) => (
				<SearchResultItem
					key={searchResult.Id}
					firstName={searchResult.FirstName}
					lastName={searchResult.LastName}
					qualificationLevel={searchResult.QualificationLevel}
					city={searchResult.City}
					latitude={searchResult.Latitude}
					longitude={searchResult.Longitude}
					highlight={searchResult.Highlight}
				/>
			))}
		</>
	);
};

export default GeolocationResultList;
