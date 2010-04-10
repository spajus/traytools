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

import java.util.HashMap;
import java.util.Map;

/**
 * User Home Configuration Factory
 *
 * @author Tomas Varaneckas
 * @version $Id: UserHomeConfigurationFactory.java 422 2009-03-12 18:18:45Z tomas.varaneckas $
 */
public class UserHomeConfigurationFactory extends ConfigurationFactory {

    @Override
    protected String loadConfigFilePath() {
        return System.getProperty("user.home").replaceAll(
				Constants.REGEX_BACKSLASH, Constants.REGEX_SLASH);
	}

	@Override
	protected Map<String, String> getDefaults() {
		return new HashMap<String, String>();
	}

}
