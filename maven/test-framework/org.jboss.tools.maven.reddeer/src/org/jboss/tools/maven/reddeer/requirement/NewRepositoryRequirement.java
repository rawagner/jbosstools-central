/*******************************************************************************
 * Copyright (c) 2017 Red Hat, Inc.
 * Distributed under license by Red Hat, Inc. All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributor:
 *     Red Hat, Inc. - initial API and implementation
 ******************************************************************************/
package org.jboss.tools.maven.reddeer.requirement;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.reddeer.junit.requirement.ConfigurableRequirement;
import org.eclipse.reddeer.workbench.ui.dialogs.WorkbenchPreferenceDialog;
import org.jboss.tools.maven.reddeer.maven.ui.preferences.ConfiguratorPreferencePage;
import org.jboss.tools.maven.reddeer.requirement.NewRepositoryRequirement.MavenRepository;
import org.jboss.tools.maven.reddeer.wizards.ConfigureMavenRepositoriesWizard;

public class NewRepositoryRequirement implements ConfigurableRequirement<RepositoryConfiguration, MavenRepository> {
	
	private MavenRepository repo;
	private List<String> repositoriesToDelete;
	private RepositoryConfiguration config;
	
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.TYPE)
	public @interface MavenRepository{
	}

	@Override
	public void fulfill() {
		repositoriesToDelete = new ArrayList<String>();
		ConfigureMavenRepositoriesWizard mr = openRepositoriesWizard();
		for(Repository r: config.getRepositories()) {
			if(r.getUrl() == null) {
				repositoriesToDelete.add(mr.chooseRepositoryFromList(r.getRepositoryId(), true, r.isSnapshots()));
			} else {
				repositoriesToDelete.add(mr.addRepository(r.getRepositoryId(), r.getUrl(), true,r.isSnapshots()));
			}
		}
		closeRepositoriesWizard();
	}

	@Override
	public void setDeclaration(org.jboss.tools.maven.reddeer.requirement.NewRepositoryRequirement.MavenRepository declaration) {
		this.repo = declaration;
	}

	@Override
	public void cleanUp() {
		ConfigureMavenRepositoriesWizard mr = openRepositoriesWizard();
		for(String r: repositoriesToDelete){
			mr.removeRepo(r);
		}
		closeRepositoriesWizard();
	}
	
	private ConfigureMavenRepositoriesWizard openRepositoriesWizard(){
		WorkbenchPreferenceDialog preferenceDialog = new WorkbenchPreferenceDialog();
		preferenceDialog.open();
		ConfiguratorPreferencePage jm = new ConfiguratorPreferencePage(preferenceDialog);
		preferenceDialog.select(jm);
		return jm.configureRepositories();
	}
	
	private void closeRepositoriesWizard(){
		ConfigureMavenRepositoriesWizard mr = new ConfigureMavenRepositoriesWizard();
		mr.confirm();
		new WorkbenchPreferenceDialog().ok();
	}

	@Override
	public MavenRepository getDeclaration() {
		return this.repo;
	}

	@Override
	public Class<RepositoryConfiguration> getConfigurationClass() {
		return RepositoryConfiguration.class;
	}

	@Override
	public void setConfiguration(RepositoryConfiguration configuration) {
		this.config = configuration;
		
	}

	@Override
	public RepositoryConfiguration getConfiguration() {
		return config;
	}
	
	

}
