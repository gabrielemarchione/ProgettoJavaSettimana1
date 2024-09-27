import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ElementoMultimediale[] elementi = new ElementoMultimediale[5];

        // Creazione degli elementi multimediali
        for (int i = 0; i < 5; i++) {
            int tipo = 0;

            // Ciclo per garantire che l'utente inserisca un valore valido (1, 2 o 3)
            while (tipo < 1 || tipo > 3) {
                try {
                    System.out.println("Inserisci il tipo di elemento (1: Immagine, 2: Audio, 3: Video): ");
                    tipo = scanner.nextInt();
                    if (tipo < 1 || tipo > 3) {
                        System.out.println("Scelta non valida. Inserisci 1, 2 o 3.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Input non valido. Devi inserire un numero (1, 2 o 3).");
                    scanner.next();  // Consumare il valore non valido
                }
            }

            scanner.nextLine(); // Consumare la newline rimasta

            System.out.println("Inserisci il titolo: ");
            String titolo = scanner.nextLine();

            if (tipo == 1) {
                int luminosita = leggiValoreIntero(scanner, "Inserisci la luminosità (0-10): ", 0, 10);
                elementi[i] = new Immagine(titolo, luminosita);
            } else if (tipo == 2) {
                // Richiedi la durata direttamente in secondi
                int durata = leggiValoreIntero(scanner, "Inserisci la durata (in secondi): ", 1, Integer.MAX_VALUE);
                int volume = leggiValoreIntero(scanner, "Inserisci il volume (0-10): ", 0, 10);
                elementi[i] = new Audio(titolo, durata, volume);
            } else if (tipo == 3) {
                // Richiedi la durata direttamente in secondi
                int durata = leggiValoreIntero(scanner, "Inserisci la durata (in secondi): ", 1, Integer.MAX_VALUE);
                int volume = leggiValoreIntero(scanner, "Inserisci il volume (0-10): ", 0, 10);
                int luminosita = leggiValoreIntero(scanner, "Inserisci la luminosità (0-10): ", 0, 10);
                elementi[i] = new Video(titolo, durata, volume, luminosita);
            }
        }

        // Ciclo per eseguire gli elementi multimediali
        int scelta = -1;
        while (scelta != 0) {
            try {
                System.out.println("Quale elemento vuoi eseguire? (1-5, 0 per terminare): ");
                scelta = scanner.nextInt();
                if (scelta > 0 && scelta <= 5) {
                    ElementoMultimediale elemento = elementi[scelta - 1];

                    // Controlla se l'elemento esiste prima di eseguirlo
                    if (elemento != null) {
                        System.out.println("Titolo dell'elemento: " + elemento.getTitolo());

                        if (elemento instanceof Immagine) {
                            Immagine immagine = (Immagine) elemento;

                            // Chiedi se l'utente vuole regolare la luminosità
                            int regolaLuminosita = leggiValoreIntero(scanner, "Vuoi regolare la luminosità? (1: Aumenta, 2: Diminuisci, 0: No): ", 0, 2);
                            if (regolaLuminosita == 1) {
                                immagine.aumentaLuminosita();
                            } else if (regolaLuminosita == 2) {
                                immagine.diminuisciLuminosita();
                            }

                            // Mostra l'immagine con la luminosità attuale
                            immagine.esegui();

                        } else if (elemento instanceof Riproducibile) {
                            Riproducibile riproducibile = (Riproducibile) elemento;

                            // Stampa la durata dell'elemento
                            System.out.println("Durata dell'elemento: " + riproducibile.getDurata() + " secondi");

                            // Regola il volume o la luminosità se è un video
                            if (elemento instanceof Audio) {
                                Audio audio = (Audio) elemento;
                                int regolaVolume = leggiValoreIntero(scanner, "Vuoi regolare il volume? (1: Alza, 2: Abbassa, 0: No): ", 0, 2);
                                if (regolaVolume == 1) {
                                    audio.alzaVolume();
                                } else if (regolaVolume == 2) {
                                    audio.abbassaVolume();
                                }
                            } else if (elemento instanceof Video) {
                                Video video = (Video) elemento;
                                int regolaVolume = leggiValoreIntero(scanner, "Vuoi regolare il volume? (1: Alza, 2: Abbassa, 0: No): ", 0, 2);
                                if (regolaVolume == 1) {
                                    video.alzaVolume();
                                } else if (regolaVolume == 2) {
                                    video.abbassaVolume();
                                }
                                int regolaLuminosita = leggiValoreIntero(scanner, "Vuoi regolare la luminosità? (1: Aumenta, 2: Diminuisci, 0: No): ", 0, 2);
                                if (regolaLuminosita == 1) {
                                    video.aumentaLuminosita();
                                } else if (regolaLuminosita == 2) {
                                    video.diminuisciLuminosita();
                                }
                            }

                            // Esegui l'elemento selezionato
                            riproducibile.play();
                        }

                    } else {
                        System.out.println("L'elemento selezionato non esiste.");
                    }
                } else if (scelta == 0) {
                    System.out.println("Chiusura del lettore multimediale.");
                } else {
                    System.out.println("Scelta non valida. Riprova.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Input non valido. Devi inserire un numero.");
                scanner.next();  // Consumare l'input non valido
            }
        }

        scanner.close();
    }

    // Metodo per leggere un valore intero con range valido
    public static int leggiValoreIntero(Scanner scanner, String messaggio, int min, int max) {
        int valore = -1;
        while (valore < min || valore > max) {
            try {
                System.out.println(messaggio);
                valore = scanner.nextInt();
                if (valore < min || valore > max) {
                    System.out.println("Il valore deve essere compreso tra " + min + " e " + max + ".");
                }
            } catch (InputMismatchException e) {
                System.out.println("Input non valido. Devi inserire un numero.");
                scanner.next(); // Consumare l'input non valido
            }
        }
        return valore;
    }
}
