package br.com.thomaszoord.scpunicoes.Comandos;

import br.com.thomaszoord.scpunicoes.Enum.Razoes;
import br.com.thomaszoord.scpunicoes.Enum.TipoPunicao;
import br.com.thomaszoord.scpunicoes.Utils.Discord;
import br.com.thomaszoord.scpunicoes.Utils.TimeUtils;
import com.fire.api.Cargos;
import com.fire.api.db.objects.Punicao;
import com.fire.api.db.punicoes.ReadPunicoes;
import com.fire.api.db.punicoes.SavePunicoes;
import com.fire.api.db.user.VerifyUser;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.awt.*;
import java.io.IOException;
import java.time.Duration;
import java.util.*;
import java.util.List;

public class Punir extends Command {

    private static final Map<String, List<Razoes>> PUNICOES_POR_PERMISSAO = new HashMap<>();

    static {
        PUNICOES_POR_PERMISSAO.put("Ajudante", Arrays.asList(
                Razoes.COMERCIO,
                Razoes.DIVULGACAO_SIMPLES,
                Razoes.AMEACA,
                Razoes.DESINFORMACAO,
                Razoes.FLOOD_SPAM,
                Razoes.NICKNAME_INAPROPRIADO_ORIGINAL,
                Razoes.NICKNAME_INAPROPRIADO_PIRATA,
                Razoes.OFENSA_JOGADOR,
                Razoes.SKIN_INAPROPRIADA));

        PUNICOES_POR_PERMISSAO.put("Moderador", Arrays.asList(
                Razoes.COMERCIO,
                Razoes.ANTIJOGO,
                Razoes.DIVULGACAO_GRAVE,
                Razoes.DIVULGACAO_SIMPLES,
                Razoes.USO_HACK,
                Razoes.USO_MOD_NAO_PERMITIDO,
                Razoes.OFENSA_EQUIPE_SERVIDOR,
                Razoes.AMEACA,
                Razoes.ABUSO_BUGS,
                Razoes.DESINFORMACAO,
                Razoes.FLOOD_SPAM,
                Razoes.NICKNAME_INAPROPRIADO_PIRATA,
                Razoes.NICKNAME_INAPROPRIADO_ORIGINAL,
                Razoes.OFENSA_JOGADOR,
                Razoes.SKIN_INAPROPRIADA,
                Razoes.FORMACAO_TIMES));

        PUNICOES_POR_PERMISSAO.put("Coord", Arrays.asList(
                Razoes.COMERCIO,
                Razoes.ANTIJOGO,
                Razoes.DISCRIMINACAO,
                Razoes.DISCURSO_DE_ODIO,
                Razoes.USO_MOD_NAO_PERMITIDO,
                Razoes.ASSEDIO,
                Razoes.FALSIFICACAO_PROVAS,
                Razoes.DIVULGACAO_GRAVE,
                Razoes.DIVULGACAO_SIMPLES,
                Razoes.USO_HACK,
                Razoes.ABUSO_BUGS,
                Razoes.OFENSA_EQUIPE_SERVIDOR,
                Razoes.AMEACA,
                Razoes.DESINFORMACAO,
                Razoes.FLOOD_SPAM,
                Razoes.NICKNAME_INAPROPRIADO_ORIGINAL,
                Razoes.NICKNAME_INAPROPRIADO_PIRATA,
                Razoes.OFENSA_JOGADOR,
                Razoes.SKIN_INAPROPRIADA,
                Razoes.FORMACAO_TIMES
        ));
        PUNICOES_POR_PERMISSAO.put("Admin", Arrays.asList(

                Razoes.COMERCIO,
                Razoes.ANTIJOGO,
                Razoes.DISCRIMINACAO,
                Razoes.DISCURSO_DE_ODIO,
                Razoes.USO_MOD_NAO_PERMITIDO,
                Razoes.ASSEDIO,
                Razoes.FALSIFICACAO_PROVAS,
                Razoes.DIVULGACAO_GRAVE,
                Razoes.DIVULGACAO_SIMPLES,
                Razoes.USO_HACK,
                Razoes.ABUSO_BUGS,
                Razoes.OFENSA_EQUIPE_SERVIDOR,
                Razoes.AMEACA,
                Razoes.DESINFORMACAO,
                Razoes.FLOOD_SPAM,
                Razoes.NICKNAME_INAPROPRIADO_ORIGINAL,
                Razoes.NICKNAME_INAPROPRIADO_PIRATA,
                Razoes.OFENSA_JOGADOR,
                Razoes.SKIN_INAPROPRIADA,
                Razoes.FORMACAO_TIMES

        ));

        PUNICOES_POR_PERMISSAO.put("Diretor", Arrays.asList(

                Razoes.COMERCIO,
                Razoes.ANTIJOGO,
                Razoes.DISCRIMINACAO,
                Razoes.DISCURSO_DE_ODIO,
                Razoes.USO_MOD_NAO_PERMITIDO,
                Razoes.ASSEDIO,
                Razoes.FALSIFICACAO_PROVAS,
                Razoes.DIVULGACAO_GRAVE,
                Razoes.DIVULGACAO_SIMPLES,
                Razoes.USO_HACK,
                Razoes.ABUSO_BUGS,
                Razoes.OFENSA_EQUIPE_SERVIDOR,
                Razoes.AMEACA,
                Razoes.DESINFORMACAO,
                Razoes.FLOOD_SPAM,
                Razoes.NICKNAME_INAPROPRIADO_ORIGINAL,
                Razoes.NICKNAME_INAPROPRIADO_PIRATA,
                Razoes.OFENSA_JOGADOR,
                Razoes.SKIN_INAPROPRIADA,
                Razoes.CHARGEBACK,
                Razoes.FORMACAO_TIMES

        ));
        PUNICOES_POR_PERMISSAO.put("CEO", Arrays.asList(
                Razoes.COMERCIO,
                Razoes.ANTIJOGO,
                Razoes.DISCRIMINACAO,
                Razoes.DISCURSO_DE_ODIO,
                Razoes.USO_MOD_NAO_PERMITIDO,
                Razoes.ASSEDIO,
                Razoes.FALSIFICACAO_PROVAS,
                Razoes.DIVULGACAO_GRAVE,
                Razoes.DIVULGACAO_SIMPLES,
                Razoes.USO_HACK,
                Razoes.ABUSO_BUGS,
                Razoes.OFENSA_EQUIPE_SERVIDOR,
                Razoes.AMEACA,
                Razoes.DESINFORMACAO,
                Razoes.FLOOD_SPAM,
                Razoes.NICKNAME_INAPROPRIADO_ORIGINAL,
                Razoes.NICKNAME_INAPROPRIADO_PIRATA,
                Razoes.OFENSA_JOGADOR,
                Razoes.SKIN_INAPROPRIADA,
                Razoes.CHARGEBACK,
                Razoes.FORMACAO_TIMES
        ));

    }


