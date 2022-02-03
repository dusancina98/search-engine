import { createReducer } from "@reduxjs/toolkit";
import * as jobApplicationActions from "./actions";

export const initialState = {
	createJobApplicationError: null,
	createJobApplicationSuccess: null,
	cities: [],
	qualificationLevels: [],
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
	[jobApplicationActions.retreiveCitiesError]: (state, action) => {
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
});
