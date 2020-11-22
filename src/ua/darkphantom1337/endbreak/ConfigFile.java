/*
 * @Author DarkPhantom1337
 * @Version 1.0.0
 */
package ua.darkphantom1337.endbreak;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigFile {

	private FileConfiguration filecfg;
	private Main plugin;
	private File file;

	public ConfigFile(Main plugin) {
		this.plugin = plugin;
		setupCfgFile();
		if (getCfgFile().isSet("EndBreak"))
			saveCfgFile();
		else
			firstFill();
	}

	private void setupCfgFile() {
		if (!plugin.getDataFolder().exists()) {
			plugin.getDataFolder().mkdir();
		}
		file = new File(plugin.getDataFolder(), "config.yml");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException localIOException) {
			}
		}
		new YamlConfiguration();
		filecfg = YamlConfiguration.loadConfiguration(file);
	}

	private FileConfiguration getCfgFile() {
		return filecfg;
	}

	public void saveCfgFile() {
		try {
			filecfg.save(file);
		} catch (IOException localIOException) {
		}
	}

	public void reloadCfgFile() {
		new YamlConfiguration();
		filecfg = YamlConfiguration.loadConfiguration(file);
	}

	private void firstFill() {
		getCfgFile().set("EndBreak", " File: config.yml || Author: DarkPhantom1337");
		getCfgFile().set("EnabledMessages", true);
		getCfgFile().set("Messages.OnBucketEmpty", "§cВам запрещено выливать жидкость в портал!");
		getCfgFile().set("Messages.OnBlockBreak", "§cВам запрещено ломать этот блок!");
		getCfgFile().set("BypassPerm", "endbreak.allow");
		saveCfgFile();
	}

	public String getStringValue(String path) {
		return getCfgFile().getString(path);
	}

	public Boolean getBooleanValue(String path) {
		return getCfgFile().getBoolean(path);
	}

}
