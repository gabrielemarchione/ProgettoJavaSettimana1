public class Video extends ElementoMultimediale implements Riproducibile {
    private final int durata;
    private int volume;
    private int luminosita;

    public Video(String titolo, int durata, int volume, int luminosita) {
        super(titolo);
        this.durata = durata;
        this.volume = Math.max(0, Math.min(volume, 10)); // Volume nel range 0-10
        this.luminosita = Math.max(0, Math.min(luminosita, 10)); // Luminosità nel range 0-10
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

    public void aumentaLuminosita() {
        if (luminosita < 10) {
            luminosita++;
        }
    }

    public void diminuisciLuminosita() {
        if (luminosita > 0) {
            luminosita--;
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

        // Stampa la sequenza di punti esclamativi e asterischi un numero di volte pari alla durata
        for (int i = 0; i < durata; i++) {
            System.out.println(" " + "!".repeat(volume) + "*".repeat(luminosita));
        }
    }

    @Override
    public void esegui() {
        play();
    }
}
