package com.cenla.models;
import com.cenla.simulations.Simulation;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class EcosystemTimer {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public void startUpdating(Simulation simulation) {
        scheduler.scheduleAtFixedRate(simulation::update, 0, 15, TimeUnit.SECONDS);
    }

    public void stopUpdating() {
        scheduler.shutdown();
        try {
            if (!scheduler.awaitTermination(60, TimeUnit.SECONDS)) {
                scheduler.shutdownNow();
            }
        } catch (InterruptedException e) {
            scheduler.shutdownNow();
        }
    }

}
