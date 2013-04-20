package net.Sluijsens.StartersQuiz.models;

import java.util.ArrayList;
import java.util.List;

import net.Sluijsens.StartersQuiz.StartersQuiz;
import net.Sluijsens.StartersQuiz.data.ConfigHandler;

public class Quiz {
	private ConfigHandler config;
	private String[] questions;
	private String[] correct_answers;
	private ArrayList<String[]> possible_answers = new ArrayList<String[]>();
	
	private int max_attempts = 1;
	private String time_of_ban;
	
	private String promotion_group = null;
	private int money_reward = 0;
	private ItemReward[] items_reward = null;
	private List<String> add_permissions = null;
	private List<String> remove_permissions = null;
	private List<String> execute_commands = null;
	
	/**
	 * Construct the Quiz with the given data
	 * @param name
	 * @param instance
	 */
	public Quiz(String name, StartersQuiz instance) {
		this.config = new ConfigHandler(name, instance.getDataFolder() + "/quizzes/", instance);
		StartersQuiz.log.info("Quiz: " + name);
		this.loadQuestions();
		this.loadRewards();
		this.loadRules();
	}
	
	/**
	 * Loads the questions, possible answers and the correct answers.
	 */
	public void loadQuestions() {
		List<String> quiz = (List<String>) config.getList("Quiz");
		
		this.questions = 
				new String[quiz.size()];
		this.correct_answers = 
				new String[quiz.size()];
		
		// Loop through all question_lines
		for(int i = 0; i < quiz.size(); i++) {
			String[] question_lines = quiz.get(i).split(";");
			
			this.questions[i] = question_lines[0];
			this.correct_answers[i] = question_lines[1];
			
			// Temporary possible answers array to populate the real possible answers ArrayList
			String [] possible_answers = new String[question_lines.length-2];
			
			// Loop further through the question lines and add the possible answers to the temporary array
			for(int y = 2; y < question_lines.length; y++) {
				possible_answers[y-2] = question_lines[y];
			}
			
			// Add the array to the ArrayList
			this.possible_answers.add(possible_answers);
		}
	}
	
	/**
	 * Load all the rewards that can be given to the player.
	 */
	public void loadRewards() {
		if(config.isSet("PromoteToGroup")) {
			this.promotion_group = config.getString("PromoteToGroup");
		}
		
		if(config.isSet("RewardMoney")) {
			this.money_reward = config.getInt("RewardMoney");
		}
		
		if(config.isSet("RewardItems")) {
			List<String> item_lines = (List<String>) config.getList("RewardItems");
			this.items_reward = new ItemReward[item_lines.size()];
			
			for(int i = 0; i < item_lines.size(); i++) {
				String item_line = item_lines.get(i);
				String[] amount_and_item = item_line.split("x");
				
				int item = Integer.parseInt(amount_and_item[1].split(":")[0]);
				int item_type = Integer.parseInt(amount_and_item[1].split(":")[1]);
				int amount = Integer.parseInt(amount_and_item[0]);
				
				this.items_reward[i] = new ItemReward(item, item_type, amount);
			}
		}
		
		if(config.isSet("AddPermissions")) {
			this.add_permissions = (List<String>) config.getList("AddPermissions");
		}
		
		if(config.isSet("RemovePermissions")) {
			this.remove_permissions = (List<String>) config.getList("RemovePermissions");
		}
		
		if(config.isSet("ExecuteCommands")) {
			this.execute_commands = (List<String>) config.getList("ExecuteCommands");
		}
	}
	
	/**
	 * Load the rules like ban times and max attempts
	 */
	public void loadRules() {
		if(config.isSet("MaxAttempts")) {
			this.max_attempts = config.getInt("MaxAttempts");
		}
		
		if(config.isSet("TimeOfBan")) {
			this.time_of_ban = config.getString("TimeOfBan");
		}
	}
}
