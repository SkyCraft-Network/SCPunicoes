package br.com.thomaszoord.scpunicoes.Comandos;

import br.com.thomaszoord.scpunicoes.Eventos.LoginListener;
import br.com.thomaszoord.scpunicoes.Utils.Discord;
import br.com.thomaszoord.scpunicoes.Utils.TimeUtils;
import com.fire.api.db.objects.Punicao;
import com.fire.api.db.punicoes.ReadPunicoes;
import com.fire.api.db.punicoes.UpdatePunicoes;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.awt.*;
import java.io.IOException;

import java.util.Arrays;

public class Revogar extends Command {
    public Revogar(){
        super("revogar", "sc.revogar", "unban", "desbanir");
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
            textoRevogar(p, args[0]);
           return;
       }


             if (!contemApenasNumeros(args[0])) {
            p.sendMessage(new TextComponent("§cID inválido."));
            return;
             }

            int id = Integer.parseInt(args[0]);
            String motivo = String.join(" ", Arrays.copyOfRange(args, 1, args.length));

            if (ReadPunicoes.getPunicoes(id) == null) {
                p.sendMessage(new TextComponent("§cEssa punição não existe."));
                return;
            }

            
            Punicao punicao = ReadPunicoes.getPunicoes(id);

            if(punicao.getStatus().equalsIgnoreCase("ATIVO")){
                if (motivo.equalsIgnoreCase("Revisão aceita")) {

                    UpdatePunicoes.updateStatus(id, "REVOGADO");
                    p.sendMessage(new TextComponent("§aPunição revogada com sucesso."));
                    mandarDiscord(punicao.getNomeUsuario(), motivo, p.getName());

                    for (ProxiedPlayer staff : LoginListener.staffers) {
                        staff.sendMessage(new TextComponent("§a" + punicao.getNomeUsuario() + " §7teve sua punição revogada por §a" + motivo));
                    }

                } else if (motivo.equalsIgnoreCase("Erro de aplicação")) {
                    UpdatePunicoes.updateStatus(id, "REVOGADO");
                    p.sendMessage(new TextComponent("§aPunição revogada com sucesso."));
                    mandarDiscord(punicao.getNomeUsuario(), motivo, p.getName());


                    for (ProxiedPlayer staff : LoginListener.staffers) {
                        staff.sendMessage(new TextComponent("§a" + punicao.getNomeUsuario() + " §7teve sua punição revogada por §a" + motivo));
                    }

                } else if (motivo.equalsIgnoreCase("Punição aceita")) {
                    UpdatePunicoes.updateStatus(id, "REVOGADO");
                    p.sendMessage(new TextComponent("§aPunição revogada com sucesso."));
                    mandarDiscord(punicao.getNomeUsuario(), motivo, p.getName());

                    for (ProxiedPlayer staff : LoginListener.staffers) {
                        staff.sendMessage(new TextComponent("§a" + punicao.getNomeUsuario() + " §7teve sua punição revogada por §a" + motivo));
                    }

                } else {
                    if (p.hasPermission("sc.coord")
                            || p.hasPermission("sc.admin")
                            || p.hasPermission("sc.diretor")
                            || p.hasPermission("sc.ceo")) {

                        UpdatePunicoes.updateStatus(id, "REVOGADO");
                        p.sendMessage(new TextComponent("§aPunição revogada com sucesso."));
                        mandarDiscord(punicao.getNomeUsuario(), motivo, p.getName());

                        for (ProxiedPlayer staff : LoginListener.staffers) {
                            staff.sendMessage(new TextComponent("§a" + punicao.getNomeUsuario() + " §7teve sua punição revogada por §a" + motivo));
                        }

                    } else {
                        p.sendMessage(new TextComponent("§cVocê não pode definir um motivo personalizado §8(Apenas §3Coordenadores §8e superiores)"));
                    }
                }
            } else {
                p.sendMessage(new TextComponent("§cEssa punição não está mais ativa."));
            }


    }

    public void mandarDiscord(String punido, String motivo, String staffer){

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
                .setFooter("Revogado por " + staffer, "")
        );

        try {
            webhook.execute();
        } catch (IOException var10) {
            var10.printStackTrace();
        }


    }


    public boolean contemApenasNumeros(String s) {
        return s.matches("[0-9]+"); // Ou use "\\d+" como você fez originalmente
    }
    public void textoRevogar(ProxiedPlayer p, String pessoa){

             TextComponent textoUm = new TextComponent();
             textoUm.setText("§8▪ §fRevisão aceita");
             TextComponent textoDois = new TextComponent();
             textoDois.setText("§8▪ §fErro de aplicação");
             TextComponent textoTres = new TextComponent();
             textoTres.setText("§8▪ §fPunição aceita");
             TextComponent textoQuatro = new TextComponent();
             textoQuatro.setText("§8▪ §fMotivo personalizado §8(Apenas §3Coordenadores §8e superiores)");


             ComponentBuilder hover = new ComponentBuilder("§eClique para selecionar!");
             textoUm.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, hover.create()));
             textoDois.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, hover.create()));
             textoTres.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, hover.create()));
             textoQuatro.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, hover.create()));


             textoUm.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/revogar " + pessoa + " Revisão aceita"));
             textoDois.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/revogar " + pessoa + " Erro de aplicação"));
             textoTres.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/revogar " + pessoa + " Punição aceita"));
             textoQuatro.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/revogar " + pessoa + " <motivo>"));

        p.sendMessage(new TextComponent("§eRevogando punição de ID §f#759§e... Selecione um motivo abaixo!"));
        p.sendMessage( new TextComponent(" "));
        p.sendMessage(textoUm);
        p.sendMessage(textoDois);
        p.sendMessage(textoTres);
        p.sendMessage(textoQuatro);
        p.sendMessage(new TextComponent(" "));
        p.sendMessage(new TextComponent("§eClique em algumas das opções para selecionar!"));

    }

}
