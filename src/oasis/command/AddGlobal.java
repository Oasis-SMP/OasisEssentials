package oasis.command;

import oasis.OasisEssentials;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.sk89q.worldguard.protection.regions.GlobalProtectedRegion;

public class AddGlobal implements CommandExecutor {

	private OasisEssentials plugin;
	public AddGlobal(OasisEssentials plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String name,
			String[] args) {
		Player player = (Player) sender;
		plugin.getWorldGuard().getRegionManager(player.getWorld()).addRegion(new GlobalProtectedRegion("__Global__"));
		sender.sendMessage(ChatColor.AQUA + "Global region added for: " + player.getWorld().getName());
		return true;
	}

}
