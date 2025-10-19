package fr.uvsq.cprog.collex.command;

import fr.uvsq.cprog.collex.Dns;
import fr.uvsq.cprog.collex.AdresseIP;
import fr.uvsq.cprog.collex.NomMachine;
import java.io.IOException;

/** Commande pour ajouter une nouvelle entrée dans le DNS. */
public class AddItemCommande implements Commande {
  private final String ip;
  private final String nom;

  public AddItemCommande(String ip, String nom) {
    this.ip = ip;
    this.nom = nom;
  }

  @Override
  public String execute(Dns dns) throws IOException {
    try {
      dns.addItem(new AdresseIP(ip), new NomMachine(nom));
      return "Ajout réussi.";
    } catch (IllegalArgumentException e) {
      return e.getMessage();
    }
  }

  public String getIp() {
    return ip;
  }

  public String getNom() {
    return nom;
  }
}


