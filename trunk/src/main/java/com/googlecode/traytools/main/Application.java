package com.googlecode.traytools.main;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.TrayItem;

import com.googlecode.traytools.tray.TrayListener;
import com.googlecode.traytools.utils.IconFactory;
import com.googlecode.traytools.utils.Logger;

public class Application {

	public static void main(String[] args) {
		new Application().start();
	}
	
	private Application() {
	}
	
	private void start() {
		long start = System.currentTimeMillis();
		final Display d = Display.getDefault();
		final TrayItem item = new TrayItem(d.getSystemTray(), SWT.NONE);
		item.setImage(IconFactory.getInstance().getTrayIcon());
		final TrayListener trayListener = new TrayListener();
		item.addMenuDetectListener(trayListener);
		item.addSelectionListener(trayListener);
		item.addDisposeListener(trayListener);
		long end = System.currentTimeMillis();
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				IconFactory.getInstance().cleanup();
				item.dispose();
				d.getSystemTray().dispose();
			}
		});
		Logger.info("TrayTools started in " + (end - start) + " ms");
		while (!item.isDisposed()) {
			if (!d.readAndDispatch()) {
				d.sleep();
			}
		}
		d.dispose();
	}
}
