package com.moonsworkshop.vexcty.event;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.moonsworkshop.vexcty.Vexcty;
import com.moonsworkshop.vexcty.command.GrantCommand;
import com.moonsworkshop.vexcty.player.PlayerData;
import com.moonsworkshop.vexcty.player.PlayerState;
import com.moonsworkshop.vexcty.rank.PlayerRank;
import com.moonsworkshop.vexcty.sql.Profile;
import com.moonsworkshop.vexcty.sql.SQLManager;
import com.moonsworkshop.vexgot.CC;
import com.moonsworkshop.vexgot.IVexctyPlayer;
import com.moonsworkshop.vexgot.Items;
import com.moonsworkshop.vexgot.Locations;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.minecraft.server.v1_8_R3.EnumParticle;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles;
import org.bukkit.*;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.sql.SQLException;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class PlayerEvent implements Listener {

    SQLManager database;

    private Cache<UUID, Long> cooldown = CacheBuilder.newBuilder().expireAfterWrite(5, TimeUnit.SECONDS).build();

    Vexcty plugin;

    public PlayerEvent(Vexcty instance) {
        this.plugin = instance;
    }

    // disabled commands

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent e) {
        if (e.getMessage() == "bukkit:version" ||
                e.getMessage() == "bukkit:about" ||
                e.getMessage() == "bukkit:ver" ||
                e.getMessage() == "icanhasbukkit" ||
                e.getMessage().contains(":") ||
                e.getMessage() == "?" ||
                e.getMessage() == "help" ||
                e.getMessage() == "pl" ||
                e.getMessage() == "plugins" ||
                e.getMessage() == "bukkit:" ||
                e.getMessage() == "me" ||
                (e.getMessage() == "minecraft:")) {
            e.getPlayer().sendMessage(CC.RED + "You do not have permission to execute this command! ");
            e.setCancelled(true);
            return;
        }
    }

    // join event
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        e.setJoinMessage(null);

        Player player = e.getPlayer();
        player.setAllowFlight(true);
        player.setFoodLevel(20);
        player.setHealth(20);

        player.teleport(Locations.SPAWN);
        player.setWalkSpeed(0.4F);

        plugin.getNametagManager().setNametags(player);
        plugin.getNametagManager().newTag(player);

        PlayerRank rank = plugin.getRankManager().getRank(player.getUniqueId());

        if (rank == PlayerRank.OWNER) {
            player.setPlayerListName("");
        }

        if (rank == PlayerRank.OWNER ||
                rank == PlayerRank.MANAGER ||
                rank == PlayerRank.ADMIN ||
                rank == PlayerRank.MOD ||
                rank == PlayerRank.DEV ||
                rank == PlayerRank.TWITCH ||
                rank == PlayerRank.YOUTUBE ||
                rank == PlayerRank.MOJANG ||
                rank == PlayerRank.LEGEND ||
                rank == PlayerRank.DONOR) {
            player.getEnderChest().clear();

        } else {
            player.getEnderChest().clear();
        }



        if (player.getName() == "Notch") {
            plugin.getRankManager().setRank(player.getUniqueId(), PlayerRank.MOJANG);
        } else if (player.getName() == "jeb_") {
            plugin.getRankManager().setRank(player.getUniqueId(), PlayerRank.MOJANG);
        } else if (player.getName() == "Kappische") {
            plugin.getRankManager().setRank(player.getUniqueId(), PlayerRank.MOJANG);
        } else if (player.getName() == "Junkboy") {
            plugin.getRankManager().setRank(player.getUniqueId(), PlayerRank.MOJANG);
         }else if (player.getName() == "JahKob") {
            plugin.getRankManager().setRank(player.getUniqueId(), PlayerRank.MOJANG);
        } else if (player.getName() == "danfrisk") {
            plugin.getRankManager().setRank(player.getUniqueId(), PlayerRank.MOJANG);
        } else if (player.getName() == "MinecraftChick") {
            plugin.getRankManager().setRank(player.getUniqueId(), PlayerRank.MOJANG);
        } else if (player.getName() == "BomuBoi") {
            plugin.getRankManager().setRank(player.getUniqueId(), PlayerRank.MOJANG);
        } else if (player.getName() == "xlson") {
            plugin.getRankManager().setRank(player.getUniqueId(), PlayerRank.MOJANG);
        } else if (player.getName() == "jonkagstrom") {
            plugin.getRankManager().setRank(player.getUniqueId(), PlayerRank.MOJANG);
        } else if (player.getName() == "KrisJelbring") {
            plugin.getRankManager().setRank(player.getUniqueId(), PlayerRank.MOJANG);
        } else if (player.getName() == "Midnight") {
            plugin.getRankManager().setRank(player.getUniqueId(), PlayerRank.MOJANG);
        } else if (player.getName() == "KarinSeverinson") {
            plugin.getRankManager().setRank(player.getUniqueId(), PlayerRank.MOJANG);
        } else if (player.getName() == "hideous_") {
            plugin.getRankManager().setRank(player.getUniqueId(), PlayerRank.MOJANG);
        } else if (player.getName() == "EttGlasVatten") {
            plugin.getRankManager().setRank(player.getUniqueId(), PlayerRank.MOJANG);
        } else if (player.getName() == "91maan90") {
            plugin.getRankManager().setRank(player.getUniqueId(), PlayerRank.MOJANG);
        } else if (player.getName() == "E_Claymore") {
            plugin.getRankManager().setRank(player.getUniqueId(), PlayerRank.MOJANG);
        } else if (player.getName() == "EvilSeph") {
            plugin.getRankManager().setRank(player.getUniqueId(), PlayerRank.MOJANG);
        } else if (player.getName() == "Grumm") {
            plugin.getRankManager().setRank(player.getUniqueId(), PlayerRank.MOJANG);
        } else if (player.getName() == "Dinnerbone") {
            plugin.getRankManager().setRank(player.getUniqueId(), PlayerRank.MOJANG);
        } else if (player.getName() == "Tahg") {
            plugin.getRankManager().setRank(player.getUniqueId(), PlayerRank.MOJANG);
        } else if (player.getName() == "C418") {
            plugin.getRankManager().setRank(player.getUniqueId(), PlayerRank.MOJANG);
        }


        PlayerInventory inv = player.getInventory();

        inv.clear();
        inv.setArmorContents(null);
        inv.setItem(4, Items.TELEPORTER_ITEM);
        inv.setItem(6, Items.network);

        Profile profile = plugin.getProfileManager().getProfile(player);
        try {
            Bukkit.getScheduler().runTaskAsynchronously(new Vexcty(), () -> profile.getData().load(player));
        } catch (NullPointerException e1) {
            player.kickPlayer("Error: Player profile could not be loaded!");
        }


