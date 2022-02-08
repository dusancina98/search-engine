import { createAsyncThunk } from "@reduxjs/toolkit";
import client from "./client";

export const createJobApplication = createAsyncThunk("job-application/create", async (jobApplication, _thunkAPI) => {
	let response = await client.post(`/job-application`, jobApplication);
	return response.data;
});

export const retrieveCities = createAsyncThunk("job-application/cities/retreive", async (_params, _thunkAPI) => {
	let response = await client.get(`/job-application/cities`);
	return response.data;
});

export const retrieveQualificationLevels = createAsyncThunk("job-application/qualification-levels/retreive", async (_params, _thunkAPI) => {
	let response = await client.get(`/job-application/qualification-levels`);
	return response.data;
});

export const searchWithComplexQueries = createAsyncThunk("job-application/search/complex", async (queries, _thunkAPI) => {
	let response = await client.post(`/search/custom`, queries);
	return response.data;
});

export const searchByGeolocation = createAsyncThunk("job-application/search/geolocation", async (params, _thunkAPI) => {
	let response = await client.get(`/search/geolocation`, { params });
	return response.data;
});

export const logFormAccessLocation = createAsyncThunk("job-application/form-access/statistics", async (params, _thunkAPI) => {
	let response = await client.post(`/statistics/form-access`, params);
	return response.data;
});
