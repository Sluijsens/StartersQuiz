package net.Sluijsens.StartersQuiz.models;

import net.Sluijsens.StartersQuiz.StartersQuiz;
import net.Sluijsens.StartersQuiz.controllers.PlayerHandler;
import net.Sluijsens.StartersQuiz.data.ConfigHandler;

import org.bukkit.entity.Player;

public class QuizTaker {
	private int current_question = 0;
	private String current_quiz = null;
	private ConfigHandler config;
	
	private boolean started = false;
	private boolean finished = false;
	
	/**
	 * Constructs QuizTaker with given data
	 * @param player
	 * @param instance
	 */
	public QuizTaker(Player player, StartersQuiz instance) {
		this.setConfig(new ConfigHandler(player.getName(), instance.getDataFolder() + "/players/", instance));
		this.findCurrentQuiz(player);
	}
	
	/**
	 * Finds the current quiz the player has to do
	 * @param player
	 */
	public void findCurrentQuiz(Player player) {
		String[] quizzes = (String[]) StartersQuiz.quiz_handler.quizzes.keySet().toArray();
		
		for(String quiz: quizzes) {
			if(StartersQuiz.permission.has(player, "sq.quiz." + quiz)) {
				this.setCurrent_quiz(quiz);
				break;
			}
		}
		
		if(this.current_quiz != null) {
			PlayerHandler.isFinished(this);
		}
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
	 * Get the current quiz the player has to do
	 * @return
	 */
	public String getCurrent_quiz() {
		return current_quiz;
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

	public void setCurrent_quiz(String current_quiz) {
		this.current_quiz = current_quiz;
	}

}
