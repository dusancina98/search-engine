const SearchResultItem = ({ firstName, lastName, city, qualificationLevel, latitude, longitude, highlight }) => {
	return (
		<>
			<div className="row p-0 m-auto my-4">
				<div className="col-12 d-flex justify-content-between">
					<div>
						<span>
							{firstName} {lastName}
						</span>
						<span className="mx-5">{qualificationLevel} stepen strucne spreme</span>
					</div>
					<span className="text-secondary">
						<i className="fa fa-map-marker  mx-2"></i>
						{city} [{latitude}, {longitude}]
					</span>
				</div>
				<div className="col-12  shadow p-2 mt-2" style={{ borderRadius: "15px" }}>
					{highlight}
					asdddddddddddddddddddddddddddddddddddddddddddddd asdsassssssss asdasdasdassd as adadasdasdasdas adasdsada asd
				</div>
			</div>
		</>
	);
};

export default SearchResultItem;
