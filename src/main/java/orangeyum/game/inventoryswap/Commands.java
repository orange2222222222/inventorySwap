package orangeyum.game.inventoryswap;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.Random;

public class Commands implements CommandExecutor {
    final private BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
    final private Random random = new Random();
    private Main plugin;

    public Commands(Main plugin) { this.plugin = plugin; }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!sender.isOp()) return true;
        switch (command.getName()) {
            case "swapnow" ->
                swap();
            case "startswap" ->
                startSwapInterval();
            case "stopswap" ->
                scheduler.cancelTasks(plugin);
        }
        return true;
    }

    private void startSwapInterval() {
        scheduler.runTaskLater(plugin, () -> {
            swap();
            Bukkit.broadcastMessage("Swapped!");
            startSwapInterval();
        }, random.nextInt(3*60*20)); //3*60*20
    }

    private void swap() {
        List<Player> players = (List<Player>) Bukkit.getOnlinePlayers();
        List<ItemStack[]> rotated = players
                .stream()
                .map(player -> player.getInventory().getContents())
                .collect(Collectors.toList());
        Collections.rotate(rotated, -1);

        int i = 0;
        for (Player player : players) {
            player.getInventory().setContents(rotated.get(i));
            i++;
        }
    }
}

// [0,1]
// [1,0]
// i = 0
// 0: 0=1 i=1
// 1: 1=0 i=2