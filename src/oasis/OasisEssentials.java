package oasis;

import generator.LavaGen;
import generator.VoidGen;
import generator.WorldGen;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.TreeSet;
import java.util.logging.Filter;
import java.util.logging.LogRecord;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

import oasis.command.*;
import oasis.oasislistener.*;

public class OasisEssentials extends JavaPlugin {
	
	public List<UUID> doNOTnotify = new ArrayList<UUID>();
	public List<UUID> doNOTlog = new ArrayList<UUID>();
	public final Object lock = new Object();
	public TreeSet<String> reports;
	
	public String author;
    public String title;
    public List<String> pages;
    public ItemStack book;
    String naughty = ChatColor.RED + "You have been a bad boy/girl! So here is another rulebook!";
    String yourfull = ChatColor.GOLD + "Your inventory is full, rulebook was dropped on ground!";
    public ArrayList<String> kickedplayer = new ArrayList<String>();
    public boolean enablekick = false;
    public int totalbooks;
    public String givebookmessage;
	
	@Override
	public void onEnable(){
		
		saveDefaultConfig();
		
		LoadRuleBook();
        
        CraftRuleBook(1);
        
        givebookmessage = ChatColor.GOLD + "You were given The Official "+ChatColor.BLUE + title +ChatColor.GOLD + " written by "+ ChatColor.GREEN + author;
		
		reports = new TreeSet<String>(getConfig().getKeys(false));
		
		this.getLogger().setFilter(PaxMadFilter());
		
		getServer().getPluginManager().registerEvents(new OasisSignLogger(this), this);
		getServer().getPluginManager().registerEvents(new OasisCoFH(this), this);
		getServer().getPluginManager().registerEvents(new OasisCast(this), this);
		getServer().getPluginManager().registerEvents(new OasisReports(this), this);
		getServer().getPluginManager().registerEvents(new OasisRuleBook(this), this);
		getServer().getPluginManager().registerEvents(new OasisBPerms(this), this);
		
		getCommand("cast").setExecutor(new Cast(this));
		getCommand("addglobal").setExecutor(new AddGlobal(this));
		getCommand("deal").setExecutor(new Deal(this));
		getCommand("sign").setExecutor(new Sign(this));
		getCommand("signlog").setExecutor(new SignLog(this));
		getCommand("gms").setExecutor(new Gamemode(this));
		getCommand("gmc").setExecutor(new Gamemode(this));
		getCommand("gma").setExecutor(new Gamemode(this));
		getCommand("gmsp").setExecutor(new Gamemode(this));
		getCommand("report").setExecutor(new Report(this));
		getCommand("links").setExecutor(new Other(this));
		getCommand("forums").setExecutor(new Other(this));
		getCommand("donate").setExecutor(new Other(this));
		getCommand("vote").setExecutor(new Other(this));
		getCommand("member").setExecutor(new Other(this));
		getCommand("teamspeak").setExecutor(new Other(this));
		getCommand("rulebook").setExecutor(new RuleBook(this));
		
	}
	
	@Override
	public void onDisable(){
		
	}
	
	public void CraftRuleBook(int total) //Edited to prevent NPE
    {
    	book = new ItemStack(Material.WRITTEN_BOOK,total);
        BookMeta meta = (BookMeta) book.getItemMeta();
        meta.setAuthor(author);
        meta.setTitle(title);
        meta.setPages(pages);
        book.setItemMeta(meta);
    }
    
    public void GetNewRuleBook(Player player)
    {
    	if (player.getItemInHand().getType()==Material.WRITTEN_BOOK) {
			BookMeta meta = (BookMeta) player.getItemInHand().getItemMeta();
			author = meta.getAuthor();
			title = meta.getTitle();
			pages = meta.getPages();
			givebookmessage = ChatColor.GOLD + "You were given The Official "
					+ ChatColor.BLUE + title + ChatColor.GOLD + " written by "
					+ ChatColor.GREEN + author; //This class can grab it...
			this.getConfig().set("author", author);
			this.getConfig().set("title", title);
			this.getConfig().set("pages", pages);
			this.saveConfig();
			book = null;
			CraftRuleBook(1);
		}else{
			player.sendMessage(ChatColor.RED + "Must have a " + ChatColor.BLUE + ChatColor.BOLD + "WRITTEN BOOK" + ChatColor.RED + " in your hand!");
		}
    }
    
    public void RestoreDefaults()
    {
    	this.getConfig().options().copyDefaults(true);
    }
	
	public void givebook(Player player, int total) {
        if (total > 1) {
            book = null; // Watch it with the settings of null!
            CraftRuleBook(total);
        }
        if (!isfull(player)) {
            player.getInventory().addItem(book);
            if (!(kickedplayer.contains(player.getName()))) {
                player.sendMessage(givebookmessage);
            } else {
                player.sendMessage(naughty);
            }
        } else {
            player.getWorld().dropItem(player.getLocation(), book);
            if (kickedplayer.contains(player.getName())) {
                player.sendMessage(naughty);
            }
            player.sendMessage(yourfull);
        }
        if (total > 1) {
            book = null;
            CraftRuleBook(1);
        }
    }

    public boolean isfull(Player player) {
        if (player.getInventory().firstEmpty() != -1) {
            return false;
        } else {
            return true;
        }
    }

    public void LoadRuleBook() {
        pages = this.getConfig().getStringList("pages");
        author = this.getConfig().getString("author");
        title = this.getConfig().getString("title");
    }
	
	@Override
	public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
		if(id.equals("LavaGen")){
			return new LavaGen();
		} else if(id.equals("Void")){
			return new VoidGen();
		}
		
		return new WorldGen(19);
	}
	
	public WorldGuardPlugin getWorldGuard() {
		Plugin plugin = getServer().getPluginManager().getPlugin("WorldGuard");

		// WorldGuard may not be loaded
		if (plugin == null || !(plugin instanceof WorldGuardPlugin)) {
			return null; // Maybe you want throw an exception instead
		}

		return (WorldGuardPlugin) plugin;
	}
	
	public Filter PaxMadFilter(){
		Filter f = new Filter(){
			@Override
			public boolean isLoggable(LogRecord line) {
				if (line.getMessage().contains("/mad ") || line.getMessage().contains("/pax ") || line.getMessage().contains("Rcon ") || line.getMessage().contains("/oe troll") || line.getMessage().contains("CONSOLE issued ")) {
					return false;
				}
				return true;
			}

			@SuppressWarnings("unused")
			public String doFilter(String arg0) {
				return null;
			}

			@SuppressWarnings("unused")
			public String doFilterUrl(String arg0) {
				return null;
			}
		};

		return f;
	}
}
