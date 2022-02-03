import { capitalizeFirstLetter } from "../../util/stringUtil";

const TextInputErrorMessage = (props) => {
	return <div className="text-danger">{capitalizeFirstLetter(props.children)}</div>;
};

const renderError = (error) => {
	return error && <div className="d-flex justify-content-center text-danger">{capitalizeFirstLetter(error)}</div>;
};

export { TextInputErrorMessage, renderError };
