import { Form, Formik } from "formik";
import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { retrieveCities, searchWithComplexQueries } from "../../services/jobApplicationService";
import { makeCities } from "../../store/job-application/selector";
import { geolocationSearchFormFields } from "../../util/searchConstants";
import InputField from "../form/InputField";
import * as Yup from "yup";

const GeolocationSearchForm = () => {
	const dispatch = useDispatch();
	const cities = useSelector(makeCities());

	let formFields = [
		{ name: geolocationSearchFormFields.city, description: "City", type: "select", as: "select", options: [{ Id: "", Name: "" }, ...cities] },
		{ name: geolocationSearchFormFields.radius, description: "Radius [km]", type: "number", as: "input" },
	];

	useEffect(() => {
		dispatch(retrieveCities());
	}, [dispatch]);

	const handleSubmit = (values, _actions) => {
		let queries = {
			City: values[geolocationSearchFormFields.city],
			Radius: geolocationSearchFormFields.radius,
		};

		dispatch(searchWithComplexQueries(queries));
	};

	return (
		<>
			<Formik
				initialValues={{
					city: "",
					radius: 20,
				}}
				validationSchema={Yup.object().shape({
					city: Yup.string().required(),
					radius: Yup.number().min(1, "Minimum radius is 1 km").max(500, "Maximum radius is 500 km"),
				})}
				onSubmit={handleSubmit}
			>
				<Form>
					<div className="row m-auto">
						<div className="col-3">
							<InputField
								key={formFields[0].name}
								name={formFields[0].name}
								description={formFields[0].description}
								type={formFields[0].type}
								as={formFields[0].as}
								options={formFields[0].options}
								disabled={formFields[0].disabled}
							/>
						</div>
						<div className="col-2">
							<InputField
								key={formFields[1].name}
								name={formFields[1].name}
								description={formFields[1].description}
								type={formFields[1].type}
								as={formFields[1].as}
								options={formFields[1].options}
								disabled={formFields[1].disabled}
							/>
						</div>
						<div className="col-1 align-self-end">
							<button className="btn btn-primary mx-5" type="submit">
								Search
							</button>
						</div>
					</div>
				</Form>
			</Formik>
		</>
	);
};

export default GeolocationSearchForm;
