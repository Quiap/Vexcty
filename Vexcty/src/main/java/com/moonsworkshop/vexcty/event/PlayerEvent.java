package com.moonsworkshop.vexcty.event;

import com.moonsworkshop.vexcty.Vexcty;
import com.moonsworkshop.vexcty.api.craftbukkit.VexctyPlayer;
import com.moonsworkshop.vexcty.api.util.CC;
import com.moonsworkshop.vexcty.player.PlayerData;
import com.moonsworkshop.vexcty.player.PlayerState;
import com.moonsworkshop.vexcty.util.Locations;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerEvent implements Listener {

    Vexcty plugin;

    public PlayerEvent(Vexcty instance) {
        this.plugin = instance;
    }

    // disabled commands

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent e) {
        if(e.getMessage() == "version" ||
                e.getMessage() == "about" ||
                e.getMessage() == "ver" ||
                e.getMessage() == "icanhasbukkit" ||
                e.getMessage().contains(":") ||
                e.getMessage() == "?" ||
                e.getMessage() == "help" ||
                e.getMessage() == "pl" ||
                e.getMessage() == "plugins" ||
                e.getMessage() == "bukkit:" ||
                e.getMessage() == "me" ||
                (e.getMessage() == "minecraft:")){
            e.getPlayer().sendMessage(CC.RED +  "You do not have permission to execute this command! ");
            e.setCancelled(true);
            return;
        }
    }

    // join event
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        player.setAllowFlight(true);

        player.teleport(Locations.SPAWN);
        player.setWalkSpeed(0.4F);

        PlayerInventory inv = player.getInventory();

        inv.clear();
        inv.setArmorContents(null);

    }

    // food level change

    @EventHandler
    public void onHunger(FoodLevelChangeEvent event) {
        if (event.getFoodLevel() < 20) {
            event.setFoodLevel(20);
        } else {
            event.setCancelled(true);
        }
    }

    // on death
    @EventHandler
    public void onEntityDeath(PlayerDeathEvent e) {
        e.setDeathMessage(null);
        e.getEntity().setHealth(20);
        e.getEntity().setFoodLevel(20);
        e.getDrops().clear();
    }

    // on quit

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        e.setQuitMessage(null);
    }

    // void check

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        if(e.getTo().getY() < 24) {
            e.getPlayer().teleport(Locations.SPAWN);
            return;
        }
    }

    // on build

    @EventHandler
    public void onBuild(BlockPlaceEvent e) {
        VexctyPlayer player = (VexctyPlayer) e.getPlayer();

        if(!player.isStaff()) {
            e.setCancelled(true);
        }
    }

    // on break
    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        VexctyPlayer player = (VexctyPlayer) event.getPlayer();

        if (player.isStaff() && player.getGameMode() == GameMode.CREATIVE) {
            return;
        }

        event.setCancelled(true);
    }

    // on place

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        VexctyPlayer player = (VexctyPlayer) event.getPlayer();

        if (player.isStaff() && player.getGameMode() == GameMode.CREATIVE) {
            return;
        }

        event.setCancelled(true);

    }

    // weather change

    @EventHandler
    public void onRain(WeatherChangeEvent event) {
        if (event.toWeatherState()) {
            event.setCancelled(true);
        }
    }

    // leaves decaying

    @EventHandler
    public void onLeavesDecay(LeavesDecayEvent event) {
        event.setCancelled(true);
    }

    // damage

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof VexctyPlayer)) {
            return;
        }

        event.setCancelled(true);

        if (event.getCause() == EntityDamageEvent.DamageCause.VOID) {
            VexctyPlayer player = (VexctyPlayer) event.getEntity();

            player.teleport(Locations.SPAWN);
        }
    }


    // item drop
    @EventHandler
    public void onItemDropEvent(PlayerDropItemEvent e) {
        VexctyPlayer player = (VexctyPlayer) e.getPlayer();
        if (!player.isStaff()) {
            e.setCancelled(true);
        } else {
            e.setCancelled(false);
        }
    }

    // item pickup
    @EventHandler
    public void onItemPickup(PlayerPickupItemEvent e) {
        VexctyPlayer player = (VexctyPlayer) e.getPlayer();
        if (!player.isStaff()) {
            e.setCancelled(true);
        } else {
            e.setCancelled(false);
        }
    }

    // fall damage

    @EventHandler
    public void noLandDamage(EntityDamageEvent e) {
        if(e.getCause() == EntityDamageEvent.DamageCause.FALL) {
            e.setCancelled(true);
        }
    }

    // leave when frozen

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        VexctyPlayer player = (VexctyPlayer) e.getPlayer();
        PlayerData data = plugin.getPlayer().getPlayerData((Player) player);
        if(data.getPlayerState() == PlayerState.FROZEN) {
            plugin.getServer().getOnlinePlayers().stream().filter(players -> player.isStaff()).forEach(players -> {
                player.sendMessage(CC.DARK_GRAY + "[" + CC.DARK_RED + "!" + CC.DARK_GRAY + "] " + CC.SECONDARY + player.getName() + CC.PRIMARY + " has left the server while he was frozen!");
                TextComponent clickToBan = new TextComponent(CC.RED + "[Click to ban]");
                clickToBan.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(CC.I_GRAY + "Click to ban " + player.getName()).create()));
                clickToBan.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/punish " + player.getName() + " -b lwf")); // leaving when frozen
                player.sendMessage(String.valueOf(clickToBan));
            });
        }
        plugin.getPlayer().destroyData((Player) player);
    }

    @EventHandler
    public void onPlayerInteractWithEntity(PlayerInteractAtEntityEvent e) {
        Player player = e.getPlayer();
        if(!(e.getRightClicked() instanceof Player)) return;
        VexctyPlayer target = (VexctyPlayer) e.getRightClicked();
        ItemStack item = e.getPlayer().getItemInHand();

        PlayerData playerData = plugin.getPlayer().getPlayerData(player);
        PlayerData targetData = plugin.getPlayer().getPlayerData((Player) target);

        PlayerState targetState = targetData.getPlayerState();

        switch(playerData.getPlayerState()) {

            case MODERATOR:
                e.setCancelled(true);

                switch(item.getType()) {

                    case PACKED_ICE:
                        if(target.isStaff()) {
                            player.sendMessage(CC.RED + "You cannot interact with other staff members.");
                            return;
                        }
                        targetData.setPlayerState(targetState == PlayerState.FROZEN ? PlayerState.PLAYING : PlayerState.FROZEN);
                        player.sendMessage(CC.PRIMARY + "You've successfully " + (targetState == PlayerState.FROZEN ? "unfrozen " : "frozen ") + CC.SECONDARY + target.getName());
                        target.sendMessage(CC.PRIMARY + "You've been " + (targetState == PlayerState.FROZEN ? "unfrozen " : "frozen "));
                        target.closeInventory();
                        break;

                    case BLAZE_ROD:
                        if(target.isStaff()) {
                            player.sendMessage(CC.RED + "You cannot interact with other staff members.");
                            return;
                        }
                        if(targetState != PlayerState.PLAYING) targetData.setPlayerState(PlayerState.PLAYING);
                        target.kickPlayer(CC.PRIMARY + "You've been kicked by " + CC.SECONDARY + player.getName());
                        break;
                    case BOOK:
                        player.openInventory(getTargetInventory((Player) target));
                        new BukkitRunnable() {

                            @Override
                            public void run() {
                                if(player.getOpenInventory().getTopInventory().getName().contains(CC.PRIMARY + "Viewing")) {
                                    PlayerInventory pi = target.getInventory();
                                    for(int i = 0; i < pi.getSize(); i++) {
                                        player.getOpenInventory().setItem(i, pi.getItem(i));
                                    }
                                }
                            }
                        }.runTaskTimer(plugin, 10L, 10L);
                        break;
                    default:
                        break;
                }

                break;

            case FROZEN:
                e.setCancelled(true);
                player.sendMessage(CC.PRIMARY + "You are frozen, you may not interact with other players.");
                break;

            default:
                break;
        }

    }

    private Inventory getTargetInventory(Player target) {
        PlayerInventory pi = target.getInventory();
        Inventory targetInventory = Bukkit.createInventory(null, 9 * 5, CC.PRIMARY + "Viewing " + CC.DARK_GRAY + "> " + CC.SECONDARY + target.getName());
        for(int i = 0; i < pi.getSize(); i++) {
            if(pi.getItem(i) != null) {
                targetInventory.setItem(i, pi.getItem(i));
            }
        }
        return targetInventory;
    }


}