import CreateJobApplicationForm from "../components/create-job-application-form";

const CreateJobApplicationPage = () => {
	return (
		<>
			<div className="row m-auto w-50 p-4 border" style={{ border: "1px", borderRadius: "15px" }}>
				<CreateJobApplicationForm />
			</div>
		</>
	);
};

export default CreateJobApplicationPage;
