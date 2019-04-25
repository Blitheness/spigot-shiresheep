package us.shirecraft.shiresheep;

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
	
	public boolean checkEntity(Entity en) {
		// Only affect sheep
		if(!en.getType().equals(EntityType.SHEEP)) {
			return false;
		}
		
		// Cast to Sheep
		Sheep sheep = (Sheep) en;
		
		// Done
    
    // @TODO: Dye method using probabilities
		return false;
	}
	
	private ShireSheep plugin;
}
