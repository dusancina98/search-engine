import { Form, Formik } from "formik";
import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { retrieveQualificationLevels, searchWithComplexQueries } from "../../services/jobApplicationService";
import { makeQualificationLevels } from "../../store/job-application/selector";
import { complexSearchFormFields, logicalOperatorOptions, logicalOperators } from "../../util/searchConstants";
import InputField from "../form/InputField";

const ComplexSearchForm = () => {
	const dispatch = useDispatch();
	const qualificationLevel = useSelector(makeQualificationLevels());

	let formFields = [
		{ name: complexSearchFormFields.firstName, description: "First name", type: "text", as: "input" },
		{ name: "operator1", description: "Operator", type: "select", as: "select", options: logicalOperatorOptions },
		{ name: complexSearchFormFields.lastName, description: "Last name", type: "text", as: "input" },
		{ name: "operator2", description: "Operator", type: "select", as: "select", options: logicalOperatorOptions },
		{ name: complexSearchFormFields.qualificationLevel, description: "Qualification level", type: "select", as: "select", options: [{ Id: "", Name: "" }, ...qualificationLevel] },
		{ name: "operator3", description: "Operator", type: "select", as: "select", options: logicalOperatorOptions },
		{ name: complexSearchFormFields.cvContent, description: "CV content", type: "text", as: "input" },
	];

	useEffect(() => {
		dispatch(retrieveQualificationLevels());
	}, [dispatch]);

	const handleSubmit = (values, _actions) => {
		let queries = [
			{
				Operator: logicalOperators.NO_OPERATOR,
				Field: complexSearchFormFields.firstName,
				Value: values[complexSearchFormFields.firstName],
			},
			{
				Operator: values.operator1,
				Field: complexSearchFormFields.lastName,
				Value: values[complexSearchFormFields.lastName],
			},
			{
				Operator: values.operator2,
				Field: complexSearchFormFields.qualificationLevel,
				Value: values[complexSearchFormFields.qualificationLevel],
			},
			{
				Operator: values.operator3,
				Field: complexSearchFormFields.cvContent,
				Value: values[complexSearchFormFields.cvContent],
			},
		];

		dispatch(searchWithComplexQueries(queries));
	};

	return (
		<>
			<Formik
				initialValues={{
					firstName: "",
					operator1: "AND",
					lastName: "",
					operator2: "AND",
					qualificationLevel: "",
					operator3: "AND",
					cvContent: "",
				}}
				onSubmit={handleSubmit}
			>
				<Form>
					{formFields.map((_formField, idx) => {
						if (idx % 2 === 0 && idx + 1 < formFields.length - 1) {
							return (
								<div key={formFields[idx].name} className="row">
									<div className="col-8">
										<InputField
											key={formFields[idx].name}
											name={formFields[idx].name}
											description={formFields[idx].description}
											type={formFields[idx].type}
											as={formFields[idx].as}
											options={formFields[idx].options}
											disabled={formFields[idx].disabled}
										/>
									</div>
									<div className="col-4">
										<InputField
											key={formFields[idx + 1].name}
											name={formFields[idx + 1].name}
											description={formFields[idx + 1].description}
											type={formFields[idx + 1].type}
											as={formFields[idx + 1].as}
											options={formFields[idx + 1].options}
											disabled={formFields[idx + 1].disabled}
										/>
									</div>
								</div>
							);
						}
						return "";
					})}

					<InputField
						key={formFields[formFields.length - 1].name}
						name={formFields[formFields.length - 1].name}
						description={formFields[formFields.length - 1].description}
						type={formFields[formFields.length - 1].type}
						as={formFields[formFields.length - 1].as}
						options={formFields[formFields.length - 1].options}
						disabled={formFields[formFields.length - 1].disabled}
					/>
					<div className="text-center">
						<button className="btn btn-primary mt-3" type="submit">
							Search
						</button>
					</div>
				</Form>
			</Formik>
			{}
		</>
	);
};

export default ComplexSearchForm;
