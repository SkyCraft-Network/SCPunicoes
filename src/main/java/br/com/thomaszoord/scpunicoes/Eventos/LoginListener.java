package br.com.thomaszoord.scpunicoes.Eventos;

import com.fire.api.db.objects.Punicao;
import com.fire.api.db.punicoes.ReadPunicoes;
import com.fire.api.db.punicoes.UpdatePunicoes;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class LoginListener implements Listener {

    public static List<ProxiedPlayer> staffers = new ArrayList<>();


    @EventHandler
    public void onPlayerLogin(PostLoginEvent e){

        ProxiedPlayer p = e.getPlayer();

        if(ReadPunicoes.getPunicoes(p.getName()) == null){
            return;
        }

        if(p.hasPermission("sc.staff")){
            staffers.add(p);
        }

        Punicao banimento = null;


      for(Punicao punicao : ReadPunicoes.getPunicoes(p.getName())){
            if(punicao.getStatus().equalsIgnoreCase("ATIVO")){
                if(punicao.getTipo().equalsIgnoreCase("BAN") ||
                        punicao.getTipo().equalsIgnoreCase("PERMANENTE")){

                    if(punicao.getTipo().equalsIgnoreCase("PERMANENTE")){
                        banimento = punicao;

                    } else {

                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH'h' mm'm' ss's'");
                        String dataString = punicao.getDataLiberacao();
                        ZonedDateTime dataLiberacao = ZonedDateTime.parse(dataString, formatter.withZone(ZoneId.of("America/Sao_Paulo")));
                        ZonedDateTime agora = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"));

                        if (agora.isAfter(dataLiberacao)) {
                            UpdatePunicoes.updateStatus(punicao.getNomeUsuario(), "EXPIRADO");
                            System.out.println("Acabou a punição ebaaa");
                        } else {
                            banimento = punicao;
                            System.out.println("A punição ainda está ativa");
                        }
                    break;
                    }
                }
            }
        }

        if(banimento != null){
            p.disconnect(kickPlayerBan(banimento));
        }


    }


    @EventHandler
    public void onPlayerDisconect(PlayerDisconnectEvent e){
        staffers.remove(e.getPlayer());
    }


    public TextComponent kickPlayerBan(Punicao p){
        TextComponent kick = new TextComponent();
        if(p.getTipo().equalsIgnoreCase("PERMANENTE")){
            kick.setText("§6§lSky§3§lCraft\n" +
                    "\n" +
                    "§cVocê está banido permanente!\n" +
                    "\n" +
                    "§cMotivo: §f" + p.getRazao() + "\n" +
                    "§cData: §f" + p.getDataPunicao() + "\n" +
                    "§cID: §f" + p.getId() + "\n" +
                    "\n" +
                    "§eCaso achar que foi punido injustamente\n" +
                    "§eFaça uma revisao em §fdiscord.gg/skycraft§e.");
        } else {
            kick.setText("§6§lSky§3§lCraft\n" +
                    "\n" +
                    "§cVocê está banido! \n" +
                    "\n" +
                    "§cSua punição expira: §f" + p.getDataLiberacao() + "\n" +
                    "\n" +
                    "§cMotivo: §f" + p.getRazao() + "\n" +
                    "§cData: §f" + p.getDataPunicao() + "\n" +
                    "§cID: §f" + p.getId() + "\n" +
                    "\n" +
                    "§eCaso achar que foi punido injustamente\n" +
                    "§eFaça uma revisao em §fdiscord.gg/skycraft§e.");
        }

        return kick;
    }

}
