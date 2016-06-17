package oasis.command;

import oasis.OasisEssentials;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Gamemode implements CommandExecutor {

	private OasisEssentials plugin;
	public Gamemode(OasisEssentials plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(command.getName().equalsIgnoreCase("gms")){
			if (args.length==0) {
				Player player = (Player) sender;
				player.setGameMode(GameMode.SURVIVAL);
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&eGamemode changed to &c&oSURVIVAL!"));
				return true;
			}
			if (args.length==1){
				if (sender.hasPermission("oasis.gamemode.survival.others")) {
					Player player = Bukkit.getPlayer(args[0]);
					if (player == null) {
						return false;
					}
					player.setGameMode(GameMode.SURVIVAL);
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&eGamemode changed to &c&oSURVIVAL!"));
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', player.getDisplayName() + "&e gamemode changed to &c&oSURVIVAL!"));
					return true;
				}
			}
		}
		if(command.getName().equalsIgnoreCase("gmc")){
			if (args.length==0) {
				Player player = (Player) sender;
				player.setGameMode(GameMode.CREATIVE);
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&eGamemode changed to &c&oCREATIVE!"));
				return true;
			}
			if (args.length==1){
				if (sender.hasPermission("oasis.gamemode.creative.others")) {
					Player player = Bukkit.getPlayer(args[0]);
					if (player == null) {
						return false;
					}
					player.setGameMode(GameMode.CREATIVE);
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&eGamemode changed to &c&oCREATIVE!"));
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', player.getDisplayName() + "&e gamemode changed to &c&oCREATIVE!"));
					return true;
				}
			}
		}
		if(command.getName().equalsIgnoreCase("gma")){
			if (args.length==0) {
				Player player = (Player) sender;
				player.setGameMode(GameMode.ADVENTURE);
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&eGamemode changed to &c&oADVENTURE!"));
				return true;
			}
			if (args.length==1){
				if (sender.hasPermission("oasis.gamemode.adventure.others")) {
					Player player = Bukkit.getPlayer(args[0]);
					if (player == null) {
						return false;
					}
					player.setGameMode(GameMode.ADVENTURE);
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&eGamemode changed to &c&oADVENTURE!"));
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', player.getDisplayName() + "&e gamemode changed to &c&oADVENTURE!"));
					return true;
				}
			}
		}
		if(command.getName().equalsIgnoreCase("gmsp")){
			if (args.length==0) {
				Player player = (Player) sender;
				player.setGameMode(GameMode.SPECTATOR);
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&eGamemode changed to &c&oSPECTATOR!"));
				return true;
			}
			if (args.length==1){
				if (sender.hasPermission("oasis.gamemode.spectator.others")) {
					Player player = Bukkit.getPlayer(args[0]);
					if (player == null) {
						return false;
					}
					player.setGameMode(GameMode.SPECTATOR);
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&eGamemode changed to &c&oSPECTATOR!"));
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', player.getDisplayName() + "&e gamemode changed to &c&oSPECTATOR!"));
					return true;
				}
			}
		}
		sender.sendMessage(ChatColor.RED + command.getPermissionMessage());
		return true;

	}

}
