import { useEffect } from "react";
import { useDispatch } from "react-redux";
import ComplexSearchForm from "../components/complex-search-form";
import FormAccessStatistics from "../components/form-access-statistics";
import SearchResultList from "../components/search-result-list";
import { searchWithComplexQueriesRequest } from "../store/job-application/actions";

const ComplexSearchPage = () => {
	const dispatch = useDispatch();

	useEffect(() => {
		dispatch(searchWithComplexQueriesRequest());
	}, [dispatch]);

	return (
		<>
			<div className="row m-auto w-75">
				<div className="col-4">
					<FormAccessStatistics />
					<div className="p-4 border mt-5" style={{ border: "1px", borderRadius: "15px" }}>
						<ComplexSearchForm />
					</div>
				</div>
				<div className="col-8">
					<SearchResultList />
				</div>
			</div>
		</>
	);
};

export default ComplexSearchPage;
