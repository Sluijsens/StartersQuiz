package net.Sluijsens.StartersQuiz.data;

import java.io.File;
import java.io.IOException;
import java.util.List;

import net.Sluijsens.StartersQuiz.StartersQuiz;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigHandler {
	private File file;
	private FileConfiguration conf;
	
	/**
	 * Loads the specified file
	 * @param file_name - Name of the file without extension
	 * @param directory - Directory where to load from and save the file to
	 * @param instance - Instance of the plugin
	 */
	public ConfigHandler(String file_name, String directory, StartersQuiz instance) {
		//load the file
		file = new File(directory, file_name + ".yml");
		conf = YamlConfiguration.loadConfiguration(file);
	}
	
	//set values
	/**
	 * Set a string property
	 * @param property The property to set
	 * @param value The value to put at the property
	 */
	public void set(String property, String value) {
		conf.set(property, value);
	}
	
	/**
	 * Set a boolean property
	 * @param property The property to set
	 * @param value The value to put at the property
	 */
	public void set(String property, boolean value) {
		conf.set(property, value);
	}
	
	/**
	 * Set a int property
	 * @param property The property to set
	 * @param value The value to put at the property
	 */
	public void set(String property, int value) {
		conf.set(property, value);
	}
	
	/**
	 * Set a long property
	 * @param property The property to set
	 * @param value The value to put at the property
	 */
	public void set(String property, long value) {
		conf.set(property, value);
	}
	
	/**
	 * Set a double property
	 * @param property The property to set
	 * @param value The value to put at the property
	 */
	public void set(String property, double value) {
		conf.set(property, value);
	}
	
	/**
	 * Set a list property
	 * @param property The property to set
	 * @param value The value to put at the property
	 */
	public void set(String property, List<?> value) {
		conf.set(property, value);
	}
	
	//get values
	/**
	 * Get a string property
	 * @param property The property to get from
	 * @return Return value
	 */
	public String getString(String property) {
		return conf.getString(property);
	}
	
	/**
	 * Get a string property and if not found use the given value 
	 * @param property The property to get from
	 * @param value The value to use when nothing is found
	 * @return Return value
	 */
	public String getString(String property, String value) {
		if(isSet(property)) value = conf.getString(property);   
		return value;
	}
	
	/**
	 * Get a boolean property
	 * @param property The property to get from
	 * @return Return value
	 */
	public boolean getBoolean(String property) {
		return conf.getBoolean(property);
	}
	
	/**
	 * Get a boolean property and if not found use the given value 
	 * @param property The property to get from
	 * @param value The value to use when nothing is found
	 * @return Return value
	 */
	public boolean getBoolean(String property, boolean value) {
		if(isSet(property)) value = conf.getBoolean(property);   
		return value;
	}
	
	/**
	 * Get a double property
	 * @param property The property to get from
	 * @return Return value
	 */
	public double getDouble(String property) {
		return conf.getDouble(property);
	}
	
	/**
	 * Get a double property and if not found use the given value 
	 * @param property The property to get from
	 * @param value The value to use when nothing is found
	 * @return Return value
	 */
	public double getDouble(String property, double value) {
		if(isSet(property)) value = conf.getDouble(property);   
		return value;
	}
	
	/**
	 * Get a long property
	 * @param property The property to get from
	 * @return Return value
	 */
	public long getLong(String property) {
		return conf.getLong(property);
	}
	
	/**
	 * Get a long property and if not found use the given value 
	 * @param property The property to get from
	 * @param value The value to use when nothing is found
	 * @return Return value
	 */
	public long getLong(String property, long value) {
		if(isSet(property)) value = conf.getLong(property);   
		return value;
	}
	
	/**
	 * Get a List property
	 * @param property The property to get from
	 * @return Return value
	 */
	public List<?> getList(String property) {
		return conf.getList(property);
	}
	
	/**
	 * Get a List property and if not found use the given value 
	 * @param property The property to get from
	 * @param value The value to use when nothing is found
	 * @return Return value
	 */
	public List<?> getList(String property, List<?> value) {
		if(isSet(property)) value = conf.getList(property);   
		return value;
	}
	
	/**
	 * Get an int property
	 * @param property The property to get from
	 * @return Return value
	 */
	public int getInt(String property) {
		return conf.getInt(property);
	}
	
	/**
	 * Get an int property and if not found use the given value 
	 * @param property The property to get from
	 * @param value The value to use when nothing is found
	 * @return Return value
	 */
	public int getInt(String property, int value) {
		if(isSet(property)) value = conf.getInt(property);   
		return value;
	}
	
	/**
	 * Get an Object property
	 * @param property The property to get from
	 * @return Return value
	 */
	public Object get(String property) {
		return conf.get(property);
	}
	
	/**
	 * Get an Object property and if not found use the given value 
	 * @param property The property to get from
	 * @param value The value to use when nothing is found
	 * @return Return value
	 */
	public Object getObject(String property, Object value) {
		if(isSet(property)) value = conf.get(property);   
		return value;
	}
	
	/**
	 * Check if the property has been set
	 * @param property Property to check
	 * @return Return true if set, return false if not
	 */
	public boolean isSet(String property) {
		if(conf.isSet(property)) return true;
		else return false;
	}
	
	/**
	 * Check if the file exists
	 * @return Return true if file exists
	 */
	public boolean exists() {
		if(file.exists()) return true;
		else return false;
	}
	
	/**
	 * Save the file
	 * @return Return true if saved successfully
	 */
	public boolean save() {
		//save file
		try {
			conf.save(file);
			return true;
		} catch (IOException e) {
			return false;
		}
	}
}
