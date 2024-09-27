// Audio.java
public class Audio extends ElementoMultimediale implements Riproducibile {
    private final int durata;
    private int volume;

    public Audio(String titolo, int durata, int volume) {
        super(titolo);
        this.durata = durata;
        this.volume = Math.max(0, Math.min(volume, 10)); // Assicura che il volume sia nel range 0-10
    }

    public void alzaVolume() {
        if (volume < 10) {
            volume++;
        }
    }

    public void abbassaVolume() {
        if (volume > 0) {
            volume--;
        }
    }

    @Override
    public int getDurata() {
        return durata;
    }

    @Override
    public void play() {
        // Stampa il titolo una sola volta
        System.out.println(getTitolo());

        // Stampa la sequenza di punti esclamativi un numero di volte pari alla durata
        for (int i = 0; i < durata; i++) {
            System.out.println(" " + "!".repeat(volume));
        }
    }

    @Override
    public void esegui() {
        play();
    }
}

