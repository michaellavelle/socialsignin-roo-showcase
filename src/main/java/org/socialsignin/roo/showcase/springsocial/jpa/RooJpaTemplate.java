package org.socialsignin.roo.showcase.springsocial.jpa;
/*
 * Copyright 2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.social.connect.jpa.JpaTemplate;
import org.springframework.social.connect.jpa.RemoteUser;
import org.springframework.social.connect.roo.RooTemplate;
import org.springframework.social.connect.roo.UserConnection;
import org.springframework.util.MultiValueMap;
/**
 * JpaTemplate implementation backed by Roo entities.
 * 
 * Delegates to RooTemplate and converts between UserConnection Roo entities
 * and RooRemoteUser adapters.
 * 
 *  (RooTemplate and UserConnection are 
 *  from https://github.com/michaellavelle/spring-social-roo-connectionrepository )
 * 
 * @author Michael Lavelle
 * 
 */
public class RooJpaTemplate implements JpaTemplate {

	@Inject
	private RooTemplate rooTemplate;

	@Override
	public RemoteUser createRemoteUser(String userId, String providerId,
			String providerUserId, int rank, String displayName,
			String profileUrl, String imageUrl, String accessToken,
			String secret, String refreshToken, Long expireTime) {

		return new RooRemoteUser(rooTemplate.createUserConnection(userId,
				providerId, providerUserId, rank, displayName, profileUrl,
				imageUrl, accessToken, secret, refreshToken, expireTime));
	}

	@Override
	public Set<String> findUsersConnectedTo(String providerId,
			Set<String> providerUserIds) {
		return rooTemplate.findUsersConnectedTo(providerId, providerUserIds);
	}

	@Override
	public List<RemoteUser> get(String providerId, String providerUserId)
			throws IncorrectResultSizeDataAccessException {
		return adapt(rooTemplate.getUserConnections(providerId, providerUserId));

	}

	@Override
	public RemoteUser get(String userId, String providerId,
			String providerUserId) {
		UserConnection userConnection = rooTemplate.getUserConnection(userId,
				providerId, providerUserId);
		if (userConnection == null) {
			throw new EmptyResultDataAccessException(1);
		}

		return new RooRemoteUser(userConnection);
	}

	@Override
	public List<RemoteUser> getAll(String userId) {
		return adapt(rooTemplate.getAllUserConnections(userId));
	}

	@Override
	public List<RemoteUser> getAll(String userId,
			MultiValueMap<String, String> providerUsers) {
		return adapt(rooTemplate.getAllUserConnections(userId, providerUsers));
	}

	@Override
	public List<RemoteUser> getAll(String userId, String providerId) {
		return adapt(rooTemplate.getAllUserConnections(userId, providerId));
	}

	private List<RemoteUser> adapt(List<UserConnection> userConnections) {
		List<RemoteUser> remoteUsers = new ArrayList<RemoteUser>();
		for (UserConnection userConnection : userConnections) {
			if (userConnection != null) {
				remoteUsers.add(new RooRemoteUser(userConnection));
			} else {
				remoteUsers.add(null);
			}
		}
		return remoteUsers;
	}

	@Override
	public List<RemoteUser> getPrimary(String userId, String providerId) {
		return adapt(rooTemplate.getPrimaryUserConnections(userId, providerId));
	}

	@Override
	public int getRank(String userId, String providerId) {
		return rooTemplate.getRank(userId, providerId);
	}

	@Override
	public void remove(String userId, String providerId) {
		rooTemplate.removeUserConnections(userId, providerId);
	}

	@Override
	public void remove(String userId, String providerId, String providerUserId) {

		rooTemplate.removeUserConnection(userId, providerId, providerUserId);
	}

	@Override
	public RemoteUser save(RemoteUser remoteUser) {

		UserConnection userConnection = ((RooRemoteUser) remoteUser)
				.getUserConnection();
		userConnection.setAccessToken(remoteUser.getAccessToken());
		userConnection.setDisplayName(remoteUser.getDisplayName());
		userConnection.setExpireTime(remoteUser.getExpireTime());
		userConnection.setImageUrl(remoteUser.getImageUrl());
		userConnection.setProfileUrl(remoteUser.getProfileUrl());
		userConnection.setProviderId(remoteUser.getProviderId());
		userConnection.setProviderUserId(remoteUser.getProviderUserId());
		userConnection.setRank(remoteUser.getRank());
		userConnection.setRefreshToken(remoteUser.getRefreshToken());
		userConnection.setSecret(remoteUser.getSecret());
		userConnection.setUserId(remoteUser.getUserId());

		return new RooRemoteUser(userConnection.merge());

	}

}
