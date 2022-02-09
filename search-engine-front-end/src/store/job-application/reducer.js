import { createReducer } from "@reduxjs/toolkit";
import * as jobApplicationActions from "./actions";

export const initialState = {
	createJobApplicationError: null,
	createJobApplicationSuccess: null,
	cities: [],
	qualificationLevels: [],
	complexSearchResults: [],
	geolocationSearchResults: [],
	statistics: {
		MostFrequentPartOfTheDay: null,
		MostFrequentCity: null,
		PartsOfDay: [],
		Cities: [],
	},
};

export const jobApplicationReducer = createReducer(initialState, {
	[jobApplicationActions.createJobApplicationRequest]: (state, _action) => {
		return { ...state, createJobApplicationError: null, createJobApplicationSuccess: null };
	},
	[jobApplicationActions.createJobApplicationSuccess]: (state, _action) => {
		return { ...state, createJobApplicationError: null, createJobApplicationSuccess: "Successfully applied for job" };
	},
	[jobApplicationActions.createJobApplicationError]: (state, action) => {
		return { ...state, createJobApplicationError: action.payload, createJobApplicationSuccess: null };
	},
	[jobApplicationActions.retreiveCitiesRequest]: (state, _action) => {
		return { ...state, cities: [] };
	},
	[jobApplicationActions.retreiveCitiesSuccess]: (state, action) => {
		return { ...state, cities: action.payload };
	},
	[jobApplicationActions.retreiveCitiesError]: (state, _action) => {
		return { ...state, cities: [] };
	},
	[jobApplicationActions.retreiveQualificationLevelsRequest]: (state, _action) => {
		return { ...state, qualificationLevels: [] };
	},
	[jobApplicationActions.retreiveQualificationLevelsSuccess]: (state, action) => {
		return { ...state, qualificationLevels: action.payload };
	},
	[jobApplicationActions.retreiveQualificationLevelsError]: (state, action) => {
		return { ...state, qualificationLevels: [] };
	},
	[jobApplicationActions.searchWithComplexQueriesRequest]: (state, _action) => {
		return { ...state, complexSearchResults: [] };
	},
	[jobApplicationActions.searchWithComplexQueriesSuccess]: (state, action) => {
		return { ...state, complexSearchResults: action.payload };
	},
	[jobApplicationActions.searchWithComplexQueriesError]: (state, action) => {
		return { ...state, complexSearchResults: [] };
	},
	[jobApplicationActions.searchByGeolocationRequest]: (state, _action) => {
		return { ...state, geolocationSearchResults: [] };
	},
	[jobApplicationActions.searchByGeolocationSuccess]: (state, action) => {
		return { ...state, geolocationSearchResults: action.payload };
	},
	[jobApplicationActions.searchByGeolocationError]: (state, _action) => {
		return { ...state, geolocationSearchResults: [] };
	},
	[jobApplicationActions.retrieveFormAccessStatisticsRequest]: (state, _action) => {
		return {
			...state,
			statistics: {
				MostFrequentPartOfTheDay: null,
				MostFrequentCity: null,
				PartsOfDay: [],
				Cities: [],
			},
		};
	},
	[jobApplicationActions.retrieveFormAccessStatisticsSuccess]: (state, action) => {
		return { ...state, statistics: action.payload };
	},
	[jobApplicationActions.retrieveFormAccessStatisticsError]: (state, _action) => {
		return {
			...state,
			statistics: {
				MostFrequentPartOfTheDay: null,
				MostFrequentCity: null,
				PartsOfDay: [],
				Cities: [],
			},
		};
	},
});
