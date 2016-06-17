package oasis.command;

import oasis.OasisEssentials;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;

public class Sign implements CommandExecutor {

	private OasisEssentials plugin;
	public Sign(OasisEssentials plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String name,
			String[] args) {
		Player player = (Player) sender;
		if(plugin.doNOTnotify.contains(player.getUniqueId())){
			plugin.doNOTnotify.remove(player.getUniqueId());
			player.sendMessage(ChatColor("&aSignLogger &2ENABLED!"));
			return true;
		} else {
			plugin.doNOTnotify.add(player.getUniqueId());
			player.sendMessage(ChatColor("&aSignLogger &cDISABLED!"));
			return true;
		}
	}
	
	public String ChatColor(String msg){
		return ChatColor.translateAlternateColorCodes('&', msg);
	}

}
