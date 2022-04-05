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

package nl.deltares.oss.download.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

import nl.deltares.oss.download.exception.NoSuchDownloadException;
import nl.deltares.oss.download.model.Download;

/**
 * The persistence interface for the download service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Erik de Rooij @ Deltares
 * @see DownloadUtil
 * @generated
 */
@ProviderType
public interface DownloadPersistence extends BasePersistence<Download> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link DownloadUtil} to access the download persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	@Override
	public Map<Serializable, Download> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

	/**
	 * Returns all the downloads where downloadId = &#63;.
	 *
	 * @param downloadId the download ID
	 * @return the matching downloads
	 */
	public java.util.List<Download> findByDownloads(long downloadId);

	/**
	 * Returns a range of all the downloads where downloadId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>DownloadModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param downloadId the download ID
	 * @param start the lower bound of the range of downloads
	 * @param end the upper bound of the range of downloads (not inclusive)
	 * @return the range of matching downloads
	 */
	public java.util.List<Download> findByDownloads(
		long downloadId, int start, int end);

	/**
	 * Returns an ordered range of all the downloads where downloadId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>DownloadModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param downloadId the download ID
	 * @param start the lower bound of the range of downloads
	 * @param end the upper bound of the range of downloads (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching downloads
	 */
	public java.util.List<Download> findByDownloads(
		long downloadId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Download>
			orderByComparator);

	/**
	 * Returns an ordered range of all the downloads where downloadId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>DownloadModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param downloadId the download ID
	 * @param start the lower bound of the range of downloads
	 * @param end the upper bound of the range of downloads (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching downloads
	 */
	public java.util.List<Download> findByDownloads(
		long downloadId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Download>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Returns the first download in the ordered set where downloadId = &#63;.
	 *
	 * @param downloadId the download ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching download
	 * @throws NoSuchDownloadException if a matching download could not be found
	 */
	public Download findByDownloads_First(
			long downloadId,
			com.liferay.portal.kernel.util.OrderByComparator<Download>
				orderByComparator)
		throws NoSuchDownloadException;

	/**
	 * Returns the first download in the ordered set where downloadId = &#63;.
	 *
	 * @param downloadId the download ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching download, or <code>null</code> if a matching download could not be found
	 */
	public Download fetchByDownloads_First(
		long downloadId,
		com.liferay.portal.kernel.util.OrderByComparator<Download>
			orderByComparator);

	/**
	 * Returns the last download in the ordered set where downloadId = &#63;.
	 *
	 * @param downloadId the download ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching download
	 * @throws NoSuchDownloadException if a matching download could not be found
	 */
	public Download findByDownloads_Last(
			long downloadId,
			com.liferay.portal.kernel.util.OrderByComparator<Download>
				orderByComparator)
		throws NoSuchDownloadException;

	/**
	 * Returns the last download in the ordered set where downloadId = &#63;.
	 *
	 * @param downloadId the download ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching download, or <code>null</code> if a matching download could not be found
	 */
	public Download fetchByDownloads_Last(
		long downloadId,
		com.liferay.portal.kernel.util.OrderByComparator<Download>
			orderByComparator);

	/**
	 * Returns the downloads before and after the current download in the ordered set where downloadId = &#63;.
	 *
	 * @param downloadPK the primary key of the current download
	 * @param downloadId the download ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next download
	 * @throws NoSuchDownloadException if a download with the primary key could not be found
	 */
	public Download[] findByDownloads_PrevAndNext(
			nl.deltares.oss.download.service.persistence.DownloadPK downloadPK,
			long downloadId,
			com.liferay.portal.kernel.util.OrderByComparator<Download>
				orderByComparator)
		throws NoSuchDownloadException;

	/**
	 * Removes all the downloads where downloadId = &#63; from the database.
	 *
	 * @param downloadId the download ID
	 */
	public void removeByDownloads(long downloadId);

	/**
	 * Returns the number of downloads where downloadId = &#63;.
	 *
	 * @param downloadId the download ID
	 * @return the number of matching downloads
	 */
	public int countByDownloads(long downloadId);

	/**
	 * Returns all the downloads where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching downloads
	 */
	public java.util.List<Download> findByUserDownloads(long userId);

	/**
	 * Returns a range of all the downloads where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>DownloadModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of downloads
	 * @param end the upper bound of the range of downloads (not inclusive)
	 * @return the range of matching downloads
	 */
	public java.util.List<Download> findByUserDownloads(
		long userId, int start, int end);

	/**
	 * Returns an ordered range of all the downloads where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>DownloadModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of downloads
	 * @param end the upper bound of the range of downloads (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching downloads
	 */
	public java.util.List<Download> findByUserDownloads(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Download>
			orderByComparator);

	/**
	 * Returns an ordered range of all the downloads where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>DownloadModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of downloads
	 * @param end the upper bound of the range of downloads (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching downloads
	 */
	public java.util.List<Download> findByUserDownloads(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Download>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Returns the first download in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching download
	 * @throws NoSuchDownloadException if a matching download could not be found
	 */
	public Download findByUserDownloads_First(
			long userId,
			com.liferay.portal.kernel.util.OrderByComparator<Download>
				orderByComparator)
		throws NoSuchDownloadException;

	/**
	 * Returns the first download in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching download, or <code>null</code> if a matching download could not be found
	 */
	public Download fetchByUserDownloads_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator<Download>
			orderByComparator);

	/**
	 * Returns the last download in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching download
	 * @throws NoSuchDownloadException if a matching download could not be found
	 */
	public Download findByUserDownloads_Last(
			long userId,
			com.liferay.portal.kernel.util.OrderByComparator<Download>
				orderByComparator)
		throws NoSuchDownloadException;

	/**
	 * Returns the last download in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching download, or <code>null</code> if a matching download could not be found
	 */
	public Download fetchByUserDownloads_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator<Download>
			orderByComparator);

	/**
	 * Returns the downloads before and after the current download in the ordered set where userId = &#63;.
	 *
	 * @param downloadPK the primary key of the current download
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next download
	 * @throws NoSuchDownloadException if a download with the primary key could not be found
	 */
	public Download[] findByUserDownloads_PrevAndNext(
			nl.deltares.oss.download.service.persistence.DownloadPK downloadPK,
			long userId,
			com.liferay.portal.kernel.util.OrderByComparator<Download>
				orderByComparator)
		throws NoSuchDownloadException;

	/**
	 * Removes all the downloads where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	public void removeByUserDownloads(long userId);

	/**
	 * Returns the number of downloads where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching downloads
	 */
	public int countByUserDownloads(long userId);

	/**
	 * Returns all the downloads where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching downloads
	 */
	public java.util.List<Download> findByPendingUserDownloads(long userId);

	/**
	 * Returns a range of all the downloads where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>DownloadModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of downloads
	 * @param end the upper bound of the range of downloads (not inclusive)
	 * @return the range of matching downloads
	 */
	public java.util.List<Download> findByPendingUserDownloads(
		long userId, int start, int end);

	/**
	 * Returns an ordered range of all the downloads where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>DownloadModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of downloads
	 * @param end the upper bound of the range of downloads (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching downloads
	 */
	public java.util.List<Download> findByPendingUserDownloads(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Download>
			orderByComparator);

	/**
	 * Returns an ordered range of all the downloads where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>DownloadModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of downloads
	 * @param end the upper bound of the range of downloads (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching downloads
	 */
	public java.util.List<Download> findByPendingUserDownloads(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Download>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Returns the first download in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching download
	 * @throws NoSuchDownloadException if a matching download could not be found
	 */
	public Download findByPendingUserDownloads_First(
			long userId,
			com.liferay.portal.kernel.util.OrderByComparator<Download>
				orderByComparator)
		throws NoSuchDownloadException;

	/**
	 * Returns the first download in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching download, or <code>null</code> if a matching download could not be found
	 */
	public Download fetchByPendingUserDownloads_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator<Download>
			orderByComparator);

	/**
	 * Returns the last download in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching download
	 * @throws NoSuchDownloadException if a matching download could not be found
	 */
	public Download findByPendingUserDownloads_Last(
			long userId,
			com.liferay.portal.kernel.util.OrderByComparator<Download>
				orderByComparator)
		throws NoSuchDownloadException;

	/**
	 * Returns the last download in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching download, or <code>null</code> if a matching download could not be found
	 */
	public Download fetchByPendingUserDownloads_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator<Download>
			orderByComparator);

	/**
	 * Returns the downloads before and after the current download in the ordered set where userId = &#63;.
	 *
	 * @param downloadPK the primary key of the current download
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next download
	 * @throws NoSuchDownloadException if a download with the primary key could not be found
	 */
	public Download[] findByPendingUserDownloads_PrevAndNext(
			nl.deltares.oss.download.service.persistence.DownloadPK downloadPK,
			long userId,
			com.liferay.portal.kernel.util.OrderByComparator<Download>
				orderByComparator)
		throws NoSuchDownloadException;

	/**
	 * Removes all the downloads where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	public void removeByPendingUserDownloads(long userId);

	/**
	 * Returns the number of downloads where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching downloads
	 */
	public int countByPendingUserDownloads(long userId);

	/**
	 * Caches the download in the entity cache if it is enabled.
	 *
	 * @param download the download
	 */
	public void cacheResult(Download download);

	/**
	 * Caches the downloads in the entity cache if it is enabled.
	 *
	 * @param downloads the downloads
	 */
	public void cacheResult(java.util.List<Download> downloads);

	/**
	 * Creates a new download with the primary key. Does not add the download to the database.
	 *
	 * @param downloadPK the primary key for the new download
	 * @return the new download
	 */
	public Download create(
		nl.deltares.oss.download.service.persistence.DownloadPK downloadPK);

	/**
	 * Removes the download with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param downloadPK the primary key of the download
	 * @return the download that was removed
	 * @throws NoSuchDownloadException if a download with the primary key could not be found
	 */
	public Download remove(
			nl.deltares.oss.download.service.persistence.DownloadPK downloadPK)
		throws NoSuchDownloadException;

	public Download updateImpl(Download download);

	/**
	 * Returns the download with the primary key or throws a <code>NoSuchDownloadException</code> if it could not be found.
	 *
	 * @param downloadPK the primary key of the download
	 * @return the download
	 * @throws NoSuchDownloadException if a download with the primary key could not be found
	 */
	public Download findByPrimaryKey(
			nl.deltares.oss.download.service.persistence.DownloadPK downloadPK)
		throws NoSuchDownloadException;

	/**
	 * Returns the download with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param downloadPK the primary key of the download
	 * @return the download, or <code>null</code> if a download with the primary key could not be found
	 */
	public Download fetchByPrimaryKey(
		nl.deltares.oss.download.service.persistence.DownloadPK downloadPK);

	/**
	 * Returns all the downloads.
	 *
	 * @return the downloads
	 */
	public java.util.List<Download> findAll();

	/**
	 * Returns a range of all the downloads.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>DownloadModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of downloads
	 * @param end the upper bound of the range of downloads (not inclusive)
	 * @return the range of downloads
	 */
	public java.util.List<Download> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the downloads.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>DownloadModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of downloads
	 * @param end the upper bound of the range of downloads (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of downloads
	 */
	public java.util.List<Download> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Download>
			orderByComparator);

	/**
	 * Returns an ordered range of all the downloads.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>DownloadModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of downloads
	 * @param end the upper bound of the range of downloads (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of downloads
	 */
	public java.util.List<Download> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Download>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Removes all the downloads from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of downloads.
	 *
	 * @return the number of downloads
	 */
	public int countAll();

	@Override
	public Set<String> getBadColumnNames();

	public Set<String> getCompoundPKColumnNames();

}