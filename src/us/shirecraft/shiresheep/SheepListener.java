package us.shirecraft.shiresheep;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.DyeColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Sheep;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.event.world.ChunkPopulateEvent;

public class SheepListener implements Listener {
	public SheepListener(ShireSheep plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler (priority = EventPriority.NORMAL)
	public void onSpawn(CreatureSpawnEvent ev) {
		// Don't mess with nature
		if(ev.getSpawnReason().equals(SpawnReason.BREEDING)) {
			return;
		}
		
		// Get the event's entity
		Entity en = ev.getEntity();
		
		// Check if it's a sheep and continue...
		checkEntity(en);
	}
	
	@EventHandler (priority = EventPriority.NORMAL)
	public void onChunkPopulation(ChunkPopulateEvent ev) {
		// Get the chunk that caused this event
		Chunk chunk = ev.getChunk();
		
		// Get an array of entities in the chunk
		Entity[] entities = chunk.getEntities();
		
		// Pass entities to be checked and dyed if sheep
		for(Entity en : entities) {
			checkEntity(en);
		}
	}
	
	public boolean checkEntity(Entity en) {
		// Only affect sheep
		if(!en.getType().equals(EntityType.SHEEP)) {
			return false;
		}
		
		// Cast to Sheep
		Sheep sheep = (Sheep) en;
		
		// Done
		return dyeSheep(sheep);
	}
	
	public boolean dyeSheep(Sheep sheep) {
		// Schedule a task to do this
		Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, new Runnable() {
			@Override
			public void run() {
				// Did the sheep vanish in the last few server ticks?
				if(null == sheep) {
					return;
				}
				
				// Dye the sheep entity
				DyeColor colour = getColour();
				sheep.setColor(colour);
			}
		}, (long) 1);
		
		// Done
		return true;
	}
	
	public DyeColor getColour() {
		if (!plugin.config.getBoolean("enable-probabilities")) {
			// Ignore probability configuration and choose a colour
			return COLOURS[RANDOM.nextInt(COLOURS_SIZE)];
		}
		
		// Determine dye colour based on probability configuration		
		HashMap<String, Float> probs = plugin.probabilities;
		float sum = (float) plugin.probabilities.values().stream().mapToDouble(i->i).sum();
		EnumMap<DyeColor, Float> data         = new EnumMap<DyeColor, Float>(DyeColor.class);
		EnumMap<DyeColor, Float> balancedData = new EnumMap<DyeColor, Float>(DyeColor.class);
		
		if(sum < 1) {
			probs.put("white", 1f);
		}
		
		data.put(DyeColor.BLACK,      probs.get("black")      / sum);
		data.put(DyeColor.BLUE,       probs.get("blue")       / sum);
		data.put(DyeColor.BROWN,      probs.get("brown")      / sum);
		data.put(DyeColor.CYAN,       probs.get("cyan")       / sum);
		data.put(DyeColor.GREEN,      probs.get("green")      / sum);
		data.put(DyeColor.GRAY,       probs.get("gray")       / sum);
		data.put(DyeColor.LIGHT_BLUE, probs.get("light_blue") / sum);
		data.put(DyeColor.LIGHT_GRAY, probs.get("light_gray") / sum);
		data.put(DyeColor.LIME,       probs.get("lime")       / sum);
		data.put(DyeColor.MAGENTA,    probs.get("magenta")    / sum);
		data.put(DyeColor.ORANGE,     probs.get("orange")     / sum);
		data.put(DyeColor.PINK,       probs.get("pink")       / sum);
		data.put(DyeColor.PURPLE,     probs.get("purple")     / sum);
		data.put(DyeColor.RED,        probs.get("red")        / sum);
		data.put(DyeColor.WHITE,      probs.get("white")      / sum);
		data.put(DyeColor.YELLOW,     probs.get("yellow")     / sum);
		
		float balancedSum = 0f;
		for(DyeColor col : COLOURS) {
			balancedSum += data.get(col);
			balancedData.put(col, balancedSum);
		}
		
		float rand = new Random().nextFloat();
		
		for(DyeColor col : COLOURS) {
			if( rand <= balancedData.get(col)) {
				return col;
			}
		}
		
		// Fall back to white
		return DyeColor.WHITE;
	}
	
	private static final DyeColor[] COLOURS = DyeColor.values();
	private static final int COLOURS_SIZE = COLOURS.length;
	private static final Random RANDOM = new Random();
	private ShireSheep plugin;
}
