package br.com.thomaszoord.scpunicoes.Comandos;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Dev extends Command {

    static List<String> dev = new ArrayList<>();

    static {
        dev.add("thomaszoord");
        dev.add("Defaulti");
        dev.add("firethekill");
        dev.add("jullyaaa");
        dev.add("gabrielandrade_");
    }
    public Dev(){
        super("dev", "");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            return;
        }

        ProxiedPlayer p = (ProxiedPlayer) sender;

        if (args.length == 0) {
            p.sendMessage(new TextComponent("§cUso correto: /dev mensagem"));
            return;
        }

        String motivo = String.join(" ", Arrays.copyOfRange(args, 0, args.length));

        if(dev.contains(p.getName())){
            for (String devs : dev){
                ProxiedPlayer dev = ProxyServer.getInstance().getPlayer(devs);
                if(dev != null){
                    dev.sendMessage(new TextComponent("§b[CHAT DEV] §f" + p.getName() + ": §7" + motivo));
                }
            }
        }



    }
}
