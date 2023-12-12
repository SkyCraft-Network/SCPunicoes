package br.com.thomaszoord.scpunicoes;

import net.md_5.bungee.api.plugin.Plugin;

public final class SCPunicoes extends Plugin {
    @Override
    public void onEnable() {
        getProxy().getConsole().sendMessage("§b[SC PUNICOES] §aPlugin ativado com sucesso!");
        RegisterCommands();

    }
    @Override
    public void onDisable() {
        getProxy().getConsole().sendMessage("§c[SC PUNICOES] Plugin desativado com sucesso!");
    }


    public void RegisterCommands(){
        getProxy().getPluginManager().registerCommand(this, new Punir());
        getProxy().getPluginManager().registerCommand(this, new Teste());
    }

}
