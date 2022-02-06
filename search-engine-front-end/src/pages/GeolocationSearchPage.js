import GeolocationSearchForm from "../components/geolocation-search-form";
import GeolocationResultList from "../components/search-result-list/GeolocationResultList";

const GeolocationSearchPage = () => {
	return (
		<>
			<div className="row m-auto w-75">
				<div className="col-12">
					<GeolocationSearchForm />
					<GeolocationResultList />
				</div>
			</div>
		</>
	);
};

export default GeolocationSearchPage;
