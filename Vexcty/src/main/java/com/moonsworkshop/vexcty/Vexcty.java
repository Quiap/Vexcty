package com.moonsworkshop.vexcty;

import com.moonsworkshop.vexcty.command.StreamingModeCommand;
import com.moonsworkshop.vexcty.command.TestCommand;
import com.moonsworkshop.vexcty.event.PlayerEvent;
import com.moonsworkshop.vexcty.managers.InventoryManager;
import com.moonsworkshop.vexcty.managers.ItemManager;
import com.moonsworkshop.vexcty.managers.PlayerManager;
import com.moonsworkshop.vexcty.managers.ReportManager;
import com.moonsworkshop.vexcty.rank.NametagManager;
import com.moonsworkshop.vexcty.rank.Perks;
import com.moonsworkshop.vexcty.rank.RankCommand;
import com.moonsworkshop.vexcty.rank.RankManager;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Vexcty extends JavaPlugin {

    private static Vexcty instance;
    @Getter
    private RankManager rankManager;
    @Getter
    private NametagManager nametagManager;
    @Getter
    private Perks perks;
    @Getter
    private InventoryManager inv;
    @Getter
    private ItemManager item;
    @Getter
    private PlayerManager player;
    @Getter
    private ReportManager reportManager;

    @Override
    public void onEnable() {
        managers();
        commands();
        listeners();
    }

    public void managers() {
        rankManager = new RankManager(this);
        nametagManager = new NametagManager(this);
        perks = new Perks(this);
        inv = new InventoryManager();
        item = new ItemManager();
        player = new PlayerManager();
        reportManager = new ReportManager();
    }

    public void commands() {
        getCommand("rank").setExecutor(new RankCommand(this));
        getCommand("test").setExecutor(new TestCommand());
        getCommand("streamingmode").setExecutor(new StreamingModeCommand(this));
    }

    public void listeners() {
        Listener PlayerEvent = (Listener) new PlayerEvent(this);
        Bukkit.getPluginManager().registerEvents(PlayerEvent, this);
    }

    public static Vexcty getInstance() {
        return Vexcty.instance;
    }
}
