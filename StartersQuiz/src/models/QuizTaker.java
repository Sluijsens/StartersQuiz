package models;

import me.Sluijsens.StartersQuiz.StartersQuiz;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

public class QuizTaker extends PlayerEvent {
	private int current_question = 0;
	private int config;
	private StartersQuiz plugin;
	
	public QuizTaker(Player player, StartersQuiz instance) {
		super(player);
		
		this.plugin = instance;
	}

	@Override
	public HandlerList getHandlers() {return null;}

	public int getCurrent_question() {
		return current_question;
	}

	public void setCurrent_question(int current_question) {
		this.current_question = current_question;
	}

}
