package fr.uvsq.cprog.collex;

import fr.uvsq.cprog.collex.tui.DnsTUI;
import fr.uvsq.cprog.collex.command.Commande;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DnsApp {

  private final Dns dns;
  private final DnsTUI tui;

  public DnsApp(Path dnsFile) throws IOException {
    // 
    Files.createDirectories(dnsFile.getParent());
    if (!Files.exists(dnsFile)) {
      Files.createFile(dnsFile);
    }

    this.dns = new Dns(dnsFile); // 
    this.tui = new DnsTUI(); // T
  }

  public void run() {
    System.out.println("=== DNS Manager ===");
    System.out.println("Commands:");
    System.out.println("  add <ip> <domain>      ? ajouter une entrée");
    System.out.println("  <ip>                   ? obtenir le nom par IP");
    System.out.println("  <domain>               ? obtenir l'IP par nom");
    System.out.println("  ls <domain> [--sort]   ? liste des machines par domaine");
    System.out.println("  quit                   ? quitter");
    System.out.println();

    while (true) {
      System.out.print("> ");
      try {
        Commande cmd = tui.nextCommande(); //
        String result = cmd.execute(dns); //
        tui.affiche(result); // 
      } catch (IOException e) {
        System.out.println("Erreur : " + e.getMessage());
      } catch (Exception e) {
        System.out.println("Erreur inattendue : " + e.getMessage());
      }
    }
  }

  public static void main(String[] args) throws IOException {
    Path dnsFile = Path.of("data/dns.txt"); // 
    DnsApp app = new DnsApp(dnsFile);
    app.run();
  }
}


