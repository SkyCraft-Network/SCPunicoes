package br.com.thomaszoord.scpunicoes.Eventos;

import com.fire.api.db.objects.Punicao;
import com.fire.api.db.punicoes.ReadPunicoes;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class LoginListener implements Listener {


    @EventHandler
    public void onPlayerLogin(PostLoginEvent e){

        ProxiedPlayer p = e.getPlayer();
        Punicao banimento = null;

        for(Punicao punicao : ReadPunicoes.getPunicoes(p.getName())){
            if(punicao.getStatus().equalsIgnoreCase("ATIVO")){
                if(punicao.getTipo().equalsIgnoreCase("BAN")){

                    banimento = punicao;
                    break;

                }
            }
        }

        if(banimento != null){
            p.disconnect(kickPlayerBan(banimento, banimento.getDataLiberacao(), banimento.getRazao(), banimento.getId(), banimento.getDataPunicao()));
        }


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

}
