public class Utility {

    protected void selectionSort(Carta[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j].getPuntiTotali() < array[minIndex].getPuntiTotali()) {
                    minIndex = j;
                }
            }

            Carta temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
    }
}
