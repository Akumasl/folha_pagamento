public class ColaboradorPadrao extends Colaborador {

    public ColaboradorPadrao(int matricula, String nome) {
        super(matricula, nome);
    }

    @Override
    public String getTipo() {
        return "Padrão";
    }

    @Override
    public double calcularSalarioFinal() {
        return getSalarioBase();
    }

    @Override
    public void exibirDados() {
        super.exibirDados();
        System.out.println("Salário Final: " + Util.moeda(calcularSalarioFinal()));
    }
}
