package com.googlecode.traytools.tray;

import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.MenuDetectEvent;
import org.eclipse.swt.events.MenuDetectListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

import com.googlecode.traytools.utils.Logger;

public class TrayListener implements MenuDetectListener, SelectionListener, DisposeListener {

	@Override
	public void menuDetected(MenuDetectEvent event) {
		Logger.debug(event);
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent event) {
		Logger.debug(event);
	}

	@Override
	public void widgetSelected(SelectionEvent event) {
		Logger.debug(event);
	}

	@Override
	public void widgetDisposed(DisposeEvent event) {
		Logger.debug(event);
	}

}
