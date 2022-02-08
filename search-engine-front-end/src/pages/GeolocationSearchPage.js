import { useEffect } from "react";
import { useDispatch } from "react-redux";
import FormAccessStatistics from "../components/form-access-statistics";
import GeolocationSearchForm from "../components/geolocation-search-form";
import GeolocationResultList from "../components/search-result-list/GeolocationResultList";
import { searchByGeolocationRequest } from "../store/job-application/actions";

const GeolocationSearchPage = () => {
	const dispatch = useDispatch();

	useEffect(() => {
		dispatch(searchByGeolocationRequest());
	}, [dispatch]);

	return (
		<>
			<div className="row m-auto w-75">
				<FormAccessStatistics />

				<div className="col-12 mt-3">
					<GeolocationSearchForm />
					<GeolocationResultList />
				</div>
			</div>
		</>
	);
};

export default GeolocationSearchPage;
