package net.Sluijsens.StartersQuiz.models;

import net.Sluijsens.StartersQuiz.StartersQuiz;
import net.Sluijsens.StartersQuiz.data.ConfigHandler;

import org.bukkit.entity.Player;

public class QuizTaker {
	private int current_question = 0;
	private ConfigHandler config;
	private StartersQuiz plugin;
	
	/**
	 * Constructs QuizTaker with given data
	 * @param player
	 * @param instance
	 */
	public QuizTaker(Player player, StartersQuiz instance) {
		this.plugin = instance;
		this.setConfig(new ConfigHandler(player.getName(), instance.getDataFolder() + "/players/", instance));
	}
	
	/**
	 * Get the current question the Quiztaker is at
	 * @return
	 */
	public int getCurrent_question() {
		return current_question;
	}
	
	/**
	 * Get the configugartion file of the current quiztaker 
	 * @return
	 */
	public ConfigHandler getConfig() {
		return config;
	}
	
	/**
	 * Set the current question
	 * @param current_question
	 */
	public void setCurrent_question(int current_question) {
		this.current_question = current_question;
	}
	
	/**
	 * Set the configuration file
	 * @param config
	 */
	public void setConfig(ConfigHandler config) {
		this.config = config;
	}

}
