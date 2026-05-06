package xyz.iwolfking.woldsvaults.events;

import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ServerWatchdog {

    private static final long TIMEOUT_MS = 60000;

    private static volatile long lastTick = System.currentTimeMillis();
    private static boolean started = false;

    private static final ScheduledExecutorService EXECUTOR =
            Executors.newSingleThreadScheduledExecutor(r -> {
                Thread t = new Thread(r, "Server-Watchdog");
                t.setDaemon(true);
                return t;
            });

    private static void startWatchdog() {
        if (started) return;
        started = true;

        EXECUTOR.scheduleAtFixedRate(() -> {
            long now = System.currentTimeMillis();

            if (now - lastTick > TIMEOUT_MS) {
                System.err.println("[Watchwolf] Server hang detected! Forcing shutdown...");
                Runtime.getRuntime().halt(1);
            }
        }, 5, 5, TimeUnit.SECONDS);
    }

    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            lastTick = System.currentTimeMillis();
            startWatchdog();
        }
    }
}