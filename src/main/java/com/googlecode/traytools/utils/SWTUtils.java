package com.googlecode.traytools.utils;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TrayItem;

public class SWTUtils {

	private static final Display display = Display.getDefault();
	
	private static final Shell shell = new Shell(display);
	
	public static Display getDisplay() {
		return display;
	}
	
	public static Shell getShell() {
		return shell;
	}
	
	public static TrayItem getTrayItem() {
		return display.getSystemTray().getItem(0);
	}
	
	public static void exit() {
		for (TrayItem ti : display.getSystemTray().getItems()) {
			ti.dispose();
		}
	}
	
}
