package com.moonsworkshop.vexcty;

import com.moonsworkshop.vexcty.afk.AFKManager;
import com.moonsworkshop.vexcty.afk.MovementChecker;
import com.moonsworkshop.vexcty.command.*;
import com.moonsworkshop.vexcty.config.ConfigManager;
import com.moonsworkshop.vexcty.event.PlayerEvent;
import com.moonsworkshop.vexcty.lang.LangCommand;
import com.moonsworkshop.vexcty.lang.LangManager;
import com.moonsworkshop.vexcty.managers.PlayerManager;
import com.moonsworkshop.vexcty.pm.MessageCommand;
import com.moonsworkshop.vexcty.pm.ReplyCommand;
import com.moonsworkshop.vexcty.rank.NametagManager;
import com.moonsworkshop.vexcty.rank.RankCommand;
import com.moonsworkshop.vexcty.rank.RankManager;
import com.moonsworkshop.vexcty.sql.ProfileManager;
import com.moonsworkshop.vexcty.sql.SQLManager;
import com.moonsworkshop.vexgot.HidePlayerList;
import com.moonsworkshop.vexgot.VexGot;
import lombok.Getter;
import me.lucko.helper.npc.Npc;
import net.minecraft.server.v1_8_R3.World;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.NPC;

import java.util.HashMap;
import java.util.UUID;

@Getter
public class Vexcty extends VexGot {

    public static HidePlayerList playerList;

    private static Vexcty instance;
    @Getter
    private LangManager langManager;
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
    public SQLManager MySQLManager;
    @Getter
    public ConfigManager configManager;
    @Getter
    private HashMap<UUID, UUID> recentMessages; // used for the private messages
    @Getter
    public ProfileManager profileManager;

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
        getMySQLManager().shutdown();
    }

    public void managers() {
//        item = new ItemManager();
        player = new PlayerManager();
        langManager = new LangManager(this);
        afkManager = new AFKManager();
        rankManager = new RankManager(this);
        nametagManager = new NametagManager(this);
        configManager = new ConfigManager();
        recentMessages = new HashMap<>();
        profileManager = new ProfileManager(this);
        playerList = new HidePlayerList(this);

    }

    public void commands() {
        getCommand("test").setExecutor(new TestCommand());
        getCommand("punish").setExecutor(new PunishCommand(this));
        getCommand("rank").setExecutor(new RankCommand(this));
        getCommand("lang").setExecutor(new LangCommand(this));
        getCommand("admin").setExecutor(new AdminCommand());
        getCommand("v").setExecutor(new VanishCommand(this));
        getCommand("version").setExecutor(new VersionCommand());
        getCommand("addvelocitytoall").setExecutor(new AddVelocityToAllCommand(this));
        getCommand("addvelocitytoplayer").setExecutor(new AddVelocityToPlayerCommand(this));
        getCommand("message").setExecutor(new MessageCommand(this));
        getCommand("reply").setExecutor(new ReplyCommand(this));
        getCommand("grant").setExecutor(new GrantCommand());
    }

    public void config() {
        saveDefaultConfig();
    }

    public void listeners() {
        Bukkit.getPluginManager().registerEvents(new PlayerEvent(this), this);
    }

    public static Vexcty getInstance() {
        return Vexcty.instance;
    }

        public void afk() {
            Bukkit.getScheduler().runTaskTimerAsynchronously(this, new MovementChecker(this.afkManager), 0L, 600L);

            this.afkManager = new AFKManager();
    }


