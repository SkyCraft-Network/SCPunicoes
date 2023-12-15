package br.com.thomaszoord.scpunicoes.Enum;
public enum TipoPunicao {
        MUTE("Silenciamento"),
        BAN("Banimento"),
        PERMANENTE("Permanente");


        private final String descricao;

        TipoPunicao(String descricao) {
            this.descricao = descricao;
        }

        public String getDescricao() {
            return descricao;
        }
}

