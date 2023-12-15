package br.com.thomaszoord.scpunicoes.Comandos;

import com.fire.api.db.objects.Punicao;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.util.Arrays;

public class Kick extends Command {

    public Kick(){
        super("kick", "sc.punir", "expulsar");
    }
    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            return;
        }

        ProxiedPlayer p = (ProxiedPlayer) sender;

        if (args.length == 0) {
            p.sendMessage(new TextComponent("§cUso correto: /kick <nick> <motivo>"));
            return;
        }

        ProxiedPlayer pKick = ProxyServer.getInstance().getPlayer(args[0]);
        String motivo = String.join(" ", Arrays.copyOfRange(args, 1, args.length));

        if(pKick != null){
           pKick.disconnect(kick(motivo));
        } else {
            p.sendMessage(new TextComponent("§cO player não está online."));
        }
    }

    public TextComponent kick(String razao){
        TextComponent kick = new TextComponent();

        razao = (razao != null) ? razao :
                "Kick";


            kick.setText("§6§lSky§3§lCraft\n" +
                    "\n" +
                    "§cVocê foi expulso!\n" +
                    "§cMotivo: §f" + razao + "\n" +
                    "\n" +
                    "§emc.skycraft.network");


        return kick;
    }

}
