package vexsmp.plugin.EventListeners;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import vexsmp.plugin.Main;

public class PlayerWorldNameListener implements Listener{
	
	private Main pluginInstance;
	
	public PlayerWorldNameListener(Main main) {
		pluginInstance = main;
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		String worldName = ChatColor.GOLD + pluginInstance.getConfig().getString("ServerName");
		player.setPlayerListHeader(worldName);
		updatePlayerWorld(player);
	}
	
	@EventHandler
	public void onPlayerChangeWorld(PlayerChangedWorldEvent event) {
		updatePlayerWorld(event.getPlayer());
	}
	
	private void updatePlayerWorld(Player player) {
		World world = player.getLocation().getWorld();
		String worldName = ChatColor.WHITE + "[" + ChatColor.valueOf(pluginInstance.getConfig().getString("OverworldColor")) + "The Overworld" + ChatColor.WHITE + "]";
		
		if(world.getName().contains("end")) {
			worldName = ChatColor.WHITE + "[" + ChatColor.valueOf(pluginInstance.getConfig().getString("EndColor")) + "The End" + ChatColor.WHITE + "]";
		}else if(world.getName().contains("nether")) {
			worldName = ChatColor.WHITE + "[" + ChatColor.valueOf(pluginInstance.getConfig().getString("NetherColor")) + "The Nether" + ChatColor.WHITE + "]";
		}
		
		player.setPlayerListName(player.getName() + " " + worldName);
	}
}
