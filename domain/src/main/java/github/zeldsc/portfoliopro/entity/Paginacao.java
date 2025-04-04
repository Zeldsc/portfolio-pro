package github.zeldsc.portfoliopro.entity;

import java.util.Objects;

public record Paginacao(
        int pagina,
        int tamanho,
        String ordenarPor,
        Direcao direcao
) {

    public Paginacao {
        if (pagina < 0) pagina = 0;
        if (tamanho <= 0) tamanho = 10;
        ordenarPor = (ordenarPor == null || ordenarPor.isBlank()) ? "id" : ordenarPor;
        direcao = Objects.requireNonNullElse(direcao, Direcao.ASC);
    }

    public Paginacao(int pagina, int tamanho) {
        this(pagina, tamanho, null, null);
    }

    public int offset() {
        return pagina * tamanho;
    }

    public enum Direcao {
        ASC, DESC;

        public static Direcao fromString(String value) {
            try {
                return Direcao.valueOf(value.toUpperCase());
            } catch (Exception e) {
                return ASC;
            }
        }
    }
}
