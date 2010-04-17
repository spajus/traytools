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
package com.googlecode.traytools.config;

import java.util.Map;
import java.util.ResourceBundle;

import com.googlecode.traytools.utils.Logger;
import com.googlecode.traytools.utils.UTF8ResourceBundle;

/**
 * Hawkscope configuration.
 *
 * @author Tomas Varaneckas
 * @version $Id: Configuration.java 591 2009-06-07 15:18:50Z tomas.varaneckas $
 */
public class Configuration {
    
    /**
     * Hawkscope properties bundle
     */
    public static final ResourceBundle SETTINGS;
    
    static {
    	ResourceBundle settings = null;
    	try {
    		settings = UTF8ResourceBundle
    			.getBundle("traytools", Configuration.class.getClassLoader());
    	} catch (final Exception e) {
    		System.err.println("You're almost there! However you should create " +
    				"traytools.properties and add it " +
    				"to your application's src/main/resources. Please refer to TrayTools " +
    				"documentation for further instructions at http://traytools.googlecode.com");
    		System.exit(-1);
    	}
    	SETTINGS = settings;
    }
    
    /**
     * Properties {@link Map}
     */
    private final Map<String, String> properties;
    
    /**
     * Properties changed flag. If true, ConfigurationFactory should persist
     * data to disk.
     */
    private boolean changed = false;
    
    /**
     * Tells if configuration is changed
     * 
     * @return changed
     */
    public boolean isChanged() {
        return changed;
    }

    /**
     * Constructor that initializes properties
     * 
     * @param properties map
     */
    public Configuration(final Map<String, String> properties) {
        this.properties = properties;
    }
    
    /**
     * Gets properties map
     */
    public Map<String, String> getProperties() {
        return properties;
    }
    
    @SuppressWarnings("unchecked")
	public static <T> T getNewInstance(String propertyName) {
    	String className = SETTINGS.getString(propertyName);
    	try {
			return (T) Class.forName(className).newInstance();
		} catch (final Exception e) {
			Logger.error("Failed loading class", propertyName, className, e);
			return null;
		}
    }
    
}
