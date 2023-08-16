package vexsmp.plugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

import vexsmp.plugin.EventListeners.PlayerWorldNameListener;

public class Main extends JavaPlugin{
	
	private final String line = "============================";

	@Override
	public void onEnable() {
		
		getConfig().options().copyDefaults();
		saveDefaultConfig();
		
		getServer().getPluginManager().registerEvents(new PlayerWorldNameListener(this), this);
		
		if(Boolean.valueOf(getConfig().getString("ShowPing"))) {
			Bukkit.getScheduler().runTaskTimer(this, this::updateAllTabNames, 0L, 100L);
		}
		
		getLogger().info(line);
		getLogger().info("Tab Info Plugin Enabled");
		getLogger().info(line);
	}
	
	@Override
	public void onDisable() {
		
		HandlerList.unregisterAll(this);
		
		getLogger().info(line);
		getLogger().info("Tab Info Plugin Disabled");
		getLogger().info(line);
	}
	
	private void updateAllTabNames() {
		for(Player player : Bukkit.getOnlinePlayers()) {
			getPlayerPing(player);
		}
	}
	
	private void getPlayerPing(Player player) {
		int ping = player.getPing();
		
		int bestMaxPing = getConfig().getInt("BestPingMax");
		int goodMaxPing = getConfig().getInt("GoodPingMax");
		
		if(ping <= bestMaxPing) {
			player.setPlayerListFooter("Ping: " + ChatColor.valueOf(this.getConfig().getString("BestPingColor")) + ping + " ms");
		}else if(ping <= goodMaxPing) {
			player.setPlayerListFooter("Ping: " + ChatColor.valueOf(this.getConfig().getString("GoodPingColor")) + ping + " ms");
		}else {
			player.setPlayerListFooter("Ping: " + ChatColor.valueOf(this.getConfig().getString("PoorPingColor")) + ping + " ms");
		}
		
		
	}
}
