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

package nl.deltares.dsd.registration.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

import nl.deltares.dsd.registration.model.Registration;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The cache model class for representing Registration in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class RegistrationCacheModel
	implements CacheModel<Registration>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof RegistrationCacheModel)) {
			return false;
		}

		RegistrationCacheModel registrationCacheModel =
			(RegistrationCacheModel)obj;

		if (registrationId == registrationCacheModel.registrationId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, registrationId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", registrationId=");
		sb.append(registrationId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", articleId=");
		sb.append(articleId);
		sb.append(", userPreferences=");
		sb.append(userPreferences);
		sb.append(", startTime=");
		sb.append(startTime);
		sb.append(", endTime=");
		sb.append(endTime);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Registration toEntityModel() {
		RegistrationImpl registrationImpl = new RegistrationImpl();

		if (uuid == null) {
			registrationImpl.setUuid("");
		}
		else {
			registrationImpl.setUuid(uuid);
		}

		registrationImpl.setRegistrationId(registrationId);
		registrationImpl.setGroupId(groupId);
		registrationImpl.setCompanyId(companyId);
		registrationImpl.setUserId(userId);
		registrationImpl.setArticleId(articleId);

		if (userPreferences == null) {
			registrationImpl.setUserPreferences("");
		}
		else {
			registrationImpl.setUserPreferences(userPreferences);
		}

		if (startTime == Long.MIN_VALUE) {
			registrationImpl.setStartTime(null);
		}
		else {
			registrationImpl.setStartTime(new Date(startTime));
		}

		if (endTime == Long.MIN_VALUE) {
			registrationImpl.setEndTime(null);
		}
		else {
			registrationImpl.setEndTime(new Date(endTime));
		}

		registrationImpl.resetOriginalValues();

		return registrationImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		registrationId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();

		articleId = objectInput.readLong();
		userPreferences = objectInput.readUTF();
		startTime = objectInput.readLong();
		endTime = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(registrationId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		objectOutput.writeLong(articleId);

		if (userPreferences == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userPreferences);
		}

		objectOutput.writeLong(startTime);
		objectOutput.writeLong(endTime);
	}

	public String uuid;
	public long registrationId;
	public long groupId;
	public long companyId;
	public long userId;
	public long articleId;
	public String userPreferences;
	public long startTime;
	public long endTime;

}