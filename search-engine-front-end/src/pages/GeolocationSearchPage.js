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
			<div className="row m-auto p-5">
				<div className="col-9">
					<GeolocationSearchForm />
					<GeolocationResultList />
				</div>
				<div className="col-3">
					<FormAccessStatistics />
				</div>
			</div>
		</>
	);
};

export default GeolocationSearchPage;
