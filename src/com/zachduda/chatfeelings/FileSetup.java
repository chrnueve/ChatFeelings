package com.zachduda.chatfeelings;

import com.zachduda.chatfeelings.other.Supports;
import org.bukkit.Bukkit;
import org.bukkit.Registry;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class FileSetup {
    private static final Main plugin = Main.getPlugin(Main.class);

    private static boolean saveFile(FileConfiguration fc, File f) {
        try {
            fc.save(f);
            return true;
        } catch (Exception err) {
            Main.log("[!] Failed to save file changes. See error below:", true, true);
            err.printStackTrace();
            return false;
        }
    }

    private static File getFolder() {
        return Objects.requireNonNull(Bukkit.getServer().getPluginManager().getPlugin("ChatFeelings")).getDataFolder();
    }

    private static void setMsgs(String configpath, String msg) {
        File msgsfile = new File(getFolder(), File.separator + "messages.yml");
        FileConfiguration msgs;
        try {
            msgs = YamlConfiguration.loadConfiguration(new InputStreamReader(Files.newInputStream(msgsfile.toPath()), StandardCharsets.UTF_8));
        } catch (IOException e) {
            if(Main.debug) {
                Main.debug("Unable to decode or create messages.yml file:");
                throw new RuntimeException(e);
            } else {
                Main.log("There was an error when trying to modify or create your messages.yml", true, true);
                return;
            }
        }

        if (!msgsfile.exists()) {
            saveFile(msgs, msgsfile);
        }

        if (!msgs.contains(configpath)) {
            msgs.set(configpath, msg);
        } else if (msgs.getString(configpath) == null) {
            Main.log("Replacing '" + configpath + " in messages.yml, it was left blank.", false, true);
            msgs.set(configpath, msg);
        }

        saveFile(msgs, msgsfile);
    }

    private static void forceMsgs(String configpath, String msg) {
        File msgsfile = new File(getFolder(), File.separator + "messages.yml");
        FileConfiguration msgs;
        try {
            msgs = YamlConfiguration.loadConfiguration(new InputStreamReader(Files.newInputStream(msgsfile.toPath()), StandardCharsets.UTF_8));
        } catch (IOException e) {
            if(Main.debug) {
                Main.debug("Unable to decode or create messages.yml file:");
                throw new RuntimeException(e);
            } else {
                Main.log("There was an error when trying to modify or create your messages.yml", true, true);
                return;
            }
        }

        if (!msgsfile.exists()) {
            saveFile(msgs, msgsfile);
        }

        msgs.set(configpath, msg);
        saveFile(msgs, msgsfile);
    }

    private static void setMsgsVersion(int vers) {
        File msgsfile = new File(getFolder(), File.separator + "messages.yml");
        FileConfiguration msgs;
        try {
            msgs = YamlConfiguration.loadConfiguration(new InputStreamReader(Files.newInputStream(msgsfile.toPath()), StandardCharsets.UTF_8));
        } catch (IOException e) {
            if(Main.debug) {
                Main.debug("Unable to decode or create messages.yml file:");
                throw new RuntimeException(e);
            } else {
                Main.log("There was an error when trying to modify or create your messages.yml", true, true);
                return;
            }
        }

        if (!msgs.contains("Version") || msgs.getInt("Version") != vers) {
            msgs.set("Version", vers);
            saveFile(msgs, msgsfile);
        }
    }

    private static void forceEmotes(String configpath, String msg) {
        File emotesfile = new File(getFolder(), File.separator + "emotes.yml");
        FileConfiguration emotes;
        try {
            emotes = YamlConfiguration.loadConfiguration(new InputStreamReader(Files.newInputStream(emotesfile.toPath()), StandardCharsets.UTF_8));
        } catch (IOException e) {
            if(Main.debug) {
                Main.debug("Unable to decode or create emotes.yml file:");
                throw new RuntimeException(e);
            } else {
                Main.log("There was an error when trying to modify or create your emotes.yml", true, true);
                return;
            }
        }

        if (!emotesfile.exists()) {
            saveFile(emotes, emotesfile);
        }

        emotes.set(configpath, msg);
        saveFile(emotes, emotesfile);
    }

    private static void setEmotes(String configpath, String msg) {
        File emotesfile = new File(getFolder(), File.separator + "emotes.yml");
        FileConfiguration emotes;
        try {
            emotes = YamlConfiguration.loadConfiguration(new InputStreamReader(Files.newInputStream(emotesfile.toPath()), StandardCharsets.UTF_8));
        } catch (IOException e) {
            if(Main.debug) {
                Main.debug("Unable to decode or create emotes.yml file:");
                throw new RuntimeException(e);
            } else {
                Main.log("There was an error when trying to modify or create your emotes.yml", true, true);
                return;
            }
        }

        if (!emotesfile.exists()) {
            saveFile(emotes, emotesfile);
        }

        if (!emotes.contains(configpath)) {
            emotes.set(configpath, msg);
        } else {
            if (emotes.getString(configpath) == null) {
                plugin.getLogger().warning("Replacing '" + configpath + " in emotes.yml, it was left blank.");
                emotes.set(configpath, msg);
            }
        }

        saveFile(emotes, emotesfile);
    }

    private static void setEmotesVersion(int vers) {
        File emotesfile = new File(getFolder(), File.separator + "emotes.yml");
        FileConfiguration emotes = YamlConfiguration.loadConfiguration(emotesfile);

        if (!emotesfile.exists()) {
            saveFile(emotes, emotesfile);
        }

        if (!emotes.contains("Version") || emotes.getInt("Version") != vers) {
            emotes.set("Version", vers);
            saveFile(emotes, emotesfile);
        }
    }

    private static void setEmotesDouble(String configpath, Double dubdub) {
        File emotesfile = new File(getFolder(), File.separator + "emotes.yml");
        FileConfiguration emotes = YamlConfiguration.loadConfiguration(emotesfile);
        if (!emotesfile.exists()) {
            saveFile(emotes, emotesfile);
        }

        if (!emotes.contains(configpath)) {
            emotes.set(configpath, dubdub);
        } else if (emotes.getString(configpath) == null) {
            plugin.getLogger().warning("Replacing '" + configpath + " (double) in emotes.yml, it was left blank.");
            emotes.set(configpath, dubdub);
        }
        saveFile(emotes, emotesfile);
    }

    private static void setEmotesBoolean(String configpath, boolean siono) {
        File emotesfile = new File(getFolder(), File.separator + "emotes.yml");
        FileConfiguration emotes = YamlConfiguration.loadConfiguration(emotesfile);
        if (!emotesfile.exists()) {
            saveFile(emotes, emotesfile);
        }

        if (!emotes.contains(configpath)) {
            emotes.set(configpath, siono);
        } else if (emotes.getString(configpath) == null) {
            plugin.getLogger().warning("Replacing '" + configpath + " (boolean) in emotes.yml, it was left blank.");
            emotes.set(configpath, siono);
        }
        saveFile(emotes, emotesfile);
    }

    private static boolean validSound(String sound) {
        try {
            return Objects.requireNonNull(Registry.SOUNDS.match(sound)).isRegistered();
        } catch(Exception e) {
            return false;
        }
    }

    @SuppressWarnings("SpellCheckingInspection")
    static void enableFiles() {
        File folder = getFolder();

        File msgsfile = new File(folder, File.separator + "messages.yml");
        FileConfiguration msgs = YamlConfiguration.loadConfiguration(msgsfile);

        File emotesfile = new File(folder, File.separator + "emotes.yml");
        FileConfiguration emotes = YamlConfiguration.loadConfiguration(emotesfile);

        final int msgfilever = 12;
        if (!msgsfile.exists() || !msgs.contains("Version")) {

            List<String> confighead = new ArrayList<>();
            confighead.add("¿Buscas los mensajes usados para los sentimientos?");
            confighead.add("¡Revisa tu archivo emotes.yml!");

            try {
                msgs.options().setHeader(confighead);
            } catch (NoSuchMethodError e) {
                // Using less than Java 18 will use this method instead.
                try {
                    msgs.options().header("¿Buscas los mensajes usados para los sentimientos? ¡Revisa el archivo emotes.yml!");
                } catch (Exception giveup) { /* just skip this */ }
            }

            if (saveFile(msgs, msgsfile)) {
                if(!Main.reducemsgs) {
                    plugin.getLogger().info("Created new messages.yml file...");
                }
            }

        } else {
            final int currentmsgv = msgs.getInt("Version");
            if (currentmsgv != msgfilever) {
                plugin.getLogger().info("Updating your messages.yml with new additional messages...");
            }
            if (currentmsgv < 6) {
                forceMsgs("Reload", "&8&l> &a&l✓  &7Plugin reloaded in &f%time%");
            }

            if (currentmsgv < 7) {
                forceMsgs("Player-Is-Sleeping", null); // added in v3, removed in v7
                forceMsgs("No-Player-Ignore", null); // removed in v7
            }

            if (currentmsgv < 10) {
                forceMsgs("Prefix", msgs.getString("Prefix") + " &f"); // removed space in prefix internally in v10
            }

            if (currentmsgv < 12) {
                // Was also v11 but had auto correct causing upgrade issues, bump to v12 - 8/18/24
                // Typo in file, move old variables to correctly spelled one.
                // INTENTIONALLY MISTYPED AS INGORING TO CORRECT TO IGNORING 
                if (msgs.getString("Ingoring-On-Player") != null) {
                    setMsgs("Ignoring-On-Player", msgs.getString("Ingoring-On-Player"));
                    forceMsgs("Ingoring-On-Player", null);
                }
                if (msgs.getString("Ingoring-Off-Player") != null) {
                    setMsgs("Ignoring-Off-Player", msgs.getString("Ingoring-Off-Player"));
                    forceMsgs("Ingoring-Off-Player", null);
                }

                if (msgs.getString("Ingoring-On-All") != null) {
                    setMsgs("Ignoring-On-All", msgs.getString("Ingoring-Off-Player"));
                    forceMsgs("Ingoring-On-Player", null);
                }

                if (msgs.getString("Ingoring-Off-All") != null) {
                    setMsgs("Ignoring-Off-All", msgs.getString("Ingoring-Off-Player"));
                    forceMsgs("Ingoring-Off-Player", null);
                }
                // Wb -> Welcome Back
                if (msgs.getString("Command_Descriptions.Wb") != null) {
                    setMsgs("Command_Descriptions.Welcomeback", msgs.getString("Command_Descriptions.Wb"));
                    forceMsgs("Command_Descriptions.Wb", null);
                }
            }
        }

        setMsgs("Prefix", "&a&lC&r&ahat&f&lF&r&feelings &8&l┃ &f");
        setMsgs("Prefix-Header", "&a&lC&r&ahat &f&lF&r&feelings");
        setMsgs("Reload", "&8&l> &a&l✓  &7Complemento recargado en &f%time%"); // updated in version 5
        setMsgs("Console-Name", "El Servidor");
        setMsgs("No-Permission", "&cLo siento. &fNo tienes permiso para eso.");
        setMsgs("Feelings-Help", "&a&lSentimientos:");
        setMsgs("Feelings-Help-Page", "&7(Página &f%page%&8&l/&r&f%pagemax%&7)");
        setMsgs("Sending-World-Disabled", "&cLo siento. &fNo puedes usar sentimientos en este mundo.");
        setMsgs("Disabled-Serverwide-Targets", "&cNo permitido. &fEste servidor ha desactivado emocionar a todos.");
        setMsgs("Receiving-World-Disabled", "&cLo siento. &fTu objetivo está en un mundo con sentimientos desactivados.");
        setMsgs("Page-Not-Found", "&cVaya. &fEsa página no existe, prueba &7/sentimientos 1");
        setMsgs("No-Player", "&c¡Ups! &fDebes indicar a qué jugador hacérselo."); // updated in version 2
        setMsgs("No-Player-Mute", "&c¡Ups! &fDebes indicar a qué jugador silenciar."); // added in version 3
        setMsgs("No-Player-Unmute", "&c¡Ups! &fDebes indicar a qué jugador reactivar."); // added in version 3
        setMsgs("Player-Offline", "&cJugador sin conexión. &fNo pudimos encontrar a &7&l%player% &fen el servidor.");
        setMsgs("Player-Never-Joined", "&cHmm. &fEse jugador nunca ha entrado antes.");
        setMsgs("Outside-Of-Radius", "&cHmm. &fEstás demasiado lejos de &7%player% &fpara usar eso.");
        setMsgs("Cooldown-Active", "&cVe más despacio. &fEspera &7%time% &fantes de volver a hacerlo.");
        setMsgs("Ignore-Cooldown", "&cVe más despacio. &fEspera un momento antes de volver a ignorar.");
        setMsgs("Console-Not-Player", "&c¡Qué loco! &fLa &7CONSOLA&f no es un jugador real.");
        setMsgs("Sender-Is-Target", "&c¡Qué gracioso! &fNo puedes %command% &fhacerte a ti mismo.");
        setMsgs("Is-Muted", "&cEstás silenciado. &fYa no puedes usar sentimientos."); // added in version 3
        setMsgs("Folder-Not-Found", "&cHmm. &fNo hay datos que mostrar aquí."); // added in version 4
        setMsgs("Stats-Header-Own", "&e&lTus estadísticas:"); // added in version 6
        setMsgs("Stats-Header-Other", "&e&lEstadísticas de %player%:"); // added in version 6
        setMsgs("Ignore-List-Header", "&c&lJugadores ignorados:"); // added in version 7
        setMsgs("Ignore-List-None", "   &8&l> &f¡Actualmente no estás ignorando a nadie!"); // added in version 7
        setMsgs("Ignore-List-All", "   &8&l> &fEstás ignorando todos los sentimientos."); // added in version 8
        setMsgs("Ignore-List-Cooldown", "&cPor favor espera. &fDebes esperar antes de revisar a quién ignoras.");
        setMsgs("Mute-List-Header", "&e&lJugadores silenciados:"); // added in version 4
        setMsgs("Mute-List-Player", "&r  &8&l> &f%player%"); // added in version 4
        setMsgs("Mute-List-Total-One", "&r  &7Hay &f&l%total% &7jugador silenciado."); // added in version 4
        setMsgs("Mute-List-Total-Many", "&r  &7Hay &f&l%total% &7jugadores silenciados."); // added in version 4
        setMsgs("Mute-List-Total-Zero", "&r  &8&l> &a&l¡Bien! &7No hay jugadores silenciados."); // added in version 4
        setMsgs("Player-Has-Been-Muted", "&cUsuario silenciado. &7%player% &fya no puede usar sentimientos."); // added in version 3
        setMsgs("Player-Muted-Via-Essentials", "&c¡Ups! &7%player%&f está silenciado mediante Essentials, usa /unmute."); // added in version 5
        setMsgs("Player-Muted-Via-LiteBans", "&c¡Ups! &7%player%&f está silenciado mediante LiteBans, usa /unmute."); // added in version 5
        setMsgs("Player-Muted-Via-AdvancedBan", "&c¡Ups! &7%player%&f está silenciado mediante AdvancedBans, usa /unmute."); // added in version 5
        setMsgs("Extra-Mute-Present", "&r&7&oYa está silenciado mediante tu sistema de castigos. &e&oConsulta /cf mutelist"); // added in version 5
        setMsgs("Player-Has-Been-Unmuted", "&aUsuario reactivado. &7%player% &fya puede usar sentimientos otra vez."); // added in version 3
        setMsgs("Cant-Mute-Self", "&c¡Qué gracioso! &fNo puedes silenciarte."); // added in version 3
        setMsgs("Player-Already-Muted", "&cUps. &fEse jugador ya está silenciado."); // added in version 3
        setMsgs("Player-Already-Unmuted", "&cUps. &fNo puedes reactivar a quien no está silenciado."); // added in version 3
        setMsgs("Already-Mute-Unmute-Suggestion", "&7&o¿Quizá quisiste decir &e&o/cf unmute&7&o?"); // added in version 3
        setMsgs("No-Perm-Mute-Suggestion", "&7&o¿Tal vez quisiste decir &e&o/cf ignore&7&o?");
        setMsgs("Emote-Disabled", "&cSentimiento desactivado. &fEl servidor inhabilitó este sentimiento.");
        setMsgs("Ignoring-On-Player", "&7Ahora has &c&lBLOQUEADO &r&7los sentimientos de: &f%player%");
        setMsgs("Ignoring-Off-Player", "&7Ahora &a&lPERMITES &7los sentimientos de: &f%player%");
        setMsgs("Ignoring-On-All", "&7Ahora has &c&lBLOQUEADO &r&7los sentimientos de todos los jugadores.");
        setMsgs("Ignoring-Off-All", "&7Ahora &a&lPERMITES &7los sentimientos de todos los jugadores.");
        setMsgs("Cant-Ignore-Self", "&c¡Qué gracioso! &fNo puedes ignorarte.");
        setMsgs("Target-Is-Ignoring", "&c¡Qué pena! &fEste jugador te ha bloqueado.");
        setMsgs("Target-Is-Ignoring-All", "&c¡Qué pena! &fEste jugador no acepta sentimientos.");
        setMsgs("Command-List-Page", "&7Para ir a la siguiente página usa &a/sentimientos %page%");
        setMsgs("Command-List-Player", "&r &f(player)");
        setMsgs("Command-List-NoPerm", "&7No puedes usar este sentimiento.");

        setMsgs("Command-Help.Descriptions.Help", "&7Muestra esta página.");
        setMsgs("Command-Help.Descriptions.Ignore", "&7Activa o desactiva ignorar sentimientos de jugadores.");
        setMsgs("Command-Help.Descriptions.Ignore-All", "&7Activa o desactiva permitir sentimientos de todos.");
        setMsgs("Command-Help.Descriptions.Stats", "&7Muestra cuántos sentimientos has enviado.");
        setMsgs("Command-Help.Descriptions.Stats-Others", "&7Muestra el total de sentimientos enviados por otro jugador.");
        setMsgs("Command-Help.Descriptions.Mute", "&7Impide que un jugador use sentimientos.");
        setMsgs("Command-Help.Descriptions.Unmute", "&7Permite de nuevo que un jugador use sentimientos.");
        setMsgs("Command-Help.Descriptions.Mute-List", "&7Lista a los jugadores que están silenciados.");
        setMsgs("Command-Help.Descriptions.Plugin-Version", "&7Muestra la información de la versión actual.");
        setMsgs("Command-Help.Descriptions.Plugin-Reload", "&7Recarga todos los archivos de configuración y mensajes.");
        setMsgs("Command-Help.Descriptions.Feelings", "&7Muestra todos los sentimientos disponibles.");

        setMsgs("Command_Descriptions.Abrazo", "¡Da a alguien un abrazo bien cálido!");
        setMsgs("Command_Descriptions.Bofetada", "Devuelve a alguien a la realidad con una bofetada.");
        setMsgs("Command_Descriptions.Picar", "Pica a alguien para llamar su atención.");
        setMsgs("Command_Descriptions.Chocala", "Demuestra tu apoyo chocando los cinco.");
        setMsgs("Command_Descriptions.Palmada", "Muestra tu desaprobación con una palmada en la cara.");
        setMsgs("Command_Descriptions.Gritar", "Grita a alguien tan fuerte como puedas.");
        setMsgs("Command_Descriptions.Morder", "Muerde a un jugador directo en el brazo.");
        setMsgs("Command_Descriptions.Acurrucar", "Acurrúcate con el poder de los abrazos cálidos.");
        setMsgs("Command_Descriptions.Sacudir", "Sacude a un jugador hasta ponerlo en pie.");
        setMsgs("Command_Descriptions.Acuchillar", "Apuñala a alguien con un cuchillo. ¡Ay!");
        setMsgs("Command_Descriptions.Besar", "Da un beso lleno de cariño. ¡Qué ternura!");
        setMsgs("Command_Descriptions.Golpear", "Golpea a alguien para regresarlo a la cordura.");
        setMsgs("Command_Descriptions.Asesinar", "Lleva la rivalidad al extremo mortal.");
        setMsgs("Command_Descriptions.Oye", "Lánzale un sonoro ‘oye’ a un jugador.");
        setMsgs("Command_Descriptions.Llorar", "¿Horas tristes? Llora con alguien.");
        setMsgs("Command_Descriptions.Dabear", "Sorprende a todos haciendo un dab frente a alguien.");
        setMsgs("Command_Descriptions.Lamer", "Lame a alguien como si fuera un helado.");
        setMsgs("Command_Descriptions.Despreciar", "Avergüenza a un jugador por lo que hizo.");
        setMsgs("Command_Descriptions.Acariciar", "Acaricia la cabeza de un jugador por ser bueno.");
        setMsgs("Command_Descriptions.Acechar", "Acecha a un jugador con sigilo... con sigilo.");
        setMsgs("Command_Descriptions.Sospechar", "Ponte muy sospechoso con ese hueso suelto.");
        setMsgs("Command_Descriptions.Saludar", "Despídete con elegancia agitando la mano.");
        setMsgs("Command_Descriptions.Bienvenido", "Da una cálida bienvenida a los jugadores que regresan.");
        setMsgs("Command_Descriptions.Tocar", "Bopea suavemente la nariz de alguien.");
        setMsgs("Command_Descriptions.Tetazo", "Lanza un tetazo amistoso y divertido.");
        setMsgs("Command_Descriptions.Punalada", "Asesta una puñalada certera.");
        setMsgsVersion(12);

        if (!emotesfile.exists() || !emotes.contains("Version")) {
            if (saveFile(emotes, emotesfile)) {
                if(!Main.reducemsgs) {
                    plugin.getLogger().info("Created new emotes.yml file...");
                }
            }
        } else {
            final int currentEmotesVersion = emotes.getInt("Version");
            if (currentEmotesVersion != 8) {
                plugin.getLogger().info("Updating your emotes.yml for the latest update...");
                if(currentEmotesVersion <= 4) {
                    if(!emotes.contains("Feelings.Bienvenido.Msgs.Sender") || Objects.requireNonNull(emotes.getString("Feelings.Bienvenido.Msgs.Sender")).equalsIgnoreCase("&7You told &a&l%player% welcome back!")) {
                        forceEmotes("Feelings.Bienvenido.Msgs.Sender", "&7Le dijiste a &a&l%player%&r &7¡bienvenido de vuelta!");
                    }
                }
                if (currentEmotesVersion <= 3) {
                    if (Objects.requireNonNull(emotes.getString("Feelings.Morder.Msgs.Sender")).contains("info")) {
                        forceEmotes("Feelings.Morder.Msgs.Sender", "&7Clavas tus dientes en la piel de &c&l%player%&r&7.");
                    }
                }

                if (currentEmotesVersion <= 5) {
                   setEmotesBoolean("Feelings.Bienvenido.Enable", emotes.getBoolean("Feelings.Wb.Enable"));
                   setEmotes("Feelings.Bienvenido.Msgs.Sender", emotes.getString("Feelings.Wb.Msgs.Sender"));
                   setEmotes("Feelings.Bienvenido.Msgs.Target", emotes.getString("Feelings.Wb.Msgs.Target"));
                   setEmotes("Feelings.Bienvenido.Msgs.Global", emotes.getString("Feelings.Wb.Msgs.Global"));
                   setEmotes("Feelings.Bienvenido.Sounds.Sound1.Name", emotes.getString("Feelings.Wb.Sounds.Sound1.Name"));
                   setEmotesDouble("Feelings.Bienvenido.Sounds.Sound1.Volume", emotes.getDouble("Feelings.Wb.Sounds.Sound1.Volume"));
                   setEmotesDouble("Feelings.Bienvenido.Sounds.Sound1.Pitch", emotes.getDouble("Feelings.Wb.Sounds.Sound1.Pitch"));
                   setEmotes("Feelings.Bienvenido.Sounds.Sound2.Name", emotes.getString("Feelings.Wb.Sounds.Sound2.Name"));
                   setEmotesDouble("Feelings.Bienvenido.Sounds.Sound2.Volume", emotes.getDouble("Feelings.Wb.Sounds.Sound2.Volume"));
                   setEmotesDouble("Feelings.Bienvenido.Sounds.Sound2.Pitch", emotes.getDouble("Feelings.Wb.Sounds.Sound2.Pitch"));

                   forceEmotes("Feelings.Wb", null);
                }
                if(currentEmotesVersion <= 6) {
                    final String path = "Feelings.Abrazo.Sounds.Sound1.Name";
                    if(emotes.contains(path)) {
                        if(Objects.requireNonNull(emotes.getString(path)).equalsIgnoreCase("ENTITY_CAT_PURREOW")) {
                            if(Supports.getMcMajorVersion() >= 1 && Supports.getMcMinorVersion() >= 20 && Supports.getMcPatchVersion() >= 6) {
                                // Sounds changed along the way somehow and I let a soft fail occur. This should fix it.
                                emotes.set(path, "ENTITY.CAT.PURREOW");
                            }
                        }
                    }
                }
                if(currentEmotesVersion <= 7) {
                    Map<String, String> migratedFeelings = new LinkedHashMap<>();
                    migratedFeelings.put("Hug", "Abrazo");
                    migratedFeelings.put("Bite", "Morder");
                    migratedFeelings.put("Punch", "Golpear");
                    migratedFeelings.put("Murder", "Asesinar");
                    migratedFeelings.put("Boi", "Oye");
                    migratedFeelings.put("Dab", "Dabear");
                    migratedFeelings.put("Cry", "Llorar");
                    migratedFeelings.put("Facepalm", "Palmada");
                    migratedFeelings.put("Highfive", "Chocala");
                    migratedFeelings.put("Kiss", "Besar");
                    migratedFeelings.put("Lick", "Lamer");
                    migratedFeelings.put("Shake", "Sacudir");
                    migratedFeelings.put("Snuggle", "Acurrucar");
                    migratedFeelings.put("Yell", "Gritar");
                    migratedFeelings.put("Poke", "Picar");
                    migratedFeelings.put("Slap", "Bofetada");
                    migratedFeelings.put("Stab", "Acuchillar");
                    migratedFeelings.put("Pat", "Acariciar");
                    migratedFeelings.put("Scorn", "Despreciar");
                    migratedFeelings.put("Stalk", "Acechar");
                    migratedFeelings.put("Sus", "Sospechar");
                    migratedFeelings.put("Wave", "Saludar");
                    migratedFeelings.put("Welcomeback", "Bienvenido");
                    migratedFeelings.put("Boop", "Tocar");

                    boolean migrated = false;
                    for (Map.Entry<String, String> entry : migratedFeelings.entrySet()) {
                        final String legacyKey = entry.getKey();
                        final String localizedKey = entry.getValue();
                        final String legacyPath = "Feelings." + legacyKey;
                        final String localizedPath = "Feelings." + localizedKey;

                        if (!emotes.contains(legacyPath)) {
                            continue;
                        }

                        if (!emotes.contains(localizedPath)) {
                            emotes.set(localizedPath, emotes.get(legacyPath));
                        } else {
                            // Merge missing nested values so existing customizations aren't lost.
                            final ConfigurationSection legacySection = emotes.getConfigurationSection(legacyPath);
                            if (legacySection != null) {
                                for (String child : legacySection.getKeys(true)) {
                                    final String childLegacyPath = legacyPath + "." + child;
                                    final String childLocalizedPath = localizedPath + "." + child;
                                    if (!emotes.contains(childLocalizedPath)) {
                                        emotes.set(childLocalizedPath, emotes.get(childLegacyPath));
                                    }
                                }
                            }
                        }

                        emotes.set(legacyPath, null);
                        migrated = true;
                    }

                    if (migrated) {
                        saveFile(emotes, emotesfile);
                        emotes = YamlConfiguration.loadConfiguration(emotesfile);
                    }
                }
                setEmotesVersion(8);
            }
        }

        setEmotesBoolean("Feelings.Abrazo.Enable", true);
        setEmotes("Feelings.Abrazo.Msgs.Sender", "&7Le das a &a&l%player% &r&7un abrazo cálido. &cAwww &4❤");
        setEmotes("Feelings.Abrazo.Msgs.Target", "&a&l%player% &r&7te da un abrazo cálido. &cAwww &4❤");
        setEmotes("Feelings.Abrazo.Msgs.Global", "&a&l%sender% &r&7le dio a &2&l%target% &r&7un abrazo cálido. &cAwww &4❤");
        //	setEmotes("Feelings.Abrazo.Msgs.Everyone", "&a&l%player% &r&7da a todos un abrazo cálido. &cAwww &4❤");
        setEmotes("Feelings.Abrazo.Sounds.Sound1.Name", "ENTITY.CAT.PURREOW");
        setEmotesDouble("Feelings.Abrazo.Sounds.Sound1.Volume", 2.0);
        setEmotesDouble("Feelings.Abrazo.Sounds.Sound1.Pitch", 2.0);
        setEmotes("Feelings.Abrazo.Sounds.Sound2.Name", "None");
        setEmotesDouble("Feelings.Abrazo.Sounds.Sound2.Volume", 0.0);
        setEmotesDouble("Feelings.Abrazo.Sounds.Sound2.Pitch", 0.0);

        setEmotesBoolean("Feelings.Morder.Enable", true);
        setEmotes("Feelings.Morder.Msgs.Sender", "&7Clavas tus dientes en la piel de &c&l%player%&r&7.");
        setEmotes("Feelings.Morder.Msgs.Target", "&c&l%player% &r&7te clava los dientes en la piel.");
        setEmotes("Feelings.Morder.Msgs.Global", "&c&l%sender% &r&7le clavó los dientes a &4&l%target%&r&7.");
        //	setEmotes("Feelings.Morder.Msgs.Everyone", "&c&l%player% &r&7le clava los dientes a todo el mundo.");
        setEmotes("Feelings.Morder.Sounds.Sound1.Name", "ENTITY.ZOMBIE.ATTACK_WOODEN_DOOR");
        setEmotesDouble("Feelings.Morder.Sounds.Sound1.Volume", 2.0);
        setEmotesDouble("Feelings.Morder.Sounds.Sound1.Pitch", 2.0);
        setEmotes("Feelings.Morder.Sounds.Sound2.Name", "None");
        setEmotesDouble("Feelings.Morder.Sounds.Sound2.Volume", 0.0);
        setEmotesDouble("Feelings.Morder.Sounds.Sound2.Pitch", 0.0);

        setEmotesBoolean("Feelings.Golpear.Enable", true);
        setEmotes("Feelings.Golpear.Msgs.Sender", "&7Asestas un puñetazo a &c&l%player% &r&7. ¡Auch!");
        setEmotes("Feelings.Golpear.Msgs.Target", "&c&l%player% &r&7te asesta un puñetazo. ¡Auch!");
        setEmotes("Feelings.Golpear.Msgs.Global", "&c&l%sender% &r&7golpeó en la cara a &4&l%target%&r&7.");
        //	setEmotes("Feelings.Golpear.Msgs.Everyone", "&c&l%player% &r&7golpea en la cara a todos.");
        setEmotes("Feelings.Golpear.Sounds.Sound1.Name", "ENTITY.IRON_GOLEM.ATTACK");
        setEmotesDouble("Feelings.Golpear.Sounds.Sound1.Volume", 2.0);
        setEmotesDouble("Feelings.Golpear.Sounds.Sound1.Pitch", 0.6);
        setEmotes("Feelings.Golpear.Sounds.Sound2.Name", "None");
        setEmotesDouble("Feelings.Golpear.Sounds.Sound2.Volume", 0.0);
        setEmotesDouble("Feelings.Golpear.Sounds.Sound2.Pitch", 0.0);

        setEmotesBoolean("Feelings.Tetazo.Enable", true);
        setEmotes("Feelings.Tetazo.Msgs.Sender", "&7Le sueltas un tetazo épico a &a&l%player%&r&7.");
        setEmotes("Feelings.Tetazo.Msgs.Target", "&a&l%player% &r&7te suelta un tetazo épico.");
        setEmotes("Feelings.Tetazo.Msgs.Global", "&a&l%sender% &r&7soltó un tetazo épico a &2&l%target%&r&7.");
        setEmotes("Feelings.Tetazo.Sounds.Sound1.Name", "ENTITY.IRON_GOLEM.ATTACK");
        setEmotesDouble("Feelings.Tetazo.Sounds.Sound1.Volume", 2.0);
        setEmotesDouble("Feelings.Tetazo.Sounds.Sound1.Pitch", 0.6);
        setEmotes("Feelings.Tetazo.Sounds.Sound2.Name", "None");
        setEmotesDouble("Feelings.Tetazo.Sounds.Sound2.Volume", 0.0);
        setEmotesDouble("Feelings.Tetazo.Sounds.Sound2.Pitch", 0.0);

        setEmotesBoolean("Feelings.Asesinar.Enable", true);
        setEmotes("Feelings.Asesinar.Msgs.Sender", "&7Asesinas a &c&l%player% &r&7sin remordimientos.");
        setEmotes("Feelings.Asesinar.Msgs.Target", "&c&l%player% &r&7te acaba de asesinar. ¿Alguien tiene curitas?");
        setEmotes("Feelings.Asesinar.Msgs.Global", "&c&l%sender% &r&7acaba de asesinar a &4&l%target%&r&7. &7&lRIP");
        setEmotes("Feelings.Asesinar.Sounds.Sound1.Name", "ENTITY.BLAZE.DEATH");
        setEmotesDouble("Feelings.Asesinar.Sounds.Sound1.Volume", 1.0);
        setEmotesDouble("Feelings.Asesinar.Sounds.Sound1.Pitch", 0.7);
        setEmotes("Feelings.Asesinar.Sounds.Sound2.Name", "None");
        setEmotesDouble("Feelings.Asesinar.Sounds.Sound2.Volume", 0.0);
        setEmotesDouble("Feelings.Asesinar.Sounds.Sound2.Pitch", 0.0);

        setEmotesBoolean("Feelings.Oye.Enable", true);
        setEmotes("Feelings.Oye.Msgs.Sender", "&7Le sueltas a &e&l%player%&r&7 un &6&l¡oye!");
        setEmotes("Feelings.Oye.Msgs.Target", "&e&l%player% &r&7te lanza un &6&l¡oye!");
        setEmotes("Feelings.Oye.Msgs.Global", "&e&l%sender% &r&7le lanza a &6&l%target%&r&7 un &6&l&o¡oye!");
        setEmotes("Feelings.Oye.Sounds.Sound1.Name", "ENTITY.CHICKEN.EGG");
        setEmotesDouble("Feelings.Oye.Sounds.Sound1.Volume", 2.0);
        setEmotesDouble("Feelings.Oye.Sounds.Sound1.Pitch", 0.1);
        setEmotes("Feelings.Oye.Sounds.Sound2.Name", "None");
        setEmotesDouble("Feelings.Oye.Sounds.Sound2.Volume", 0.0);
        setEmotesDouble("Feelings.Oye.Sounds.Sound2.Pitch", 0.0);

        setEmotesBoolean("Feelings.Dabear.Enable", true);
        setEmotes("Feelings.Dabear.Msgs.Sender", "&7Le haces un dab fresquísimo a &a&l%player%&r&7... &7&o¡Toma!");
        setEmotes("Feelings.Dabear.Msgs.Target", "&a&l%player% &r&7te lanza un dab fresquísimo... &7&o¡Toma!");
        setEmotes("Feelings.Dabear.Msgs.Global", "&a&l%sender% &r&7le lanza un dab fresquísimo a &2&l%target%&r&7... &7&o¡Toma!");
        setEmotes("Feelings.Dabear.Sounds.Sound1.Name", "ENTITY.CHICKEN.EGG");
        setEmotesDouble("Feelings.Dabear.Sounds.Sound1.Volume", 2.0);
        setEmotesDouble("Feelings.Dabear.Sounds.Sound1.Pitch", 0.1);
        setEmotes("Feelings.Dabear.Sounds.Sound2.Name", "None");
        setEmotesDouble("Feelings.Dabear.Sounds.Sound2.Volume", 0.0);
        setEmotesDouble("Feelings.Dabear.Sounds.Sound2.Pitch", 0.0);

        setEmotesBoolean("Feelings.Llorar.Enable", true);
        setEmotes("Feelings.Llorar.Msgs.Sender", "&7Lloras sobre el hombro de &b&l%player%&r&7.");
        setEmotes("Feelings.Llorar.Msgs.Target", "&b&l%player% &r&7llora sobre tu hombro.");
        setEmotes("Feelings.Llorar.Msgs.Global", "&b&l%sender% &r&7se apoya en el hombro de &3&l%target%&r&7 y llora.");
        setEmotes("Feelings.Llorar.Sounds.Sound1.Name", "ENTITY.GHAST.DEATH");
        setEmotesDouble("Feelings.Llorar.Sounds.Sound1.Volume", 1.0);
        setEmotesDouble("Feelings.Llorar.Sounds.Sound1.Pitch", 0.8);
        setEmotes("Feelings.Llorar.Sounds.Sound2.Name", "None");
        setEmotesDouble("Feelings.Llorar.Sounds.Sound2.Volume", 0.0);
        setEmotesDouble("Feelings.Llorar.Sounds.Sound2.Pitch", 0.0);

        setEmotesBoolean("Feelings.Palmada.Enable", true);
        setEmotes("Feelings.Palmada.Msgs.Sender", "&7Te llevas la mano a la cara por lo que &e&l%player% &r&7acaba de decir.");
        setEmotes("Feelings.Palmada.Msgs.Target", "&e&l%player% &r&7se llevó la mano a la cara por lo que acabas de decir.");
        setEmotes("Feelings.Palmada.Msgs.Global", "&e&l%sender% &r&7se lleva la mano a la cara por la tontería de &6&l%target%&r&7.");
        setEmotes("Feelings.Palmada.Sounds.Sound1.Name", "ENTITY.VILLAGER.NO");
        setEmotesDouble("Feelings.Palmada.Sounds.Sound1.Volume", 2.0);
        setEmotesDouble("Feelings.Palmada.Sounds.Sound1.Pitch", 1.0);
        setEmotes("Feelings.Palmada.Sounds.Sound2.Name", "None");
        setEmotesDouble("Feelings.Palmada.Sounds.Sound2.Volume", 0.0);
        setEmotesDouble("Feelings.Palmada.Sounds.Sound2.Pitch", 0.0);

        // need pitch & volume values:

        setEmotesBoolean("Feelings.Chocala.Enable", true);
        setEmotes("Feelings.Chocala.Msgs.Sender", "&7Le das un poderoso chócala a &a&l%player%&7.");
        setEmotes("Feelings.Chocala.Msgs.Target", "&a&l%player% &7te da un poderoso chócala.");
        setEmotes("Feelings.Chocala.Msgs.Global", "&a&l%sender% &7le da a &2&l%target% &r&7un poderoso chócala.");
        setEmotes("Feelings.Chocala.Sounds.Sound1.Name", "ENTITY.VILLAGER.YES");
        setEmotesDouble("Feelings.Chocala.Sounds.Sound1.Volume", 2.0);
        setEmotesDouble("Feelings.Chocala.Sounds.Sound1.Pitch", 1.0);
        setEmotes("Feelings.Chocala.Sounds.Sound2.Name", "None");
        setEmotesDouble("Feelings.Chocala.Sounds.Sound2.Volume", 0.0);
        setEmotesDouble("Feelings.Chocala.Sounds.Sound2.Pitch", 0.0);

        setEmotesBoolean("Feelings.Besar.Enable", true);
        setEmotes("Feelings.Besar.Msgs.Sender", "&7Le das un beso a &a&l%player%&r&7. &cAwww &4❤");
        setEmotes("Feelings.Besar.Msgs.Target", "&a&l%player% &r&7te da un beso. &cAwww &4❤");
        setEmotes("Feelings.Besar.Msgs.Global", "&a&l%sender% &7le da un beso a &2&l%target%&7. &cAwww &4❤");
        setEmotes("Feelings.Besar.Sounds.Sound1.Name", "ENTITY.ARROW.HIT_PLAYER");
        setEmotesDouble("Feelings.Besar.Sounds.Sound1.Volume", 2.0);
        setEmotesDouble("Feelings.Besar.Sounds.Sound1.Pitch", 1.0);
        setEmotes("Feelings.Besar.Sounds.Sound2.Name", "None");
        setEmotesDouble("Feelings.Besar.Sounds.Sound2.Volume", 0.0);
        setEmotesDouble("Feelings.Besar.Sounds.Sound2.Pitch", 0.0);

        setEmotesBoolean("Feelings.Lamer.Enable", true);
        setEmotes("Feelings.Lamer.Msgs.Sender", "&7Lames a &e&l%player% &7como si fuera helado. &6¡Qué asco!");
        setEmotes("Feelings.Lamer.Msgs.Target", "&e&l%player% &r&7te lame como si fueras helado. &6¡Qué asco!");
        setEmotes("Feelings.Lamer.Msgs.Global", "&e&l%target% &r&7fue lamido por &6&l%sender%&r&7. &8Qué asco.");
        setEmotes("Feelings.Lamer.Sounds.Sound1.Name", "ENTITY.GENERIC.DRINK");
        setEmotesDouble("Feelings.Lamer.Sounds.Sound1.Volume", 2.0);
        setEmotesDouble("Feelings.Lamer.Sounds.Sound1.Pitch", 0.1); // not sure
        setEmotes("Feelings.Lamer.Sounds.Sound2.Name", "None");
        setEmotesDouble("Feelings.Lamer.Sounds.Sound2.Volume", 0.0);
        setEmotesDouble("Feelings.Lamer.Sounds.Sound2.Pitch", 0.0);

        setEmotesBoolean("Feelings.Sacudir.Enable", true);
        setEmotes("Feelings.Sacudir.Msgs.Sender", "&7Sacudes todo el cuerpo de &c&l%player%&r&7.");
        setEmotes("Feelings.Sacudir.Msgs.Target", "&c&l%player% &r&7sacude todo tu cuerpo.");
        setEmotes("Feelings.Sacudir.Msgs.Global", "&c&l%sender% &r&7levanta a &4&l%target%&r&7 y lo sacude.");
        setEmotes("Feelings.Sacudir.Sounds.Sound1.Name", "ENTITY.WOLF.SHAKE");
        setEmotesDouble("Feelings.Sacudir.Sounds.Sound1.Volume", 2.0);
        setEmotesDouble("Feelings.Sacudir.Sounds.Sound1.Pitch", 0.7); // not sure
        setEmotes("Feelings.Sacudir.Sounds.Sound2.Name", "None");
        setEmotesDouble("Feelings.Sacudir.Sounds.Sound2.Volume", 0.0);
        setEmotesDouble("Feelings.Sacudir.Sounds.Sound2.Pitch", 0.0);

        setEmotesBoolean("Feelings.Acurrucar.Enable", true);
        setEmotes("Feelings.Acurrucar.Msgs.Sender", "&7Te acurrucas con &a&l%player% &r&7con mucho amor. &cAwww &4❤");
        setEmotes("Feelings.Acurrucar.Msgs.Target", "&a&l%player% &r&7se acurruca contigo con amor. &cAwww &4❤");
        setEmotes("Feelings.Acurrucar.Msgs.Global", "&a&l%sender% &r&7se acurruca con &2&l%target% &r&7entre abrazos. &cAwww &4❤");
        setEmotes("Feelings.Acurrucar.Sounds.Sound1.Name", "ENTITY.CAT.PURR");
        setEmotesDouble("Feelings.Acurrucar.Sounds.Sound1.Volume", 2.0);
        setEmotesDouble("Feelings.Acurrucar.Sounds.Sound1.Pitch", 1.0); // not sure
        setEmotes("Feelings.Acurrucar.Sounds.Sound2.Name", "None");
        setEmotesDouble("Feelings.Acurrucar.Sounds.Sound2.Volume", 0.0);
        setEmotesDouble("Feelings.Acurrucar.Sounds.Sound2.Pitch", 0.0);

        setEmotesBoolean("Feelings.Gritar.Enable", true);
        setEmotes("Feelings.Gritar.Msgs.Sender", "&7Le gritas a &c&l%player%&r&7 con todas tus fuerzas.");
        setEmotes("Feelings.Gritar.Msgs.Target", "&c&l%player% &r&7te grita con todas sus fuerzas.");
        setEmotes("Feelings.Gritar.Msgs.Global", "&c&l%sender% &r&7le grita directamente a &4&l%target% &r&7 con todas sus fuerzas.");
        setEmotes("Feelings.Gritar.Sounds.Sound1.Name", "ENTITY.GHAST.SCREAM");
        setEmotesDouble("Feelings.Gritar.Sounds.Sound1.Volume", 2.0);
        setEmotesDouble("Feelings.Gritar.Sounds.Sound1.Pitch", 1.0); // not sure
        setEmotes("Feelings.Gritar.Sounds.Sound2.Name", "None");
        setEmotesDouble("Feelings.Gritar.Sounds.Sound2.Volume", 0.0);
        setEmotesDouble("Feelings.Gritar.Sounds.Sound2.Pitch", 0.0);

        setEmotesBoolean("Feelings.Picar.Enable", true);
        setEmotes("Feelings.Picar.Msgs.Sender", "&7Pinchaste a &e&l%player%&7. ¿Será que está de vacaciones?");
        setEmotes("Feelings.Picar.Msgs.Target", "&e&l%player% &r&7te ha pinchado. ¿Hay alguien ahí?");
        setEmotes("Feelings.Picar.Msgs.Global", "&e&l%target% &r&7fue pinchado por &6&l%sender%&r&7. &7&o¿Hay alguien en casa?");
        setEmotes("Feelings.Picar.Sounds.Sound1.Name", "ENTITY.CHICKEN.EGG");
        setEmotesDouble("Feelings.Picar.Sounds.Sound1.Volume", 2.0);
        setEmotesDouble("Feelings.Picar.Sounds.Sound1.Pitch", 0.1); // not sure
        setEmotes("Feelings.Picar.Sounds.Sound2.Name", "None");
        setEmotesDouble("Feelings.Picar.Sounds.Sound2.Volume", 0.0);
        setEmotesDouble("Feelings.Picar.Sounds.Sound2.Pitch", 0.0);

        setEmotesBoolean("Feelings.Bofetada.Enable", true);
        setEmotes("Feelings.Bofetada.Msgs.Sender", "&7Le das una bofetada con espaguetis a &c&l%player%&r&7.");
        setEmotes("Feelings.Bofetada.Msgs.Target", "&c&l%player% &r&7te da una bofetada con espaguetis.");
        setEmotes("Feelings.Bofetada.Msgs.Global", "&c&l%target% &r&7recibió una bofetada de &4&l%sender%&r&7.");
        setEmotes("Feelings.Bofetada.Sounds.Sound1.Name", "ENTITY.BLAZE.HURT");
        setEmotesDouble("Feelings.Bofetada.Sounds.Sound1.Volume", 2.0);
        setEmotesDouble("Feelings.Bofetada.Sounds.Sound1.Pitch", 0.7); // not sure
        setEmotes("Feelings.Bofetada.Sounds.Sound2.Name", "None");
        setEmotesDouble("Feelings.Bofetada.Sounds.Sound2.Volume", 0.0);
        setEmotesDouble("Feelings.Bofetada.Sounds.Sound2.Pitch", 0.0);

        setEmotesBoolean("Feelings.Acuchillar.Enable", true);
        setEmotes("Feelings.Acuchillar.Msgs.Sender", "&7Apuñalas a &c&l%player% &r&7con un cuchillo. ¿Tienes curitas?");
        setEmotes("Feelings.Acuchillar.Msgs.Target", "&c&l%player% &r&7toma un cuchillo y te apuñala. ¿Tienes curitas?");
        setEmotes("Feelings.Acuchillar.Msgs.Global", "&c&l%sender% &r&7toma un cuchillo y apuñala a &4&l%target%&r&7.");
        setEmotes("Feelings.Acuchillar.Sounds.Sound1.Name", "ENTITY.GENERIC.HURT");
        setEmotesDouble("Feelings.Acuchillar.Sounds.Sound1.Volume", 2.0);
        setEmotesDouble("Feelings.Acuchillar.Sounds.Sound1.Pitch", 0.7); // not sure
        setEmotes("Feelings.Acuchillar.Sounds.Sound2.Name", "None");
        setEmotesDouble("Feelings.Acuchillar.Sounds.Sound2.Volume", 0.0);
        setEmotesDouble("Feelings.Acuchillar.Sounds.Sound2.Pitch", 0.0);

        setEmotesBoolean("Feelings.Punalada.Enable", true);
        setEmotes("Feelings.Punalada.Msgs.Sender", "&7Asestas una puñalada certera a &c&l%player%&r&7.");
        setEmotes("Feelings.Punalada.Msgs.Target", "&c&l%player% &r&7te asesta una puñalada certera.");
        setEmotes("Feelings.Punalada.Msgs.Global", "&c&l%sender% &r&7asestó una puñalada certera a &4&l%target%&r&7.");
        setEmotes("Feelings.Punalada.Sounds.Sound1.Name", "ENTITY.GENERIC.HURT");
        setEmotesDouble("Feelings.Punalada.Sounds.Sound1.Volume", 2.0);
        setEmotesDouble("Feelings.Punalada.Sounds.Sound1.Pitch", 0.7);
        setEmotes("Feelings.Punalada.Sounds.Sound2.Name", "None");
        setEmotesDouble("Feelings.Punalada.Sounds.Sound2.Volume", 0.0);
        setEmotesDouble("Feelings.Punalada.Sounds.Sound2.Pitch", 0.0);

        setEmotesBoolean("Feelings.Acariciar.Enable", true);
        setEmotes("Feelings.Acariciar.Msgs.Sender", "&7Acaricias suavemente la cabeza de &a&l%player%&r&7 por portarse bien.");
        setEmotes("Feelings.Acariciar.Msgs.Target", "&a&l%player% &r&7acaricia suavemente tu cabeza por portarte bien.");
        setEmotes("Feelings.Acariciar.Msgs.Global", "&a&l%sender% &r&7acaricia suavemente la cabeza de &2&l%target%&r&7 por portarse bien.");
        setEmotes("Feelings.Acariciar.Sounds.Sound1.Name", "ENTITY.WOLF.PANT");
        setEmotesDouble("Feelings.Acariciar.Sounds.Sound1.Volume", 2.0);
        setEmotesDouble("Feelings.Acariciar.Sounds.Sound1.Pitch", 0.8); // not sure
        setEmotes("Feelings.Acariciar.Sounds.Sound2.Name", "None");
        setEmotesDouble("Feelings.Acariciar.Sounds.Sound2.Volume", 0.0);
        setEmotesDouble("Feelings.Acariciar.Sounds.Sound2.Pitch", 0.0);

        setEmotesBoolean("Feelings.Despreciar.Enable", true);
        setEmotes("Feelings.Despreciar.Msgs.Sender", "&7Desprecias a &c&l%player% &r&7por lo que ha hecho.");
        setEmotes("Feelings.Despreciar.Msgs.Target", "&c&l%player% &r&7te desprecia por lo que hiciste.");
        setEmotes("Feelings.Despreciar.Msgs.Global", "&c&l%sender% &r&7desprecia a &4&l%target% &r&7por lo que hizo.");
        setEmotes("Feelings.Despreciar.Sounds.Sound1.Name", "ENTITY.ENDERMAN.STARE");
        setEmotesDouble("Feelings.Despreciar.Sounds.Sound1.Volume", 2.0);
        setEmotesDouble("Feelings.Despreciar.Sounds.Sound1.Pitch", 0.8); // not sure
        setEmotes("Feelings.Despreciar.Sounds.Sound2.Name", "None");
        setEmotesDouble("Feelings.Despreciar.Sounds.Sound2.Volume", 0.0);
        setEmotesDouble("Feelings.Despreciar.Sounds.Sound2.Pitch", 0.0);

        setEmotesBoolean("Feelings.Acechar.Enable", true);
        setEmotes("Feelings.Acechar.Msgs.Sender", "&7Acechas con cuidado a &e&l%player%&r&7. &7&oJe je.");
        setEmotes("Feelings.Acechar.Msgs.Target", "&e&l%player% &r&7te acecha desde un árbol cercano.");
        setEmotes("Feelings.Acechar.Msgs.Global", "&e&l%sender% &r&7acecha a &6&l%target% &r&7desde un árbol cercano.");
        setEmotes("Feelings.Acechar.Sounds.Sound1.Name", "AMBIENT.CAVE");
        setEmotesDouble("Feelings.Acechar.Sounds.Sound1.Volume", 2.0);
        setEmotesDouble("Feelings.Acechar.Sounds.Sound1.Pitch", 2.0); // not sure
        setEmotes("Feelings.Acechar.Sounds.Sound2.Name", "None");
        setEmotesDouble("Feelings.Acechar.Sounds.Sound2.Volume", 0.0);
        setEmotesDouble("Feelings.Acechar.Sounds.Sound2.Pitch", 0.0);

        setEmotesBoolean("Feelings.Sospechar.Enable", true);
        setEmotes("Feelings.Sospechar.Msgs.Sender", "&7Miras con sospecha el cuerpo de un solo hueso de &e&l%player%&r&7.");
        setEmotes("Feelings.Sospechar.Msgs.Target", "&e&l%player% &r&7mira con sospecha tu cuerpo de un solo hueso.");
        setEmotes("Feelings.Sospechar.Msgs.Global", "&e&l%sender% &r&7mira a &6&l%target% &r&7con sospecha de un solo hueso.");

        if(validSound("AMBIENT_NETHER_WASTES_MOOD")) {
            setEmotes("Feelings.Sospechar.Sounds.Sound1.Name", "AMBIENT.NETHER_WASTES.MOOD");
        } else {
            setEmotes("Feelings.Sospechar.Sounds.Sound1.Name", "AMBIENT.CAVE");
        }

        setEmotesDouble("Feelings.Sospechar.Sounds.Sound1.Volume", 2.0);
        setEmotesDouble("Feelings.Sospechar.Sounds.Sound1.Pitch", 1.2);

        if(validSound("BLOCK_RESPAWN_ANCHOR_DEPLETE")) {
            setEmotes("Feelings.Sospechar.Sounds.Sound2.Name", "BLOCK.RESPAWN_ANCHOR.DEPLETE");
        } else {
            setEmotes("Feelings.Sospechar.Sounds.Sound2.Name", "None");
        }
        setEmotesDouble("Feelings.Sospechar.Sounds.Sound2.Volume", 0.25);
        setEmotesDouble("Feelings.Sospechar.Sounds.Sound2.Pitch", 0.1);

        setEmotesBoolean("Feelings.Saludar.Enable", true);
        setEmotes("Feelings.Saludar.Msgs.Sender", "&7Te despides agitando la mano a &a&l%player%&r&7.");
        setEmotes("Feelings.Saludar.Msgs.Target", "&a&l%player% &r&7se despide agitando la mano hacia ti.");
        setEmotes("Feelings.Saludar.Msgs.Global", "&a&l%sender% &r&7se despide agitando la mano a &2&l%target%.");

        if(validSound("BLOCK_AMETHYST_BLOCK_RESONATE")) {
            setEmotes("Feelings.Saludar.Sounds.Sound1.Name", "BLOCK.AMETHYST_BLOCK.RESONATE");
        } else {
            setEmotes("Feelings.Saludar.Sounds.Sound1.Name", "BLOCK.NOTE_BLOCK.BELL");
        }

        setEmotesDouble("Feelings.Saludar.Sounds.Sound1.Volume", 2.0);
        setEmotesDouble("Feelings.Saludar.Sounds.Sound1.Pitch", 2.0);

        setEmotes("Feelings.Saludar.Sounds.Sound2.Name", "None");
        setEmotesDouble("Feelings.Saludar.Sounds.Sound2.Volume", 0.0);
        setEmotesDouble("Feelings.Saludar.Sounds.Sound2.Pitch", 0.0);

        setEmotesBoolean("Feelings.Bienvenido.Enable", true);
        setEmotes("Feelings.Bienvenido.Msgs.Sender", "&7Le dijiste a &a&l%player%&r &7¡bienvenido de vuelta!");
        setEmotes("Feelings.Bienvenido.Msgs.Target", "&a&l%player% &r&7te dio una cálida bienvenida de vuelta.");
        setEmotes("Feelings.Bienvenido.Msgs.Global", "&a&l%sender% &r&7le dio la bienvenida de vuelta a &2&l%target%&r&7.");
        setEmotes("Feelings.Bienvenido.Sounds.Sound1.Name", "BLOCK.BEACON.POWER_SELECT");
        setEmotesDouble("Feelings.Bienvenido.Sounds.Sound1.Volume", 2.0);
        setEmotesDouble("Feelings.Bienvenido.Sounds.Sound1.Pitch", 2.0);
        setEmotes("Feelings.Bienvenido.Sounds.Sound2.Name", "None");
        setEmotesDouble("Feelings.Bienvenido.Sounds.Sound2.Volume", 0.0);
        setEmotesDouble("Feelings.Bienvenido.Sounds.Sound2.Pitch", 0.0);


        setEmotesBoolean("Feelings.Tocar.Enable", true);
        setEmotes("Feelings.Tocar.Msgs.Sender", "&7Le tocas la nariz a &a&l%player%&7.");
        setEmotes("Feelings.Tocar.Msgs.Target", "&a&l%player% &r&7te toca la nariz.");
        setEmotes("Feelings.Tocar.Msgs.Global", "&a&l%target% &r&7recibió un toque en la nariz de &a&l%sender%&r&7.");
        setEmotes("Feelings.Tocar.Sounds.Sound1.Name", "ENTITY.CHICKEN.EGG");
        setEmotesDouble("Feelings.Tocar.Sounds.Sound1.Volume", 2.0);
        setEmotesDouble("Feelings.Tocar.Sounds.Sound1.Pitch", 2.0);
        setEmotes("Feelings.Tocar.Sounds.Sound2.Name", "None");
        setEmotesDouble("Feelings.Tocar.Sounds.Sound2.Volume", 0.0);
        setEmotesDouble("Feelings.Tocar.Sounds.Sound2.Pitch", 0.0);

        setEmotesVersion(8);
        reloadFiles();
    }

    static void reloadFiles() {
        plugin.folder = getFolder();
        plugin.msgsfile = new File(plugin.folder, File.separator + "messages.yml");
        plugin.msg = YamlConfiguration.loadConfiguration(plugin.msgsfile);

        plugin.emotesfile = new File(plugin.folder, File.separator + "emotes.yml");
        plugin.emotes = YamlConfiguration.loadConfiguration(plugin.emotesfile);
    }
}