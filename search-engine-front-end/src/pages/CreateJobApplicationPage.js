import { useEffect } from "react";
import { useDispatch } from "react-redux";
import CreateJobApplicationForm from "../components/create-job-application-form";
import { createJobApplicationRequest } from "../store/job-application/actions";

const CreateJobApplicationPage = () => {
	const dispatch = useDispatch();

	useEffect(() => {
		dispatch(createJobApplicationRequest());
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
