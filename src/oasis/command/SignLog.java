package oasis.command;

import oasis.OasisEssentials;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SignLog implements CommandExecutor {

	private OasisEssentials plugin;
	public SignLog(OasisEssentials plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String name,
			String[] args) {
		if(plugin.getServer().getPlayer(args[0])!=null){
			if (plugin.getServer().getPlayer(args[0]).isOnline()) {
				if(plugin.doNOTlog.contains(plugin.getServer().getPlayer(args[0]).getUniqueId())){
					plugin.doNOTlog.remove(plugin.getServer().getPlayer(args[0]).getUniqueId());
					sender.sendMessage(ChatColor("&aSign Logging is &2ENABLED &afor " + args[0]));
					return true;
				} else {
					plugin.doNOTlog.add(plugin.getServer().getPlayer(args[0]).getUniqueId());
					sender.sendMessage(ChatColor("&aSign Logging is &cDISABLED &afor " + args[0]));
					return true;
				}
			}
		}
		return true;
	}
	
	public String ChatColor(String msg){
		return ChatColor.translateAlternateColorCodes('&', msg);
	}

}
