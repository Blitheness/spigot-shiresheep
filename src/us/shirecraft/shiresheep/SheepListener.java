package us.shirecraft.shiresheep;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Sheep;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

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
		// Schedule a task to run asynchronously
		Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, new Runnable() {
			@Override
			public void run() {
				// Did the sheep vanish in the last few server ticks?
				if(null == sheep) {
					return;
				}
				
				// @TODO Dye the entity
			}
		}, (long) 1);
		
		// Done
		return true;
	}
	
	private ShireSheep plugin;
}
