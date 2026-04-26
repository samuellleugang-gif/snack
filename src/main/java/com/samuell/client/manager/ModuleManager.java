package com.samuell.client.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.samuell.client.module.Module;
import com.samuell.client.module.ModuleCategory;
import com.samuell.client.module.impl.*;

public class ModuleManager {
    private final List<Module> modules = new ArrayList<>();
    private final Map<ModuleCategory, List<Module>> categorizedModules = new HashMap<>();

    public ModuleManager() {
        initializeModules();
    }

    private void initializeModules() {
        // Combat
        addModule(new KillAuraModule());
        addModule(new AimbotModule());
        addModule(new CriticalsModule());
        addModule(new ReachModule());
        addModule(new AutoClickerModule());

        // Mouvement
        addModule(new SpeedModule());
        addModule(new JesusModule());
        addModule(new SpiderModule());
        addModule(new StrafeModule());
        addModule(new BunnyHopModule());
        addModule(new AutoWalkModule());
        addModule(new NoFallModule());

        // Vision
        addModule(new XRayModule());
        addModule(new FullbrightModule());
        addModule(new TracersModule());
        addModule(new EntityESPModule());
        addModule(new AutoTotemModule());

        // Utility
        addModule(new AntiKnockbackModule());
        addModule(new FastBreakModule());
        addModule(new AutoMineModule());
        addModule(new ScaffoldingModule());

        // Griefing
        addModule(new NukerModule());
        addModule(new FlightModule());
        addModule(new ESPModule());
        addModule(new FollowModule());
    }

    private void addModule(Module module) {
        modules.add(module);
        categorizedModules.computeIfAbsent(module.getCategory(), k -> new ArrayList<>()).add(module);
    }

    public List<Module> getModules() {
        return modules;
    }

    public Map<ModuleCategory, List<Module>> getCategorizedModules() {
        return categorizedModules;
    }

    public Module getModule(String name) {
        return modules.stream().filter(m -> m.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    public void toggleModule(String name) {
        Module module = getModule(name);
        if (module != null) {
            module.toggle();
        }
    }
}
