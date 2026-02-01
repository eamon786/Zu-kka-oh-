
public class Partita {

    private Giocatore giocatore1;
    private Giocatore giocatore2;
    private int turno;
    private boolean primoTurno;

    public static void main(String[] args) {
        Partita partita = new Partita("Pino", "Gino");
        partita.gioca();
    }

    public Partita(String nome1, String nome2) {
        giocatore1 = new Giocatore(nome1);
        giocatore2 = new Giocatore(nome2);
        turno = 0;
        primoTurno = true;
    }

    // Gioca 1 solo turno. Se `turno` è pari, tocca al giocatore1,
    // altrimenti tocca al giocatore2
    public void turno() {
        Giocatore att = (turno % 2 == 0) ? giocatore1 : giocatore2;
        Giocatore dif = (turno % 2 == 0) ? giocatore2 : giocatore1;
        System.out.println("E' il turno di " + att.getNome());

        evocaCarte(att);

        if (turno > 0) {
            att.battglia(dif);
        }
        att.pesca();
        primoTurno = false;
    }

    private void evocaCarte(Giocatore att) {

        Carta[] mano = att.getMano();
        Carta[] campo = att.getCartaInGioco();
        boolean vuota = true;
        for (Carta c : mano) {
            if (c != null) {
                vuota = false;
                break;
            }
        }
        if (vuota) {
            System.out.println(att.getNome() + " non ha carte da evocare.");
            return;
        }
        int slot = -1;
        for (int i = 0; i < campo.length; i++) {
            if (campo[i] == null) {
                slot = i;
                break;
            }
        }
        if (slot == -1) {
            System.out.println(att.getNome() + " non ha spazio per evocare altre carte.");
            return;
        }
        selectionSort(mano);
        int idx = -1;
        for (int i = mano.length - 1; i >= 0; i--) {
            if (mano[i] != null) {
                idx = i;
                break;
            }
        }
        if (idx == -1) {
            System.out.println(att.getNome() + " non ha carte da evocare.");
            return;
        }

        Carta evocata = mano[idx];
        campo[slot] = evocata;
        mano[idx] = null;

        System.out.println(att.getNome() + " ha evocato la carta: " + evocata.getNome());
    }

    // Controlla se la partita è finita
    public boolean isFinita() {
        if (giocatore1.getPuntiVita() <= 0 || giocatore2.getPuntiVita() <= 0) {
            return true;
        }
        return false;
    }

    // Ritorna il vincitore
    public Giocatore getVincitore() {
        if (giocatore1.getPuntiVita() <= 0) {
            return giocatore2;
        } else if (giocatore2.getPuntiVita() <= 0) {
            return giocatore1;
        } else {
            return null;
        }
    }

    // Simula l'intera partita (vedi dopo)
    public void gioca() {
        while (!isFinita()) {
            System.out.println("Turno " + (turno + 1));
            System.out.println("Giocatore 1: " + giocatore1.getPuntiVita() + " HP");
            System.out.println("Giocatore 2: " + giocatore2.getPuntiVita() + " HP");
            System.out.println("-------------------------");
            turno();
            turno++;

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
                if (array[j].getHp() < array[min].getHp())
                    min = j;
            }

            Carta tmp = array[min];
            array[min] = array[i];
            array[i] = tmp;
        }
    }

}
