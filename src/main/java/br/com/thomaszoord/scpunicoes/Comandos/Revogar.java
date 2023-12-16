package br.com.thomaszoord.scpunicoes.Comandos;

import br.com.thomaszoord.scpunicoes.Eventos.LoginListener;
import br.com.thomaszoord.scpunicoes.Utils.Discord;
import br.com.thomaszoord.scpunicoes.Utils.TimeUtils;
import com.fire.api.db.objects.Punicao;
import com.fire.api.db.punicoes.ReadPunicoes;
import com.fire.api.db.punicoes.UpdatePunicoes;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.awt.*;
import java.io.IOException;

import java.util.Arrays;

public class Revogar extends Command {
    public Revogar(){
        super("revogar", "sc.unban", "unban", "desbanir");
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

        if(args.length == 1){

        int id = Integer.parseInt(args[0]);
        String motivo = String.join(" ", Arrays.copyOfRange(args, 1, args.length));

        if(ReadPunicoes.getPunicoes(id) == null){
            p.sendMessage(new TextComponent("§cEssa punição não existe."));
            return;
        }

        Punicao punicao = (Punicao) ReadPunicoes.getPunicoes(id);

        if(motivo.equalsIgnoreCase("Revisão aceita")){
            UpdatePunicoes.updateStatus(id, "REVOGADO");
            p.sendMessage(new TextComponent("§aPunição revogada com sucesso."));

        } else if(motivo.equalsIgnoreCase("Erro de aplicação")) {
            UpdatePunicoes.updateStatus(id, "REVOGADO");
            p.sendMessage(new TextComponent("§aPunição revogada com sucesso."));

        } else if(motivo.equalsIgnoreCase("Punição aceita")){
            UpdatePunicoes.updateStatus(id, "REVOGADO");
            p.sendMessage(new TextComponent("§aPunição revogada com sucesso."));

        } else {
            if (p.hasPermission("sc.coord")
                    || p.hasPermission("sc.admin")
                    || p.hasPermission("sc.diretor")
                    || p.hasPermission("sc.ceo")) {

                UpdatePunicoes.updateStatus(id, "REVOGADO");
                p.sendMessage(new TextComponent("§aPunição revogada com sucesso."));

                for(ProxiedPlayer staff : LoginListener.staffers){
                    staff.sendMessage(new TextComponent("§a" + punicao.getNomeUsuario() + " §7teve sua punição revogada por §a" + motivo));
                }

            } else {
                p.sendMessage(new TextComponent("§cVocê não pode definir um motivo personalizado §8(Apenas §3Coordenadores §8e superiores)"));
            }
        }

    }

    public void mandarDiscord(Punicao p, String punido, String motivo, String staffer){

        String url = "https://discord.com/api/webhooks/1184975427089997964/Jjrl-H7MyHsGBbvXQPp-LAFotdaHtHGXinv95KdbysDh8Q8AepkO3OuoEpT2ClDqhZBK";
        String cabeca = "https://cravatar.eu/helmhead/" + punido + "/600.png";

        Discord webhook = new Discord(url);

        webhook.addEmbed(new Discord.EmbedObject().setColor(Color.YELLOW)
                .setThumbnail(cabeca)
                .setTitle("Revogado - " + TimeUtils.getDateDiscord())
                .setDescription(" Punição retirada")
                .addField("Nick", punido, true)
                .addField("Motivo", motivo, true)
                .addField("Data: ", TimeUtils.getDateDiscord(), false)
                .setFooter("Revigado por " + staffer, "")
        );

        try {
            webhook.execute();
        } catch (IOException var10) {
            var10.printStackTrace();
        }


    }

}
