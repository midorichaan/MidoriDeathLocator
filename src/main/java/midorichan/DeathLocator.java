package midorichan;

import midorichan.commands.deathCommand;
import midorichan.events.playerDeath;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public final class DeathLocator extends JavaPlugin {

    private static DeathLocator instance;
    private static String prefix = "§a>§2>§r ";
    private static Map<UUID, Location> deathCache = new HashMap<>();

    public static Map<UUID, Location> getDeathCache() {
        return deathCache;
    }

    public static String getPrefix() {
        return prefix;
    }

    public static DeathLocator getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;

        //Register Events
        Bukkit.getPluginManager().registerEvents(new playerDeath(), this);

        //Register Command
        Bukkit.getPluginCommand("death").setExecutor(new deathCommand());

        this.getLogger().info(prefix + "Enabled MidoriDeathLocator");
    }

    @Override
    public void onDisable() {
        this.getLogger().info(prefix + "Disabled MidoriDeathLocator");
    }

}
