package br.com.thomaszoord.scpunicoes;

import br.com.thomaszoord.scpunicoes.Comandos.Punir;
import br.com.thomaszoord.scpunicoes.Eventos.LoginListener;
import net.md_5.bungee.api.plugin.Plugin;

public final class SCPunicoes extends Plugin {
    @Override
    public void onEnable() {
        getProxy().getConsole().sendMessage("§b[SC Punicoes] §aPlugin ativado com sucesso!");
        RegisterCommands();

    }
    @Override
    public void onDisable() {
        getProxy().getConsole().sendMessage("§c[SC Punicoes] Plugin desativado com sucesso!");
    }


    public void RegisterCommands(){
        getProxy().getPluginManager().registerCommand(this, new Punir());
        getProxy().getPluginManager().registerListener(this, new LoginListener());

    }

}
