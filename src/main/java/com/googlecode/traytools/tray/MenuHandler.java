package com.googlecode.traytools.tray;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.TypedEvent;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

import com.googlecode.traytools.utils.Logger;
import com.googlecode.traytools.utils.SWTUtils;

public class MenuHandler implements ClickHandler {
	
	private Menu menu;

	public MenuHandler() {
		menu = new Menu(SWTUtils.getShell(), SWT.POP_UP);
		MenuItem itemExit = new MenuItem(menu, SWT.PUSH);
		itemExit.setText("Exit");
		itemExit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent selectionevent) {
				SWTUtils.exit();
			}
		});
	}
	
	@Override
	public void handle(TypedEvent event) {
		Logger.info("Menu Handler clicked", event);

		menu.setLocation(event.display.getCursorLocation());
		menu.setVisible(!menu.getVisible());
	}

}
