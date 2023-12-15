package br.com.thomaszoord.scpunicoes.Enum;

import java.time.Duration;

public enum Razoes {
    ANTIJOGO("Antijogo",
            "§7Praticar uma conduta que prejudique a \n" +
                    "§7experiência de jogo de outros jogadores. \n",
            TipoPunicao.BAN, Duration.ofDays(1),
            TipoPunicao.BAN, Duration.ofDays(7),
            TipoPunicao.BAN, Duration.ofDays(15),
            TipoPunicao.BAN, Duration.ofDays(30), false),

    AMEACA("Ameaça",
            "§7Proferir ameaças graves a \n " +
                    "§7jogadores ou membros da equipe \n",

            TipoPunicao.MUTE, Duration.ofDays(1),
            TipoPunicao.BAN, Duration.ofDays(7),
            TipoPunicao.BAN, Duration.ofDays(15),
            TipoPunicao.BAN, Duration.ofDays(30), true),
    DISCURSO_DE_ODIO("Discurso de Ódio",
            "§7Proferir de ofensas discriminatórias \n" +
                    "§7a fim de diminuir outro jogador. \n",
            TipoPunicao.PERMANENTE, Duration.ZERO, true),

    DISCRIMINACAO("Discriminação",
            "§7Usar de informações erradas para \n " +
                    "§7tentar ludibriar outros jogadores \n",
            TipoPunicao.PERMANENTE, Duration.ZERO, false),

    COMERCIO("Comércio",
            "§7Comerciar itens in-game ou off-game usando \n" +
                    "§7o servidor como plataforma de venda. \n",
            TipoPunicao.MUTE, Duration.ofDays(1),
            TipoPunicao.BAN, Duration.ofDays(1),
            TipoPunicao.BAN, Duration.ofDays(3),
            TipoPunicao.BAN, Duration.ofDays(7), true),

    DESINFORMACAO("Desinformação",
            "§7Usar de informações erradas para \n " +
                    "§7tentar ludibriar outros jogadores \n",
            TipoPunicao.MUTE, Duration.ofHours(12),
            TipoPunicao.MUTE, Duration.ofDays(1),
            TipoPunicao.BAN, Duration.ofDays(3),
            TipoPunicao.BAN, Duration.ofDays(7), true),

    DIVULGACAO_GRAVE("Divulgação grave",
            "§7Divulgar sites, servidores e \n" +
                    "§7afins sem autorização da equipe. \n",
            TipoPunicao.MUTE, Duration.ofHours(12),
            TipoPunicao.MUTE, Duration.ofDays(1),
            TipoPunicao.BAN, Duration.ofDays(3),
            TipoPunicao.BAN, Duration.ofDays(7),true),

    DIVULGACAO_SIMPLES("Divulgação simples",
            "§7Divulgar redes sociais sem \n " +
                    "§7a permissão da equipe \n",
            TipoPunicao.MUTE, Duration.ofHours(12),
            TipoPunicao.MUTE, Duration.ofDays(1),
            TipoPunicao.BAN, Duration.ofDays(3),
            TipoPunicao.BAN, Duration.ofDays(7),true),


    CHARGEBACK("Chargeback",
            "§7Cancelar o pagamento de um pacote da loja a \n " +
                    "§7fim de obter o produto gratuitamente. \n",
            TipoPunicao.PERMANENTE, Duration.ZERO, false),

    FALSIFICACAO_PROVAS("Falsificação de provas",
            "§7Falsificar provas a fim de punir \n " +
                    "§7outro jogador de forma injusta. \n",
            TipoPunicao.BAN, Duration.ofDays(180),
            TipoPunicao.PERMANENTE, Duration.ZERO, false),
    USO_MOD_NAO_PERMITIDO("Uso de mod não permitido",
            "§7Usar modificações do Minecraft \n" +
                    "§7ou clientes não permitidos \n",
            TipoPunicao.BAN, Duration.ofDays(3),
            TipoPunicao.BAN, Duration.ofDays(7),
            TipoPunicao.BAN, Duration.ofDays(30),
            TipoPunicao.BAN, Duration.ofDays(180), false),
    FLOOD_SPAM("Flood/Spam",
            "§7Usar de mensagens repetidas ou caracteres \n" +
                    "§7excessivos para prejudicar o chat. \n",
            TipoPunicao.MUTE, Duration.ofHours(2),
            TipoPunicao.MUTE, Duration.ofHours(6),
            TipoPunicao.MUTE, Duration.ofDays(1),
            TipoPunicao.BAN, Duration.ofDays(3), true),
    USO_HACK("Uso de Hack",
            "§7Fazer uso de clients e/ou programas ilícitos \n" +
                    "§7a fim de obter vantagens in-game. \n",
            TipoPunicao.BAN, Duration.ofDays(180), false),
    NICKNAME_INAPROPRIADO_ORIGINAL("Nickname inapropriado (original)",
            "§7Usar um nickname ofensivo e/ou \n " +
                    "§7inadequado com as diretrizes do servidor \n",
            TipoPunicao.BAN, Duration.ofDays(30),false),
    NICKNAME_INAPROPRIADO_PIRATA("Nickname inapropriado (pirata)",
            "§7Usar um nickname ofensivo e/ou \n" +
                    "§7inadequado com as diretrizes do servidor \n",
            TipoPunicao.PERMANENTE, Duration.ZERO, false),
    OFENSA_JOGADOR("Ofensa a jogador",
            "§7Proferir ofensas e/ou \n desrespeitar outros jogadores \n",
            TipoPunicao.MUTE, Duration.ofHours(6),
            TipoPunicao.MUTE, Duration.ofHours(12),
            TipoPunicao.BAN, Duration.ofDays(3),
            TipoPunicao.BAN, Duration.ofDays(7), true),
    OFENSA_EQUIPE_SERVIDOR("Ofensa à equipe/servidor",
            "§7Proferir ofensas e/ou desrespeitar membros da \n" +
                    "§7equipe ou o servidor como um todo. \n",
            TipoPunicao.MUTE, Duration.ofDays(1),
            TipoPunicao.BAN, Duration.ofDays(7),
            TipoPunicao.BAN, Duration.ofDays(15), true),
    SKIN_INAPROPRIADA("Skin inapropriada",
            "§7Usar uma skin ofensiva e/ou \n " +
                    "§7inadequada com as diretrizes do servidor \n",
            TipoPunicao.BAN, Duration.ofHours(6),
            TipoPunicao.BAN, Duration.ofDays(1),
            TipoPunicao.BAN, Duration.ofDays(3),
            TipoPunicao.PERMANENTE, Duration.ZERO, true),

