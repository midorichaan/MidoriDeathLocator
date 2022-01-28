package midorichan.commands;

import midorichan.DeathLocator;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class deathCommand implements CommandExecutor {

    private DeathLocator plugin = DeathLocator.getInstance();
    private Map<UUID, Location> data = plugin.getDeathCache();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("death")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(plugin.getPrefix() + "Console cannot use this command.");
                return true;
            }

            Player p = (Player) sender;
            if (p.isOp() || p.hasPermission("midorideath.command")) {
                if (p.isOp() || p.hasPermission("midorideath.command.admin")) {
                    if (args.length == 0) {
                        if (data.containsKey(p.getUniqueId())) {
                            Location loc = data.get(p.getUniqueId());
                            String[] msg = {
                                    " §2-------------|" + " §aDeathLocation " + "§2|-------------",
                                    "   World: " + loc.getWorld().getName(),
                                    "   X: " + loc.getX(),
                                    "   Y: " + loc.getY(),
                                    "   Z: " + loc.getZ(),
                                    " §2---------------------------------------"
                            };
                            p.sendMessage(msg);
                            return true;
                        } else {
                            p.sendMessage(plugin.getPrefix() + "まだデータがありません");
                            return true;
                        }
                    } else if (args.length == 1) {
                        String tar = args[0];
                        Player target = Bukkit.getPlayer(tar);

                        if (target == null) {
                            p.sendMessage(plugin.getPrefix() + "プレイヤー " + tar + " は存在しません");
                            return true;
                        }

                        if (data.containsKey(target.getUniqueId())) {
                            Location loc = data.get(target.getUniqueId());
                            String[] msg = {
                                    " §2-------------|" + " §aDeathLocation " + "§2|-------------",
                                    "   World: " + loc.getWorld().getName(),
                                    "   X: " + loc.getX(),
                                    "   Y: " + loc.getY(),
                                    "   Z: " + loc.getZ(),
                                    " §2---------------------------------------"
                            };
                            p.sendMessage(msg);
                            return true;
                        } else {
                            p.sendMessage(plugin.getPrefix() + "データが存在しません");
                            return true;
                        }
                    } else {
                        p.sendMessage(plugin.getPrefix() + "引数が不正です");
                        return true;
                    }
                } else {
                    if (args.length == 0) {
                        if (data.containsKey(p.getUniqueId())) {
                            Location loc = data.get(p.getUniqueId());
                            String[] msg = {
                                    " §2-------------|" + " §aDeathLocation " + "§2|-------------",
                                    "   World: " + loc.getWorld().getName(),
                                    "   X: " + loc.getX(),
                                    "   Y: " + loc.getY(),
                                    "   Z: " + loc.getZ(),
                                    " §2---------------------------------------"
                            };
                            p.sendMessage(msg);
                            return true;
                        } else {
                            p.sendMessage(plugin.getPrefix() + "まだデータがありません");
                            return true;
                        }
                    } else {
                        p.sendMessage(plugin.getPrefix() + "権限がありません");
                        return true;
                    }
                }
            } else {
                p.sendMessage(plugin.getPrefix() + "権限がありません");
                return true;
            }
        }
        return true;
    }

}
