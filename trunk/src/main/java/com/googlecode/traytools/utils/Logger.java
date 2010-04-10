package com.googlecode.traytools.utils;

import java.io.OutputStream;
import java.io.PrintStream;

import com.googlecode.traytools.config.Configuration;

public class Logger {

	public static enum Level {
		DEBUG(5), INFO(4), WARNING(3), ERROR(2), FATAL(1);
		private final int lvl;
		public int getLevel() {
			return lvl;
		}
		private Level(int lvl) {
			this.lvl = lvl;
		}
	}

	private static Level level = getDefaultLevel();
	
	private static PrintStream output = System.out;
	
	public static void setOutput(final OutputStream out) {
		if (output instanceof PrintStream) {
			output = (PrintStream) out;
		} else if (out != null) {
			output = new PrintStream(out);
		} else {
			output = null;
		}
	}
	
	private static Level getDefaultLevel() {
		String level = Configuration.SETTINGS.getString("logger.level").toUpperCase();
		if (level.equals("OFF")) {
			return null;
		}
		return Level.valueOf(level);
	}

	public static void log(Level logLevel, Object ... messages) {
		if (output == null || level == null || logLevel.getLevel() > level.getLevel()) {
			return;
		}
		output.print(level);
		output.print(": ");
		for (int i = 0; i < messages.length; i++) {
			output.print(messages[i]);
			if (i != messages.length - 1) {
				output.print(", ");
			}
		}
		output.println();
	}
	
	public static void debug(Object ... messages) {
		log(Level.DEBUG, messages);
	}
	
	public static void info(Object ... messages) {
		log(Level.INFO, messages);
	}
	
	public static void warn(Object ... messages) {
		log(Level.WARNING, messages);
	}
	
	public static void error(Object ... messages) {
		log(Level.ERROR, messages);
	}
	
	public static void fatal(Object ... messages) {
		log(Level.FATAL, messages);
	}
	
}
