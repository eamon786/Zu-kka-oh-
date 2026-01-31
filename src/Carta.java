public class Carta {

    public String nome;
    public int hp;
    public int atk;
    public int def;
    public String target;
    public int somma;

    public Carta() {
        do {
            this.hp = (int) (Math.random() * 50);
            this.atk = (int) (Math.random() * 50);
            this.def = (int) (Math.random() * 50);
            this.somma = hp + atk + def;
        } while (somma > 100);
        this.target = targets.values()[(int) (Math.random() * targets.values().length)].toString();
        generanome();
    }
    
    private void generanome() {
        String[] prefissi = {"Drago", "Lupo", "Tigre", "Serpente", "Aquila", "Golem", "Spirito", "Ombra", "Fuoco", "Ghiaccio", "Tuono", "Terra", "Vento", "Luce", "Oscuro"};
        String[] suffissi = {"Antico", "Furioso", "Mistico", "Selvaggio", "Celeste", "Infernale", "Glaciale", "Tonante", "Sacro", "Maledetto", "Supremo", "Eterno", "Fatale", "Divino", "Spettrale"};
        this.nome = prefissi[(int) (Math.random() * prefissi.length)] + " " + suffissi[(int) (Math.random() * suffissi.length)];
    }

    public enum targets {
        ATK_FORTE,
        ATK_DEBOLE,
        DEF_FORTE,
        DEF_DEBOLE,
        HP_ALTO,
        HP_BASSO;
    }

    public int getSomma() {
        return somma;
    }

    @Override
    public String toString() {
        return "{" +
                "\n nome: " + nome +
                ",\n hp: " + hp +
                ",\n atk: " + atk +
                ",\n def: " + def +
                ",\n target: " + target +
                ",\n Punti Totali: " + getSomma() +
                "\n}";
    }

    public static void main(String[] args) {
        Carta carta = new Carta();
        System.out.println(carta.toString());
    }
}
