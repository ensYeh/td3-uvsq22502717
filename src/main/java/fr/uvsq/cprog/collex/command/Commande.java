package fr.uvsq.cprog.collex.command;

import fr.uvsq.cprog.collex.Dns;

/** Interface pour les commandes DNS. */
public interface Commande {
  String execute(Dns dns) throws Exception;
}


