package fr.solmey.clienthings.util;

import fr.solmey.clienthings.config.Config;
import fr.solmey.clienthings.mixin.firework.FireworkRocketEntityAccessor;

import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.FireworkRocketEntity;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;

public class Firework {

    private static long[] timestamps = new long[256];
    public static ProjectileEntity[] projectiles = new ProjectileEntity[256];

    public static void set(long _timestamp, ProjectileEntity _projectiles) {
        clear();
        int cursor = 0;
        for(int i = 0; i < 256 ; i++)
            if(timestamps[i] == 0)
                cursor = i;

        timestamps[cursor] = _timestamp;
        projectiles[cursor] = _projectiles;
    }

    private static void clear() {
        for(int i = 0; i < 256 ; i++)
            if(System.currentTimeMillis() - timestamps[i] >= Config.firework_MaxTime)
                timestamps[i] = 0;
    }

    public static void tick(FireworkRocketEntity _fireworkEntity) {
        FireworkRocketEntityAccessor accessor = (FireworkRocketEntityAccessor) _fireworkEntity;
        if(accessor.getLife() > accessor.getLifeTime() && accessor.getLifeTime() != 0)
            _fireworkEntity.discard();  //fireworkEntity.explodeAndRemove(world); in vanilla but doesn't work with
    }

    public static boolean needToCancel(EntitySpawnS2CPacket packet) {
        clear();
        int cursor = 0;
        boolean needed = false;
        double distance = 0;
        for(int i = 0; i < 256 ; i++)
            if(timestamps[i] >= timestamps[cursor])
                cursor = i;

        for(int i = 0; i < 256 ; i++) {
            if(projectiles[i] != null) {
                if(timestamps[i] <= timestamps[cursor] && timestamps[i] != 0
                && packet.getX() == projectiles[i].getX()
                && packet.getY() == projectiles[i].getY()
                && packet.getZ() == projectiles[i].getZ()
                && packet.getYaw() == projectiles[i].getYaw()
                && packet.getPitch() == projectiles[i].getPitch()
                //&& distance <= Config.firework_MaxDistance // cuz the distance is 0
                && packet.getEntityType() == projectiles[i].getType()) {
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