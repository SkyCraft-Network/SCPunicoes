package br.com.thomaszoord.scpunicoes.Comandos;

import br.com.thomaszoord.scpunicoes.Utils.Discord;
import br.com.thomaszoord.scpunicoes.Utils.TimeUtils;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.awt.*;
import java.io.IOException;

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
           p.sendMessage(new TextComponent("Jogador expulso com sucesso!"));
           mandarDiscord(pKick.getName(), motivo, null);
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

    public void mandarDiscord(String punido, String razao, String staffer){

        String url = "https://discord.com/api/webhooks/1184975427089997964/Jjrl-H7MyHsGBbvXQPp-LAFotdaHtHGXinv95KdbysDh8Q8AepkO3OuoEpT2ClDqhZBK";
        String cabeca = "https://cravatar.eu/helmhead/" + punido + "/600.png";

        //MANDA MENSAGEM DO DISCORD
        Discord webhook = new Discord(url);



        webhook.addEmbed(new Discord.EmbedObject().setColor(Color.ORANGE)
                .setThumbnail(cabeca)
                .setTitle("Kick" + " - " + TimeUtils.getDateDiscord())
                .setDescription( "<:laranja:1184978488235659354> Expulsão aplicada ")
                .addField("Nick", punido, true)
                .addField("Motivo", razao, true)
                .addField("Data de aplicação", TimeUtils.getDateDiscord(), false)
                .setFooter("Aplicado por " + staffer, "")
        );

        try {
            webhook.execute();
        } catch (IOException var10) {
            var10.printStackTrace();
        }


    }


}
