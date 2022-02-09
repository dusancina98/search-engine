import { useEffect } from "react";
import ReactApexChart from "react-apexcharts";
import { useDispatch, useSelector } from "react-redux";
import { retrieveFormAccessStatistics } from "../../services/jobApplicationService";
import { makeFormAccessStatistics } from "../../store/job-application/selector";

const FormAccessStatistics = () => {
	const dispatch = useDispatch();
	const statistics = useSelector(makeFormAccessStatistics());
	const { Cities, PartsOfDay, MostFrequentCity, MostFrequentPartOfTheDay } = statistics;

	useEffect(() => {
		dispatch(retrieveFormAccessStatistics());
	}, [dispatch]);

	const citiesChartOptions = {
		series: Cities.map((c) => c.Count),
		options: {
			labels: Cities.map((c) => c.Name),
		},
	};

	const partsOfDayChartOptions = {
		series: [
			{
				name: "Count",
				data: PartsOfDay.map((p) => p.Count),
			},
		],
		options: {
			chart: {
				type: "bar",
				height: 350,
			},
			plotOptions: {
				bar: {
					borderRadius: 2,
					horizontal: true,
				},
			},
			dataLabels: {
				enabled: false,
			},
			xaxis: {
				categories: PartsOfDay.map((p) => `${p.Name} [${p.From} - ${p.To}]`),
			},
		},
	};

	return (
		<div className="border-start">
			<ReactApexChart options={citiesChartOptions.options} series={citiesChartOptions.series} type="pie" width={380} />

			<ReactApexChart options={partsOfDayChartOptions.options} series={partsOfDayChartOptions.series} type="bar" height={350} />

			<div className="ms-4">
				<h4>Statistics</h4>
				{MostFrequentPartOfTheDay ? (
					<div>
						Najposeceniji deo dana:{" "}
						<b>
							{MostFrequentPartOfTheDay.Name} [{MostFrequentPartOfTheDay.From} - {MostFrequentPartOfTheDay.To}]
						</b>
						, broj poseta: <b>{MostFrequentPartOfTheDay.Count}</b>
					</div>
				) : (
					<div>Podaci o delu dana nisu dostupni</div>
				)}
				{MostFrequentCity ? (
					<div>
						Najvise poseta iz grada: <b>{MostFrequentCity.Name}</b>, broj poseta: <b>{MostFrequentCity.Count}</b>
					</div>
				) : (
					<div>Podaci o najposecenijem gradu nisu dostupni</div>
				)}
			</div>
		</div>
	);
};

export default FormAccessStatistics;
