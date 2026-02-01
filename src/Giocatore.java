
public class Giocatore {
    private String nome;
    private Carta[] mazzo = new Carta[50];
    private Carta[] mano = new Carta[10];
    private Carta[] cartaInGioco = new Carta[5];
    private int puntiVita = 3;

    public Giocatore(String nome) {
        this.nome = nome;
        generaMazzo(mazzo);
        pescaCarteIniziali(mano);

    }

    void pesca() {
        boolean pieno = true;
        for (Carta carta : mano) {
            if (carta == null) {
                pieno = false;
                break;
            }
        }
        if (pieno) {
            System.out.println(nome + " non può pescare altre carte, la mano è piena.");
            return;
        }

        if (!pieno) {
            for (int i = 0; i < mazzo.length; i++) {
                if (mazzo[i] != null) {
                    for (int j = 0; j < mano.length; j++) {
                        if (mano[j] == null) {
                            mano[j] = mazzo[i];
                            mazzo[i] = null;
                            System.out.println(nome + " ha pescato la carta: " + mano[j].getNome());
                            return;
                        }
                    }
                }
            }
        }

    }

    private void generaMazzo(Carta[] mazzo) {
        for (int i = 0; i < mazzo.length; i++) {
            Carta carta = new Carta();
            mazzo[i] = carta;
        }
    }

    private static void selectionSort(Carta[] array) {
        for (int i = 0; i < array.length - 1; i++) {

            int min = -1;
            for (int k = i; k < array.length; k++) {
                if (array[k] != null) {
                    min = k;
                    break;
                }
            }
            if (min == -1)
                return;

            for (int j = min + 1; j < array.length; j++) {
                if (array[j] == null)
                    continue;
                if (array[j].getPuntiTotali() < array[min].getPuntiTotali())
                    min = j;
            }

            Carta tmp = array[min];
            array[min] = array[i];
            array[i] = tmp;
        }
    }

    private void pescaCarteIniziali(Carta[] mano) {
        for (int i = 0; i < 5; i++) {
            mano[i] = mazzo[i];
            mazzo[i] = null;
        }
    }
    /*
     * 1. Ordina le carte sul campo di A per punti totali crescenti
     * 2. Per ogni carta di A (dalla più debole alla più forte):
     * Trova il bersaglio sul campo di B in base al target della carta
     * Calcola il danno: danno = il massimo tra attaccante.atk - bersaglio.def e 1
     * (ogni
     * attacco fa almeno 1 danno)
     * Sottrai il danno dagli HP del bersaglio
     * Se gli HP del bersaglio scendono a 0 o meno, rimuovilo dal campo
     * Esempio:
     * Attacca la Carta A: ATK=30, target="DEF_DEBOLE"
     * Il campo del giocatore B: [Mostro1 DEF=10, Mostro2 DEF=25, Mostro3 DEF=15]
     * Il bersaglio sarà Mostro1 (DEF più bassa)
     * Il danno sarà 30 - 10 = 20
     */

    public void battaglia(Giocatore avversario) {
        selectionSort(cartaInGioco);

        for (int i = 0; i < cartaInGioco.length; i++) {
            Carta attaccante = cartaInGioco[i];
            if (attaccante == null)
                continue;

            int indiceBersaglio = trovaBersaglio(attaccante, avversario);
            if (indiceBersaglio == -1) {
                avversario.puntiVita--;
                continue;
            }

            Carta bersaglio = avversario.cartaInGioco[indiceBersaglio];
            int danno = Math.max(attaccante.getAtk() - bersaglio.getDef(), 1);
            bersaglio.setHp(bersaglio.getHp() - danno);
            System.out.println(
                    attaccante.getNome() + " attacca " + bersaglio.getNome() + " infliggendo " + danno + " danni.");

            if (bersaglio.getHp() <= 0) {
                avversario.cartaInGioco[indiceBersaglio] = null;
            }

        }
    }

    private int trovaBersaglio(Carta attaccante, Giocatore avversario) {
        String target = attaccante.getTarget();
        int best = -1;
        Carta bersaglio = null;

        for (int i = 0; i < avversario.cartaInGioco.length; i++) {
            Carta c = avversario.cartaInGioco[i];
            if (c == null)
                continue;

            switch (target) {
                case "ATK_FORTE":
                    if (best == -1) {
                        bersaglio = c;
                        best = i;
                    } else if (c.getAtk() > bersaglio.getAtk()) {
                        bersaglio = c;
                        best = i;
                    }
                    break;
                case "ATK_DEBOLE":
                    if (best == -1) {
                        best = i;
                        bersaglio = c;
                    } else if (c.getAtk() < bersaglio.getAtk()) {
                        best = i;
                        bersaglio = c;
                    }
                    break;
                case "DEF_FORTE":
                    if (best == -1) {
                        bersaglio = c;
                        best = i;
                    } else if (c.getDef() > bersaglio.getDef()) {
                        bersaglio = c;
                        best = i;
                    }
                    break;
                case "DEF_DEBOLE":
                    if (best == -1) {
                        bersaglio = c;
                        best = i;
                    } else if (c.getDef() < bersaglio.getDef()) {
                        bersaglio = c;
                        best = i;
                    }
                    break;
                case "HP_ALTO":
                    if (best == -1) {
                        bersaglio = c;
                        best = i;
                    } else if (c.getHp() > bersaglio.getHp()) {
                        bersaglio = c;
                        best = i;
                    }
                    break;
                case "HP_BASSO":
                    if (best == -1) {
                        bersaglio = c;
                        best = i;
                    } else if (c.getHp() < bersaglio.getHp()) {
                        bersaglio = c;
                        best = i;
                    }
                    break;
                default:
                    break;
            }
        }
        return best;
    }

    public String getNome() {
        return nome;
    }

    public Carta[] getMazzo() {
        return mazzo;
    }

    public Carta[] getMano() {
        return mano;
    }

    public Carta[] getCartaInGioco() {
        return cartaInGioco;
    }

    public int getPuntiVita() {
        return puntiVita;
    }

}
