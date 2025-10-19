package fr.uvsq.cprog.collex.command;

import fr.uvsq.cprog.collex.Dns;
import fr.uvsq.cprog.collex.AdresseIP;
import fr.uvsq.cprog.collex.DnsItem;

/** Commande pour obtenir le nom de machine à partir d'une adresse IP. */
public class GetNameCommande implements Commande {
  private final String ip;

  public GetNameCommande(String ip) {
    this.ip = ip;
  }

  @Override
  public String execute(Dns dns) {
    DnsItem item = dns.getItem(new AdresseIP(ip));
    return item == null ? "Adresse inconnue." : item.getNom().toString();
  }

  /** Retourne l'adresse IP associée à cette commande. */
  public String getIp() {
    return ip;
  }
}


