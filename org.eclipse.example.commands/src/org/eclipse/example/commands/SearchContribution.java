package org.eclipse.example.commands;

import org.eclipse.jface.layout.RowLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.menus.WorkbenchWindowControlContribution;

public class SearchContribution extends WorkbenchWindowControlContribution {

	@Override
	protected Control createControl(Composite parent) {
		Composite control = new Composite(parent, SWT.NONE);
		RowLayout layout = RowLayoutFactory.fillDefaults().wrap(false).spacing(0).create();
		layout.marginLeft = 3;
		layout.center = true;
		control.setLayout(layout);
		Label label = new Label(control, SWT.NONE);
		label.setText("Search:");
		Text text = new Text(control, SWT.BORDER);
		RowData data = new RowData(80, SWT.DEFAULT);
		text.setLayoutData(data);
		return control;
	}

}
