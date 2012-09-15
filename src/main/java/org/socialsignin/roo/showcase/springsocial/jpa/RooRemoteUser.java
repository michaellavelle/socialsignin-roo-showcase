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
import org.springframework.social.connect.jpa.RemoteUser;
import org.springframework.social.connect.roo.UserConnection;

/**
 * RemoteUser adapter for UserConnection (from https://github.com/michaellavelle/spring-social-roo-connectionrepository )
 *
 * Allows RooJpaTemplate to convert between UserConnection entities and RooRemoteUsers,
 * allowing RooJpaTemplate to delegate to RooTemplate.
 * 
 * @author Michael Lavelle
 * 
 */
public class RooRemoteUser implements RemoteUser {

	private UserConnection userConnection;

	public RooRemoteUser(UserConnection userConnection) {
		this.userConnection = userConnection;
	}

	public RooRemoteUser() {
		this.userConnection = new UserConnection();
	}

	public UserConnection getUserConnection() {
		return userConnection;
	}

	@Override
	public String getAccessToken() {
		return userConnection.getAccessToken();
	}

	@Override
	public String getDisplayName() {
		return userConnection.getDisplayName();
	}

	@Override
	public Long getExpireTime() {
		return userConnection.getExpireTime();
	}

	@Override
	public String getImageUrl() {
		return userConnection.getImageUrl();
	}

	@Override
	public String getProfileUrl() {
		// TODO Auto-generated method stub
		return userConnection.getProfileUrl();
	}

	@Override
	public String getProviderId() {
		// TODO Auto-generated method stub
		return userConnection.getProviderId();
	}

	@Override
	public String getProviderUserId() {
		return userConnection.getProviderUserId();
	}

	@Override
	public int getRank() {
		return userConnection.getRank();
	}

	@Override
	public String getRefreshToken() {
		return userConnection.getRefreshToken();
	}

	@Override
	public String getSecret() {
		return userConnection.getSecret();
	}

	@Override
	public String getUserId() {
		return userConnection.getUserId();
	}

	@Override
	public void setAccessToken(String arg0) {

		userConnection.setAccessToken(arg0);
	}

	@Override
	public void setDisplayName(String arg0) {
		userConnection.setDisplayName(arg0);
	}

	@Override
	public void setExpireTime(Long arg0) {

		userConnection.setExpireTime(arg0);
	}

	@Override
	public void setImageUrl(String arg0) {

		userConnection.setImageUrl(arg0);
	}

	@Override
	public void setProfileUrl(String arg0) {

		userConnection.setProfileUrl(arg0);
	}

	@Override
	public void setProviderId(String arg0) {

		userConnection.setProviderId(arg0);
	}

	@Override
	public void setProviderUserId(String arg0) {
		userConnection.setProviderUserId(arg0);
	}

	@Override
	public void setRank(int arg0) {
		userConnection.setRank(arg0);
	}

	@Override
	public void setRefreshToken(String arg0) {
		userConnection.setRefreshToken(arg0);
	}

	@Override
	public void setSecret(String arg0) {
		// TODO Auto-generated method stub
		userConnection.setSecret(arg0);
	}

	@Override
	public void setUserId(String arg0) {
		userConnection.setUserId(arg0);
	}

}
