package com.moonsworkshop.vexcty;

import com.moonsworkshop.vexcty.afk.AFKManager;
import com.moonsworkshop.vexcty.afk.MovementChecker;
import com.moonsworkshop.vexcty.command.PunishCommand;
import com.moonsworkshop.vexcty.command.TestCommand;
import com.moonsworkshop.vexcty.event.PlayerEvent;
import com.moonsworkshop.vexcty.lang.Lang;
import com.moonsworkshop.vexcty.lang.LangCommand;
import com.moonsworkshop.vexcty.lang.LangManager;
import com.moonsworkshop.vexcty.managers.PlayerManager;
import com.moonsworkshop.vexcty.rank.NametagManager;
import com.moonsworkshop.vexcty.rank.RankCommand;
import com.moonsworkshop.vexcty.rank.RankManager;
import com.moonsworkshop.vexcty.sql.ProfileManager;
import com.moonsworkshop.vexcty.sql.SQLManager;
import com.moonsworkshop.vexgot.Board;
import com.moonsworkshop.vexgot.VexGot;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Vexcty extends VexGot {

    public static Map<UUID, Board> boards = new HashMap<>();

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
    public ProfileManager profileManager;

    @Override
    public void PluginEnable() {
        managers();
        commands();
        listeners();
        afk();
    }

    @Override
    public void PluginDisable() {

    }

    public void managers() {
//        item = new ItemManager();
        player = new PlayerManager();
        langManager = new LangManager(this);
        afkManager = new AFKManager();
        rankManager = new RankManager(this);
        nametagManager = new NametagManager(this);
        MySQLManager = new SQLManager();
        profileManager = new ProfileManager();

    }

    public void commands() {
        getCommand("test").setExecutor(new TestCommand());
        getCommand("punish").setExecutor(new PunishCommand(this));
        getCommand("rank").setExecutor(new RankCommand(this));
        getCommand("lang").setExecutor(new LangCommand(this));
    }

    public void listeners() {
        Bukkit.getPluginManager().registerEvents(new PlayerEvent(this, new SQLManager()), this);
    }

    public static Vexcty getInstance() {
        return Vexcty.instance;
    }

        public void afk() {
            Bukkit.getScheduler().runTaskTimerAsynchronously(this, new MovementChecker(this.afkManager), 0L, 600L);

            this.afkManager = new AFKManager();
    }

    public void scoreboard(Player player) {
        getServer().getScheduler().runTaskTimer(this, () -> {
            for (Board board : boards.values()) {
                updateBoard(board, player);
            }
        }, 0, 60);
    }

    private void updateBoard(Board board, Player player) {

        if (getLangManager().getLang(player.getUniqueId(), Lang.EN)) {
            board.updateLines(
                    ChatColor.GRAY + String.valueOf(player.getWorld()),
                    "",
                    "Players: " + getServer().getOnlinePlayers().size(),
                    "",
                    "Rank: " + getRankManager().getRank(player.getUniqueId()).getName(),
                    "",
                    ChatColor.YELLOW + "www.vexcty.com"
            );
        } else if (getLangManager().getLang(player.getUniqueId(), Lang.AR)) {
            board.updateLines(
                    ChatColor.GRAY + String.valueOf(player.getWorld()),
                    "",
                    "اللاعبين: " + getServer().getOnlinePlayers().size(),
                    "",
                    "مرتبة: " + getRankManager().getRank(player.getUniqueId()).getName(),
                    "",
                    ChatColor.YELLOW + "www.vexcty.com"
            );
        } else if (getLangManager().getLang(player.getUniqueId(), Lang.FR)) {
            board.updateLines(
                    ChatColor.GRAY + String.valueOf(player.getWorld()),
                    "",
                    "Joueurs: " + getServer().getOnlinePlayers().size(),
                    "",
                    "Rang: " + getRankManager().getRank(player.getUniqueId()).getName(),
                    "",
                    ChatColor.YELLOW + "www.vexcty.com"
            );
        } else if (getLangManager().getLang(player.getUniqueId(), Lang.IT)) {
            board.updateLines(
                    ChatColor.GRAY + String.valueOf(player.getWorld()),
                    "",
                    "Giocatori: " + getServer().getOnlinePlayers().size(),
                    "",
                    "Rango: " + getRankManager().getRank(player.getUniqueId()).getName(),
                    "",
                    ChatColor.YELLOW + "www.vexcty.com"
            );
        } else if (getLangManager().getLang(player.getUniqueId(), Lang.JA)) {
            board.updateLines(
                    ChatColor.GRAY + String.valueOf(player.getWorld()),
                    "",
                    "プレイヤー: " + getServer().getOnlinePlayers().size(),
                    "",
                    "ランク: " + getRankManager().getRank(player.getUniqueId()).getName(),
                    "",
                    ChatColor.YELLOW + "www.vexcty.com"
            );
        } else if (getLangManager().getLang(player.getUniqueId(), Lang.LA)) {
            board.updateLines(
                    ChatColor.GRAY + String.valueOf(player.getWorld()),
                    "",
                    "Histriones: " + getServer().getOnlinePlayers().size(),
                    "",
                    "Rank: " + getRankManager().getRank(player.getUniqueId()).getName(),
                    "",
                    ChatColor.YELLOW + "www.vexcty.com"
            );
        } else if (getLangManager().getLang(player.getUniqueId(), Lang.PA)) {
            board.updateLines(
                    ChatColor.GRAY + String.valueOf(player.getWorld()),
                    "",
                    "ਖਿਡਾਰੀ: " + getServer().getOnlinePlayers().size(),
                    "",
                    "ਰੈਂਕ: " + getRankManager().getRank(player.getUniqueId()).getName(),
                    "",
                    ChatColor.YELLOW + "www.vexcty.com"
            );
        } else if (getLangManager().getLang(player.getUniqueId(), Lang.RU)) {
            board.updateLines(
                    ChatColor.GRAY + String.valueOf(player.getWorld()),
                    "",
                    "Игроки: " + getServer().getOnlinePlayers().size(),
                    "",
                    "Классифицировать: " + getRankManager().getRank(player.getUniqueId()).getName(),
                    "",
                    ChatColor.YELLOW + "www.vexcty.com"
            );
        } else if (getLangManager().getLang(player.getUniqueId(), Lang.ES)) {
            board.updateLines(
                    ChatColor.GRAY + String.valueOf(player.getWorld()),
                    "",
                    "Jugadores: " + getServer().getOnlinePlayers().size(),
                    "",
                    "Rango: " + getRankManager().getRank(player.getUniqueId()).getName(),
                    "",
                    ChatColor.YELLOW + "www.vexcty.com"
            );
        } else if (getLangManager().getLang(player.getUniqueId(), Lang.TR)) {
            board.updateLines(
                    ChatColor.GRAY + String.valueOf(player.getWorld()),
                    "",
                    "oyuncular: " + getServer().getOnlinePlayers().size(),
                    "",
                    "Rütbe: " + getRankManager().getRank(player.getUniqueId()).getName(),
                    "",
                    ChatColor.YELLOW + "www.vexcty.com"
            );
        } else if (getLangManager().getLang(player.getUniqueId(), Lang.UR)) {
            board.updateLines(
                    ChatColor.GRAY + String.valueOf(player.getWorld()),
                    "",
                    "کھلاڑی: " + getServer().getOnlinePlayers().size(),
                    "",
                    "رینک: " + getRankManager().getRank(player.getUniqueId()).getName(),
                    "",
                    ChatColor.YELLOW + "www.vexcty.com"
            );
        } else if (getLangManager().getLang(player.getUniqueId(), Lang.AM)) {
            board.updateLines(
                    ChatColor.GRAY + String.valueOf(player.getWorld()),
                    "",
                    "ተጫዋቾች: " + getServer().getOnlinePlayers().size(),
                    "",
                    "ደረጃ: " + getRankManager().getRank(player.getUniqueId()).getName(),
                    "",
                    ChatColor.YELLOW + "www.vexcty.com"
            );
        } else if (getLangManager().getLang(player.getUniqueId(), Lang.ZH)) {
            board.updateLines(
                    ChatColor.GRAY + String.valueOf(player.getWorld()),
                    "",
                    "球员: " + getServer().getOnlinePlayers().size(),
                    "",
                    "秩: " + getRankManager().getRank(player.getUniqueId()).getName(),
                    "",
                    ChatColor.YELLOW + "www.vexcty.com"
            );
        } else if (getLangManager().getLang(player.getUniqueId(), Lang.ZHHK)) {
            board.updateLines(
                    ChatColor.GRAY + String.valueOf(player.getWorld()),
                    "",
                    "球员: " + getServer().getOnlinePlayers().size(),
                    "",
                    "秩: " + getRankManager().getRank(player.getUniqueId()).getName(),
                    "",
                    ChatColor.YELLOW + "www.vexcty.com"
            );
        } else if (getLangManager().getLang(player.getUniqueId(), Lang.CS)) {
            board.updateLines(
                    ChatColor.GRAY + String.valueOf(player.getWorld()),
                    "",
                    "Hráči: " + getServer().getOnlinePlayers().size(),
                    "",
                    "Hodnost: " + getRankManager().getRank(player.getUniqueId()).getName(),
                    "",
                    ChatColor.YELLOW + "www.vexcty.com"
            );
        } else if (getLangManager().getLang(player.getUniqueId(), Lang.DE)) {
            board.updateLines(
                    ChatColor.GRAY + String.valueOf(player.getWorld()),
                    "",
                    "Spieler: " + getServer().getOnlinePlayers().size(),
                    "",
                    "Rang: " + getRankManager().getRank(player.getUniqueId()).getName(),
                    "",
                    ChatColor.YELLOW + "www.vexcty.com"
            );
        } else if (getLangManager().getLang(player.getUniqueId(), Lang.FI)) {
            board.updateLines(
                    ChatColor.GRAY + String.valueOf(player.getWorld()),
                    "",
                    "Pelaajat: " + getServer().getOnlinePlayers().size(),
                    "",
                    "Sijoitus: " + getRankManager().getRank(player.getUniqueId()).getName(),
                    "",
                    ChatColor.YELLOW + "www.vexcty.com"
            );
        } else if (getLangManager().getLang(player.getUniqueId(), Lang.PT)) {
            board.updateLines(
                    ChatColor.GRAY + String.valueOf(player.getWorld()),
                    "",
                    "Jogadoras: " + getServer().getOnlinePlayers().size(),
                    "",
                    "Classificação: " + getRankManager().getRank(player.getUniqueId()).getName(),
                    "",
                    ChatColor.YELLOW + "www.vexcty.com"
            );
        } else if (getLangManager().getLang(player.getUniqueId(), Lang.PT)) {
            board.updateLines(
                    ChatColor.GRAY + String.valueOf(player.getWorld()),
                    "",
                    "Gracze: " + getServer().getOnlinePlayers().size(),
                    "",
                    "Ranga: " + getRankManager().getRank(player.getUniqueId()).getName(),
                    "",
                    ChatColor.YELLOW + "www.vexcty.com"
            );
        }

    }
}
