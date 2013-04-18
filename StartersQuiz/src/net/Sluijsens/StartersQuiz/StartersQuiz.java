package net.Sluijsens.StartersQuiz;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.Sluijsens.StartersQuiz.controllers.CmdExecutor;
import net.Sluijsens.StartersQuiz.controllers.LanguageHandler;
import net.Sluijsens.StartersQuiz.controllers.PlayerHandler;
import net.Sluijsens.StartersQuiz.controllers.QuizHandler;
import net.Sluijsens.StartersQuiz.data.ConfigHandler;
import net.Sluijsens.StartersQuiz.models.QuizTaker;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class StartersQuiz extends JavaPlugin {
	Logger log = Logger.getLogger("Minecraft");
	
	public static HashMap<Player, QuizTaker> players = new HashMap<Player, QuizTaker>();
	
	public static ConfigHandler config_handler;
	public static LanguageHandler language_handler;
	public static QuizHandler quiz_handler;
	private final PlayerHandler playerListener = new PlayerHandler(this);
	
	public static String chat_tag = ChatColor.AQUA + "*StartersQuiz* " + ChatColor.GOLD;
	public static Permission permission = null;
	public static Economy economy = null;
	
	/**
	 * Gets called when plugin is enabled
	 */
	public void onEnable() {
		this.loadConfig();
		
		if(this.getServer().getPluginManager().getPlugin("Vault") == null) {
			log.log(Level.SEVERE, "The plugin Vault ha snot been found so " + getDescription().getName() + " will be disabled.", getDescription().getName());
		} else {
			setupPermissions();
			setupEconomy();
		}
		
		//Declare the PluginManager and CommandExecutor
		PluginManager pm = this.getServer().getPluginManager();
		CmdExecutor cmdExecutor = new CmdExecutor(this);
		
		/* Register Events
		 * Register Player events*/
		pm.registerEvents(playerListener, this);
		
		//Register Command events
		getCommand("sq").setExecutor(cmdExecutor);
		getCommand("sqhelp").setExecutor(cmdExecutor);
		getCommand("sqstart").setExecutor(cmdExecutor);
		getCommand("sqquestion").setExecutor(cmdExecutor);
		getCommand("sqanswer").setExecutor(cmdExecutor);
		getCommand("sqretry").setExecutor(cmdExecutor);
		getCommand("sqreload").setExecutor(cmdExecutor);
		
		log.info("The plugin " + this.getName() + " v" + this.getDescription().getVersion() + " is enabled.");
	}
	
	/**
	 * Gets called when the plugin is disabled
	 */
	public void onDisable() {
		log.info("The plugin " + this.getName() + " v" + this.getDescription().getVersion() + " is disabled.");
	}
	
	/**
	 * Setup the permissions using from Vault
	 * @return Returns true if setup was successful
	 */
    private Boolean setupPermissions()
    {
        RegisteredServiceProvider<Permission> permissionProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
        if (permissionProvider != null) {
            permission = permissionProvider.getProvider();
        }
        return (permission != null);
    }
    
    /**
	 * Setup the economy possibilities from Vault
	 * @return Returns true if setup was successful
	 */
	private boolean setupEconomy() {
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economyProvider != null) {
            economy = economyProvider.getProvider();
        }

        return (economy != null);
    }
	
	/**
	 * Checks if object is in an array
	 * @param value
	 * @param array
	 * @return
	 */
	public boolean in_array(Object value, Object[] array) {
		for(Object o: array) {
			if(o == value) return true;
		}
		return false;
	}
	
	/**
	 * Check if string is in an array
	 * @param value
	 * @param array
	 * @return
	 */
	public boolean in_array(String value, String[] array) {
		for(String s: array) {
			if(s.equalsIgnoreCase(value)) return true;
		}
		return false;
	}
	
	public boolean loadConfig() {
		try {
			config_handler = new ConfigHandler("config", this.getDataFolder() + "/", this);
			language_handler = new LanguageHandler(config_handler.getString("Language", "en-GB"), this.getDataFolder() + "/lang", this);
			quiz_handler = new QuizHandler(this);
			
			this.getServer().broadcastMessage(chat_tag + "Loaded configuration files!");
			
			return true;
		} catch(Exception e) {
			this.getServer().broadcastMessage(chat_tag + "Failed to load configuration files!");
		}
		
		return false;
	}
	
	public void reload() {
		loadConfig();
	}
}
