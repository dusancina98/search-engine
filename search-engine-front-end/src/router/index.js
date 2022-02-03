import { routes } from "./routes";
import { Routes, Route } from "react-router-dom";
import CreateJobApplicationPage from "../pages/CreateJobApplicationPage";

const PageRouter = () => {
	return (
		<Routes>
			<Route path={routes.CREATE_JOB_APPLICATION} element={<CreateJobApplicationPage />} />
		</Routes>
	);
};

export default PageRouter;
