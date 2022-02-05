import ComplexSearchForm from "../components/complex-search-form";
import SearchResultList from "../components/search-result-list";

const ComplexSearchPage = () => {
	return (
		<>
			<div className="row m-auto w-75">
				<div className="col-4 p-4 border" style={{ border: "1px", borderRadius: "15px" }}>
					<ComplexSearchForm />
				</div>
				<div className="col-8">
					<SearchResultList />
				</div>
			</div>
		</>
	);
};

export default ComplexSearchPage;
