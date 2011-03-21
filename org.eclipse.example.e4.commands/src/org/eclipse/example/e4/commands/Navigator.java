/*******************************************************************************
 * Copyright (c) 2011 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 ******************************************************************************/

package org.eclipse.example.e4.commands;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.widgets.Composite;

/**
 * @since 1.0
 * 
 */
public class Navigator {

	@Inject
	private ESelectionService service;

	private ListViewer viewer;

	@Inject
	public Navigator(final Composite parent) {
		viewer = new ListViewer(parent);
		viewer.setContentProvider(new VersionContentProvider());
		viewer.setLabelProvider(new VersionLabelProvider());
		viewer.setInput(getInput());
		viewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				service.setSelection(viewer.getSelection());
			}
		});
	}

	@PostConstruct
	private void init(final IEclipseContext context) {
		context.set(IResetable.class, new IResetable() {
			@Override
			public void reset() {
				Navigator.this.reset();
			}
		});
	}

	private void reset() {
		service.setSelection(null);
		viewer.setSelection(null);
	}

	private Object getInput() {
		Version[] versions = { new Version(4, 4, 2, "v20110115"), new Version(4, 4, 2, "v20110115"),
				new Version(0, 1, 41, "v201101211617"), new Version(0, 1, 41, "v201101211617"),
				new Version(2, 5, 0, "v201012071420"), new Version(2, 0, 0, "v201101211617"),
				new Version(2, 0, 0, "v201101211617"), new Version(2, 5, 0, "v201012071420"),
				new Version(1, 7, 1, "v20100518-1145"), new Version(1, 7, 1, "v20100518-1145"),
				new Version(1, 3, 0, "v201101211617"), new Version(1, 3, 0, "v201101211617"),
				new Version(1, 0, 0, "v201101211617"), new Version(1, 0, 0, "v201101211617"),
				new Version(3, 1, 0, "v201012070820"), new Version(3, 1, 0, "v201012070820"),
				new Version(1, 0, 4, "v201101211617"), new Version(1, 0, 4, "v201101211617"),
				new Version(5, 5, 17, "v201101211617"), new Version(5, 5, 17, "v201101211617"),
				new Version(2, 9, 1, "v201101211721"), new Version(2, 9, 1, "v201101211721"),
				new Version(2, 9, 1, "v201101211721"), new Version(2, 9, 1, "v201101211721"),
				new Version(2, 9, 1, "v201101211721"), new Version(2, 9, 1, "v201101211721"),
				new Version(3, 2, 200, "v20101129"), new Version(3, 2, 200, "v20101129"),
				new Version(1, 0, 100, "v20101022"), new Version(1, 0, 100, "v20101022"),
				new Version(3, 5, 100, "v20110131"), new Version(3, 5, 100, "v20110131"),
				new Version(3, 5, 200, "I20101206-1300"), new Version(3, 5, 200, "I20101102-0800"),
				new Version(3, 5, 200, "I20101102-0800"), new Version(3, 5, 200, "I20101206-1300"),
				new Version(3, 1, 200, "v20100505"), new Version(3, 6, 0, "I20110111-0800"),
				new Version(3, 6, 0, "I20110111-0800"), new Version(3, 4, 100, "v20101203-1034"),
				new Version(3, 4, 100, "v20101203-1034"), new Version(1, 4, 0, "I20110111-0800"),
				new Version(1, 2, 100, "I20100824-0800"), new Version(1, 2, 100, "I20100824-0800"),
				new Version(1, 3, 0, "I20100824-0800"), new Version(1, 3, 0, "I20100824-0800"),
				new Version(1, 3, 0, "I20110111-0800"), new Version(1, 3, 0, "I20110111-0800"),
				new Version(1, 4, 0, "I20110111-0800"), new Version(3, 4, 200, "v20100505"),
				new Version(3, 4, 200, "v20100505"), new Version(1, 0, 100, "v20100830"),
				new Version(1, 0, 100, "v20100830"), new Version(3, 5, 200, "v20110124-0100"),
				new Version(3, 5, 200, "v20110124-0100"), new Version(1, 3, 100, "v20110117"),
				new Version(1, 3, 100, "v20110117"), new Version(3, 5, 100, "v20101008"),
				new Version(3, 5, 100, "v20101008"), new Version(1, 2, 100, "I20110118-0800"),
				new Version(1, 2, 100, "I20110118-0800"), new Version(3, 7, 100, "v20110131"),
				new Version(3, 7, 100, "v20110131"), new Version(3, 7, 0, "v20110110"),
				new Version(3, 2, 100, "v20100505"), new Version(3, 2, 200, "v20110110"),
				new Version(3, 2, 200, "v20110110"), new Version(3, 3, 100, "v20101108"),
				new Version(3, 3, 100, "v20101108"), new Version(3, 2, 100, "v20100505"),
				new Version(3, 7, 0, "v20110110"), new Version(3, 2, 500, "v20110131"),
				new Version(3, 2, 500, "v20110131"), new Version(1, 0, 500, "v201102010800"),
				new Version(1, 0, 500, "v201102010800"), new Version(3, 7, 0, "v20110124"),
				new Version(3, 7, 0, "v20110124"), new Version(3, 7, 0, "v20110131"),
				new Version(3, 7, 0, "v20110131"), new Version(3, 1, 0, "v20100906-1425"),
				new Version(4, 0, 0, "v20100906-1425"), new Version(4, 0, 0, "v20100906-1425"),
				new Version(3, 1, 0, "v20100906-1425"), new Version(3, 1, 0, "v20100906-1425"),
				new Version(3, 1, 0, "v20100906-1425"), new Version(4, 0, 0, "v20100906-1425"),
				new Version(4, 0, 0, "v20100906-1425"), new Version(1, 0, 0, "v20100906-1425"),
				new Version(1, 0, 0, "v20100906-1425"), new Version(3, 1, 0, "v20100906-1425"),
				new Version(1, 0, 0, "v20100906-1425"), new Version(1, 0, 0, "v20100906-1425"),
				new Version(3, 1, 0, "v20100906-1425"), new Version(1, 0, 100, "v20100906-1425"),
				new Version(1, 0, 100, "v20100906-1425"), new Version(1, 3, 100, "v20110110"),
				new Version(1, 3, 100, "v20110110"), new Version(3, 6, 0, "v20110110"),
				new Version(3, 6, 0, "v20110110"), new Version(1, 0, 100, "v20100503"),
				new Version(1, 3, 0, "v20110124-0830"), new Version(1, 3, 0, "v20110124-0830"),
				new Version(1, 2, 100, "v20110110"), new Version(1, 2, 100, "v20110110"),
				new Version(2, 0, 0, "v20100920"), new Version(1, 0, 200, "v20101004"),
				new Version(1, 0, 200, "v20101004"), new Version(2, 0, 0, "v20100920"),
				new Version(2, 0, 0, "v20100503"), new Version(2, 0, 0, "v20100503"),
				new Version(1, 1, 100, "v20101101"), new Version(1, 1, 100, "v20101101"),
				new Version(1, 1, 100, "v20101204"), new Version(1, 1, 100, "v20101204"),
				new Version(1, 0, 300, "v20101101"), new Version(1, 0, 200, "v20100503"),
				new Version(1, 0, 200, "v20100503"), new Version(1, 0, 300, "v20101101"),
				new Version(1, 2, 0, "v20110124-0830"), new Version(1, 2, 0, "v20110124-0830"),
				new Version(1, 1, 100, "v20110201-0200"), new Version(1, 1, 100, "v20110201-0200"),
				new Version(1, 0, 300, "v20110104-0005"), new Version(1, 0, 300, "v20110104-0005"),
				new Version(2, 1, 0, "v20110201-0200"), new Version(2, 1, 0, "v20110201-0200"),
				new Version(2, 1, 0, "v20101221-0005"), new Version(1, 0, 300, "v20110104-0005"),
				new Version(1, 0, 300, "v20110104-0005"), new Version(2, 1, 0, "v20101221-0005"),
				new Version(1, 0, 300, "v20100920"), new Version(1, 0, 300, "v20100920"),
				new Version(2, 1, 0, "v20110201-0200"), new Version(2, 1, 0, "v20110201-0200"),
				new Version(1, 2, 100, "v20110104-0005"), new Version(1, 2, 100, "v20110104-0005"),
				new Version(1, 0, 200, "v20110104-0005"), new Version(1, 0, 200, "v20110104-0005"),
				new Version(1, 0, 200, "v20100920"), new Version(1, 0, 200, "v20100920"),
				new Version(2, 1, 0, "v20110122-2355"), new Version(1, 0, 300, "v20101213-2340"),
				new Version(1, 0, 300, "v20101213-2340"), new Version(1, 1, 100, "v20110201-0200"),
				new Version(1, 1, 100, "v20110201-0200"), new Version(2, 1, 0, "v20110122-2355"),
				new Version(2, 1, 0, "v20110117-2315"), new Version(2, 1, 0, "v20110117-2315"),
				new Version(1, 2, 0, "v20110104-0005"), new Version(1, 2, 0, "v20110104-0005"),
				new Version(2, 0, 100, "v20101011-2100"), new Version(2, 0, 100, "v20101011-2100"),
				new Version(1, 1, 100, "v20110122-2355"), new Version(1, 1, 100, "v20110122-2355"),
				new Version(2, 1, 0, "v20110201-0200"), new Version(2, 1, 0, "v20110201-0200"),
				new Version(2, 0, 100, "v20110121-1730"), new Version(2, 0, 100, "v20110121-1730"),
				new Version(2, 0, 100, "v20110104-0005"), new Version(2, 0, 100, "v20110104-0005"),
				new Version(1, 0, 300, "v20101122"), new Version(1, 0, 300, "v20101122"),
				new Version(1, 0, 0, "v20110117-2315"), new Version(1, 0, 0, "v20110117-2315"),
				new Version(2, 1, 0, "v20110201-0200"), new Version(1, 0, 200, "v20110104-0005"),
				new Version(1, 0, 100, "v20110104-0005"), new Version(1, 0, 100, "v20110104-0005"),
				new Version(1, 0, 200, "v20110104-0005"), new Version(2, 1, 0, "v20110201-0200"),
				new Version(1, 1, 200, "v20101011-2100"), new Version(1, 1, 200, "v20101011-2100"),
				new Version(1, 0, 300, "v20110201-0200"), new Version(1, 0, 300, "v20110201-0200"),
				new Version(3, 4, 0, "v20110124-0830"), new Version(3, 4, 0, "v20110124-0830"),
				new Version(3, 5, 100, "v20101101"), new Version(3, 5, 100, "v20101101"),
				new Version(1, 1, 0, "v20110124-0830"), new Version(1, 1, 0, "v20110124-0830"),
				new Version(1, 1, 0, "v20101004"), new Version(1, 1, 0, "v20101004"),
				new Version(1, 0, 200, "v20100920"), new Version(2, 0, 0, "v20100920"),
				new Version(2, 0, 0, "v20100920"), new Version(1, 0, 200, "v20100920"),
				new Version(1, 0, 200, "v20100503"), new Version(1, 0, 200, "v20100503"),
				new Version(3, 5, 0, "v20100524"), new Version(3, 1, 400, "v20100427"),
				new Version(3, 1, 400, "v20100427"), new Version(3, 6, 0, "v201102010800"),
				new Version(3, 6, 0, "v201102010800"), new Version(3, 5, 0, "v20100524"),
				new Version(3, 5, 100, "v20110131"), new Version(3, 5, 100, "v20110131"),
				new Version(3, 6, 0, "v20110121"), new Version(3, 6, 0, "v20110121"),
				new Version(3, 7, 0, "v201102010800"), new Version(3, 3, 500, "v20110125-2000"),
				new Version(3, 3, 500, "v20110125-2000"), new Version(1, 0, 400, "v20101130-0130"),
				new Version(1, 0, 400, "v20101130-0130"), new Version(3, 3, 300, "v20110110-1230"),
				new Version(3, 3, 300, "v20110110-1230"), new Version(1, 0, 300, "v20110125-2000"),
				new Version(1, 0, 300, "v20110125-2000"), new Version(1, 0, 100, "v_B35"),
				new Version(1, 0, 100, "v_B35"), new Version(3, 7, 0, "v_B35"), new Version(1, 3, 0, "v20100824-0800"),
				new Version(1, 3, 0, "v20100824-0800"), new Version(3, 7, 0, "v_B35"),
				new Version(3, 7, 0, "v20110124"), new Version(3, 7, 0, "v20110124"),
				new Version(3, 6, 0, "v20110126"), new Version(3, 6, 0, "v20110126"),
				new Version(3, 7, 0, "v20110131"), new Version(3, 7, 0, "v20110124-0100"),
				new Version(3, 7, 0, "v20110201-0800"), new Version(1, 1, 200, "v20101206-1800"),
				new Version(1, 1, 200, "v20101206-1800"), new Version(3, 7, 0, "v20110111-0800"),
				new Version(3, 7, 0, "v20110111-0800"), new Version(3, 4, 300, "v20101206-1800"),
				new Version(3, 4, 300, "v20101206-1800"), new Version(3, 7, 0, "v20110201-0800"),
				new Version(3, 6, 0, "v20110124"), new Version(3, 6, 0, "v20110124"),
				new Version(3, 7, 0, "v201102010800"), new Version(3, 7, 0, "v20110201-0800"),
				new Version(3, 7, 0, "v20110201-0800"), new Version(3, 7, 0, "I20110118-0800"),
				new Version(1, 5, 0, "I20100907-0800"), new Version(1, 5, 0, "I20100907-0800"),
				new Version(3, 7, 0, "I20110118-0800"), new Version(3, 7, 0, "v20110126-1800"),
				new Version(3, 7, 0, "v20110126-1800"), new Version(1, 1, 300, "I20110118-0800"),
				new Version(1, 1, 300, "I20110118-0800"), new Version(1, 1, 300, "I20100505-1245"),
				new Version(1, 1, 300, "I20100505-1245"), new Version(3, 5, 200, "v20101130-0800"),
				new Version(3, 5, 200, "v20101130-0800"), new Version(3, 5, 100, "v20110111-0800"),
				new Version(3, 5, 100, "v20110111-0800"), new Version(3, 7, 0, "v20110124-0830"),
				new Version(3, 3, 0, "v20110110"), new Version(3, 3, 0, "v20110110"),
				new Version(3, 7, 0, "v20110124-0830"), new Version(3, 2, 200, "v20110110"),
				new Version(3, 2, 200, "v20110110"), new Version(3, 6, 100, "v201102010800"),
				new Version(1, 0, 300, "v20110131"), new Version(1, 0, 300, "v20110131"),
				new Version(1, 0, 300, "v20110131"), new Version(1, 0, 300, "v20110131"),
				new Version(3, 6, 100, "v20110121-1730"), new Version(3, 6, 100, "v20110121-1730"),
				new Version(3, 7, 0, "v20110124-1800"), new Version(3, 7, 0, "v20110124-1800"),
				new Version(3, 7, 0, "v20110124-1800"), new Version(1, 0, 100, "v20100601"),
				new Version(1, 0, 100, "v20100601"), new Version(1, 0, 100, "v20100601"),
				new Version(1, 0, 100, "v20100601"), new Version(3, 4, 100, "v20100601"),
				new Version(3, 4, 100, "v20100601"), new Version(3, 6, 0, "v20110124-1400"),
				new Version(3, 6, 0, "v20110124-1400"), new Version(3, 4, 200, "v20101130"),
				new Version(3, 4, 200, "v20101130"), new Version(3, 6, 100, "v201102010800"),
				new Version(1, 0, 100, "v20100504"), new Version(1, 0, 100, "v20100504"),
				new Version(1, 0, 100, "v20100628"), new Version(1, 0, 100, "v20100628"),
				new Version(3, 6, 100, "v20110124-1500"), new Version(3, 6, 100, "v20110124-1500"),
				new Version(3, 4, 400, "v20101206"), new Version(3, 4, 400, "v20101206"),
				new Version(3, 7, 0, "v201102010800"), new Version(3, 7, 0, "v20110131"),
				new Version(3, 7, 0, "v20110131"), new Version(3, 7, 0, "v201102010800"),
				new Version(3, 7, 0, "v201102010800"), new Version(3, 7, 0, "v201102010800"),
				new Version(3, 6, 0, "v20101104"), new Version(3, 7, 0, "v201102010800"),
				new Version(3, 6, 100, "v20110124-0100"), new Version(3, 6, 100, "v20110124-0100"),
				new Version(3, 7, 0, "v3721c"), new Version(3, 6, 0, "I20110125-0640"),
				new Version(3, 6, 0, "I20110125-0640"), new Version(3, 3, 400, "I20110125-0640"),
				new Version(3, 3, 400, "I20110125-0640"), new Version(3, 2, 300, "I20100526-0800"),
				new Version(3, 2, 300, "I20100526-0800"), new Version(3, 3, 400, "I20110125-0640"),
				new Version(3, 3, 400, "I20110125-0640"), new Version(3, 5, 200, "I20110125-0640"),
				new Version(3, 5, 200, "I20110125-0640"), new Version(3, 5, 100, "v20110124-0100"),
				new Version(3, 5, 100, "v20110124-0100"), new Version(3, 7, 0, "I20110201-0800"),
				new Version(3, 3, 100, "v20101220"), new Version(3, 3, 100, "v20101220"),
				new Version(3, 4, 0, "v20101108"), new Version(3, 4, 0, "v20101108"),
				new Version(3, 5, 100, "v20110126"), new Version(3, 5, 100, "v20110126"),
				new Version(3, 7, 0, "v20110201-0800"), new Version(3, 7, 0, "v20110201-0800"),
				new Version(3, 2, 0, "v20110124"), new Version(3, 2, 0, "v20110124"),
				new Version(3, 5, 100, "v20101220"), new Version(3, 5, 100, "v20101220"),
				new Version(3, 7, 0, "I20110201-0800"), new Version(1, 0, 300, "I20101206-1300"),
				new Version(1, 0, 300, "I20101206-1300"), new Version(3, 7, 0, "I20110201-0800"),
				new Version(3, 4, 0, "v20101101"), new Version(3, 4, 0, "v20101101"),
				new Version(3, 2, 500, "v20101001"), new Version(3, 2, 500, "v20101001"),
				new Version(3, 5, 0, "I20100601-0800"), new Version(3, 4, 300, "I20101116-0800"),
				new Version(3, 4, 300, "I20101116-0800"), new Version(3, 5, 0, "I20100601-0800"),
				new Version(1, 2, 100, "I20101012-0800"), new Version(1, 2, 100, "I20101012-0800"),
				new Version(3, 2, 200, "I20100517-1500"), new Version(3, 2, 200, "I20100517-1500"),
				new Version(3, 7, 0, "I20110201-0800"), new Version(3, 6, 0, "I20110111-0800"),
				new Version(3, 105, 0, null), new Version(1, 0, 200, "v20110119"), new Version(1, 0, 200, "v20110119"),
				new Version(3, 5, 200, "I20110201-0800"), new Version(3, 5, 200, "I20110201-0800"),
				new Version(3, 6, 0, "I20110111-0800"), new Version(3, 7, 0, "I20110201-0800"),
				new Version(3, 2, 100, "I20100511-0800"), new Version(3, 2, 100, "I20100511-0800"),
				new Version(3, 7, 0, "I20110201-0800"), new Version(3, 7, 0, "v20110124-0100"),
				new Version(3, 7, 0, "v20110124-0100"), new Version(3, 3, 100, "v20100512"),
				new Version(3, 3, 100, "v20100512"), new Version(3, 2, 400, "v20100512"),
				new Version(3, 2, 400, "v20100512"), new Version(3, 2, 300, "v20100512"),
				new Version(3, 2, 300, "v20100512"), new Version(3, 2, 300, "v20100512"),
				new Version(3, 2, 300, "v20100512"), new Version(1, 1, 0, "v20090501071000"),
				new Version(1, 1, 0, "v20090501071000"), new Version(3, 8, 2, "v3_8_2_v20100427-1100"),
				new Version(4, 8, 1, "v20100525"), new Version(4, 8, 1, "v4_8_1_v20100427-1100"),
				new Version(3, 8, 2, "v3_8_2_v20100427-1100"), new Version(4, 8, 1, "v4_8_1_v20100427-1100"),
				new Version(6, 1, 23, "v201012071420"), new Version(6, 1, 23, "v201012071420"),
				new Version(6, 1, 23, "v201012071420"), new Version(6, 1, 23, "v201012071420"),
				new Version(3, 2, 0, "v200909071300"), new Version(3, 2, 0, "v200909071300"),
				new Version(2, 2, 0, "v20100429"), new Version(2, 2, 0, "v20100429") };
		return versions;
	}
}
