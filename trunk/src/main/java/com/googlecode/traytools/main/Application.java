package com.googlecode.traytools.main;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.TrayItem;

import com.googlecode.traytools.config.ConfigurationFactory;
import com.googlecode.traytools.tray.TrayListener;
import com.googlecode.traytools.utils.IconFactory;
import com.googlecode.traytools.utils.Logger;
import com.googlecode.traytools.utils.SWTUtils;

public final class Application {

	public static void main(String[] args) {
		ConfigurationFactory.getConfigurationFactory(args);
		new Application().start();
		
	}
	
	private Application() {}
	
	private void start() {
		long start = System.currentTimeMillis();
		Logger.info("Starting TrayTools");
		final Display d = SWTUtils.getDisplay();
		final TrayItem item = new TrayItem(d.getSystemTray(), SWT.NONE);
		item.setImage(IconFactory.getInstance().getTrayIcon());
		final TrayListener trayListener = new TrayListener();
		item.addMenuDetectListener(trayListener);
		item.addSelectionListener(trayListener);
		item.addDisposeListener(trayListener);
		long end = System.currentTimeMillis();
		Logger.info("TrayTools started in " + (end - start) + " ms");
		while (!item.isDisposed()) {
			if (!d.readAndDispatch()) {
				d.sleep();
			}
		}
		IconFactory.getInstance().cleanup();
		d.getSystemTray().dispose();
		d.dispose();
		Logger.info("Exiting TrayTools");
	}
}
