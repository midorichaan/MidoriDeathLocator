package midorichan.commands;

import midorichan.DeathLocator;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.UUID;

public class deathTpCommand implements CommandExecutor {

    private DeathLocator plugin = DeathLocator.getInstance();
    private Map<UUID, Location> data = plugin.getDeathCache();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("deathtp")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(plugin.getPrefix() + "コンソールからは使用できません");
                return true;
            }

            Player p = (Player) sender;
            if (p.hasPermission("midorideath.command.tp")) {
                if (args.length == 0) {
                    if (data.containsKey(p.getUniqueId())) {
                        Location loc = data.get(p.getUniqueId());
                        p.teleport(loc);
                        p.sendMessage(plugin.getPrefix() + "最終死亡位置にテレポートしました");
                    } else {
                        p.sendMessage(plugin.getPrefix() + "データが存在しないため、TPできません");
                    }
                    return true;
                } else if (args.length == 1) {
                    if (!p.hasPermission("midorideath.command.tp.admin")) {
                        p.sendMessage(plugin.getPrefix() + "権限がありません");
                        return true;
                    }

                    String tar = args[0];
                    Player target = Bukkit.getPlayer(tar);
                    if (target == null) {
                        target = (Player) Bukkit.getOfflinePlayer(tar);
                        if (target == null) {
                            p.sendMessage(plugin.getPrefix() + "プレイヤー " + tar + " は存在しません");
                            return true;
                        }
                    }

                    if (data.containsKey(target.getUniqueId())) {
                        Location loc = data.get(target.getUniqueId());
                        p.teleport(loc);
                        p.sendMessage(plugin.getPrefix() + tar + "の最終死亡位置にテレポートしました");
                    } else {
                        p.sendMessage(plugin.getPrefix() + "データが存在しないため、TPできません");
                    }
                    return true;
                } else {
                    p.sendMessage(plugin.getPrefix() + "引数が不正です");
                    return true;
                }
            } else {
                p.sendMessage(plugin.getPrefix() + "権限がありません");
                return true;
            }
        }
        return true;
    }

}
