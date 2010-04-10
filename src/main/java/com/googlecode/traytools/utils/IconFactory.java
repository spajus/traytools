/*
 * Copyright (c) 2008-2009 Tomas Varaneckas
 * http://www.varaneckas.com
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.googlecode.traytools.utils;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

import com.googlecode.traytools.config.Configuration;

/**
 * {@link IconFactory} - SWT implmementation
 * 
 * @author Tomas Varaneckas
 * @version $Id: IconFactory.java 528 2009-04-04 09:59:18Z tomas.varaneckas $
 */
public class IconFactory {

    /**
     * Singleton instance
     */
	private static IconFactory instance;
	
	/**
	 * Singleton constructor
	 */
	private IconFactory() {}
	
	private final Display display = Display.getDefault();
	
	/**
	 * Gets the singleton instance
	 * 
	 * @return
	 */
	public static IconFactory getInstance() {
	    synchronized (IconFactory.class) {
    	    if (instance == null) {
    	        instance = new IconFactory();
    	    }
	    }
		return instance;
	}
	
	/**
	 * Image Resource Pool
	 */
    private final Map<String, Image> resourcePool = new HashMap<String, Image>();
    
    /**
     * Gets uncached icon
     * 
     * @param name Icon name with extension
     * @return icon
     */
    public Image getIcon(final String name) {
        if (resourcePool.containsKey(name)) {
            return resourcePool.get(name);
        } 
        final Image i = new Image(display, IconFactory.class.getClassLoader()
                .getResourceAsStream(name));
        resourcePool.put(name, i);
        return i;
    }
    
    /**
     * Gets icon with plugin's classloader
     * 
     * @param name Icon name with extension
     * @return icon
     */
    public Image getIcon(final String name, final ClassLoader classLoader) {
        if (resourcePool.containsKey(name)) {
            return resourcePool.get(name);
        } 
        final Image i = new Image(display, classLoader
                .getResourceAsStream(name));
        resourcePool.put(name, i);
        return i;
    }    

    /**
     * Gets Hawkscope Tray Icon of best size
     * 
     * @return tray icon
     */
    public Image getTrayIcon() {
        final String name = "TrayToolsTrayIcon";
        if (resourcePool.containsKey(name)) {
            resourcePool.get(name);
        }
        final Image trayIcon = new Image(display, IconFactory.class
                .getClassLoader().getResourceAsStream(getBestTrayIcon()));
        resourcePool.put(name, trayIcon);
        return trayIcon;
    }
    
    /**
     * Releases the resources
     */
    public synchronized void cleanup() {
        for (final String im : resourcePool.keySet()) {
            try {
                Logger.debug("Releasing icon: " + im);
                resourcePool.get(im).dispose();
            } catch (final Exception e) {
                Logger.debug("Failed releasing icon", e);
            }
        }        
        resourcePool.clear();
    }
    
    
    /**
     * Gets best sized tray icon name for current setup
     * 
     * @return tray icon name
     */
    protected String getBestTrayIcon() {
        final float height = OSUtils.getTrayIconSize();
        int[] sizes = new int[] { 64, 48, 32, 24, 16 };
        int best = 64;
        for (int i = 0; i < sizes.length; i++) {
            if (sizes[i] / height >= 1) {
                best = sizes[i];
            }
            else {
                break;
            }
        }
        sizes = null;
        final String res = 
        	Configuration.SETTINGS.getString("tray.icon.".concat(String.valueOf(best)));
        	Logger.debug("Chose best icon for ", height, "pixel tray", res);
        return res;
    }   
}
