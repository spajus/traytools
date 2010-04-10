package com.googlecode.traytools.tray;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.TypedEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

import com.googlecode.traytools.utils.Logger;
import com.googlecode.traytools.utils.OSUtils;
import com.googlecode.traytools.utils.SWTUtils;
import com.googlecode.traytools.utils.OSUtils.OS;

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
		menu.setLocation(findPopupMenuLocation(event));
		menu.setVisible(!menu.getVisible());
	}
	
	/**
     * Finds popup menu location and makes a {@link StateEvent}
     * 
     * @return
     */
    public static synchronized Point findPopupMenuLocation(TypedEvent event) {
    	final Display d = event.display;
    	final Point loc = d.getCursorLocation();
        int x, y;
        x = loc.x;
        y = loc.y;
        if (!OSUtils.CURRENT_OS.equals(OS.WIN)) {
            final int traySize = OSUtils.getTraySize();
            //assume click is in the middle of the icon
            x = loc.x - traySize / 2;
            if (loc.y < traySize * 4) {
                //tray is on top side of the screen
                y = traySize;
            } else {
                //tray is on bottom side of the screen
                y = d.getBounds().height - traySize;
            }
        } else {
            loc.y -= 2;
        }
        return new Point(x, y);
    }


}
