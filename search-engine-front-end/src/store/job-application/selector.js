import { createSelector } from "@reduxjs/toolkit";
import { initialState } from "./reducer";

const selectJobApplication = (state) => state.jobApplication || initialState;

export const makeCreateJobApplicationError = () => createSelector(selectJobApplication, (substate) => substate.createJobApplicationError);
export const makeCreateJobApplicationSuccess = () => createSelector(selectJobApplication, (substate) => substate.createJobApplicationSuccess);

export const makeCities = () => createSelector(selectJobApplication, (substate) => substate.cities);
export const makeQualificationLevels = () => createSelector(selectJobApplication, (substate) => substate.qualificationLevels);
export const makeComplexSearchResults = () => createSelector(selectJobApplication, (substate) => substate.complexSearchResults);
export const makeGeolocationSearchResults = () => createSelector(selectJobApplication, (substate) => substate.geolocationSearchResults);
