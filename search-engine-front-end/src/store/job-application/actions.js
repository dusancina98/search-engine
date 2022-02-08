import { createJobApplication, retrieveCities, retrieveFormAccessStatistics, retrieveQualificationLevels, searchByGeolocation, searchWithComplexQueries } from "../../services/jobApplicationService";

export const createJobApplicationRequest = createJobApplication.pending;
export const createJobApplicationSuccess = createJobApplication.fulfilled;
export const createJobApplicationError = createJobApplication.rejected;

export const retreiveCitiesRequest = retrieveCities.pending;
export const retreiveCitiesSuccess = retrieveCities.fulfilled;
export const retreiveCitiesError = retrieveCities.rejected;

export const retreiveQualificationLevelsRequest = retrieveQualificationLevels.pending;
export const retreiveQualificationLevelsSuccess = retrieveQualificationLevels.fulfilled;
export const retreiveQualificationLevelsError = retrieveQualificationLevels.rejected;

export const searchWithComplexQueriesRequest = searchWithComplexQueries.pending;
export const searchWithComplexQueriesSuccess = searchWithComplexQueries.fulfilled;
export const searchWithComplexQueriesError = searchWithComplexQueries.rejected;

export const searchByGeolocationRequest = searchByGeolocation.pending;
export const searchByGeolocationSuccess = searchByGeolocation.fulfilled;
export const searchByGeolocationError = searchByGeolocation.rejected;

export const retrieveFormAccessStatisticsRequest = retrieveFormAccessStatistics.pending;
export const retrieveFormAccessStatisticsSuccess = retrieveFormAccessStatistics.fulfilled;
export const retrieveFormAccessStatisticsError = retrieveFormAccessStatistics.rejected;
