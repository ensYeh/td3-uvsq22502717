package fr.uvsq.cprog.collex.tui;

import fr.uvsq.cprog.collex.command.AddItemCommande;
import fr.uvsq.cprog.collex.command.Commande;
import fr.uvsq.cprog.collex.command.GetIpCommande;
import fr.uvsq.cprog.collex.command.GetNameCommande;
import fr.uvsq.cprog.collex.command.ListDomainCommande;
import fr.uvsq.cprog.collex.command.QuitCommande;
import java.util.Scanner;

/** Interface textuelle pour interagir avec l'utilisateur. */
public class DnsTUI {

  private final Scanner scanner;

  /** Конструктор по умолчанию для интерактивного использования */
  public DnsTUI() {
    this.scanner = new Scanner(System.in);
  }

  /** Новый конструктор для автоматических тестов с переданным Scanner */
  public DnsTUI(Scanner scanner) {
    this.scanner = scanner;
  }

  /**
   * Lit la ligne de commande et retourne la commande correspondante.
   *
   * @return la commande ou null si invalide
   */
  public Commande nextCommande() {
    System.out.print("> ");
    if (!scanner.hasNextLine()) {
      return new QuitCommande();
    }
    String ligne = scanner.nextLine().trim();

    if (ligne.equalsIgnoreCase("quit")) {
      return new QuitCommande();
    } else if (ligne.startsWith("add ")) {
      String[] parts = ligne.split("\\s+");
      if (parts.length >= 3) {
        return new AddItemCommande(parts[1], parts[2]);
      }
    } else if (ligne.startsWith("ls")) {
      boolean sortByIp = ligne.contains("-a");
      String domaine = ligne.replaceAll("ls( -a)?\\s*", "");
      return new ListDomainCommande(domaine, sortByIp);
    } else if (ligne.matches("^\\d+\\.\\d+\\.\\d+\\.\\d+$")) {
      return new GetNameCommande(ligne);
    } else if (ligne.contains(".")) {
      return new GetIpCommande(ligne);
    }

    return null;
  }

  /**
   * Affiche un message à l'utilisateur.
   *
   * @param message texte à afficher
   */
  public void affiche(String message) {
    System.out.println(message);
  }
}


