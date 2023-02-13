package com.moonsworkshop.vexcty;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.moonsworkshop.vexcty.command.PunishCommand;
import com.moonsworkshop.vexcty.command.ReportCommand;
import com.moonsworkshop.vexcty.command.TestCommand;
import com.moonsworkshop.vexcty.event.PlayerEvent;
import com.moonsworkshop.vexcty.lang.LangManager;
import com.moonsworkshop.vexcty.managers.*;
import com.moonsworkshop.vexcty.rank.*;
import lombok.Getter;
import net.minecraft.server.v1_8_R3.MinecraftServer;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public class Vexcty extends JavaPlugin {



    private static Vexcty instance;
    @Getter
    private LangManager langManager;
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
        Player player = null;

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
        langManager = new LangManager(this);

    }

    public void commands() {
        Arrays.asList(
        new ReportCommand()).forEach(command -> this.bukkitCommand(getName(), command));
        getCommand("rank").setExecutor(new RankCommand(this));
        getCommand("test").setExecutor(new TestCommand());
        getCommand("punish").setExecutor(new PunishCommand(this));
        getCommand("report").setExecutor(new ReportCommand(this));
    }

    public void listeners() {
        Listener PlayerEvent = (Listener) new PlayerEvent(this);
        Listener RankListener = (Listener) new RankListener(this);
        Bukkit.getPluginManager().registerEvents(PlayerEvent, this);
        Bukkit.getPluginManager().registerEvents(RankListener, this);
    }

    public static Vexcty getInstance() {
        return Vexcty.instance;
    }

    private void bukkitCommand(String prefix, Command command) {
        MinecraftServer.getServer().server.getCommandMap().register(prefix, command);
    }

}
