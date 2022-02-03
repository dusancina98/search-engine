import { configureStore } from "@reduxjs/toolkit";
import { jobApplicationReducer } from "./job-application/reducer";

export default configureStore({
	reducer: {
		jobApplication: jobApplicationReducer,
	},
});
