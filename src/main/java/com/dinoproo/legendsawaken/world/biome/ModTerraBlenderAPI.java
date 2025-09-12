package com.dinoproo.legendsawaken.world.biome;

import com.dinoproo.legendsawaken.LegendsAwaken;
import com.dinoproo.legendsawaken.world.biome.surface.ModMaterialRules;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;
import terrablender.api.TerraBlenderApi;

public class ModTerraBlenderAPI implements TerraBlenderApi {
    @Override
    public void onTerraBlenderInitialized() {
        Regions.register(new ModOverworldRegion(1));

        SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, LegendsAwaken.MOD_ID, ModMaterialRules.makeRules());
    }
}
