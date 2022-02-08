import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { retrieveFormAccessStatistics } from "../../services/jobApplicationService";
import { makeFormAccessStatistics } from "../../store/job-application/selector";

const FormAccessStatistics = () => {
	const dispatch = useDispatch();
	const statistics = useSelector(makeFormAccessStatistics());

	useEffect(() => {
		dispatch(retrieveFormAccessStatistics());
	}, []);

	return (
		<div>
			<h4>Statistics</h4>
			{statistics.PartOfTheDay ? (
				<div>
					Najposeceniji deo dana: <b>{statistics.PartOfTheDay}</b>
				</div>
			) : (
				<div>Podaci o delu dana nisu dostupni</div>
			)}
			{statistics.City ? (
				<div>
					Najvise poseta iz grada: <b>{statistics.City.Name}</b>, broj poseta: <b>{statistics.City.Count}</b>
				</div>
			) : (
				<div>Podaci o najposecenijem gradu nisu dostupni</div>
			)}
		</div>
	);
};

export default FormAccessStatistics;
