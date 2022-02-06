import { routes } from "./routes";
import { Routes, Route } from "react-router-dom";
import CreateJobApplicationPage from "../pages/CreateJobApplicationPage";
import ComplexSearchPage from "../pages/ComplexSearchPage";
import GeolocationSearchPage from "../pages/GeolocationSearchPage";

const PageRouter = () => {
	return (
		<Routes>
			<Route path={routes.CREATE_JOB_APPLICATION} element={<CreateJobApplicationPage />} />
			<Route path={routes.COMPLEX_SEARCH} element={<ComplexSearchPage />} />
			<Route path={routes.GEOLOCATION_SEARCH} element={<GeolocationSearchPage />} />
		</Routes>
	);
};

export default PageRouter;
