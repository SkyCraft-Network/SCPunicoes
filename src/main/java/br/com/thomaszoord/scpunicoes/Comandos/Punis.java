package br.com.thomaszoord.scpunicoes.Comandos;


import com.fire.api.db.objects.Punicao;
import com.fire.api.db.punicoes.ReadPunicoes;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.util.List;

public class Punis extends Command {

    public Punis(){
        super("punis", "sc.punir");
    }


    @Override
    public void execute(CommandSender sender, String[] args) {
        if(!(sender instanceof ProxiedPlayer)){
            return;
        }

        ProxiedPlayer p = (ProxiedPlayer) sender;

        if(args.length == 0){
            p.sendMessage(new TextComponent("§cUso correto: /punis <nick>"));
        return;
        }


        List<Punicao> punicoes = ReadPunicoes.getPunicoes(args[0]);

        if(args.length >= 2){
            handleListarCommand(p, args, punicoes, args[0]);
        }

        if(punicoes == null){
            p.sendMessage(new TextComponent("§cEste jogador nunca foi punido."));
            return;
        }


        sistemaDePagina(p, 1, punicoes, args[0]);




    }


    private void handleListarCommand(ProxiedPlayer p, String[] args, List<Punicao> pc, String pName) {
        int punicoesSize = pc.size();

        int page = 1;
        if (args.length >= 2) {
            try {
                page = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                p.sendMessage(new TextComponent("§cPágina inválida. Use /punis <nick> [número da página]"));
                return;
            }
        }

        int totalPages = (punicoesSize + 3) / 4;

        if (page < 1 || page > totalPages) {
            p.sendMessage(new TextComponent("§cPágina inválida. Use /punis <nick> [1-" + totalPages + "]"));
            return;
        }

        sistemaDePagina(p, page, pc, pName);
    }

    private void sistemaDePagina(ProxiedPlayer p, int page, List<Punicao> pc, String pName) {


        int punicoesSize = pc.size();

        int totalPages = (punicoesSize + 3) / 4;

        int startIndex = (page - 1) * 4;
        int endIndex = Math.min(startIndex + 4, pc.size());


        p.sendMessage(new TextComponent(" "));
        p.sendMessage(new TextComponent("§eHistórico de punições de §f" + pName + " §e(Página " + page + " de " + totalPages + "):"));
        p.sendMessage(new TextComponent(" "));

        for (int i = startIndex; i < endIndex; i++) {
            p.sendMessage(InformacoesBan(pc.get(i)));
        }


        p.sendMessage(new TextComponent(" "));
        p.sendMessage(new TextComponent("§ePágina §f" + page + " §ede §f" + totalPages + "§e. Utilize o comando §f/punis <nick> [página]§e."));
    }

    public static TextComponent InformacoesBan(Punicao punicao){
        TextComponent status = new TextComponent();

        ComponentBuilder hover = new ComponentBuilder("§e "+ punicao.getRazao() + "\n");

        String pStatus =
                (punicao.getStatus().equalsIgnoreCase("ATIVO") ? "§aAtivo" :
                        punicao.getStatus().equalsIgnoreCase("REVOGADO") ? "§8Revogado" :
                                punicao.getStatus().equalsIgnoreCase("EXPIRADO") ? "§cExpirado" : "");



        if(punicao.getTipo().equalsIgnoreCase("PERMANENTE")){
            hover.append(
                    "§8Informações:" +
                            "\n" +
                            "§8▪ §7ID: §f" + punicao.getId() + "\n" +
                            "§8▪ §7Status: " + punicao.getStatus() + "\n" +
                            "\n" +
                            "§8▪ §7Autoria: " + punicao.getStaffer() + "\n" +
                            "§8▪ §7Expira em: §c(Permanente)" + "\n" +
                            "\n" +
                            "§eClique para acessar as provas!");
        } else {
            hover.append(
                    "§8Informações:" +
                            "\n" +
                            "§8▪ §7ID: §f" + punicao.getId() + "\n" +
                            "§8▪ §7Status: " + pStatus + "\n" +
                            "\n" +
                            "§8▪ §7Aplicada por: " + punicao.getStaffer() + "\n" +
                            "§8▪ §7Expira em: " + punicao.getDataLiberacao() + "\n" +
                            "\n" +
                            "§eClique para acessar as provas!");

        }



        status.setText("§7▪§f" + punicao.getRazao() + "§7(" + pStatus+ "§7)");
        status.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, hover.create()));


        return status;
    }


}
