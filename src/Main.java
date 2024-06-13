import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DatabaseNotation.createTable();  // Créer la table des étudiants dans la base de données

        Scanner scanner = new Scanner(System.in);
        int choix;

        do {
            afficherMenu();
            choix = scanner.nextInt();
            scanner.nextLine();  // Consommer la nouvelle ligne

            switch (choix) {
                case 1:
                    DatabaseNotation.afficherEtudiants();  // Afficher toutes les notes
                    break;
                case 2:
                    System.out.print("Entrez l'ID de l'étudiant: ");
                    int id = scanner.nextInt();
                    DatabaseNotation.afficherNotesEtudiant(id);  // Afficher les notes d'un étudiant particulier
                    break;
                case 3:
                    System.out.print("Entrez l'ID de l'étudiant: ");
                    int idNote = scanner.nextInt();
                    System.out.print("Entrez la nouvelle note de l'étudiant: ");
                    double note = scanner.nextDouble();
                    DatabaseNotation.ajouterNoteEtudiant(idNote, note);  // Ajouter une note à un étudiant
                    break;
                case 4:
                    System.out.print("Entrez l'ID de l'étudiant: ");
                    int idMoyenne = scanner.nextInt();
                    DatabaseNotation.afficherMoyenneEtudiant(idMoyenne);  // Afficher la moyenne d'un étudiant
                    break;
                case 5:
                    DatabaseNotation.afficherMoyenne();  // Afficher la moyenne de tous les étudiants
                    break;
                case 0:
                    System.out.println("Au revoir!");
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        } while (choix != 0);
    }

    // Méthode pour afficher le menu
    public static void afficherMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. Afficher toutes les notes");
        System.out.println("2. Afficher les notes d'un étudiant");
        System.out.println("3. Ajouter une note à un étudiant");
        System.out.println("4. Afficher la moyenne d'un étudiant");
        System.out.println("5. Afficher la moyenne de tous les étudiants");
        System.out.println("0. Quitter");
        System.out.print("Entrez votre choix: ");
    }
}
