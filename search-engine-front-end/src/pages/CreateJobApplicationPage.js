import { useEffect, useState } from "react";
import { useDispatch } from "react-redux";
import CreateJobApplicationForm from "../components/create-job-application-form";
import { logFormAccessLocation } from "../services/jobApplicationService";
import { createJobApplicationRequest } from "../store/job-application/actions";

const CreateJobApplicationPage = () => {
	const dispatch = useDispatch();
	const [location, setLocation] = useState(null);

	const getCurrentCoords = () => {
		console.log(navigator);
		if (navigator.geolocation) {
			navigator.geolocation.getCurrentPosition((position) => setLocation({ Latitude: position.coords.latitude, Longitude: position.coords.longitude }));
		}
	};

	useEffect(() => {
		if (location) {
			dispatch(logFormAccessLocation(location));
		}
	}, [dispatch, location]);

	useEffect(() => {
		dispatch(createJobApplicationRequest());
		getCurrentCoords();
	}, [dispatch]);

	return (
		<>
			<div className="row m-auto w-50 p-4 border" style={{ border: "1px", borderRadius: "15px" }}>
				<CreateJobApplicationForm />
			</div>
		</>
	);
};

export default CreateJobApplicationPage;
