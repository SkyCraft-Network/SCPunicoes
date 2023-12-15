package br.com.thomaszoord.scpunicoes.Utils;


import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class TimeUtils
{

    public static String getBrazilTime()
    {
        Instant instant = Instant.now();
        ZonedDateTime currentISTime = instant.atZone(ZoneId.of("America/Sao_Paulo"));
        Locale localePortugues = new Locale("pt", "BR");

        return currentISTime.format(DateTimeFormatter.ofPattern("EEEE, dd 'de' MMMM 'de' yyyy 'às' HH:mm").withLocale(localePortugues));
    }

    public static String getDate()
    {
        return DateTimeFormatter.ofPattern("dd/MM/yyyy").format(LocalDateTime.now());
    }

    public static String getDateDiscord()
    {
        return DateTimeFormatter.ofPattern("dd/MM/yyyy").format(LocalDateTime.now());
    }

    public static String getCalculoDiscord(Duration duracao) {
        LocalDateTime dataAtual = LocalDateTime.now();

        LocalDateTime dataCalculada = dataAtual.plus(duracao);

        return dataCalculada.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public static String getRawDate()
    {
        return DateTimeFormatter.ofPattern("dd.MM.yyyy - HH.mm.ss").format(LocalDateTime.now());
    }

    public static String getTime()
    {
        return DateTimeFormatter.ofPattern("HH:mm:ss").format(LocalDateTime.now());
    }


    public static String calcularPunicao(Duration duracao) {
        LocalDateTime dataAtual = LocalDateTime.now();

        LocalDateTime dataCalculada = dataAtual.plus(duracao);

        return dataCalculada.format(DateTimeFormatter.ofPattern("dd.MM.yyyy - HH.mm.ss"));
    }

}