    ASSEDIO("Assédio",
            "§7Assediar verbalmente um jogador.",
            TipoPunicao.BAN, Duration.ofDays(90),
            TipoPunicao.BAN, Duration.ofDays(180),
            TipoPunicao.PERMANENTE, Duration.ZERO, true),

    ABUSO_BUGS("Abuso de Bugs",
            "§7Usar de um bug a fim de de conseguir \n" +
                    "§7vantagens in-game \n",
            TipoPunicao.BAN, Duration.ofDays(3),
            TipoPunicao.BAN, Duration.ofDays(7),
            TipoPunicao.BAN, Duration.ofDays(15),
            TipoPunicao.BAN, Duration.ofDays(30), false),
    FORMACAO_TIMES("Formação de times",
            "§7Formar times com outros jogadores. \n",
            TipoPunicao.BAN, Duration.ofHours(2),
            TipoPunicao.BAN, Duration.ofDays(1),
            TipoPunicao.BAN, Duration.ofDays(3),
            TipoPunicao.BAN, Duration.ofDays(7),false);

    private final String descricao;
    private final String descricaoPunicao;



    private final TipoPunicao punicao1;
    private final TipoPunicao punicao2;
    private final TipoPunicao punicao3;
    private final TipoPunicao punicao4;

    private final Duration duracao1;
    private final Duration duracao2;
    private final Duration duracao3;
    private final Duration duracao4;

    private boolean necessitaProvas;

    Razoes(String descricao,
           String descricaoPunicao,
           TipoPunicao punicao1, Duration duracao1,
           TipoPunicao punicao2, Duration duracao2,
           TipoPunicao punicao3, Duration duracao3,
           TipoPunicao punicao4, Duration duracao4, boolean necessitaProvas) {

        this.descricao = descricao;
        this.descricaoPunicao = descricaoPunicao;

        this.punicao1 = punicao1;
        this.duracao1 = duracao1;

        this.punicao2 = punicao2;
        this.duracao2 = duracao2;

        this.punicao3 = punicao3;
        this.duracao3 = duracao3;

        this.punicao4 = punicao4;
        this.duracao4 = duracao4;

        this.necessitaProvas = necessitaProvas;

    }

    Razoes(String descricao,
           String descricaoPunicao,
           TipoPunicao punicao1, Duration duracao1,
           TipoPunicao punicao2, Duration duracao2,
           TipoPunicao punicao3, Duration duracao3,
           boolean necessitaProvas) {
        this(descricao, descricaoPunicao, punicao1, duracao1, punicao2, duracao2, punicao3,

                duracao3, null, null, necessitaProvas);
    }

    Razoes(String descricao,
           String descricaoPunicao,
           TipoPunicao punicao1, Duration duracao1,
           TipoPunicao punicao2, Duration duracao2,
           boolean necessitaProvas) {
        this(descricao, descricaoPunicao,
                punicao1, duracao1, punicao2, duracao2, null, null, null,
                null, necessitaProvas);
    }

    Razoes(String descricao,
           String descricaoPunicao,
           TipoPunicao punicao1, Duration duracao1,
           boolean necessitaProvas) {
        this(descricao, descricaoPunicao, punicao1, duracao1, null, null, null, null, null, null,
                necessitaProvas);
    }

    public String getDescricao() {
        return descricao;
    }

    public TipoPunicao getPunicao1() {
        return punicao1;
    }

    public TipoPunicao getPunicao2() {
        return punicao2;
    }

    public TipoPunicao getPunicao3() {
        return punicao3;
    }

    public TipoPunicao getPunicao4() {
        return punicao4;
    }

    public Duration getDuracao1() {
        return duracao1;
    }

    public Duration getDuracao2() {
        return duracao2;
    }

    public Duration getDuracao3() {
        return duracao3;
    }

    public Duration getDuracao4() {
        return duracao4;
    }


    public String getDescricaoPunicao() {
        return descricaoPunicao;
    }
}
