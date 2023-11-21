package giovanna2005.ativ_firebase;

public class Atleta {
    private String nome;
    private String peso;
    private String idade;
    private String sexo;
    private String diaSemana;
    private String descritivoTreino;

    // Construtor vazio necessário para o Firebase
    public Atleta() {
    }

    // Construtor para criar um novo Atleta
    public Atleta(String nome, String peso, String idade, String sexo, String diaSemana, String descritivoTreino) {
        this.nome = nome;
        this.peso = peso;
        this.idade = idade;
        this.sexo = sexo;
        this.diaSemana = diaSemana;
        this.descritivoTreino = descritivoTreino;
    }

    // Getters
    public String getNome() {
        return nome;
    }

    public String getPeso() {
        return peso;
    }

    public String getIdade() {
        return idade;
    }

    public String getSexo() {
        return sexo;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public String getDescritivoTreino() {
        return descritivoTreino;
    }
}

