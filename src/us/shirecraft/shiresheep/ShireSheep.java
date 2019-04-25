package us.shirecraft.shiresheep;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class ShireSheep extends JavaPlugin {
	
	@Override
	public void onEnable() {
		loadConfiguration();
	}
	
	@Override
	public void onDisable() {
		saveConfiguration();
	}
	
	public void loadConfiguration() {
		saveDefaultConfig();
		config = getConfig();
	}
	
	public void saveConfiguration() {
		saveConfig();
	}

	FileConfiguration config;
}
