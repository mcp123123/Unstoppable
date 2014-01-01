package me.mcp123123.Kits;

import java.io.File;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class Kits extends JavaPlugin {
    public void onEnable() {
    	try {
    		saveConfig();
    		setupConfig(getConfig());
    		saveConfig();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    
    @SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command command,
    		String commandLabel, String[] args) {
    	if (commandLabel.equalsIgnoreCase("GetKit")) {
    		if(sender instanceof Player) {
    			Player player = (Player) sender;
    			if(args.length ==0) {
    				String[] classes = getConfig().getString("Kits.Names")
    						.split(",");
    				for(String s : classes) {
    					if(s != null) {
    						player.sendMessage(ChatColor.AQUA + "Please click one of the class signs to get one.");
    					}
    				}
    			} else {
    				
    				for(String s : getConfig().getConfigurationSection("Kits")
    						.getKeys(false)) {
    					if(args[0].equalsIgnoreCase(s)) {
    						player.getInventory().clear();
    						try{
    							String items = getConfig().getString("Kits." + s + ".Items");
    							
    							String[] indiItems = items.split(",");
    							for(String s1 : indiItems) {
    								String[] itemAmounts = s1.split("-");
									ItemStack item = new ItemStack(
    										Integer.valueOf(itemAmounts[0]), 
    										Integer.valueOf(itemAmounts[1]));
									player.getInventory().addItem(item);
    							}
    							player.updateInventory();
    						}catch(Exception e) {
    							e.printStackTrace();
    						}
    					}
    				}
    			}
    		}
    	}
    	return false;
    }

	private void setupConfig(FileConfiguration config) throws IOException {
		if (!new File(getDataFolder(), "RESET.FILE").exists()) {
			new File(getDataFolder(), "RESET.FILE").createNewFile();
			config.set("Kits.Archer.Items", "261-1,272-1,262-32");
			config.set("Kits.Knight.Items", "267-1,373:8229-1");
			config.set("Kits.Medic.Items", "267-1,373:16421-1,373:16421-1,373:16421-1,373:16421-1,373:16421-1");
			config.set("Kits.Names", "Archer,Knight,Medic");
		}
	}
	
	
	
	
	
	
	
}
