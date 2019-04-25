package us.shirecraft.shiresheep;

import java.util.HashMap;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class ShireSheep extends JavaPlugin {
	
	@Override
	public void onEnable() {
		loadConfiguration();
		loadProbabilities();
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
	
	public void loadProbabilities() {
		probabilities = new HashMap<String, Float>();
		probabilities.put("black",      Integer.valueOf(config.getInt("colours.black")).floatValue());
		probabilities.put("blue",       Integer.valueOf(config.getInt("colours.blue")).floatValue());
		probabilities.put("brown",      Integer.valueOf(config.getInt("colours.brown")).floatValue());
		probabilities.put("cyan",       Integer.valueOf(config.getInt("colours.cyan")).floatValue());
		probabilities.put("green",      Integer.valueOf(config.getInt("colours.green")).floatValue());
		probabilities.put("gray",       Integer.valueOf(config.getInt("colours.gray")).floatValue());
		probabilities.put("light_blue", Integer.valueOf(config.getInt("colours.light_blue")).floatValue());
		probabilities.put("light_gray", Integer.valueOf(config.getInt("colours.light_gray")).floatValue());
		probabilities.put("lime",       Integer.valueOf(config.getInt("colours.lime")).floatValue());
		probabilities.put("magenta",    Integer.valueOf(config.getInt("colours.magenta")).floatValue());
		probabilities.put("orange",     Integer.valueOf(config.getInt("colours.orange")).floatValue());
		probabilities.put("pink",       Integer.valueOf(config.getInt("colours.pink")).floatValue());
		probabilities.put("purple",     Integer.valueOf(config.getInt("colours.purple")).floatValue());
		probabilities.put("red",        Integer.valueOf(config.getInt("colours.red")).floatValue());
		probabilities.put("white",      Integer.valueOf(config.getInt("colours.white")).floatValue());
		probabilities.put("yellow",     Integer.valueOf(config.getInt("colours.yellow")).floatValue());
	}

	public HashMap<String, Float> probabilities;
	FileConfiguration config;
}
