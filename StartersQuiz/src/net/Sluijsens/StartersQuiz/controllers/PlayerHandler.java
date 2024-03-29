package net.Sluijsens.StartersQuiz.controllers;

import net.Sluijsens.StartersQuiz.StartersQuiz;
import net.Sluijsens.StartersQuiz.data.ConfigHandler;
import net.Sluijsens.StartersQuiz.models.QuizTaker;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class PlayerHandler implements Listener {
	private StartersQuiz plugin;
	
	public PlayerHandler(StartersQuiz instance) {
		this.plugin = instance;
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerInteract(PlayerInteractEvent event) {
		
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerPickupItem(PlayerPickupItemEvent event) {
		
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerDropItem(PlayerDropItemEvent event) {
		
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerChat(AsyncPlayerChatEvent event) {
		
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerChangedWorld(PlayerChangedWorldEvent event) {
		onPlayerJoinedWorld(event.getPlayer());
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerJoin(PlayerJoinEvent event) {
		onPlayerJoinedWorld(event.getPlayer());
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onBlockBreak(BlockBreakEvent event) {
		
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onBlockPlace(BlockPlaceEvent event) {
		
	}
	
	public void onPlayerJoinedWorld(Player player) {
		checkPlayer(player);
		loadPlayer(player);
		StartersQuiz.players.put(player, new QuizTaker(player, plugin));
	}
	
	public void loadPlayer(Player player) {
		
	}
	
	public static boolean isFinished(QuizTaker quiz_taker) {
		ConfigHandler config = quiz_taker.getConfig();
		if(config.isSet(quiz_taker.getCurrent_quiz() + ".finished")) {
			return config.getBoolean(quiz_taker.getCurrent_quiz() + ".finished");
		}
		return false;
	}
	
	public static boolean isStarted(QuizTaker quiz_taker) {
		ConfigHandler config = quiz_taker.getConfig();
		if(config.isSet(quiz_taker.getCurrent_quiz() + ".started")) {
			return config.getBoolean(quiz_taker.getCurrent_quiz() + ".started");
		}
		return false;
	}
	
	public static void checkPlayer(Player player) {
		
	}
}
