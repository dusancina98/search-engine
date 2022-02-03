import { ErrorMessage, Field } from "formik";
import { TextInputErrorMessage } from "./ErrorMessage";

const InputField = ({ name, type, as, description, options = [], disabled = false }) => {
	return (
		<>
			<label className="form-label" htmlFor={name}>
				{description}
			</label>
			{["input", "textarea"].includes(as) && <Field className="form-control" name={name} type={type} as={as} placeholder={description} disabled={disabled} />}
			{as === "select" && (
				<Field className="form-control" name={name} as={as} placeholder={description}>
					{options.map((option) => (
						<option key={option.Id} value={option.Id}>
							{option.Name}
						</option>
					))}
				</Field>
			)}

			<ErrorMessage name={name} component={TextInputErrorMessage} />
		</>
	);
};

export default InputField;
