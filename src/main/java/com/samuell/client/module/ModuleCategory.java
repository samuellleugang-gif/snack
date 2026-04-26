package com.samuell.client.module;

public enum ModuleCategory {
    COMBAT("Combat", 0xFF0000),
    MOVEMENT("Mouvement", 0xFF8800),
    VISION("Vision", 0xFFFF00),
    UTILITY("Utility", 0x00FF00),
    GRIEFING("Griefing", 0x0088FF);

    private final String displayName;
    private final int color;

    ModuleCategory(String displayName, int color) {
        this.displayName = displayName;
        this.color = color;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getColor() {
        return color;
    }
}
