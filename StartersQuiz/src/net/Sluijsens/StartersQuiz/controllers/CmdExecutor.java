package net.Sluijsens.StartersQuiz.controllers;

import net.Sluijsens.StartersQuiz.StartersQuiz;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CmdExecutor implements CommandExecutor {
	private StartersQuiz plugin;
	
	/**
	 * Constructs CmdExecutor and puts StartersQuiz instance into the plugin variable
	 * @param instance Instance of StartersQuiz
	 */
	public CmdExecutor(StartersQuiz instance) {
		this.plugin = instance;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		String[] available = {"quiz","help","start","question","answer","retry","reload"};
		
		if(args.length >= 1 && plugin.in_array(args[0], available)) {
			
		}
		return false;
	}

}
