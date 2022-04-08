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

package nl.deltares.oss.download.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Types;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

import nl.deltares.oss.download.model.Download;
import nl.deltares.oss.download.model.DownloadModel;

/**
 * The base model implementation for the Download service. Represents a row in the &quot;Downloads_Download&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface </code>DownloadModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link DownloadImpl}.
 * </p>
 *
 * @author Erik de Rooij @ Deltares
 * @see DownloadImpl
 * @generated
 */
@ProviderType
public class DownloadModelImpl
	extends BaseModelImpl<Download> implements DownloadModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a download model instance should use the <code>Download</code> interface instead.
	 */
	public static final String TABLE_NAME = "Downloads_Download";

	public static final Object[][] TABLE_COLUMNS = {
		{"id_", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"groupId", Types.BIGINT}, {"downloadId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP}, {"filePath", Types.VARCHAR},
		{"expiryDate", Types.TIMESTAMP}, {"organization", Types.VARCHAR},
		{"countryCode", Types.VARCHAR}, {"city", Types.VARCHAR},
		{"shareId", Types.BIGINT}, {"directDownloadUrl", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("id_", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("downloadId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("filePath", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("expiryDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("organization", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("countryCode", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("city", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("shareId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("directDownloadUrl", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table Downloads_Download (id_ LONG not null primary key,companyId LONG,groupId LONG,downloadId LONG,userId LONG,createDate DATE null,modifiedDate DATE null,filePath STRING null,expiryDate DATE null,organization VARCHAR(75) null,countryCode VARCHAR(75) null,city VARCHAR(75) null,shareId LONG,directDownloadUrl STRING null)";

	public static final String TABLE_SQL_DROP = "drop table Downloads_Download";

	public static final String ORDER_BY_JPQL = " ORDER BY download.id ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY Downloads_Download.id_ ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(
		nl.deltares.oss.download.service.util.ServiceProps.get(
			"value.object.entity.cache.enabled.nl.deltares.oss.download.model.Download"),
		true);

	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(
		nl.deltares.oss.download.service.util.ServiceProps.get(
			"value.object.finder.cache.enabled.nl.deltares.oss.download.model.Download"),
		true);

	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(
		nl.deltares.oss.download.service.util.ServiceProps.get(
			"value.object.column.bitmask.enabled.nl.deltares.oss.download.model.Download"),
		true);

	public static final long DOWNLOADID_COLUMN_BITMASK = 1L;

	public static final long GROUPID_COLUMN_BITMASK = 2L;

	public static final long USERID_COLUMN_BITMASK = 4L;

	public static final long ID_COLUMN_BITMASK = 8L;

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		nl.deltares.oss.download.service.util.ServiceProps.get(
			"lock.expiration.time.nl.deltares.oss.download.model.Download"));

	public DownloadModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _id;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _id;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return Download.class;
	}

	@Override
	public String getModelClassName() {
		return Download.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<Download, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<Download, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Download, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName, attributeGetterFunction.apply((Download)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<Download, Object>> attributeSetterBiConsumers =
			getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<Download, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(Download)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<Download, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<Download, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, Download>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			Download.class.getClassLoader(), Download.class,
			ModelWrapper.class);

		try {
			Constructor<Download> constructor =
				(Constructor<Download>)proxyClass.getConstructor(
					InvocationHandler.class);

			return invocationHandler -> {
				try {
					return constructor.newInstance(invocationHandler);
				}
				catch (ReflectiveOperationException roe) {
					throw new InternalError(roe);
				}
			};
		}
		catch (NoSuchMethodException nsme) {
			throw new InternalError(nsme);
		}
	}

	private static final Map<String, Function<Download, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<Download, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<Download, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<Download, Object>>();
		Map<String, BiConsumer<Download, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<Download, ?>>();

		attributeGetterFunctions.put(
			"id",
			new Function<Download, Object>() {

				@Override
				public Object apply(Download download) {
					return download.getId();
				}

			});
		attributeSetterBiConsumers.put(
			"id",
			new BiConsumer<Download, Object>() {

				@Override
				public void accept(Download download, Object id) {
					download.setId((Long)id);
				}

			});
		attributeGetterFunctions.put(
			"companyId",
			new Function<Download, Object>() {

				@Override
				public Object apply(Download download) {
					return download.getCompanyId();
				}

			});
		attributeSetterBiConsumers.put(
			"companyId",
			new BiConsumer<Download, Object>() {

				@Override
				public void accept(Download download, Object companyId) {
					download.setCompanyId((Long)companyId);
				}

			});
		attributeGetterFunctions.put(
			"groupId",
			new Function<Download, Object>() {

				@Override
				public Object apply(Download download) {
					return download.getGroupId();
				}

			});
		attributeSetterBiConsumers.put(
			"groupId",
			new BiConsumer<Download, Object>() {

				@Override
				public void accept(Download download, Object groupId) {
					download.setGroupId((Long)groupId);
				}

			});
		attributeGetterFunctions.put(
			"downloadId",
			new Function<Download, Object>() {

				@Override
				public Object apply(Download download) {
					return download.getDownloadId();
				}

			});
		attributeSetterBiConsumers.put(
			"downloadId",
			new BiConsumer<Download, Object>() {

				@Override
				public void accept(Download download, Object downloadId) {
					download.setDownloadId((Long)downloadId);
				}

			});
		attributeGetterFunctions.put(
			"userId",
			new Function<Download, Object>() {

				@Override
				public Object apply(Download download) {
					return download.getUserId();
				}

			});
		attributeSetterBiConsumers.put(
			"userId",
			new BiConsumer<Download, Object>() {

				@Override
				public void accept(Download download, Object userId) {
					download.setUserId((Long)userId);
				}

			});
		attributeGetterFunctions.put(
			"createDate",
			new Function<Download, Object>() {

				@Override
				public Object apply(Download download) {
					return download.getCreateDate();
				}

			});
		attributeSetterBiConsumers.put(
			"createDate",
			new BiConsumer<Download, Object>() {

				@Override
				public void accept(Download download, Object createDate) {
					download.setCreateDate((Date)createDate);
				}

			});
		attributeGetterFunctions.put(
			"modifiedDate",
			new Function<Download, Object>() {

				@Override
				public Object apply(Download download) {
					return download.getModifiedDate();
				}

			});
		attributeSetterBiConsumers.put(
			"modifiedDate",
			new BiConsumer<Download, Object>() {

				@Override
				public void accept(Download download, Object modifiedDate) {
					download.setModifiedDate((Date)modifiedDate);
				}

			});
		attributeGetterFunctions.put(
			"filePath",
			new Function<Download, Object>() {

				@Override
				public Object apply(Download download) {
					return download.getFilePath();
				}

			});
		attributeSetterBiConsumers.put(
			"filePath",
			new BiConsumer<Download, Object>() {

				@Override
				public void accept(Download download, Object filePath) {
					download.setFilePath((String)filePath);
				}

			});
		attributeGetterFunctions.put(
			"expiryDate",
			new Function<Download, Object>() {

				@Override
				public Object apply(Download download) {
					return download.getExpiryDate();
				}

			});
		attributeSetterBiConsumers.put(
			"expiryDate",
			new BiConsumer<Download, Object>() {

				@Override
				public void accept(Download download, Object expiryDate) {
					download.setExpiryDate((Date)expiryDate);
				}

			});
		attributeGetterFunctions.put(
			"organization",
			new Function<Download, Object>() {

				@Override
				public Object apply(Download download) {
					return download.getOrganization();
				}

			});
		attributeSetterBiConsumers.put(
			"organization",
			new BiConsumer<Download, Object>() {

				@Override
				public void accept(Download download, Object organization) {
					download.setOrganization((String)organization);
				}

			});
		attributeGetterFunctions.put(
			"countryCode",
			new Function<Download, Object>() {

				@Override
				public Object apply(Download download) {
					return download.getCountryCode();
				}

			});
		attributeSetterBiConsumers.put(
			"countryCode",
			new BiConsumer<Download, Object>() {

				@Override
				public void accept(Download download, Object countryCode) {
					download.setCountryCode((String)countryCode);
				}

			});
		attributeGetterFunctions.put(
			"city",
			new Function<Download, Object>() {

				@Override
				public Object apply(Download download) {
					return download.getCity();
				}

			});
		attributeSetterBiConsumers.put(
			"city",
			new BiConsumer<Download, Object>() {

				@Override
				public void accept(Download download, Object city) {
					download.setCity((String)city);
				}

			});
		attributeGetterFunctions.put(
			"shareId",
			new Function<Download, Object>() {

				@Override
				public Object apply(Download download) {
					return download.getShareId();
				}

			});
		attributeSetterBiConsumers.put(
			"shareId",
			new BiConsumer<Download, Object>() {

				@Override
				public void accept(Download download, Object shareId) {
					download.setShareId((Long)shareId);
				}

			});
		attributeGetterFunctions.put(
			"directDownloadUrl",
			new Function<Download, Object>() {

				@Override
				public Object apply(Download download) {
					return download.getDirectDownloadUrl();
				}

			});
		attributeSetterBiConsumers.put(
			"directDownloadUrl",
			new BiConsumer<Download, Object>() {

				@Override
				public void accept(
					Download download, Object directDownloadUrl) {

					download.setDirectDownloadUrl((String)directDownloadUrl);
				}

			});

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@Override
	public long getId() {
		return _id;
	}

	@Override
	public void setId(long id) {
		_id = id;
	}

	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_columnBitmask |= GROUPID_COLUMN_BITMASK;

		if (!_setOriginalGroupId) {
			_setOriginalGroupId = true;

			_originalGroupId = _groupId;
		}

		_groupId = groupId;
	}

	public long getOriginalGroupId() {
		return _originalGroupId;
	}

	@Override
	public long getDownloadId() {
		return _downloadId;
	}

	@Override
	public void setDownloadId(long downloadId) {
		_columnBitmask |= DOWNLOADID_COLUMN_BITMASK;

		if (!_setOriginalDownloadId) {
			_setOriginalDownloadId = true;

			_originalDownloadId = _downloadId;
		}

		_downloadId = downloadId;
	}

	public long getOriginalDownloadId() {
		return _originalDownloadId;
	}

	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_columnBitmask |= USERID_COLUMN_BITMASK;

		if (!_setOriginalUserId) {
			_setOriginalUserId = true;

			_originalUserId = _userId;
		}

		_userId = userId;
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return "";
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	public long getOriginalUserId() {
		return _originalUserId;
	}

	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public boolean hasSetModifiedDate() {
		return _setModifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_setModifiedDate = true;

		_modifiedDate = modifiedDate;
	}

	@Override
	public String getFilePath() {
		if (_filePath == null) {
			return "";
		}
		else {
			return _filePath;
		}
	}

	@Override
	public void setFilePath(String filePath) {
		_filePath = filePath;
	}

	@Override
	public Date getExpiryDate() {
		return _expiryDate;
	}

	@Override
	public void setExpiryDate(Date expiryDate) {
		_expiryDate = expiryDate;
	}

	@Override
	public String getOrganization() {
		if (_organization == null) {
			return "";
		}
		else {
			return _organization;
		}
	}

	@Override
	public void setOrganization(String organization) {
		_organization = organization;
	}

	@Override
	public String getCountryCode() {
		if (_countryCode == null) {
			return "";
		}
		else {
			return _countryCode;
		}
	}

	@Override
	public void setCountryCode(String countryCode) {
		_countryCode = countryCode;
	}

	@Override
	public String getCity() {
		if (_city == null) {
			return "";
		}
		else {
			return _city;
		}
	}

	@Override
	public void setCity(String city) {
		_city = city;
	}

	@Override
	public long getShareId() {
		return _shareId;
	}

	@Override
	public void setShareId(long shareId) {
		_shareId = shareId;
	}

	@Override
	public String getDirectDownloadUrl() {
		if (_directDownloadUrl == null) {
			return "";
		}
		else {
			return _directDownloadUrl;
		}
	}

	@Override
	public void setDirectDownloadUrl(String directDownloadUrl) {
		_directDownloadUrl = directDownloadUrl;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), Download.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public Download toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, Download>
				escapedModelProxyProviderFunction =
					EscapedModelProxyProviderFunctionHolder.
						_escapedModelProxyProviderFunction;

			_escapedModel = escapedModelProxyProviderFunction.apply(
				new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		DownloadImpl downloadImpl = new DownloadImpl();

		downloadImpl.setId(getId());
		downloadImpl.setCompanyId(getCompanyId());
		downloadImpl.setGroupId(getGroupId());
		downloadImpl.setDownloadId(getDownloadId());
		downloadImpl.setUserId(getUserId());
		downloadImpl.setCreateDate(getCreateDate());
		downloadImpl.setModifiedDate(getModifiedDate());
		downloadImpl.setFilePath(getFilePath());
		downloadImpl.setExpiryDate(getExpiryDate());
		downloadImpl.setOrganization(getOrganization());
		downloadImpl.setCountryCode(getCountryCode());
		downloadImpl.setCity(getCity());
		downloadImpl.setShareId(getShareId());
		downloadImpl.setDirectDownloadUrl(getDirectDownloadUrl());

		downloadImpl.resetOriginalValues();

		return downloadImpl;
	}

	@Override
	public int compareTo(Download download) {
		long primaryKey = download.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof Download)) {
			return false;
		}

		Download download = (Download)obj;

		long primaryKey = download.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return ENTITY_CACHE_ENABLED;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return FINDER_CACHE_ENABLED;
	}

	@Override
	public void resetOriginalValues() {
		DownloadModelImpl downloadModelImpl = this;

		downloadModelImpl._originalGroupId = downloadModelImpl._groupId;

		downloadModelImpl._setOriginalGroupId = false;

		downloadModelImpl._originalDownloadId = downloadModelImpl._downloadId;

		downloadModelImpl._setOriginalDownloadId = false;

		downloadModelImpl._originalUserId = downloadModelImpl._userId;

		downloadModelImpl._setOriginalUserId = false;

		downloadModelImpl._setModifiedDate = false;

		downloadModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<Download> toCacheModel() {
		DownloadCacheModel downloadCacheModel = new DownloadCacheModel();

		downloadCacheModel.id = getId();

		downloadCacheModel.companyId = getCompanyId();

		downloadCacheModel.groupId = getGroupId();

		downloadCacheModel.downloadId = getDownloadId();

		downloadCacheModel.userId = getUserId();

		Date createDate = getCreateDate();

		if (createDate != null) {
			downloadCacheModel.createDate = createDate.getTime();
		}
		else {
			downloadCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			downloadCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			downloadCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		downloadCacheModel.filePath = getFilePath();

		String filePath = downloadCacheModel.filePath;

		if ((filePath != null) && (filePath.length() == 0)) {
			downloadCacheModel.filePath = null;
		}

		Date expiryDate = getExpiryDate();

		if (expiryDate != null) {
			downloadCacheModel.expiryDate = expiryDate.getTime();
		}
		else {
			downloadCacheModel.expiryDate = Long.MIN_VALUE;
		}

		downloadCacheModel.organization = getOrganization();

		String organization = downloadCacheModel.organization;

		if ((organization != null) && (organization.length() == 0)) {
			downloadCacheModel.organization = null;
		}

		downloadCacheModel.countryCode = getCountryCode();

		String countryCode = downloadCacheModel.countryCode;

		if ((countryCode != null) && (countryCode.length() == 0)) {
			downloadCacheModel.countryCode = null;
		}

		downloadCacheModel.city = getCity();

		String city = downloadCacheModel.city;

		if ((city != null) && (city.length() == 0)) {
			downloadCacheModel.city = null;
		}

		downloadCacheModel.shareId = getShareId();

		downloadCacheModel.directDownloadUrl = getDirectDownloadUrl();

		String directDownloadUrl = downloadCacheModel.directDownloadUrl;

		if ((directDownloadUrl != null) && (directDownloadUrl.length() == 0)) {
			downloadCacheModel.directDownloadUrl = null;
		}

		return downloadCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<Download, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<Download, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Download, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((Download)this));
			sb.append(", ");
		}

		if (sb.index() > 1) {
			sb.setIndex(sb.index() - 1);
		}

		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		Map<String, Function<Download, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<Download, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Download, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((Download)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, Download>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private long _id;
	private long _companyId;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private long _downloadId;
	private long _originalDownloadId;
	private boolean _setOriginalDownloadId;
	private long _userId;
	private long _originalUserId;
	private boolean _setOriginalUserId;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private String _filePath;
	private Date _expiryDate;
	private String _organization;
	private String _countryCode;
	private String _city;
	private long _shareId;
	private String _directDownloadUrl;
	private long _columnBitmask;
	private Download _escapedModel;

}