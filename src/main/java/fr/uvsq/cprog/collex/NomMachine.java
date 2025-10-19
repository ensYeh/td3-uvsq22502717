package fr.uvsq.cprog.collex;

/** Représente un nom de machine pleinement qualifié. */
public class NomMachine {
  private final String nom;

  public NomMachine(String nom) {
    if (!nom.contains(".")) {
      throw new IllegalArgumentException("Nom de machine invalide : " + nom);
    }
    this.nom = nom.toLowerCase();
  }

  public String getNom() {
    return nom;
  }

  public String getDomaine() {
    return nom.substring(nom.indexOf('.') + 1);
  }

  @Override
  public String toString() {
    return nom;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof NomMachine)) {
      return false;
    }
    return nom.equals(((NomMachine) o).nom);
  }

  @Override
  public int hashCode() {
    return nom.hashCode();
  }
}
