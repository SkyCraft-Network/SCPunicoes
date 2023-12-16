package br.com.thomaszoord.scpunicoes;

import br.com.thomaszoord.scpunicoes.Comandos.*;
import br.com.thomaszoord.scpunicoes.Eventos.LoginListener;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Plugin;

public final class SCPunicoes extends Plugin {
    @Override
    public void onEnable() {
        getProxy().getConsole().sendMessage(new TextComponent("§b[SC Punicoes] §aPlugin ativado com sucesso!"));
        RegisterCommands();

    }
    @Override
    public void onDisable() {
        getProxy().getConsole().sendMessage("§c[SC Punicoes] Plugin desativado com sucesso!");
    }


    public void RegisterCommands(){
        getProxy().getPluginManager().registerCommand(this, new Punir());
        getProxy().getPluginManager().registerCommand(this, new Punis());
        getProxy().getPluginManager().registerCommand(this, new Revogar());
        getProxy().getPluginManager().registerCommand(this, new Kick());
        getProxy().getPluginManager().registerCommand(this, new Dev());


        getProxy().getPluginManager().registerListener(this, new LoginListener());

    }

}
