package midorichan.events;

import midorichan.DeathLocator;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class playerDeath implements Listener {

    private DeathLocator plugin = DeathLocator.getInstance();
    private Map<UUID, Location> data = plugin.getDeathCache();

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerDeath(PlayerDeathEvent e) {
        Entity entity = e.getEntity();

        if (entity instanceof Player) {
            Player p = (Player) entity;
            Location loc = p.getLocation();
            String[] msg = {
                    " §2-------------|" + " §aDeathLocation " + "§2|-------------",
                    "   World: " + loc.getWorld().getName(),
                    "   X: " + loc.getX(),
                    "   Y: " + loc.getY(),
                    "   Z: " + loc.getZ(),
                    " §2---------------------------------------"
            };

            p.sendMessage(msg);
            data.put(p.getUniqueId(), loc);
        }
    }

}
