package fr.uvsq.cprog.collex.command;

import fr.uvsq.cprog.collex.Dns;

/** Commande pour quitter l’application. */
public class QuitCommande implements Commande {

  @Override
  public String execute(Dns dns) {
    System.exit(0);
    return "";
  }
}

