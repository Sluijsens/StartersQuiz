package net.Sluijsens.StartersQuiz.controllers;

import net.Sluijsens.StartersQuiz.StartersQuiz;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

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
	public void onPlayerTeleport(PlayerTeleportEvent event) {
		World from = event.getFrom().getWorld();
		World to = event.getTo().getWorld();
		
		if(from != to) {
			Player player = event.getPlayer();
			
			player.sendMessage(plugin.chat_tag + " Same world");
		}
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onBlockBreak(BlockBreakEvent event) {
		
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onBlockPlace(BlockPlaceEvent event) {
		
	}
}
