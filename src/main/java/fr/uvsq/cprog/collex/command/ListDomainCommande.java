package fr.uvsq.cprog.collex.command;

import fr.uvsq.cprog.collex.Dns;
import java.util.stream.Collectors;

/** Commande pour lister les machines d’un domaine. */
public class ListDomainCommande implements Commande {
  private final String domaine;
  private final boolean sortByIp;

  public ListDomainCommande(String domaine, boolean sortByIp) {
    this.domaine = domaine;
    this.sortByIp = sortByIp;
  }

  @Override
  public String execute(Dns dns) {
    return dns.getItems(domaine, sortByIp).stream().map(i -> i.getAdresse() + " " + i.getNom())
        .collect(Collectors.joining("\n"));
  }

  /** Retourne le nom du domaine. */
  public String getDomaine() {
    return domaine;
  }

  /** Indique si le tri doit se faire par adresse IP. */
  public boolean isSortByIp() {
    return sortByIp;
  }
}

