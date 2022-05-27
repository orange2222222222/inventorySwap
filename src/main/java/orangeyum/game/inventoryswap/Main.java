package orangeyum.game.inventoryswap;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getPluginCommand("startswap").setExecutor(new Commands(this));
        Bukkit.getPluginCommand("swapnow").setExecutor(new Commands(this));
        Bukkit.getPluginCommand("stopswap").setExecutor(new Commands(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
