package org.jboss.tools.maven.reddeer.requirement;

public class Repository {
	
	private String url;
	private String repositoryId;
	private boolean snapshots;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getRepositoryId() {
		return repositoryId;
	}

	public void setRepositoryId(String repositoryId) {
		this.repositoryId = repositoryId;
	}

	public boolean isSnapshots() {
		return snapshots;
	}

	public void setSnapshots(boolean snapshots) {
		this.snapshots = snapshots;
	}

}
