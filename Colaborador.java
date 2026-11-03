public class Colaborador {

    // Constante que representa o salário base de todos os colaboradores
    public static final double SALARIO_BASE = 2000.00;

    private int matricula;
    private String nome;
    private double salarioBase;

    public Colaborador(int matricula, String nome) {
        this.matricula = matricula;
        this.nome = nome;
        this.salarioBase = SALARIO_BASE;
    }

    // Getters e setters (encapsulamento)
    public int getMatricula() { return matricula; }
    public void setMatricula(int matricula) { this.matricula = matricula; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public double getSalarioBase() { return salarioBase; }
    public void setSalarioBase(double salarioBase) { this.salarioBase = salarioBase; }

    // Tipo do colaborador (sobrescrito nas subclasses)
    public String getTipo() {
        return "Colaborador";
    }

    public double calcularSalarioFinal() {
        return salarioBase;
    }

    public void exibirDados() {
        System.out.println("Matrícula: " + matricula);
        System.out.println("Nome: " + nome);
        System.out.println("Tipo: " + getTipo());
        System.out.println("Salário Base: " + Util.moeda(salarioBase));
    }
}
