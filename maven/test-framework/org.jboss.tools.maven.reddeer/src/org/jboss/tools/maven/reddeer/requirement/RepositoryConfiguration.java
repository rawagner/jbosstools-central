package org.jboss.tools.maven.reddeer.requirement;

import org.eclipse.reddeer.junit.requirement.configuration.RequirementConfiguration;

public class RepositoryConfiguration implements RequirementConfiguration{
	
	private String groupId;
	private Repository[] repositories;

	@Override
	public String getId() {
		return null;
	}

	public Repository[] getRepositories() {
		return repositories;
	}

	public void setRepositories(Repository[] repositories) {
		this.repositories = repositories;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	
	

}
