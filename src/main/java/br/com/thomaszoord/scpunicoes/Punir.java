package br.com.thomaszoord.scpunicoes;

import com.fire.api.Cargos;
import com.fire.api.db.objects.Punicao;
import com.fire.api.db.punicoes.ReadPunicoes;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.time.Duration;
import java.util.*;

public class Punir extends Command {

    private static final Map<String, List<Punicoes.Razoes>> PUNICOES_POR_PERMISSAO = new HashMap<>();

    static {
        PUNICOES_POR_PERMISSAO.put("Ajudante", Arrays.asList(
                Punicoes.Razoes.COMERCIO,
                Punicoes.Razoes.DIVULGACAO_SIMPLES,
                Punicoes.Razoes.AMEACA,
                Punicoes.Razoes.DESINFORMACAO,
                Punicoes.Razoes.FLOOD_SPAM,
                Punicoes.Razoes.NICKNAME_INAPROPRIADO_ORIGINAL,
                Punicoes.Razoes.NICKNAME_INAPROPRIADO_PIRATA,
                Punicoes.Razoes.OFENSA_JOGADOR,
                Punicoes.Razoes.SKIN_INAPROPRIADA));

        PUNICOES_POR_PERMISSAO.put("Moderador", Arrays.asList(
                Punicoes.Razoes.COMERCIO,
                Punicoes.Razoes.ANTIJOGO,
                Punicoes.Razoes.DIVULGACAO_GRAVE,
                Punicoes.Razoes.DIVULGACAO_SIMPLES,
                Punicoes.Razoes.USO_HACK,
                Punicoes.Razoes.USO_MOD_NAO_PERMITIDO,
                Punicoes.Razoes.OFENSA_EQUIPE_SERVIDOR,
                Punicoes.Razoes.AMEACA,
                Punicoes.Razoes.ABUSO_BUGS,
                Punicoes.Razoes.DESINFORMACAO,
                Punicoes.Razoes.FLOOD_SPAM,
                Punicoes.Razoes.NICKNAME_INAPROPRIADO_PIRATA,
                Punicoes.Razoes.NICKNAME_INAPROPRIADO_ORIGINAL,
                Punicoes.Razoes.OFENSA_JOGADOR,
                Punicoes.Razoes.SKIN_INAPROPRIADA,
                Punicoes.Razoes.FORMACAO_TIMES));

        PUNICOES_POR_PERMISSAO.put("Coord", Arrays.asList(
                Punicoes.Razoes.COMERCIO,
                Punicoes.Razoes.ANTIJOGO,
                Punicoes.Razoes.DISCRIMINACAO,
                Punicoes.Razoes.DISCURSO_DE_ODIO,
                Punicoes.Razoes.USO_MOD_NAO_PERMITIDO,
                Punicoes.Razoes.ASSEDIO,
                Punicoes.Razoes.FALSIFICACAO_PROVAS,
                Punicoes.Razoes.DIVULGACAO_GRAVE,
                Punicoes.Razoes.DIVULGACAO_SIMPLES,
                Punicoes.Razoes.USO_HACK,
                Punicoes.Razoes.ABUSO_BUGS,
                Punicoes.Razoes.OFENSA_EQUIPE_SERVIDOR,
                Punicoes.Razoes.AMEACA,
                Punicoes.Razoes.DESINFORMACAO,
                Punicoes.Razoes.FLOOD_SPAM,
                Punicoes.Razoes.NICKNAME_INAPROPRIADO_ORIGINAL,
                Punicoes.Razoes.NICKNAME_INAPROPRIADO_PIRATA,
                Punicoes.Razoes.OFENSA_JOGADOR,
                Punicoes.Razoes.SKIN_INAPROPRIADA,
                Punicoes.Razoes.FORMACAO_TIMES
        ));
        PUNICOES_POR_PERMISSAO.put("Admin", Arrays.asList(

                Punicoes.Razoes.COMERCIO,
                Punicoes.Razoes.ANTIJOGO,
                Punicoes.Razoes.DISCRIMINACAO,
                Punicoes.Razoes.DISCURSO_DE_ODIO,
                Punicoes.Razoes.USO_MOD_NAO_PERMITIDO,
                Punicoes.Razoes.ASSEDIO,
                Punicoes.Razoes.FALSIFICACAO_PROVAS,
                Punicoes.Razoes.DIVULGACAO_GRAVE,
                Punicoes.Razoes.DIVULGACAO_SIMPLES,
                Punicoes.Razoes.USO_HACK,
                Punicoes.Razoes.ABUSO_BUGS,
                Punicoes.Razoes.OFENSA_EQUIPE_SERVIDOR,
                Punicoes.Razoes.AMEACA,
                Punicoes.Razoes.DESINFORMACAO,
                Punicoes.Razoes.FLOOD_SPAM,
                Punicoes.Razoes.NICKNAME_INAPROPRIADO_ORIGINAL,
                Punicoes.Razoes.NICKNAME_INAPROPRIADO_PIRATA,
                Punicoes.Razoes.OFENSA_JOGADOR,
                Punicoes.Razoes.SKIN_INAPROPRIADA,
                Punicoes.Razoes.FORMACAO_TIMES

        ));

        PUNICOES_POR_PERMISSAO.put("Diretor", Arrays.asList(

                Punicoes.Razoes.COMERCIO,
                Punicoes.Razoes.ANTIJOGO,
                Punicoes.Razoes.DISCRIMINACAO,
                Punicoes.Razoes.DISCURSO_DE_ODIO,
                Punicoes.Razoes.USO_MOD_NAO_PERMITIDO,
                Punicoes.Razoes.ASSEDIO,
                Punicoes.Razoes.FALSIFICACAO_PROVAS,
                Punicoes.Razoes.DIVULGACAO_GRAVE,
                Punicoes.Razoes.DIVULGACAO_SIMPLES,
                Punicoes.Razoes.USO_HACK,
                Punicoes.Razoes.ABUSO_BUGS,
                Punicoes.Razoes.OFENSA_EQUIPE_SERVIDOR,
                Punicoes.Razoes.AMEACA,
                Punicoes.Razoes.DESINFORMACAO,
                Punicoes.Razoes.FLOOD_SPAM,
                Punicoes.Razoes.NICKNAME_INAPROPRIADO_ORIGINAL,
                Punicoes.Razoes.NICKNAME_INAPROPRIADO_PIRATA,
                Punicoes.Razoes.OFENSA_JOGADOR,
                Punicoes.Razoes.SKIN_INAPROPRIADA,
                Punicoes.Razoes.CHARGEBACK,
                Punicoes.Razoes.FORMACAO_TIMES

        ));
        PUNICOES_POR_PERMISSAO.put("CEO", Arrays.asList(
                Punicoes.Razoes.COMERCIO,
                Punicoes.Razoes.ANTIJOGO,
                Punicoes.Razoes.DISCRIMINACAO,
                Punicoes.Razoes.DISCURSO_DE_ODIO,
                Punicoes.Razoes.USO_MOD_NAO_PERMITIDO,
                Punicoes.Razoes.ASSEDIO,
                Punicoes.Razoes.FALSIFICACAO_PROVAS,
                Punicoes.Razoes.DIVULGACAO_GRAVE,
                Punicoes.Razoes.DIVULGACAO_SIMPLES,
                Punicoes.Razoes.USO_HACK,
                Punicoes.Razoes.ABUSO_BUGS,
                Punicoes.Razoes.OFENSA_EQUIPE_SERVIDOR,
                Punicoes.Razoes.AMEACA,
                Punicoes.Razoes.DESINFORMACAO,
                Punicoes.Razoes.FLOOD_SPAM,
                Punicoes.Razoes.NICKNAME_INAPROPRIADO_ORIGINAL,
                Punicoes.Razoes.NICKNAME_INAPROPRIADO_PIRATA,
                Punicoes.Razoes.OFENSA_JOGADOR,
                Punicoes.Razoes.SKIN_INAPROPRIADA,
                Punicoes.Razoes.CHARGEBACK,
                Punicoes.Razoes.FORMACAO_TIMES
        ));

    }


