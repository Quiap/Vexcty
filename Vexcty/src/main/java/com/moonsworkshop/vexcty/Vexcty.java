package com.moonsworkshop.vexcty;

import com.moonsworkshop.vexcty.afk.AFKManager;
import com.moonsworkshop.vexcty.afk.MovementChecker;
import com.moonsworkshop.vexcty.command.*;
import com.moonsworkshop.vexcty.config.ConfigManager;
import com.moonsworkshop.vexcty.event.PlayerEvent;
import com.moonsworkshop.vexcty.managers.PlayerManager;
import com.moonsworkshop.vexcty.rank.NametagManager;
import com.moonsworkshop.vexcty.rank.RankCommand;
import com.moonsworkshop.vexcty.rank.RankManager;
import com.moonsworkshop.vexgot.HidePlayerList;
import com.moonsworkshop.vexgot.VexGot;
import lombok.Getter;
import org.bukkit.Bukkit;

import java.util.HashMap;
import java.util.UUID;

@Getter
public class Vexcty extends VexGot {

    public static HidePlayerList playerList;

    private static Vexcty instance;
//    @Getter
//    private ItemManager item;
    @Getter
    private PlayerManager player;
    @Getter
    private AFKManager afkManager;
    @Getter
    private RankManager rankManager;
    @Getter
    private NametagManager nametagManager;
    @Getter
    public ConfigManager configManager;
    @Getter
    private HashMap<UUID, UUID> recentMessages; // used for the private messages

    @Override
    public void PluginEnable() {
        managers();
        commands();
        listeners();
        afk();
        config();;
    }

    @Override
    public void PluginDisable() {
    }

    public void managers() {
//        item = new ItemManager();
        player = new PlayerManager();
        afkManager = new AFKManager();
        rankManager = new RankManager(this);
        nametagManager = new NametagManager(this);
        configManager = new ConfigManager();
        recentMessages = new HashMap<>();
        playerList = new HidePlayerList(this);

    }

    public void commands() {
        getCommand("test").setExecutor(new TestCommand());
        getCommand("punish").setExecutor(new PunishCommand(this));
        getCommand("rank").setExecutor(new RankCommand(this));
        getCommand("admin").setExecutor(new AdminCommand());
        getCommand("v").setExecutor(new VanishCommand(this));
        getCommand("version").setExecutor(new VersionCommand());
        getCommand("addvelocitytoall").setExecutor(new AddVelocityToAllCommand(this));
        getCommand("addvelocitytoplayer").setExecutor(new AddVelocityToPlayerCommand(this));
        getCommand("grant").setExecutor(new GrantCommand());
        getCommand("ip").setExecutor(new IPCommand());
    }

    public void config() {
        saveDefaultConfig();
    }

    public void listeners() {
        Bukkit.getPluginManager().registerEvents(new PlayerEvent(), this);
    }

    public static Vexcty getInstance() {
        return Vexcty.instance;
    }

        public void afk() {
            Bukkit.getScheduler().runTaskTimerAsynchronously(this, new MovementChecker(this.afkManager), 0L, 600L);

            this.afkManager = new AFKManager();
    }

}
