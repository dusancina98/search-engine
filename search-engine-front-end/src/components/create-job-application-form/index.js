import { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { createJobApplication, retrieveCities, retrieveQualificationLevels } from "../../services/jobApplicationService";
import { makeCities, makeCreateJobApplicationError, makeCreateJobApplicationSuccess, makeQualificationLevels } from "../../store/job-application/selector";
import * as Yup from "yup";
import DynamicForm from "../form";

const CreateJobApplicationForm = () => {
	const dispatch = useDispatch();
	const cities = useSelector(makeCities());
	const qualificationLevel = useSelector(makeQualificationLevels());
	const errorMessage = useSelector(makeCreateJobApplicationError());
	const successMessage = useSelector(makeCreateJobApplicationSuccess());

	const [cvDocument, setCvDocument] = useState(null);
	const [coverLetterDocument, setCoverLetterDocument] = useState(null);

	let formFields = [
		{ name: "firstName", description: "First name", type: "text", as: "input" },
		{ name: "lastName", description: "Last name", type: "text", as: "input" },
		{ name: "qualificationLevel", description: "Qualification level", type: "select", as: "select", options: qualificationLevel },
		{ name: "city", description: "City", type: "select", as: "select", options: cities },
	];

	const onCvChange = (e) => {
		if (e.target.files?.[0]) {
			setCvDocument(e.target.files[0]);
		}
	};

	const onCoverLetterChange = (e) => {
		if (e.target.files?.[0]) {
			setCoverLetterDocument(e.target.files[0]);
		}
	};

	const handleSubmit = (values, _actions) => {
		let formData = new FormData();
		formData.append("ApplicationCandidate", JSON.stringify({ FirstName: values.firstName, LastName: values.lastName, CityId: values.city, QualificationLevel: values.qualificationLevel }));
		formData.append("CVDocument", cvDocument);
		formData.append("CoverLetterDocument", coverLetterDocument);

		dispatch(createJobApplication(formData));
	};

	useEffect(() => {
		dispatch(retrieveCities());
		dispatch(retrieveQualificationLevels());
	}, [dispatch]);

	return (
		<>
			{successMessage && (
				<div className="alert alert-success" role="alert">
					<i className="fa fa-check mx-2"></i>
					{successMessage}
				</div>
			)}
			<DynamicForm
				initialValues={{
					firstName: "",
					lastName: "",
					qualificationLevel: "",
					city: "",
				}}
				validationSchema={Yup.object().shape({
					firstName: Yup.string().required(),
					lastName: Yup.string().required(),
					qualificationLevel: Yup.string().required(),
					city: Yup.string().required(),
				})}
				formFields={formFields}
				handleSubmit={handleSubmit}
				submitMessage="Submit"
				errorMessage={errorMessage}
			>
				<label className="mt-1">Insert CV document</label>
				<input className="form-control" type="file" accept="application/pdf" onChange={onCvChange} />
				<label className="mt-1">Insert cover letter document</label>
				<input className="form-control mb-3" type="file" accept="application/pdf" onChange={onCoverLetterChange} />
			</DynamicForm>
		</>
	);
};

export default CreateJobApplicationForm;
