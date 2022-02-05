import { useSelector } from "react-redux";
import { makeComplexSearchResults } from "../../store/job-application/selector";
import SearchResultItem from "./SearchResultItem";

const SearchResultList = () => {
	const complexSearchResults = useSelector(makeComplexSearchResults());

	return (
		<>
			{complexSearchResults.map((searchResult) => (
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

export default SearchResultList;
