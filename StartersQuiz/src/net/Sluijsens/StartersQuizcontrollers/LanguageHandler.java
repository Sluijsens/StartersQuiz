package net.Sluijsens.StartersQuizcontrollers;

import java.io.File;

import net.Sluijsens.StartersQuiz.StartersQuiz;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class LanguageHandler {
	private File file;
	private FileConfiguration conf;
	
	private File englishFile;
	private FileConfiguration englishConf;
	
	public LanguageHandler(String lang, String dir, StartersQuiz instance) {
		//load the file
		file = new File(dir, lang + ".yml");
		conf = YamlConfiguration.loadConfiguration(file);
		
		englishFile = new File(dir, "en-GB.yml");
		englishConf = YamlConfiguration.loadConfiguration(englishFile);
	}
	
	/**
	 * Get the message from the language file. If the configured language file does not exist then it checks for the english file.
	 * @param property Property to get from the language files
	 * @return Returns the message it got from the language file.
	 */
	public String getMessage(String property) {
		// Check if configured or English language file exists
		if(!file.exists() && !englishFile.exists()) return "Both the configured and the english language files do not exist!";
		
		// Either the configured language file exists, the English file exists or both
		// Return the string in the configured language or English
		if(conf.isSet("phrases." + property)) {
			return replaceColor(conf.getString("phrases." + property));
		} else return replaceColor(englishConf.getString("phrases." + property));
	}
	
	/**
	 * Replace the &-character with the Color Character used for colors in the chat.
	 * @param msg Message to check and replace & in.
	 * @return Returns the edited message
	 */
	private String replaceColor(String msg) {
		msg = msg.replaceAll("&", "" + ChatColor.COLOR_CHAR);
		return msg;
	}
}
