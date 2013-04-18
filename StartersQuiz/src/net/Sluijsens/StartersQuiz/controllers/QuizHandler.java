package net.Sluijsens.StartersQuiz.controllers;

import java.io.File;
import java.util.HashMap;

import net.Sluijsens.StartersQuiz.StartersQuiz;
import net.Sluijsens.StartersQuiz.models.Quiz;

public class QuizHandler {
	public HashMap<String, Quiz> quizzes = new HashMap<String, Quiz>();
	private StartersQuiz plugin;
	
	public QuizHandler(StartersQuiz instance) {
		this.plugin = instance;
	}
	
	public void loadQuizzes() {
		File dir = new File(plugin.getDataFolder() + "/quizzes/");
		File[] files = dir.listFiles();
		
		for(File f: files) {
			String quiz_name = f.getName().replace(".yml", "");
			quizzes.put(quiz_name, new Quiz(quiz_name));
		}
	}
}
