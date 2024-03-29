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


/**
 * A class for storing constant values
 *
 * @author Tomas Varaneckas
 * @version $Id: Constants.java 505 2009-03-29 06:48:35Z tomas.varaneckas $
 */
public class Constants {
    
    /**
     * A "UTF-8" String litteral
     */
    public static final String ENCODING = Configuration.SETTINGS.getString("encoding");
    
    /**
     * Slash for regex
     */
    public static final String REGEX_SLASH = "/";
    
    /**
     * Backslash for regex
     */
    public static final String REGEX_BACKSLASH = "\\\\";
    
}
