/*
 * @Author DarkPhantom1337
 * @Version 1.0.0
 */
package ua.darkphantom1337.endbreak;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

	private ConfigFile cfg;

	public void onEnable() {
		try {
			cfg = new ConfigFile(this);
			Bukkit.getPluginManager().registerEvents(this, this);
			Bukkit.getConsoleSender()
					.sendMessage("§a[§eEndBreak§a] §f-> §aPlugin succesfully enabled! // by Darkphantom1337, 2020");
		} catch (Exception e) {
			Bukkit.getConsoleSender()
					.sendMessage("§c[§eEndBreak§c] §f-> §cError in enabling plugin! Plugin disabled!\nError:"
							+ e.getLocalizedMessage());
			this.setEnabled(false);
		}
	}

	public void onDisable() {
		Bukkit.getConsoleSender()
				.sendMessage("§c[§eEndBreak§c] §f-> §cPlugin succesfully disabled! // by Darkphantom1337, 2020");
	}

	@EventHandler
	public void onBucketEmpty(PlayerBucketEmptyEvent e) {
		if (e.getBlock() != null && (e.getBlock().getType().equals(Material.END_PORTAL_FRAME) || e.getBlock().getType().equals(Material.END_PORTAL))
				&& !e.getPlayer().hasPermission(cfg.getStringValue("BypassPerm"))) {
			e.setCancelled(true);
			if (cfg.getBooleanValue("EnabledMessages"))
				e.getPlayer().sendMessage(cfg.getStringValue("Messages.OnBucketEmpty").replaceAll("&", "§"));

		}
	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		if (e.getBlock() != null && (e.getBlock().getType().equals(Material.END_PORTAL_FRAME) || e.getBlock().getType().equals(Material.END_PORTAL)) 
				&& !e.getPlayer().hasPermission(cfg.getStringValue("BypassPerm"))) {
			e.setCancelled(true);
			if (cfg.getBooleanValue("EnabledMessages"))
				e.getPlayer().sendMessage(cfg.getStringValue("Messages.OnBlockBreak").replaceAll("&", "§"));
		}
	}

}
