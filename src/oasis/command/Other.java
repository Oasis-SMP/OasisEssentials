package oasis.command;

import oasis.OasisEssentials;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Other implements CommandExecutor {

	private OasisEssentials plugin;
	
	public Other(OasisEssentials plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String name,
			String[] args) {
		if(cmd.getName().equalsIgnoreCase("links")){
			sender.sendMessage(ChatColor.GOLD + "Forums - " + plugin.getConfig().getString("other.forum"));
			sender.sendMessage(ChatColor.GOLD + "Donations - " + plugin.getConfig().getString("other.donate"));
			sender.sendMessage(ChatColor.GOLD + "Vote 1 - " + plugin.getConfig().getString("other.vote1"));
			sender.sendMessage(ChatColor.GOLD + "Vote 2 - " + plugin.getConfig().getString("other.vote2"));
			sender.sendMessage(ChatColor.GOLD + "Member - " + plugin.getConfig().getString("other.member"));
			sender.sendMessage(ChatColor.GOLD + "TeamSpeak IP - ts3.oasis-mc.us");
			return true;
		}
		if(cmd.getName().equalsIgnoreCase("forums")){
			sender.sendMessage(ChatColor.GOLD + "Forums - " + plugin.getConfig().getString("other.forum"));
			return true;
		}
		if(cmd.getName().equalsIgnoreCase("donate")){
			sender.sendMessage(ChatColor.GOLD + "Donations - " + plugin.getConfig().getString("other.donate"));
			return true;
		}
		if(cmd.getName().equalsIgnoreCase("vote")){
			sender.sendMessage(ChatColor.GOLD + "Vote 1 - " + plugin.getConfig().getString("other.vote1"));
			sender.sendMessage(ChatColor.GOLD + "Vote 2 - " + plugin.getConfig().getString("other.vote2"));
			return true;
		}
		if(cmd.getName().equalsIgnoreCase("member")){
			sender.sendMessage(ChatColor.GOLD + cmd.getName() + " - " + plugin.getConfig().getString("other.member"));
			return true;
		}
		if(cmd.getName().equalsIgnoreCase("teamspeak")){
			sender.sendMessage(ChatColor.GOLD + "TeamSpeak IP - ts3.oasis-mc.us");
			sender.sendMessage(ChatColor.GOLD + "Download at http://www.teamspeak.com/?page=downloads");
			return true;
		}
		return false;
	}

}
