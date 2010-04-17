package com.googlecode.traytools.testapp;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.TypedEvent;
import org.eclipse.swt.widgets.ToolTip;

import com.googlecode.traytools.tray.ClickHandler;
import com.googlecode.traytools.utils.Logger;
import com.googlecode.traytools.utils.SWTUtils;

public class TestClickHandler implements ClickHandler {

	@Override
	public void handle(TypedEvent event) {
		Logger.debug("Our component was hooked!");
		ToolTip balloon = new ToolTip(SWTUtils.getShell(), SWT.BALLOON
				| SWT.ICON_INFORMATION);
		balloon.setText("TrayTools");
		balloon.setMessage("Testing Tray Tools!");
		SWTUtils.getTrayItem().setToolTip(balloon);
		balloon.setVisible(true);
	}

}