    public Punir() {
        super("punir", "sc.punir", "mute", "ban");
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

        if(!(VerifyUser.containsUsuario(args[0]))){
            p.sendMessage(new TextComponent("§cO jogador não foi encontrado." +
                    " Tente adicionar o caractere * no final do nick."));
            return;
        }

        if (args.length == 1) {
            sistemaDePagina(p, 1, args[0]);
            return;
        }

        if (args.length >= 3 && args[1].equalsIgnoreCase("listar")) {
            handleListarCommand(p, args);
            return;
        }

        String motivo = args[1].toLowerCase();
        boolean motivoEncontrado = false;

        for (Razoes punicoesList : PUNICOES_POR_PERMISSAO.get(Cargos.getGroup(p))) {
            if (motivo.equals(punicoesList.name().toLowerCase())) {
                punirPlayer(args[0], p.getName(), punicoesList);
                motivoEncontrado = true;

                break;
            }

        }


        if (!motivoEncontrado) {
            sistemaDePagina(p, 1, args[0]);
            p.sendMessage(new TextComponent(" "));

            p.sendMessage(new TextComponent("Não encontrado."));

        }
    }

    private void handleListarCommand(ProxiedPlayer p, String[] args) {
        List<Razoes> punicoesList = PUNICOES_POR_PERMISSAO.get(Cargos.getGroup(p));
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
        List<Razoes> punicoesList = PUNICOES_POR_PERMISSAO.get(Cargos.getGroup(p));

        int punicoesSize = punicoesList.size();

        int totalPages = (punicoesSize + 3) / 4;

        int startIndex = (page - 1) * 4;
        int endIndex = Math.min(startIndex + 4, punicoesList.size());


        p.sendMessage(new TextComponent(" "));
        p.sendMessage(new TextComponent("§eInfrações disponíveis (Página " + page + " de " + totalPages + "):"));
        p.sendMessage(new TextComponent(" "));

        for (int i = startIndex; i < endIndex; i++) {
            p.sendMessage(getTextComponent(p, punicoesList.get(i), player));
        }


        p.sendMessage(new TextComponent(" "));
        p.sendMessage(new TextComponent("§ePágina §f" + page + " §ede §f" + totalPages + "§e. Utilize o comando §f/punir <nick> listar [página]§e."));
    }

