import java.util.Scanner;

public class LettoreMultimediale {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ElementoMultimediale[] elementi = new ElementoMultimediale[5];

        // Creazione degli elementi multimediali
        for (int i = 0; i < 5; i++) {
            int tipo = 0;

            // Ciclo per garantire che l'utente inserisca un valore valido (1, 2 o 3)
            while (tipo < 1 || tipo > 3) {
                System.out.println("Inserisci il tipo di elemento (1: Immagine, 2: Audio, 3: Video): ");
                tipo = scanner.nextInt();
                if (tipo < 1 || tipo > 3) {
                    System.out.println("Scelta non valida. Inserisci 1, 2 o 3.");
                }
            }

            scanner.nextLine();  // Consumare la newline rimasta

            System.out.println("Inserisci il titolo: ");
            String titolo = scanner.nextLine();

            if (tipo == 1) {
                System.out.println("Inserisci la luminosità: ");
                int luminosita = scanner.nextInt();
                elementi[i] = new Immagine(titolo, luminosita);
            } else if (tipo == 2) {
                System.out.println("Inserisci la durata (in formato minuti:secondi, es. 3:45): ");
                String durataInput = scanner.next();
                int durata = convertiDurata(durataInput);
                System.out.println("Inserisci il volume: ");
                int volume = scanner.nextInt();
                elementi[i] = new Audio(titolo, durata, volume);
            } else if (tipo == 3) {
                System.out.println("Inserisci la durata (in formato minuti:secondi, es. 3:45): ");
                String durataInput = scanner.next();
                int durata = convertiDurata(durataInput);
                System.out.println("Inserisci il volume: ");
                int volume = scanner.nextInt();
                System.out.println("Inserisci la luminosità: ");
                int luminosita = scanner.nextInt();
                elementi[i] = new Video(titolo, durata, volume, luminosita);
            }
        }

        // Ciclo per eseguire gli elementi multimediali
        int scelta = -1;
        while (scelta != 0) {
            System.out.println("Quale elemento vuoi eseguire? (1-5, 0 per terminare): ");
            scelta = scanner.nextInt();
            if (scelta > 0 && scelta <= 5) {
                ElementoMultimediale elemento = elementi[scelta - 1];

                // Controlla se l'elemento esiste prima di eseguirlo
                if (elemento != null) {
                    if (elemento instanceof Audio) {
                        Audio audio = (Audio) elemento;
                        System.out.println("Vuoi regolare il volume? (1: Alza, 2: Abbassa, 0: No): ");
                        int regolaVolume = scanner.nextInt();
                        if (regolaVolume == 1) {
                            audio.alzaVolume();
                        } else if (regolaVolume == 2) {
                            audio.abbassaVolume();
                        }
                    } else if (elemento instanceof Video) {
                        Video video = (Video) elemento;
                        System.out.println("Vuoi regolare il volume? (1: Alza, 2: Abbassa, 0: No): ");
                        int regolaVolume = scanner.nextInt();
                        if (regolaVolume == 1) {
                            video.alzaVolume();
                        } else if (regolaVolume == 2) {
                            video.abbassaVolume();
                        }
                        System.out.println("Vuoi regolare la luminosità? (1: Aumenta, 2: Diminuisci, 0: No): ");
                        int regolaLuminosita = scanner.nextInt();
                        if (regolaLuminosita == 1) {
                            video.aumentaLuminosita();
                        } else if (regolaLuminosita == 2) {
                            video.diminuisciLuminosita();
                        }
                    } else if (elemento instanceof Immagine) {
                        Immagine immagine = (Immagine) elemento;
                        System.out.println("Vuoi regolare la luminosità? (1: Aumenta, 2: Diminuisci, 0: No): ");
                        int regolaLuminosita = scanner.nextInt();
                        if (regolaLuminosita == 1) {
                            immagine.aumentaLuminosita();
                        } else if (regolaLuminosita == 2) {
                            immagine.diminuisciLuminosita();
                        }
                    }

                    // Esegui l'elemento selezionato
                    elemento.esegui();
                } else {
                    System.out.println("L'elemento selezionato non esiste.");
                }
            } else if (scelta == 0) {
                System.out.println("Chiusura del lettore multimediale.");
            } else {
                System.out.println("Scelta non valida. Riprova.");
            }
        }

        scanner.close();
    }

    // Metodo per convertire la durata in formato minuti:secondi in secondi totali
    public static int convertiDurata(String durataInput) {
        String[] parti = durataInput.split(":");
        int minuti = Integer.parseInt(parti[0]);
        int secondi = Integer.parseInt(parti[1]);
        return minuti * 60 + secondi;
    }
}
