public class Etudiant {
    private String id;
    private String nom;
    private double note;

    // Constructeur pour initialiser les attributs d'un étudiant
    public Etudiant(String id, String nom, double note) {
        this.id = id;
        this.nom = nom;
        this.note = note;
    }

    // Getters et setters pour accéder et modifier les attributs d'un étudiant
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getNote() {
        return note;
    }
    public void setNote(double note){
        if(note < 0 || note > 20 ){
            throw new IllegalArgumentException("la note doit être comprise entre  0 et 20");
        }
        this.note = note;
    }
}