    private static TextComponent getTextComponent(ProxiedPlayer p, Razoes punicao, String player) {
        TextComponent serverText = new TextComponent("§8▪ §f" + punicao.getDescricao());
        serverText.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/punir " + player + " " + punicao.name()));

        ComponentBuilder hoverText = new ComponentBuilder("§e" + punicao.getDescricao() + "\n" +
                punicao.getDescricaoPunicao() + "\n" +
                "§e1° §f " + punicao.getPunicao1().getDescricao() + " §7(" + formatDuration(punicao.getDuracao1()) + ")\n");

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
                "§7Seu grupo: " + Cargos.getGroup(p) + "\n" +
                "\n" +
                "§eClique para selecionar!");

        serverText.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, hoverText.create()));
        return serverText;
    }

    private static String formatDuration(Duration duration) {
        long days = duration.toDays();
        long hours = duration.toHours();
        long minutes = duration.toMinutes();

        if (duration.isZero()) {
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

    private void punirPlayer(String punido, String staffer, Razoes razao) {


        if (ReadPunicoes.getPunicoes(punido) != null) {

            List<Punicao> punicoes = ReadPunicoes.getPunicoes(punido);
            int vezPunidaPeloMotivo = 0;

            for(Punicao pc : punicoes){
                if(razao.getDescricao().equalsIgnoreCase(pc.getRazao())) {
                    vezPunidaPeloMotivo++;
                }
            }

            TipoPunicao tipoPunicao = null;
            Duration duracao = null;

            //VERIFICA POR QUAL VEZ O PLAYER FOI PUNIDO
            if(vezPunidaPeloMotivo == 0){
                if(razao.getPunicao1() != null){
                    tipoPunicao = razao.getPunicao1();
                    duracao = razao.getDuracao1();
                }
            } else if(vezPunidaPeloMotivo == 1){
                if(razao.getPunicao2() != null){
                    tipoPunicao = razao.getPunicao2();
                    duracao = razao.getDuracao2();
                } else {
                    tipoPunicao = razao.getPunicao1();
                    duracao = razao.getDuracao1();
                }



            } else if(vezPunidaPeloMotivo == 2){

                if(razao.getPunicao3() != null){
                    tipoPunicao = razao.getPunicao3();
                    duracao = razao.getDuracao3();
                } else {
                    if(razao.getPunicao2() != null){
                        tipoPunicao = razao.getPunicao2();
                        duracao = razao.getDuracao2();
                    } else {
                        tipoPunicao = razao.getPunicao1();
                        duracao = razao.getDuracao1();
                    }
                }


            } else if(vezPunidaPeloMotivo == 4){
                    if(razao.getPunicao4() != null){
                        tipoPunicao = razao.getPunicao4();
                        duracao = razao.getDuracao4();
                    } else {
                        if(razao.getPunicao3() != null){
                            tipoPunicao = razao.getPunicao3();
                            duracao = razao.getDuracao3();
                        } else {
                            if(razao.getPunicao2() != null){
                                tipoPunicao = razao.getPunicao2();
                                duracao = razao.getDuracao2();
                            } else {
                                tipoPunicao = razao.getPunicao1();
                                duracao = razao.getDuracao1();
                            }
                        }
                    }


            } else {
                if(razao.getPunicao4() != null){
                    tipoPunicao = razao.getPunicao4();
                    duracao = razao.getDuracao4();
                } else {
                    if(razao.getPunicao3() != null){
                        tipoPunicao = razao.getPunicao3();
                        duracao = razao.getDuracao3();
                    } else {
                        if(razao.getPunicao2() != null){
                            tipoPunicao = razao.getPunicao2();
                            duracao = razao.getDuracao2();
                        } else {
                            tipoPunicao = razao.getPunicao1();
                            duracao = razao.getDuracao1();
                        }
                    }
                }
            }

            //CONSTRUTOR DA PUNIÇÃO
            Punicao punicao = new Punicao(punido, tipoPunicao.name(), 0, staffer, TimeUtils.getRawDate(),
                    TimeUtils.calcularPunicao(duracao), razao.getDescricaoPunicao(), "ATIVO");

            //SALVA PUNIÇÃO NO BANCO DE DADOS
            SavePunicoes.savePunicao(punicao);
            mandarDiscord(punido, razao, null, duracao, staffer);

            if(ProxyServer.getInstance().getPlayer(punido) != null){
                ProxyServer.getInstance().getPlayer(punido).disconnect(kickPlayerBan(punicao, punicao.getDataLiberacao(), punicao.getRazao(), punicao.getId(), punicao.getDataPunicao()));
            }

        } else {
            //CONSTRUTOR DA PUNIÇÃO
            TipoPunicao tipoPunicao = razao.getPunicao1();
            Duration duracao = razao.getDuracao1();
            Punicao punicao = new Punicao(punido, tipoPunicao.name(), 0, staffer, TimeUtils.getRawDate(),
                    TimeUtils.calcularPunicao(duracao), razao.getDescricaoPunicao(), "ATIVO");

            //SALVA PUNIÇÃO NO BANCO DE DADOS
            SavePunicoes.savePunicao(punicao);

            mandarDiscord(punido, razao, null, duracao, staffer);

            if(ProxyServer.getInstance().getPlayer(punido) != null){
                ProxyServer.getInstance().getPlayer(punido).disconnect(kickPlayerBan(punicao, punicao.getDataLiberacao(), punicao.getRazao(), punicao.getId(), punicao.getDataPunicao()));
            }
        }

        //VERIFICA SE PLAYER ESTÁ ONLINE NO SERVIDOR, SE TIVER, DESCONECTA ELE



    }

    public TextComponent kickPlayerBan(Punicao p, String duracao, String razao, int id, String data){
        TextComponent kick = new TextComponent();
        if(p.getTipo().equalsIgnoreCase("PERMANENTE")){
            kick.setText("§6§lSky§3§lCraft\n" +
                    "\n" +
                    "§cVocê está banido permanente!\n" +
                    "\n" +
                    "§cMotivo: §f" + razao + "\n" +
                    "§cData: §f" + data + "\n" +
                    "§cID: §7" + id + "\n" +
                    "\n" +
                    "§eCaso achar que foi punido injustamente\n" +
                    "§eFaça uma revisao em §fdiscord.gg/skycraft§e.");
        } else {
            kick.setText("§6§lSky§3§lCraft\n" +
                    "\n" +
                    "§cVocê está banido!\n" +
                    "§cSua punição expira em " + duracao  +
                    "\n" +
                    "§cMotivo: §f" + razao + "\n" +
                    "§cData: §f" + data + "\n" +
                    "§cID: §7" + id + "\n" +
                    "\n" +
                    "§eCaso achar que foi punido injustamente\n" +
                    "§eFaça uma revisao em §fdiscord.gg/skycraft§e.");
        }





        return kick;
    }


    public void mandarDiscord(String punido, Razoes razao, String prova, Duration duracao, String staffer){

        String url = "https://discord.com/api/webhooks/1184975427089997964/Jjrl-H7MyHsGBbvXQPp-LAFotdaHtHGXinv95KdbysDh8Q8AepkO3OuoEpT2ClDqhZBK";
        //MANDA MENSAGEM DO DISCORD
        Discord webhook = new Discord(url);

        webhook.addEmbed(new Discord.EmbedObject().setColor(Color.RED)
                .setTitle("Banimento - " + TimeUtils.getDateDiscord())
                .setDescription( "<:aprovado:1184978484297211934> Punição aplicada ")
                .addField("Nick", punido, true)
                .addField("Motivo", razao.getDescricao(), true)
                .addField("Data de aplicação", TimeUtils.getDateDiscord(), false)
                .addField("Data de expiração", TimeUtils.getCalculoDiscord(duracao), false)
                .setFooter("Aplicado por " + staffer, "")
        );
        webhook.addEmbed(new Discord.EmbedObject().setColor(Color.black)
                .setDescription("## PROVA ##")
                .addField("Link",
                        " ",
                        false));

        try {
            webhook.execute();
        } catch (IOException var10) {
            var10.printStackTrace();
        }


    }

}
