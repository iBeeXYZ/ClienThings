package fr.solmey.clienthings.config;

import java.io.*;
import java.util.Properties;

public class Config {
    private static final File configFile = new File("config/clienthings.properties");

    
    public static boolean consumables = true;               // Enables or disables consumable optimization
    public static double consumables_MaxDistance = 0.40;    // Maximum distance between the end of consumption sound location between the client and the server
    public static long consumables_MaxTime = 6400;          // Maximum time before canceling end of consumption sounds in ms
    public static boolean cooldowns = true;                 // Enables or disables cooldowns optimization
    public static boolean crystals = true;                  // Enables or disables cooldowns optimization
    public static long crystals_MaxTime = 800;              // Maximum time before canceling in ms
    public static boolean debugMode = false;                // Debug mode (for developers)
    public static boolean elytras = true;                   // Enables or disables elytra optimization
    public static boolean firework = true;                  // Enables or disables firework optimization
    public static double firework_MaxDistance = 3.0;        // Maximum distance between the firework sound location between the client and the server
    public static long firework_MaxTime = 3200;             // Maximum time before canceling in ms
    public static boolean pose = true;                      // Enables or disables pose optimization
    //public static boolean falldamageExperimental = false; // Enables or disables the experimental fall damage optimization
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
                crystals = Boolean.parseBoolean(props.getProperty("crystals", String.valueOf(crystals)));
                crystals_MaxTime = Long.parseLong(props.getProperty("crystals_MaxTime", String.valueOf(crystals_MaxTime)));
                debugMode = Boolean.parseBoolean(props.getProperty("debugMode", String.valueOf(debugMode)));
                elytras = Boolean.parseBoolean(props.getProperty("elytras", String.valueOf(elytras)));
                firework = Boolean.parseBoolean(props.getProperty("firework", String.valueOf(firework)));
                firework_MaxDistance = Double.parseDouble(props.getProperty("firework_MaxDistance", String.valueOf(firework_MaxDistance)));
                firework_MaxTime = Long.parseLong(props.getProperty("firework_MaxTime", String.valueOf(firework_MaxTime)));
                pose = Boolean.parseBoolean(props.getProperty("pose", String.valueOf(pose)));
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
        props.setProperty("crystals", String.valueOf(crystals));
        props.setProperty("crystals_MaxTime", String.valueOf(crystals_MaxTime));
        props.setProperty("debugMode", String.valueOf(debugMode));
        props.setProperty("elytras", String.valueOf(elytras));
        props.setProperty("firework", String.valueOf(firework));
        props.setProperty("firework_MaxDistance", String.valueOf(firework_MaxDistance));
        props.setProperty("firework_MaxTime", String.valueOf(firework_MaxTime));
        props.setProperty("pose", String.valueOf(pose));
        //props.setProperty("falldamageExperimental", String.valueOf(falldamageExperimental));
        props.setProperty("optout", String.valueOf(optout));

        try {
            configFile.getParentFile().mkdirs();
            try (OutputStream output = new FileOutputStream(configFile)) {
                String comment = "consumables: Enables or disables consumable optimization\n"
                + "consumables_MaxDistance: Maximum distance (in blocks) between the client and the server for the end-of-consumption sound(s) to be ignored\n"
                + "consumables_MaxTime: Maximum time (in ms) the server has to play the end-of-consumption sound(s) to be ignored\n"
                + "cooldowns: Enables or disables cooldowns optimization\n"
                + "crystals: Enables or disables crystals optimization\n"
                + "crystals_MaxTime: Maximum time (in ms) the server has to send crystals for the optimization to work\n"
                + "debugMode: Debug mode (for developers)\n"
                + "elytras: Enables or disables elytras optimization\n"
                + "firework: Enables or disables firework optimization\n"
                + "firework_MaxDistance: Maximum distance (in blocks) between the client and the server for the firework sound to be ignored\n"
                + "firework_MaxTime: Maximum time (in ms) the server has to delete the firework\n"
                + "pose: Enables or disables pose optimization\n"
                //+ "falldamageExperimental: Enables or disables the experimental fall damage optimization\n"
                + "optout: Enables or disables the opt-out";
                props.store(output, comment);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}