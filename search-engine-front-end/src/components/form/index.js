import { Form, Formik } from "formik";
import { renderError } from "./ErrorMessage";
import InputField from "./InputField";

const DynamicForm = ({ initialValues, validationSchema, handleSubmit, formFields, submitMessage, children, errorMessage, ...rest }) => {
	return (
		<Formik {...rest} initialValues={initialValues} validationSchema={validationSchema} onSubmit={handleSubmit}>
			<Form className="">
				{formFields.map((formField) => (
					<InputField
						key={formField.name}
						name={formField.name}
						description={formField.description}
						type={formField.type}
						as={formField.as}
						options={formField.options}
						disabled={formField.disabled}
					/>
				))}
				<span className="my-5"></span>
				{children}

				{renderError(errorMessage)}

				<div className="text-center">
					<button className="btn btn-primary mt-3" type="submit">
						{submitMessage}
					</button>
				</div>
			</Form>
		</Formik>
	);
};

export default DynamicForm;