    public Punir(){
        super("punir", "sc.punir", "mute");
    }


    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            return;
        }

        ProxiedPlayer p = (ProxiedPlayer) sender;

        if (args.length == 0) {
            p.sendMessage(new TextComponent("§cUso correto: /punir <nick>"));
            return;
        }

        if(args.length == 1){
            sistemaDePagina(p, 1, args[0]);
            return;
        }

        if (args.length >= 3 && args[1].equalsIgnoreCase("listar")) {
            handleListarCommand(p, args);
            return;
        }

        String motivo = args[1].toLowerCase();
        boolean motivoEncontrado = false;

        for(Punicoes.Razoes punicoesList : PUNICOES_POR_PERMISSAO.get(Cargos.getGroup(p))) {
            if (motivo.equals(punicoesList.name().toLowerCase())) {
                p.sendMessage(new TextComponent("Banindo por: " + punicoesList.getDescricao()));
                motivoEncontrado = true;

                break;
            }

        }


        if(!motivoEncontrado){
            sistemaDePagina(p, 1, args[0]);
            p.sendMessage(new TextComponent(" "));

            p.sendMessage(new TextComponent("Não encontrado."));

        }
    }

    private void handleListarCommand(ProxiedPlayer p, String[] args) {
        List<Punicoes.Razoes> punicoesList = PUNICOES_POR_PERMISSAO.get(Cargos.getGroup(p));
        int punicoesSize = punicoesList.size();

        int page = 1;
        if (args.length >= 3) {
            try {
                page = Integer.parseInt(args[2]);
            } catch (NumberFormatException e) {
                p.sendMessage(new TextComponent("§cPágina inválida. Use /punir <nick> listar [número da página]"));
                return;
            }
        }

        int totalPages = (punicoesSize + 3) / 4;

        if (page < 1 || page > totalPages) {
            p.sendMessage(new TextComponent("§cPágina inválida. Use /punir <nick> listar [1-" + totalPages + "]"));
            return;
        }

        sistemaDePagina(p, page, args[0]);
    }

    private void sistemaDePagina(ProxiedPlayer p, int page, String player) {
        List<Punicoes.Razoes> punicoesList = PUNICOES_POR_PERMISSAO.get(Cargos.getGroup(p));

        int punicoesSize = punicoesList.size();

        int totalPages = (punicoesSize + 3) / 4;

        int startIndex = (page - 1) * 4;
        int endIndex = Math.min(startIndex + 4, punicoesList.size());


        p.sendMessage(new TextComponent(" "));
        p.sendMessage(new TextComponent("§eInfrações disponíveis (Página " + page + " de " + totalPages + "):"));
        p.sendMessage(new TextComponent(" "));

        for (int i = startIndex; i < endIndex; i++) {
            p.sendMessage(getTextComponent(punicoesList.get(i), player));
        }


        p.sendMessage(new TextComponent(" "));
        p.sendMessage(new TextComponent("§ePágina §f" + page + " §ede §f" + totalPages + "§e. Utilize o comando §f/punir <nick> listar [página]§e."));
    }

    private static TextComponent getTextComponent(Punicoes.Razoes punicao, String player) {
        TextComponent serverText = new TextComponent("§8▪ §f" + punicao.getDescricao());
        serverText.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/punir " + player + " " + punicao.name()));

        ComponentBuilder hoverText = new ComponentBuilder("§e" + punicao.getDescricao() + "\n" +
                punicao.getDescricaoPunicao() + "\n" +
                "§e1° §f " + punicao.getPunicao1().getDescricao() + " §7(" + formatDuration(punicao.getDuracao1()) + ")\n" );

        if (punicao.getPunicao2() != null) {
               hoverText.append("§e2° §f " + punicao.getPunicao2().getDescricao() + " §7(" + formatDuration(punicao.getDuracao2()) + ")\n");
        }
        if (punicao.getPunicao3() != null) {
            hoverText.append("§e3° §f " + punicao.getPunicao3().getDescricao() + " §7(" + formatDuration(punicao.getDuracao3()) + ")\n");
        }

        if (punicao.getPunicao4() != null) {
            hoverText.append("§e4° §f " + punicao.getPunicao4().getDescricao() + " §7(" + formatDuration(punicao.getDuracao4()) + ")\n");
        }

        hoverText.append("\n" +
                "§7Grupo necessário: §aAjudante\n" +
                "\n" +
                "§eClique para selecionar!");

        serverText.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, hoverText.create()));
        return serverText;
    }

    private static String formatDuration(Duration duration) {
        long days = duration.toDays();
        long hours = duration.toHours();
        long minutes = duration.toMinutes();

        if(duration.isZero()){
            return "Permanente";
        }

        if (days > 0) {
            return days + " dias";
        } else if (hours > 0) {
            return hours + " horas";
        } else {
            return minutes + " minutos";
        }
    }

    private void punirPlayer(String player, String staffer, String razao){


          if(ReadPunicoes.getPunicoes(player) != null) {

              List<Punicao> punicao = ReadPunicoes.getPunicoes(player);


          } else {}

    }
}

