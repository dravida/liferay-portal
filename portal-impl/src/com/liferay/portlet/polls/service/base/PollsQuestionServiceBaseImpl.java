/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.portlet.polls.service.base;

import com.liferay.counter.service.CounterLocalService;

import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.ResourceLocalService;
import com.liferay.portal.service.ResourceService;
import com.liferay.portal.service.UserLocalService;
import com.liferay.portal.service.UserService;
import com.liferay.portal.service.base.PrincipalBean;
import com.liferay.portal.service.persistence.ResourceFinder;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserFinder;
import com.liferay.portal.service.persistence.UserPersistence;

import com.liferay.portlet.polls.service.PollsChoiceLocalService;
import com.liferay.portlet.polls.service.PollsQuestionLocalService;
import com.liferay.portlet.polls.service.PollsQuestionService;
import com.liferay.portlet.polls.service.PollsVoteLocalService;
import com.liferay.portlet.polls.service.PollsVoteService;
import com.liferay.portlet.polls.service.persistence.PollsChoiceFinder;
import com.liferay.portlet.polls.service.persistence.PollsChoicePersistence;
import com.liferay.portlet.polls.service.persistence.PollsQuestionPersistence;
import com.liferay.portlet.polls.service.persistence.PollsVotePersistence;

import javax.sql.DataSource;

/**
 * <a href="PollsQuestionServiceBaseImpl.java.html"><b><i>View Source</i></b>
 * </a>
 *
 * @author Brian Wing Shun Chan
 */
public abstract class PollsQuestionServiceBaseImpl extends PrincipalBean
	implements PollsQuestionService {
	public PollsChoiceLocalService getPollsChoiceLocalService() {
		return pollsChoiceLocalService;
	}

	public void setPollsChoiceLocalService(
		PollsChoiceLocalService pollsChoiceLocalService) {
		this.pollsChoiceLocalService = pollsChoiceLocalService;
	}

	public PollsChoicePersistence getPollsChoicePersistence() {
		return pollsChoicePersistence;
	}

	public void setPollsChoicePersistence(
		PollsChoicePersistence pollsChoicePersistence) {
		this.pollsChoicePersistence = pollsChoicePersistence;
	}

	public PollsChoiceFinder getPollsChoiceFinder() {
		return pollsChoiceFinder;
	}

	public void setPollsChoiceFinder(PollsChoiceFinder pollsChoiceFinder) {
		this.pollsChoiceFinder = pollsChoiceFinder;
	}

	public PollsQuestionLocalService getPollsQuestionLocalService() {
		return pollsQuestionLocalService;
	}

	public void setPollsQuestionLocalService(
		PollsQuestionLocalService pollsQuestionLocalService) {
		this.pollsQuestionLocalService = pollsQuestionLocalService;
	}

	public PollsQuestionService getPollsQuestionService() {
		return pollsQuestionService;
	}

	public void setPollsQuestionService(
		PollsQuestionService pollsQuestionService) {
		this.pollsQuestionService = pollsQuestionService;
	}

	public PollsQuestionPersistence getPollsQuestionPersistence() {
		return pollsQuestionPersistence;
	}

	public void setPollsQuestionPersistence(
		PollsQuestionPersistence pollsQuestionPersistence) {
		this.pollsQuestionPersistence = pollsQuestionPersistence;
	}

	public PollsVoteLocalService getPollsVoteLocalService() {
		return pollsVoteLocalService;
	}

	public void setPollsVoteLocalService(
		PollsVoteLocalService pollsVoteLocalService) {
		this.pollsVoteLocalService = pollsVoteLocalService;
	}

	public PollsVoteService getPollsVoteService() {
		return pollsVoteService;
	}

	public void setPollsVoteService(PollsVoteService pollsVoteService) {
		this.pollsVoteService = pollsVoteService;
	}

	public PollsVotePersistence getPollsVotePersistence() {
		return pollsVotePersistence;
	}

	public void setPollsVotePersistence(
		PollsVotePersistence pollsVotePersistence) {
		this.pollsVotePersistence = pollsVotePersistence;
	}

	public CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	public void setCounterLocalService(CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
	}

	public ResourceLocalService getResourceLocalService() {
		return resourceLocalService;
	}

	public void setResourceLocalService(
		ResourceLocalService resourceLocalService) {
		this.resourceLocalService = resourceLocalService;
	}

	public ResourceService getResourceService() {
		return resourceService;
	}

	public void setResourceService(ResourceService resourceService) {
		this.resourceService = resourceService;
	}

	public ResourcePersistence getResourcePersistence() {
		return resourcePersistence;
	}

	public void setResourcePersistence(ResourcePersistence resourcePersistence) {
		this.resourcePersistence = resourcePersistence;
	}

	public ResourceFinder getResourceFinder() {
		return resourceFinder;
	}

	public void setResourceFinder(ResourceFinder resourceFinder) {
		this.resourceFinder = resourceFinder;
	}

	public UserLocalService getUserLocalService() {
		return userLocalService;
	}

	public void setUserLocalService(UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	public UserFinder getUserFinder() {
		return userFinder;
	}

	public void setUserFinder(UserFinder userFinder) {
		this.userFinder = userFinder;
	}

	protected void runSQL(String sql) throws SystemException {
		try {
			DataSource dataSource = pollsQuestionPersistence.getDataSource();

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
					sql, new int[0]);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = PollsChoiceLocalService.class)
	protected PollsChoiceLocalService pollsChoiceLocalService;
	@BeanReference(type = PollsChoicePersistence.class)
	protected PollsChoicePersistence pollsChoicePersistence;
	@BeanReference(type = PollsChoiceFinder.class)
	protected PollsChoiceFinder pollsChoiceFinder;
	@BeanReference(type = PollsQuestionLocalService.class)
	protected PollsQuestionLocalService pollsQuestionLocalService;
	@BeanReference(type = PollsQuestionService.class)
	protected PollsQuestionService pollsQuestionService;
	@BeanReference(type = PollsQuestionPersistence.class)
	protected PollsQuestionPersistence pollsQuestionPersistence;
	@BeanReference(type = PollsVoteLocalService.class)
	protected PollsVoteLocalService pollsVoteLocalService;
	@BeanReference(type = PollsVoteService.class)
	protected PollsVoteService pollsVoteService;
	@BeanReference(type = PollsVotePersistence.class)
	protected PollsVotePersistence pollsVotePersistence;
	@BeanReference(type = CounterLocalService.class)
	protected CounterLocalService counterLocalService;
	@BeanReference(type = ResourceLocalService.class)
	protected ResourceLocalService resourceLocalService;
	@BeanReference(type = ResourceService.class)
	protected ResourceService resourceService;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = ResourceFinder.class)
	protected ResourceFinder resourceFinder;
	@BeanReference(type = UserLocalService.class)
	protected UserLocalService userLocalService;
	@BeanReference(type = UserService.class)
	protected UserService userService;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	@BeanReference(type = UserFinder.class)
	protected UserFinder userFinder;
}