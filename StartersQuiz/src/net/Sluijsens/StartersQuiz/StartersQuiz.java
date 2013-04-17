package net.Sluijsens.StartersQuiz;

import java.util.logging.Level;
import java.util.logging.Logger;

import net.Sluijsens.StartersQuiz.data.ConfigHandler;
import net.Sluijsens.StartersQuizcontrollers.CmdExecutor;
import net.Sluijsens.StartersQuizcontrollers.LanguageHandler;
import net.Sluijsens.StartersQuizcontrollers.PlayerHandler;
import net.Sluijsens.StartersQuizcontrollers.QuizHandler;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;

import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;



public class StartersQuiz extends JavaPlugin {
	Logger log = Logger.getLogger("Minecraft");
	
	public static ConfigHandler config_handler;
	public static LanguageHandler language_handler;
	public static QuizHandler quiz_handler;
	private final PlayerHandler playerListener = new PlayerHandler(this);
	
	public static String chat_tag = ChatColor.AQUA + "*StartersQuiz* " + ChatColor.GOLD;
	public static Permission permission = null;
	public static Economy economy = null;
	
	public void onEnable() {
		config_handler = new ConfigHandler("config", this.getDataFolder() + "/", this);
		language_handler = new LanguageHandler(config_handler.getString("Language", "en-GB"), this.getDataFolder() + "/lang", this);
		quiz_handler = new QuizHandler(this);
		
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
	}
	
	public void onDisable() {
		
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
}
