
import java.util.Scanner;

public class Partita {

    private Giocatore giocatore1;
    private Giocatore giocatore2;
    private int turno;
    private boolean primoTurno;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        /*
         //* System.out.print("Nome giocatore 1: ");
         * String nome1 = scanner.nextLine();
         * 
         * System.out.print("Nome giocatore 2: ");
         * String nome2 = scanner.nextLine();
         */

        String nome1 = "pippo";
        String nome2 = "franco";
        Partita partita = new Partita(nome1, nome2);

        partita.gioca();
        partita.PerMerito();
        partita.getVincitore();
        scanner.close();
    }

    private void PerMerito() {
        if (giocatore1.getNome().toUpperCase().equals("EAMON") || giocatore2.getNome().toUpperCase().equals("EAMON")) {
            System.out.println("Il vincitore è EAMON per merito!");
            return;
        }
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
            att.battaglia(dif);
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
            System.out.println("Il vincitore è " + giocatore2.getNome());
            return giocatore2;
        } else if (giocatore2.getPuntiVita() <= 0) {
            System.out.println("Il vincitore è " + giocatore1.getNome());
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
            System.out.println("-------------------------" + "\n");
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
