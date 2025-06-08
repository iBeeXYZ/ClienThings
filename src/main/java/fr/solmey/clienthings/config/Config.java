package fr.solmey.clienthings.config;

import java.io.*;
import java.util.Properties;

public class Config {
    private static final File configFile = new File("config/clienthings.properties");

    
    public static boolean consumables = true;               // Enables or disables consumable prediction
    public static double consumables_MaxDistance = 0.30;    // Maximum distance between the end of consumption sound location between the client and the server
    public static long consumables_MaxTime = 3200;          // Maximum time before canceling in ms
    public static boolean cooldowns = true;                  // Enables or disables cooldowns prediction
    public static boolean debugMode = false;                // Debug mode (for developers)
    public static boolean elytras = true;                   // Enables or disables elytra prediction
    public static boolean firework = true;                  // Enables or disables firework prediction
    public static double firework_MaxDistance = 2.0;        // Maximum distance between the firework sound location between the client and the server
    public static long firework_MaxTime = 3200;             // Maximum time before canceling in ms
    //public static boolean falldamageExperimental = false; // Enables or disables the experimental fall damage prediction
    public static boolean optout = true;                    // Enables or disables opt-out 
    // public int consumables_MaxCancels = 256; // Max things to cancel

    public static void loadConfig() {
        Properties props = new Properties();

        if (!configFile.exists()) {
            saveConfig();
        }
        else {
            try (InputStream input = new FileInputStream(configFile)) {
                props.load(input);

                
                consumables = Boolean.parseBoolean(props.getProperty("consumables", String.valueOf(consumables)));
                consumables_MaxDistance = Double.parseDouble(props.getProperty("consumables_MaxDistance", String.valueOf(consumables_MaxDistance)));
                consumables_MaxTime = Long.parseLong(props.getProperty("consumables_MaxTime", String.valueOf(consumables_MaxTime)));
                cooldowns = Boolean.parseBoolean(props.getProperty("cooldowns", String.valueOf(cooldowns)));
                debugMode = Boolean.parseBoolean(props.getProperty("debugMode", String.valueOf(debugMode)));
                elytras = Boolean.parseBoolean(props.getProperty("elytras", String.valueOf(elytras)));
                firework = Boolean.parseBoolean(props.getProperty("firework", String.valueOf(firework)));
                firework_MaxDistance = Double.parseDouble(props.getProperty("firework_MaxDistance", String.valueOf(firework_MaxDistance)));
                firework_MaxTime = Long.parseLong(props.getProperty("firework_MaxTime", String.valueOf(firework_MaxTime)));
                //falldamageExperimental = Boolean.parseBoolean(props.getProperty("falldamageExperimental", String.valueOf(falldamageExperimental)));
                optout = Boolean.parseBoolean(props.getProperty("optout", String.valueOf(optout)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        saveConfig();
    }

    public static void saveConfig() {
        Properties props = new Properties();
        props.setProperty("consumables", String.valueOf(consumables));
        props.setProperty("consumables_MaxDistance", String.valueOf(consumables_MaxDistance));
        props.setProperty("consumables_MaxTime", String.valueOf(consumables_MaxTime));
        props.setProperty("cooldowns", String.valueOf(cooldowns));
        props.setProperty("debugMode", String.valueOf(debugMode));
        props.setProperty("elytras", String.valueOf(elytras));
        props.setProperty("firework", String.valueOf(firework));
        props.setProperty("firework_MaxDistance", String.valueOf(firework_MaxDistance));
        props.setProperty("firework_MaxTime", String.valueOf(firework_MaxTime));
        //props.setProperty("falldamageExperimental", String.valueOf(falldamageExperimental));
        props.setProperty("optout", String.valueOf(optout));

        try {
            configFile.getParentFile().mkdirs();
            try (OutputStream output = new FileOutputStream(configFile)) {
                String comment = "consumables: Enables or disables consumable prediction\n"
                + "consumables_MaxDistance: Maximum distance (in blocks) between the client and the server for the end-of-consumption sound(s) to be ignored\n"
                + "consumables_MaxTime: Maximum time (in ms) the server has to play the end-of-consumption sound(s) to be ignored\n"
                + "cooldowns: Enables or disables cooldowns prediction\n"
                + "debugMode: Debug mode (for developers)\n"
                + "elytras: Enables or disables elytras prediction\n"
                + "firework: Enables or disables firework prediction\n"
                + "firework_MaxDistance: Maximum distance (in blocks) between the client and the server for the firework sound to be ignored\n"
                + "firework_MaxTime: Maximum time (in ms) the server has to delete the firework\n"
                //+ "falldamageExperimental: Enables or disables the experimental fall damage prediction\n"
                + "optout: Enables or disables the opt-out";
                props.store(output, comment);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}