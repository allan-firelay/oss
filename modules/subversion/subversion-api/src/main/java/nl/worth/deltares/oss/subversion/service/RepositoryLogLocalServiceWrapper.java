/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package nl.worth.deltares.oss.subversion.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link RepositoryLogLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see RepositoryLogLocalService
 * @generated
 */
public class RepositoryLogLocalServiceWrapper
	implements RepositoryLogLocalService,
			   ServiceWrapper<RepositoryLogLocalService> {

	public RepositoryLogLocalServiceWrapper() {
		this(null);
	}

	public RepositoryLogLocalServiceWrapper(
		RepositoryLogLocalService repositoryLogLocalService) {

		_repositoryLogLocalService = repositoryLogLocalService;
	}

	/**
	 * Adds the repository log to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect RepositoryLogLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param repositoryLog the repository log
	 * @return the repository log that was added
	 */
	@Override
	public nl.worth.deltares.oss.subversion.model.RepositoryLog
		addRepositoryLog(
			nl.worth.deltares.oss.subversion.model.RepositoryLog
				repositoryLog) {

		return _repositoryLogLocalService.addRepositoryLog(repositoryLog);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _repositoryLogLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Creates a new repository log with the primary key. Does not add the repository log to the database.
	 *
	 * @param logId the primary key for the new repository log
	 * @return the new repository log
	 */
	@Override
	public nl.worth.deltares.oss.subversion.model.RepositoryLog
		createRepositoryLog(long logId) {

		return _repositoryLogLocalService.createRepositoryLog(logId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _repositoryLogLocalService.deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the repository log with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect RepositoryLogLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param logId the primary key of the repository log
	 * @return the repository log that was removed
	 * @throws PortalException if a repository log with the primary key could not be found
	 */
	@Override
	public nl.worth.deltares.oss.subversion.model.RepositoryLog
			deleteRepositoryLog(long logId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _repositoryLogLocalService.deleteRepositoryLog(logId);
	}

	/**
	 * Deletes the repository log from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect RepositoryLogLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param repositoryLog the repository log
	 * @return the repository log that was removed
	 */
	@Override
	public nl.worth.deltares.oss.subversion.model.RepositoryLog
		deleteRepositoryLog(
			nl.worth.deltares.oss.subversion.model.RepositoryLog
				repositoryLog) {

		return _repositoryLogLocalService.deleteRepositoryLog(repositoryLog);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _repositoryLogLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _repositoryLogLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _repositoryLogLocalService.dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _repositoryLogLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>nl.worth.deltares.oss.subversion.model.impl.RepositoryLogModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _repositoryLogLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>nl.worth.deltares.oss.subversion.model.impl.RepositoryLogModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _repositoryLogLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _repositoryLogLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return _repositoryLogLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public nl.worth.deltares.oss.subversion.model.RepositoryLog
		fetchRepositoryLog(long logId) {

		return _repositoryLogLocalService.fetchRepositoryLog(logId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _repositoryLogLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _repositoryLogLocalService.getIndexableActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.json.JSONArray getLastActivityLogs(
		int number) {

		return _repositoryLogLocalService.getLastActivityLogs(number);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _repositoryLogLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _repositoryLogLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public int getRepositorLogsCount(String action) {
		return _repositoryLogLocalService.getRepositorLogsCount(action);
	}

	/**
	 * Returns the repository log with the primary key.
	 *
	 * @param logId the primary key of the repository log
	 * @return the repository log
	 * @throws PortalException if a repository log with the primary key could not be found
	 */
	@Override
	public nl.worth.deltares.oss.subversion.model.RepositoryLog
			getRepositoryLog(long logId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _repositoryLogLocalService.getRepositoryLog(logId);
	}

	/**
	 * Returns a range of all the repository logs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>nl.worth.deltares.oss.subversion.model.impl.RepositoryLogModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of repository logs
	 * @param end the upper bound of the range of repository logs (not inclusive)
	 * @return the range of repository logs
	 */
	@Override
	public java.util.List<nl.worth.deltares.oss.subversion.model.RepositoryLog>
		getRepositoryLogs(int start, int end) {

		return _repositoryLogLocalService.getRepositoryLogs(start, end);
	}

	/**
	 * Returns the number of repository logs.
	 *
	 * @return the number of repository logs
	 */
	@Override
	public int getRepositoryLogsCount() {
		return _repositoryLogLocalService.getRepositoryLogsCount();
	}

	@Override
	public int getRepositoryLogsCount(String repository, String action) {
		return _repositoryLogLocalService.getRepositoryLogsCount(
			repository, action);
	}

	@Override
	public int getRepositoryLogsCount(
		String screenName, String ipAddress, String repository) {

		return _repositoryLogLocalService.getRepositoryLogsCount(
			screenName, ipAddress, repository);
	}

	/**
	 * Updates the repository log in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect RepositoryLogLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param repositoryLog the repository log
	 * @return the repository log that was updated
	 */
	@Override
	public nl.worth.deltares.oss.subversion.model.RepositoryLog
		updateRepositoryLog(
			nl.worth.deltares.oss.subversion.model.RepositoryLog
				repositoryLog) {

		return _repositoryLogLocalService.updateRepositoryLog(repositoryLog);
	}

	@Override
	public RepositoryLogLocalService getWrappedService() {
		return _repositoryLogLocalService;
	}

	@Override
	public void setWrappedService(
		RepositoryLogLocalService repositoryLogLocalService) {

		_repositoryLogLocalService = repositoryLogLocalService;
	}

	private RepositoryLogLocalService _repositoryLogLocalService;

}