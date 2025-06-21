package fr.solmey.clienthings.util;

import fr.solmey.clienthings.config.Config;
import net.minecraft.sound.SoundEvent;

public class Sounds {


    public static final int CONSUMABLES = 0;
    public static final int FIREWORK = 1;

    private static long[] timestamps = new long[256];
    private static double[][] coordinates = new double[256][3];
    private static SoundEvent[] sounds = new SoundEvent[256];
    private static int[] category = new int[256];

    public static void set(long _timestamp, double _X, double _Y, double _Z, SoundEvent _sound, int _category) {
        int cursor = 0;
        clear();
        for(int i = 0; i < 256 ; i++)
            if(timestamps[i] == 0)
                cursor = i;

        timestamps[cursor] = _timestamp;
        coordinates[cursor][0] = _X;
        coordinates[cursor][1] = _Y;
        coordinates[cursor][2] = _Z;
        sounds[cursor] = _sound;
        category[cursor] = _category;
    }

    public static void clear() {
       for(int i = 0; i < 256 ; i++) {
            double MaxTime;
            switch(category[i]) {
                case CONSUMABLES:
                    MaxTime = Config.consumables_MaxTime;
                    break;
                case FIREWORK:
                    MaxTime = Config.firework_MaxTime;
                    break;
                default:
                    MaxTime = 3200;
                    break;
            }
            if(System.currentTimeMillis() - timestamps[i] >= MaxTime)
                timestamps[i] = 0;
        }
    }

    public static boolean needToCancel(SoundEvent sound, double posX, double posY, double posZ) {
        clear();
        int cursor = 0;
        boolean needed = false;
        double distance = 0;
        for(int i = 0; i < 256 ; i++)
            if(timestamps[i] >= timestamps[cursor])
                cursor = i;
        
        for(int i = 0; i < 256 ; i++) {
            if(sounds[i] != null) {
                distance = Math.sqrt(
                    Math.pow(coordinates[i][0] - posX, 2) +
                    Math.pow(coordinates[i][1] - posY, 2) +
                    Math.pow(coordinates[i][2] - posZ, 2)
                );
                double MaxDistance;
                switch(category[i]) {
                    case CONSUMABLES: 
                        MaxDistance = Config.consumables_MaxDistance;
                        break;
                    case FIREWORK:
                        MaxDistance = Config.firework_MaxDistance;
                        break;
                    default:
                        MaxDistance = 2.0;
                        break;
                }
                if(distance <= MaxDistance && sound.equals(sounds[i]) && timestamps[i] <= timestamps[cursor] && timestamps[i] != 0) {
                    cursor = i;
                    needed = true;
                }
            }
        }
        if(needed == true)
            timestamps[cursor] = 0;

        return needed;
    }
}