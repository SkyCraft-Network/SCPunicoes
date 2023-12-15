package br.com.thomaszoord.scpunicoes.Utils;


import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class TimeUtils {

    private static final ZoneId ZONE_BR = ZoneId.of("America/Sao_Paulo");
    private static final Locale LOCALE_PT_BR = new Locale("pt", "BR");

    public static String getBrazilTime() {
        ZonedDateTime currentISTime = ZonedDateTime.now(ZONE_BR);
        return currentISTime.format(DateTimeFormatter.ofPattern("EEEE, dd 'de' MMMM 'de' yyyy 'às' HH:mm").withLocale(LOCALE_PT_BR));
    }

    public static String getDate() {
        return DateTimeFormatter.ofPattern("dd/MM/yyyy").withLocale(LOCALE_PT_BR).format(ZonedDateTime.now(ZONE_BR));
    }

    public static String getDateDiscord() {
        return DateTimeFormatter.ofPattern("dd/MM/yyyy").withLocale(LOCALE_PT_BR).format(ZonedDateTime.now(ZONE_BR));
    }

    public static String getCalculoDiscord(Duration duracao) {
        LocalDateTime dataAtual = LocalDateTime.now(ZONE_BR);

        LocalDateTime dataCalculada = dataAtual.plus(duracao);

        return dataCalculada.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public static String getRawDate() {
        return DateTimeFormatter.ofPattern("dd/MM/yyyy - HH'h' mm'm' ss's'").withLocale(LOCALE_PT_BR).format(ZonedDateTime.now(ZONE_BR));
    }

    public static String getTime() {
        return DateTimeFormatter.ofPattern("HH:mm:ss").withLocale(LOCALE_PT_BR).format(ZonedDateTime.now(ZONE_BR));
    }

    public static String calcularPunicao(Duration duracao) {
        LocalDateTime dataAtual = LocalDateTime.now(ZONE_BR);

        LocalDateTime dataCalculada = dataAtual.plus(duracao);

        return dataCalculada.format(DateTimeFormatter.ofPattern("dd/MM/yyyy - HH'h' mm'm' ss's'"));
    }
}
