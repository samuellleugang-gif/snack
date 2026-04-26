package com.samuell.client.gui;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import com.samuell.client.SamuellClient;
import com.samuell.client.module.Module;
import com.samuell.client.module.ModuleCategory;

public class ModuleScreen extends Screen {
    private ModuleCategory selectedCategory = ModuleCategory.COMBAT;
    private int scrollOffset = 0;

    public ModuleScreen() {
        super(Text.literal("Samuell Client Menu"));
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.fillGradient(matrices, 0, 0, this.width, this.height, 0xFF1a1a1a, 0xFF1a1a1a);
        
        int categoryX = 10;
        for (ModuleCategory category : ModuleCategory.values()) {
            int color = category == selectedCategory ? 0xFF00FF00 : 0xFF888888;
            this.drawCenteredTextWithShadow(matrices, this.textRenderer, category.getDisplayName(), categoryX + 50, 10, color);
            categoryX += 110;
        }

        int moduleY = 40;
        for (Module module : SamuellClient.moduleManager.getCategorizedModules().getOrDefault(selectedCategory, new java.util.ArrayList<>())) {
            int moduleColor = module.isEnabled() ? 0xFF00FF00 : 0xFFFF0000;
            this.drawTextWithShadow(matrices, this.textRenderer, module.getName(), 20, moduleY, moduleColor);
            moduleY += 25;
        }

        super.render(matrices, mouseX, mouseY, delta);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        int categoryX = 10;
        for (ModuleCategory category : ModuleCategory.values()) {
            if (mouseX >= categoryX && mouseX <= categoryX + 100 && mouseY >= 5 && mouseY <= 25) {
                selectedCategory = category;
                return true;
            }
            categoryX += 110;
        }

        int moduleY = 40;
        for (Module module : SamuellClient.moduleManager.getCategorizedModules().getOrDefault(selectedCategory, new java.util.ArrayList<>())) {
            if (mouseX >= 20 && mouseX <= 200 && mouseY >= moduleY && mouseY <= moduleY + 20) {
                module.toggle();
                return true;
            }
            moduleY += 25;
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return true;
    }
}
