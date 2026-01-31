public class Giocatore {
private String nome;
private Carta[] mazzo = new Carta[50];
private Carta[] mano = new Carta[10];
private Carta[] cartaInGioco = new Carta[5];
private int puntiVita = 3;

public Giocatore(Carta[] mazzo, Carta[] mano) {
    this.mazzo = mazzo;
    this.mano = mano;
    generaMazzo(mazzo);
    pescaCarteIniziali(mano);
    
    }

    private void generaMazzo(Carta[] mazzo) {
        for (int i = 0; i < mazzo.length; i++) {
        Carta carta = new Carta();
        mazzo[i] = carta;
        }
    }
        private static void selectionSort(Carta[] array) {            
            for (int i = 0; i < array.length - 1; i++) {
                int minIndex = i;
                for (int j = i + 1; j < array.length; j++) {
                    if (array[j].getSomma() < array[minIndex].getSomma()) {
                        minIndex = j;
                    }
                }
                
                Carta temp = array[minIndex];
                array[minIndex] = array[i];
                array[i] = temp;
            }
        }

    private void pescaCarteIniziali(Carta[] mano) {
        for (int i = 0; i < 5; i++) {
            mano[i] = mazzo[i];  
        }
    }

    public void battglia(Giocatore avversario){
    selectionSort(cartaInGioco);
    selectionSort(mazzo);
    for (Carta carta : avversario.cartaInGioco) {
        
    }   
    
    }

}
