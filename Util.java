import java.util.Locale;

// Classe auxiliar para formatar valores em reais (R$ 2.000,00)
public class Util {
    public static String moeda(double valor) {
        return "R$ " + String.format(Locale.forLanguageTag("pt-BR"), "%,.2f", valor);
    }
}
