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
package org.jboss.tools.maven.reddeer.wizards;

import org.jboss.reddeer.swt.api.Button;
import org.jboss.reddeer.swt.condition.ShellIsActive;
import org.jboss.reddeer.workbench.core.condition.JobIsRunning;
import org.jboss.reddeer.eclipse.topmenu.NewMenuWizard;
import org.jboss.reddeer.swt.impl.button.PushButton;
import org.jboss.reddeer.swt.impl.shell.DefaultShell;
import org.jboss.reddeer.common.wait.TimePeriod;
import org.jboss.reddeer.common.wait.WaitWhile;

public class MavenProjectWizard extends NewMenuWizard{
	
	public static final String SHELL_TEXT="New Maven Project";
	public static final String CATEGORY="Maven";
	public static final String NAME="Maven Project";
	
	public MavenProjectWizard(){
		super(SHELL_TEXT,CATEGORY,NAME);
	}
	
	@Override
	public void finish() {
		String shell = new DefaultShell().getText();
		Button button = new PushButton("Finish");
		button.click();

		new WaitWhile(new ShellIsActive(shell), TimePeriod.LONG);
		new WaitWhile(new JobIsRunning(), TimePeriod.VERY_LONG);
	}
	
	

}
