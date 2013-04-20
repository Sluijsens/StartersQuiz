package net.Sluijsens.StartersQuiz.models;

import java.util.ArrayList;
import java.util.logging.Level;

import net.Sluijsens.StartersQuiz.StartersQuiz;
import net.Sluijsens.StartersQuiz.controllers.PlayerHandler;
import net.Sluijsens.StartersQuiz.controllers.QuizHandler;
import net.Sluijsens.StartersQuiz.data.ConfigHandler;

import org.bukkit.entity.Player;

public class QuizTaker {
	private int current_question = -1;
	private String current_quiz = null;
	private ConfigHandler config = null;
	
	private boolean started = false;
	private boolean finished = false;
	
	private StartersQuiz plugin;
	
	/**
	 * Constructs QuizTaker with given data
	 * @param player
	 * @param instance
	 */
	public QuizTaker(Player player, StartersQuiz instance) {
		this.plugin = instance;
		
		this.setConfig(new ConfigHandler(player.getName(), instance.getDataFolder() + "/players/", instance));
		this.findCurrentQuiz(player);
		
		if(this.current_quiz != null) {
			this.setFinished(PlayerHandler.isFinished(this));
			try {
				this.setStarted(PlayerHandler.isStarted(this));
			} catch (Exception e) {
				StartersQuiz.log.log(Level.SEVERE, e.getMessage());
				player.sendMessage(StartersQuiz.chat_tag + " ##Could not set started error");
			}
		}
	}
	
	/**
	 * Finds the current quiz the player has to do
	 * @param player
	 */
	public void findCurrentQuiz(Player player) {
		ArrayList<String> quizzes = new ArrayList<String>(QuizHandler.quizzes.keySet());
		
		for(String quiz: quizzes) {
			if(StartersQuiz.permission.has(player, "sq.quiz." + quiz)) {
				this.setCurrentQuiz(quiz);
				player.sendMessage("Your quiz: " + quiz);
				break;
			}
		}
	}
	
	/**
	 * Load current question from file
	 */
	public void loadCurrentQuestion() {
		current_question = config.getInt(current_quiz + ".current_question", 1);
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
	
	/**
	 * Set the current quiz
	 * @param current_quiz
	 */
	public void setCurrentQuiz(String current_quiz) {
		this.current_quiz = current_quiz;
	}
	
	/**
	 * Returns whether the QuizTaker has finished his/her current quiz
	 * @return
	 */
	public boolean isFinished() {
		return finished;
	}
	
	/**
	 * Sets finished
	 * @param finished
	 */
	public void setFinished(boolean finished) {
		this.finished = finished;
		
		if(this.finished == true) {
			try {
				this.setStarted(false);
			} catch (Exception e) {
				StartersQuiz.log.log(Level.SEVERE, e.getMessage());
			}
		}
	}
	
	/**
	 * Returns whether the QuizTaker has started the current quiz
	 * @return
	 */
	public boolean isStarted() {
		return started;
	}
	
	
	public void setStarted(boolean started) throws Exception {
		if(this.isFinished()) {
			throw new Exception("Cannot start quiz because it has already been finished!");
		}
		this.started = started;
	}

}
