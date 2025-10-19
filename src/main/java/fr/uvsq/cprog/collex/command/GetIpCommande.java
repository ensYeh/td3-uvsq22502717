package fr.uvsq.cprog.collex.command;

import fr.uvsq.cprog.collex.Dns;
import fr.uvsq.cprog.collex.DnsItem;
import fr.uvsq.cprog.collex.NomMachine;

/** Commande pour obtenir l'adresse IP d'un nom qualifié de machine. */
public class GetIpCommande implements Commande {
  private final String nom;

  public GetIpCommande(String nom) {
    this.nom = nom;
  }

  @Override
  public String execute(Dns dns) {
    DnsItem item = dns.getItem(new NomMachine(nom));
    return item == null ? "Nom inconnu." : item.getAdresse().toString();
  }

  /** Retourne le nom (argument de la commande). */
  public String getNom() {
    return nom;
  }
}

