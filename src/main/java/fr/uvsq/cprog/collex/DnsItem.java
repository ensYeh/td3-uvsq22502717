package fr.uvsq.cprog.collex;

/** Représente une entrée DNS (association IP ↔ nom de machine). */
public class DnsItem {
  private final AdresseIP adresse;
  private final NomMachine nom;

  public DnsItem(AdresseIP adresse, NomMachine nom) {
    this.adresse = adresse;
    this.nom = nom;
  }

  public AdresseIP getAdresse() {
    return adresse;
  }

  public NomMachine getNom() {
    return nom;
  }

  @Override
  public String toString() {
    return adresse + " " + nom;
  }
}
