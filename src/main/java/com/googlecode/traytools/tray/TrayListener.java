package com.googlecode.traytools.tray;

import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.MenuDetectEvent;
import org.eclipse.swt.events.MenuDetectListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

import com.googlecode.traytools.config.Configuration;
import com.googlecode.traytools.utils.Logger;

public class TrayListener implements MenuDetectListener, SelectionListener, DisposeListener {
	
	private ClickHandler leftClickHandler;
	private ClickHandler rightClickHandler;
	
	public TrayListener() {
		String allHandler = Configuration.SETTINGS.getString("tray.handler.click");
		
		try {
			if (allHandler.equals("")) {
				leftClickHandler = Configuration.getNewInstance("tray.handler.left.click");
				rightClickHandler = Configuration.getNewInstance("tray.handler.right.click");
			} else {
				leftClickHandler = Configuration.getNewInstance("tray.handler.click");
				rightClickHandler = leftClickHandler;
			}
		} catch (final Exception e) {
			Logger.fatal("Failed instantiating a click handler", e);
		}
	}

	@Override
	public void menuDetected(MenuDetectEvent event) {
		rightClickHandler.handle(event);
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent event) {
		leftClickHandler.handle(event);
	}

	@Override
	public void widgetSelected(SelectionEvent event) {
		leftClickHandler.handle(event);
	}

	@Override
	public void widgetDisposed(DisposeEvent event) {
		Logger.debug(event);
	}

}