//        Board board = new Board((Player) player);
//
//        if (plugin.getLangManager().getLang(player.getUniqueId(), Lang.EN)) {
//            board.updateTitle(CC.RED + "Vexcty Network");
//        } else if (plugin.getLangManager().getLang(player.getUniqueId(), Lang.AR)) {
//            board.updateTitle(CC.RED + "شبكة فيكستي");
//        } else if (plugin.getLangManager().getLang(player.getUniqueId(), Lang.FR)) {
//            board.updateTitle(CC.RED + "Réseau Vexcty");
//        } else if (plugin.getLangManager().getLang(player.getUniqueId(), Lang.IT)) {
//            board.updateTitle(CC.RED + "Rete Vexcty");
//        } else if (plugin.getLangManager().getLang(player.getUniqueId(), Lang.JA)) {
//            board.updateTitle(CC.RED + "ベクティ ネットワーク");
//        } else if (plugin.getLangManager().getLang(player.getUniqueId(), Lang.LA)) {
//            board.updateTitle(CC.RED + "Vexcty Network");
//        } else if (plugin.getLangManager().getLang(player.getUniqueId(), Lang.PA)) {
//            board.updateTitle(CC.RED + "ਵੇਕਸੀਟੀ ਨੈੱਟਵਰਕ");
//        } else if (plugin.getLangManager().getLang(player.getUniqueId(), Lang.RU)) {
//            board.updateTitle(CC.RED + "Сеть Вексти");
//        } else if (plugin.getLangManager().getLang(player.getUniqueId(), Lang.ES)) {
//            board.updateTitle(CC.RED + "red vejatorio");
//        } else if (plugin.getLangManager().getLang(player.getUniqueId(), Lang.TR)) {
//            board.updateTitle(CC.RED + "Vexty Ağı");
//        } else if (plugin.getLangManager().getLang(player.getUniqueId(), Lang.UR)) {
//            board.updateTitle(CC.RED + "ویکسیٹی نیٹ ورک");
//        } else if (plugin.getLangManager().getLang(player.getUniqueId(), Lang.AM)) {
//            board.updateTitle(CC.RED + "Vexcty አውታረ መረብ");
//        } else if (plugin.getLangManager().getLang(player.getUniqueId(), Lang.ZH)) {
//            board.updateTitle(CC.RED + "Vexcty网络");
//        } else if (plugin.getLangManager().getLang(player.getUniqueId(), Lang.ZHHK)) {
//            board.updateTitle(CC.RED + "Vexcty網絡");
//        } else if (plugin.getLangManager().getLang(player.getUniqueId(), Lang.CS)) {
//            board.updateTitle(CC.RED + "Síť Vexcty");
//        } else if (plugin.getLangManager().getLang(player.getUniqueId(), Lang.DE)) {
//            board.updateTitle(CC.RED + "Vexcty-Netzwerk");
//        } else if (plugin.getLangManager().getLang(player.getUniqueId(), Lang.FI)) {
//            board.updateTitle(CC.RED + "Vexcty verkko");
//        } else if (plugin.getLangManager().getLang(player.getUniqueId(), Lang.PT)) {
//            board.updateTitle(CC.RED + "Rede Vexcty");
//        } else if (plugin.getLangManager().getLang(player.getUniqueId(), Lang.PL)) {
//            board.updateTitle(CC.RED + "Sieć Vexcty");
//        }
//
//
//        boards.put(player.getUniqueId(), board);


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
        Player player = e.getPlayer();

        Profile profile = plugin.getProfileManager().getProfile(player);
        if(profile != null) {
            Bukkit.getScheduler().runTaskAsynchronously(new Vexcty(), () -> profile.getData().save(player));
        }

        plugin.getNametagManager().removeTag(e.getPlayer());


        e.setQuitMessage(null);

        PlayerData data = plugin.getPlayer().getPlayerData((Player) player);
        if (data.getPlayerState() == PlayerState.FROZEN) {
            plugin.getServer().getOnlinePlayers().stream().filter(players -> player.hasPermission("f2erg.vexcty.admin")).forEach(players -> { // for the players that are staff
                player.sendMessage(CC.DARK_GRAY + "[" + CC.DARK_RED + "!" + CC.DARK_GRAY + "] " + CC.SECONDARY + player.getName() + CC.PRIMARY + " has left the server while he was frozen!");
                TextComponent clickToBan = new TextComponent(CC.RED + "[Click to ban]");
                clickToBan.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(CC.I_GRAY + "Click to ban " + player.getName()).create()));
                clickToBan.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/punish " + player.getName() + " -b lwf")); // leaving when frozen
                player.sendMessage(String.valueOf(clickToBan));
            });
        }
        plugin.getPlayer().destroyData((Player) player);
        plugin.getRecentMessages().remove(e.getPlayer().getUniqueId());
    }

    // void check

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        if (e.getTo().getY() < 24) {
            e.getPlayer().teleport(Locations.SPAWN);
            return;
        }
    }

    // on build

    @EventHandler
    public void onBuild(BlockPlaceEvent e) {
        IVexctyPlayer player = (IVexctyPlayer) e.getPlayer();


        if (!player.isStaff()) {
            e.setCancelled(true);
        } else {
            return;
        }
    }

    // on break
    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Player player = (Player) event.getPlayer();

        if (player.hasPermission("f2erg.vexcty.admin") && player.getGameMode() == GameMode.CREATIVE) {
            return;
        }

        event.setCancelled(true);
    }

    // on place

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        Player player = (Player) event.getPlayer();

        if (player.hasPermission("f2erg.vexcty.admin") && player.getGameMode() == GameMode.CREATIVE) {
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
        if (!(event.getEntity() instanceof Player)) {
            return;
        }

        Player player = (Player) event.getEntity();

        event.setCancelled(true);

        if (event.getCause() == EntityDamageEvent.DamageCause.VOID) {

            player.teleport(Locations.SPAWN);
        }
    }


    // item drop
    @EventHandler
    public void onItemDropEvent(PlayerDropItemEvent e) {
        Player player = (Player) e.getPlayer();
        if (player.hasPermission("f2erg.vexcty.admin")) {
            e.setCancelled(true);
        } else {
            e.setCancelled(false);
        }
    }

    // item pickup
    @EventHandler
    public void onItemPickup(PlayerPickupItemEvent e) {
        Player player = (Player) e.getPlayer();
        if (player.hasPermission("f2erg.vexcty.admin")) {
            e.setCancelled(true);
        } else {
            e.setCancelled(false);
        }
    }

    // fall damage

    @EventHandler
    public void noLandDamage(EntityDamageEvent e) {
        if (e.getCause() == EntityDamageEvent.DamageCause.FALL) {
            if (e.getEntity() instanceof Player) {
                Player player = (Player) e.getEntity();
                if (player.getHealth() > 20) {
                    player.setHealth(20);
                }
            }
            e.setCancelled(true);
        }
    }

    // player interacts with entity

    @EventHandler
    public void onPlayerInteractWithEntity(PlayerInteractAtEntityEvent e) {
        Player player = e.getPlayer();
        if (!(e.getRightClicked() instanceof Player)) return;
        IVexctyPlayer target = (IVexctyPlayer) e.getRightClicked();
        ItemStack item = e.getPlayer().getItemInHand();

        PlayerData playerData = plugin.getPlayer().getPlayerData(player);
        PlayerData targetData = plugin.getPlayer().getPlayerData((Player) target);

        PlayerState targetState = targetData.getPlayerState();

        switch (playerData.getPlayerState()) {

            case MODERATOR:
                e.setCancelled(true);

                switch (item.getType()) {

                    case PACKED_ICE:
                        if (target.isStaff()) {
                            player.sendMessage(CC.RED + "You cannot interact with other staff members.");
                            return;
                        }
                        targetData.setPlayerState(targetState == PlayerState.FROZEN ? PlayerState.PLAYING : PlayerState.FROZEN);
                        player.sendMessage(CC.PRIMARY + "You've successfully " + (targetState == PlayerState.FROZEN ? "unfrozen " : "frozen ") + CC.SECONDARY + target.getName());
                        target.sendMessage(CC.PRIMARY + "You've been " + (targetState == PlayerState.FROZEN ? "unfrozen " : "frozen "));
                        target.closeInventory();
                        break;

                    case BLAZE_ROD:
                        if (target.isStaff()) {
                            player.sendMessage(CC.RED + "You cannot interact with other staff members.");
                            return;
                        }
                        if (targetState != PlayerState.PLAYING) targetData.setPlayerState(PlayerState.PLAYING);
                        target.kickPlayer(CC.PRIMARY + "You've been kicked by " + CC.SECONDARY + player.getName());
                        break;
                    case BOOK:
                        player.openInventory(getTargetInventory((Player) target));
                        new BukkitRunnable() {

                            @Override
                            public void run() {
                                if (player.getOpenInventory().getTopInventory().getName().contains(CC.PRIMARY + "Viewing")) {
                                    PlayerInventory pi = target.getInventory();
                                    for (int i = 0; i < pi.getSize(); i++) {
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
        for (int i = 0; i < pi.getSize(); i++) {
            if (pi.getItem(i) != null) {
                targetInventory.setItem(i, pi.getItem(i));
            }
        }
        return targetInventory;
    }

    // pre double jump
    @EventHandler
    public void onPreDoubleJump(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        if (player.getGameMode() == GameMode.CREATIVE) {
            return;
        }

            player.setAllowFlight(true);

    }

    // double jump

    @EventHandler
    public void onDoubleJump(PlayerToggleFlightEvent event) {
        Player player = event.getPlayer();

        if (player.getGameMode() == GameMode.CREATIVE) {
            return;
        }

        Vector jumpVelocity = player.getLocation().getDirection().normalize().multiply(3).setY(4.0);

        player.playSound(player.getLocation(), Sound.GHAST_FIREBALL, 64.0F, 1.0F);
        player.setVelocity(jumpVelocity);
        player.setAllowFlight(false);

        float red = 255;
        float green = 255;
        float blue = 255;
        Location location = player.getLocation();
        Location loc = player.getLocation();

        for (int i = 0; i < 10; i++) {
            PacketPlayOutWorldParticles packet2 = new PacketPlayOutWorldParticles(EnumParticle.SMOKE_NORMAL,true, (float) (loc.getX()), (float) (loc.getY()), (float) (loc.getZ()), 0.6F, 0, 0.6F, 0, 3);
            PacketPlayOutWorldParticles particles = new PacketPlayOutWorldParticles(EnumParticle.CLOUD, true, (float) location.getX(), (float) location.getY(), (float) location.getZ(), red, green, blue, (float)255, 0, 10);
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(particles);
            ((CraftPlayer)player).getHandle().playerConnection.sendPacket(packet2);
        }

        plugin.getServer().getScheduler().runTask(plugin, () -> {
            if (player.isOnline()) {
                player.setFlying(false);
            }
        });

    }

//    @EventHandler
//    public void preLoginEvent(AsyncPlayerPreLoginEvent e) {
//
//        PlayerPunishData playerPunishData = plugin.getPunishmentManager().getPlayer(e.getUniqueId());
//
//        List<AppliedPunishment> appliedPunishments = playerPunishData.getActivePunishments(PunishmentType.BAN);
//
//        if (appliedPunishments.isEmpty()) return;
//
//        AppliedPunishment appliedPunishment = playerPunishData.getActivePunishments(PunishmentType.BAN).get(0);
//
//        if (banID == 1) {
//            e.setKickMessage(CC.RED + "You are banned from Vexcty for having a inappropriate name.\n" +
//                    "Time left: " + Methods.makeReadable(appliedPunishment.getTimeRemaining()));
//        }
//
//        if (banID == 2) {
//            e.setKickMessage(CC.RED + "You are banned for Cheating and giving other players an unfair advantage.\n" +
//                    "Time left: " + Methods.makeReadable(appliedPunishment.getTimeRemaining()));
//        }
//
//        if (banID == 3) {
//            e.setKickMessage(CC.RED + "You are banned for inappropriate chat.\n" +
//                    "Time left: " + Methods.makeReadable(appliedPunishment.getTimeRemaining()));
//        }
//
//        if (banID == 4) {
//            e.setKickMessage(CC.RED + "You are banned for Threating other people on the network \n" +
//                    "Time left: " + Methods.makeReadable(appliedPunishment.getTimeRemaining()));
//        }
//        if (banID == 5) {
//            e.setKickMessage(CC.RED + "You are banned for Exploiting bugs. \n" +
//                    "Time left: " + Methods.makeReadable(appliedPunishment.getTimeRemaining()));
//        }
//
//
//        e.setLoginResult(AsyncPlayerPreLoginEvent.Result.KICK_BANNED);
//
//    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player player = (Player) e.getPlayer();
        e.setCancelled(true);

        if (plugin.getRankManager().getRank(player.getUniqueId()) == PlayerRank.MEMBER) {
            Bukkit.broadcastMessage(CC.GRAY + player.getName() + ": " + e.getMessage());
        } else {
            Bukkit.broadcastMessage(plugin.getRankManager().getRank(player.getUniqueId()).getName() + " " + player.getName() + ": " + ChatColor.WHITE + e.getMessage());
        }

        if (plugin.getRankManager().getRank(player.getUniqueId()) == PlayerRank.MEMBER) {

            if (!cooldown.asMap().containsKey(player.getUniqueId())) {
                cooldown.put(player.getUniqueId(), System.currentTimeMillis() + 3000); // must wait 3 seconds
            } else {
                long distance = cooldown.asMap().get(player.getUniqueId()) - System.currentTimeMillis();
                if (System.currentTimeMillis() > 1000) { // see if the wait time is less than 1 second
                    player.sendMessage(ChatColor.RED + "You must wait " + TimeUnit.MILLISECONDS.toSeconds(distance) + " second to type again. Ranked player can bypass this.");
                } else {
                    player.sendMessage(ChatColor.RED + "You must wait " + TimeUnit.MILLISECONDS.toSeconds(distance) + " seconds to type again. Ranked player can bypass this.");
                }

            }
        } else {
            return;
        }
    }

    @EventHandler
    public void onProfileLoad(AsyncPlayerPreLoginEvent event) {
        try {
            plugin.getProfileManager().handleProfileCreation(event.getUniqueId(), event.getName());
        } catch (NullPointerException e) {
            event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_FULL, "ERROR: Profile could not be created");
        }
    }

    @EventHandler
    public void OnInventoryClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();

        if (e.getView().getTitle().equals("Select Player")) {
            e.setCancelled(true);
            if(e.getCurrentItem() != null) {
                p.closeInventory();
                String name = e.getCurrentItem().getItemMeta().getDisplayName();
                GrantCommand.firstGUI(p);


            }
        } else if (e.getView().getTitle().equals("Select")) {

            e.setCancelled(true);

            switch (e.getSlot()) {

                case 13:
                    p.closeInventory();
                    GrantCommand.secondGUI(p);

                    break;
            }
        } else if (e.getView().getTitle().equals("Select Rank")) {
            e.setCancelled(true);

            switch (e.getSlot()) {
                case 10:
                    p.closeInventory();
                    p.sendMessage(CC.GREEN + "You gave yourself Legend");
                    p.performCommand("rank " + p.getName() + " legend");
                    break;

                case 12:
                    p.closeInventory();
                    p.sendMessage(CC.GREEN + "You gave yourself Hacker");
                    p.performCommand("rank " + p.getName() + " hacker");
                    break;

                case 14:
                    p.closeInventory();
                    p.sendMessage(CC.GREEN + "You gave yourself Noob");
                    p.performCommand("rank " + p.getName() + " noob");
                    break;

                case 16:
                    p.closeInventory();
                    p.sendMessage(CC.GREEN + "You gave yourself Voter");
                    p.performCommand("rank " + p.getName() + " voter");
                    break;

                case 26:
                    p.closeInventory();
                    GrantCommand.adminGUI(p);
                    break;
            }
        } else if (e.getView().getTitle().equals("Admin Ranks")) {
            e.setCancelled(true);

            switch (e.getSlot()) {
                case 9:
                    p.closeInventory();
                    p.sendMessage(CC.GREEN + "You gave yourself Owner");
                    p.performCommand("rank " + p.getName() + " owner");
                    break;

                case 11:
                    p.closeInventory();
                    p.sendMessage(CC.GREEN + "You gave yourself Manager");
                    p.performCommand("rank " + p.getName() + " manager");
                    break;

                case 13:
                    p.closeInventory();
                    p.sendMessage(CC.GREEN + "You gave yourself Administrator");
                    p.performCommand("rank " + p.getName() + " admin");
                    break;

                case 15:
                    p.closeInventory();
                    p.sendMessage(CC.GREEN + "You gave yourself Moderator");
                    p.performCommand("rank " + p.getName() + " mod");
                    break;

                case 17:
                    p.closeInventory();
                    p.sendMessage(CC.GREEN + "You gave yourself Developer");
                    p.performCommand("rank " + p.getName() + " dev");
                    break;
            }
        }
    }


}