//    private void updateBoard(Board board, Player player) {
//
//        if (getLangManager().getLang(player.getUniqueId(), Lang.EN)) {
//            board.updateLines(
//                    ChatColor.GRAY + String.valueOf(player.getWorld()),
//                    "",
//                    "Players: " + getServer().getOnlinePlayers().size(),
//                    "",
//                    "Rank: " + getRankManager().getRank(player.getUniqueId()).getName(),
//                    "",
//                    ChatColor.YELLOW + "www.vexcty.com"
//            );
//        } else if (getLangManager().getLang(player.getUniqueId(), Lang.AR)) {
//            board.updateLines(
//                    ChatColor.GRAY + String.valueOf(player.getWorld()),
//                    "",
//                    "اللاعبين: " + getServer().getOnlinePlayers().size(),
//                    "",
//                    "مرتبة: " + getRankManager().getRank(player.getUniqueId()).getName(),
//                    "",
//                    ChatColor.YELLOW + "www.vexcty.com"
//            );
//        } else if (getLangManager().getLang(player.getUniqueId(), Lang.FR)) {
//            board.updateLines(
//                    ChatColor.GRAY + String.valueOf(player.getWorld()),
//                    "",
//                    "Joueurs: " + getServer().getOnlinePlayers().size(),
//                    "",
//                    "Rang: " + getRankManager().getRank(player.getUniqueId()).getName(),
//                    "",
//                    ChatColor.YELLOW + "www.vexcty.com"
//            );
//        } else if (getLangManager().getLang(player.getUniqueId(), Lang.IT)) {
//            board.updateLines(
//                    ChatColor.GRAY + String.valueOf(player.getWorld()),
//                    "",
//                    "Giocatori: " + getServer().getOnlinePlayers().size(),
//                    "",
//                    "Rango: " + getRankManager().getRank(player.getUniqueId()).getName(),
//                    "",
//                    ChatColor.YELLOW + "www.vexcty.com"
//            );
//        } else if (getLangManager().getLang(player.getUniqueId(), Lang.JA)) {
//            board.updateLines(
//                    ChatColor.GRAY + String.valueOf(player.getWorld()),
//                    "",
//                    "プレイヤー: " + getServer().getOnlinePlayers().size(),
//                    "",
//                    "ランク: " + getRankManager().getRank(player.getUniqueId()).getName(),
//                    "",
//                    ChatColor.YELLOW + "www.vexcty.com"
//            );
//        } else if (getLangManager().getLang(player.getUniqueId(), Lang.LA)) {
//            board.updateLines(
//                    ChatColor.GRAY + String.valueOf(player.getWorld()),
//                    "",
//                    "Histriones: " + getServer().getOnlinePlayers().size(),
//                    "",
//                    "Rank: " + getRankManager().getRank(player.getUniqueId()).getName(),
//                    "",
//                    ChatColor.YELLOW + "www.vexcty.com"
//            );
//        } else if (getLangManager().getLang(player.getUniqueId(), Lang.PA)) {
//            board.updateLines(
//                    ChatColor.GRAY + String.valueOf(player.getWorld()),
//                    "",
//                    "ਖਿਡਾਰੀ: " + getServer().getOnlinePlayers().size(),
//                    "",
//                    "ਰੈਂਕ: " + getRankManager().getRank(player.getUniqueId()).getName(),
//                    "",
//                    ChatColor.YELLOW + "www.vexcty.com"
//            );
//        } else if (getLangManager().getLang(player.getUniqueId(), Lang.RU)) {
//            board.updateLines(
//                    ChatColor.GRAY + String.valueOf(player.getWorld()),
//                    "",
//                    "Игроки: " + getServer().getOnlinePlayers().size(),
//                    "",
//                    "Классифицировать: " + getRankManager().getRank(player.getUniqueId()).getName(),
//                    "",
//                    ChatColor.YELLOW + "www.vexcty.com"
//            );
//        } else if (getLangManager().getLang(player.getUniqueId(), Lang.ES)) {
//            board.updateLines(
//                    ChatColor.GRAY + String.valueOf(player.getWorld()),
//                    "",
//                    "Jugadores: " + getServer().getOnlinePlayers().size(),
//                    "",
//                    "Rango: " + getRankManager().getRank(player.getUniqueId()).getName(),
//                    "",
//                    ChatColor.YELLOW + "www.vexcty.com"
//            );
//        } else if (getLangManager().getLang(player.getUniqueId(), Lang.TR)) {
//            board.updateLines(
//                    ChatColor.GRAY + String.valueOf(player.getWorld()),
//                    "",
//                    "oyuncular: " + getServer().getOnlinePlayers().size(),
//                    "",
//                    "Rütbe: " + getRankManager().getRank(player.getUniqueId()).getName(),
//                    "",
//                    ChatColor.YELLOW + "www.vexcty.com"
//            );
//        } else if (getLangManager().getLang(player.getUniqueId(), Lang.UR)) {
//            board.updateLines(
//                    ChatColor.GRAY + String.valueOf(player.getWorld()),
//                    "",
//                    "کھلاڑی: " + getServer().getOnlinePlayers().size(),
//                    "",
//                    "رینک: " + getRankManager().getRank(player.getUniqueId()).getName(),
//                    "",
//                    ChatColor.YELLOW + "www.vexcty.com"
//            );
//        } else if (getLangManager().getLang(player.getUniqueId(), Lang.AM)) {
//            board.updateLines(
//                    ChatColor.GRAY + String.valueOf(player.getWorld()),
//                    "",
//                    "ተጫዋቾች: " + getServer().getOnlinePlayers().size(),
//                    "",
//                    "ደረጃ: " + getRankManager().getRank(player.getUniqueId()).getName(),
//                    "",
//                    ChatColor.YELLOW + "www.vexcty.com"
//            );
//        } else if (getLangManager().getLang(player.getUniqueId(), Lang.ZH)) {
//            board.updateLines(
//                    ChatColor.GRAY + String.valueOf(player.getWorld()),
//                    "",
//                    "球员: " + getServer().getOnlinePlayers().size(),
//                    "",
//                    "秩: " + getRankManager().getRank(player.getUniqueId()).getName(),
//                    "",
//                    ChatColor.YELLOW + "www.vexcty.com"
//            );
//        } else if (getLangManager().getLang(player.getUniqueId(), Lang.ZHHK)) {
//            board.updateLines(
//                    ChatColor.GRAY + String.valueOf(player.getWorld()),
//                    "",
//                    "球员: " + getServer().getOnlinePlayers().size(),
//                    "",
//                    "秩: " + getRankManager().getRank(player.getUniqueId()).getName(),
//                    "",
//                    ChatColor.YELLOW + "www.vexcty.com"
//            );
//        } else if (getLangManager().getLang(player.getUniqueId(), Lang.CS)) {
//            board.updateLines(
//                    ChatColor.GRAY + String.valueOf(player.getWorld()),
//                    "",
//                    "Hráči: " + getServer().getOnlinePlayers().size(),
//                    "",
//                    "Hodnost: " + getRankManager().getRank(player.getUniqueId()).getName(),
//                    "",
//                    ChatColor.YELLOW + "www.vexcty.com"
//            );
//        } else if (getLangManager().getLang(player.getUniqueId(), Lang.DE)) {
//            board.updateLines(
//                    ChatColor.GRAY + String.valueOf(player.getWorld()),
//                    "",
//                    "Spieler: " + getServer().getOnlinePlayers().size(),
//                    "",
//                    "Rang: " + getRankManager().getRank(player.getUniqueId()).getName(),
//                    "",
//                    ChatColor.YELLOW + "www.vexcty.com"
//            );
//        } else if (getLangManager().getLang(player.getUniqueId(), Lang.FI)) {
//            board.updateLines(
//                    ChatColor.GRAY + String.valueOf(player.getWorld()),
//                    "",
//                    "Pelaajat: " + getServer().getOnlinePlayers().size(),
//                    "",
//                    "Sijoitus: " + getRankManager().getRank(player.getUniqueId()).getName(),
//                    "",
//                    ChatColor.YELLOW + "www.vexcty.com"
//            );
//        } else if (getLangManager().getLang(player.getUniqueId(), Lang.PT)) {
//            board.updateLines(
//                    ChatColor.GRAY + String.valueOf(player.getWorld()),
//                    "",
//                    "Jogadoras: " + getServer().getOnlinePlayers().size(),
//                    "",
//                    "Classificação: " + getRankManager().getRank(player.getUniqueId()).getName(),
//                    "",
//                    ChatColor.YELLOW + "www.vexcty.com"
//            );
//        } else if (getLangManager().getLang(player.getUniqueId(), Lang.PT)) {
//            board.updateLines(
//                    ChatColor.GRAY + String.valueOf(player.getWorld()),
//                    "",
//                    "Gracze: " + getServer().getOnlinePlayers().size(),
//                    "",
//                    "Ranga: " + getRankManager().getRank(player.getUniqueId()).getName(),
//                    "",
//                    ChatColor.YELLOW + "www.vexcty.com"
//            );
//        }
//
//    }
}
