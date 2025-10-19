package fr.uvsq.cprog.collex;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/** Gère la base de données DNS. */
public class Dns {
  private final Map<AdresseIP, NomMachine> ipToName = new HashMap<>();
  private final Map<NomMachine, AdresseIP> nameToIp = new HashMap<>();
  private final Path fichier;

  public Dns(Path fichier) throws IOException {
    this.fichier = fichier;
    charger();
  }

  private void charger() throws IOException {
    if (!Files.exists(fichier)) {
      return;
    }
    for (String ligne : Files.readAllLines(fichier)) {
      String[] parts = ligne.trim().split("\\s+");
      if (parts.length == 2) {
        addItem(new AdresseIP(parts[1]), new NomMachine(parts[0]), false);
      }
    }
  }

  private void sauvegarder() throws IOException {
    List<String> lignes = ipToName.entrySet().stream().map(e -> e.getValue() + " " + e.getKey())
        .sorted().collect(Collectors.toList());
    Files.write(fichier, lignes);
  }

  public DnsItem getItem(AdresseIP ip) {
    NomMachine nom = ipToName.get(ip);
    return nom == null ? null : new DnsItem(ip, nom);
  }

  public DnsItem getItem(NomMachine nom) {
    AdresseIP ip = nameToIp.get(nom);
    return ip == null ? null : new DnsItem(ip, nom);
  }

  public List<DnsItem> getItems(String domaine, boolean sortByIp) {
    Comparator<DnsItem> cmp = sortByIp ? Comparator.comparing(i -> i.getAdresse().getAdresse())
        : Comparator.comparing(i -> i.getNom().getNom());
    return ipToName.entrySet().stream().filter(e -> e.getValue().getDomaine().equals(domaine))
        .map(e -> new DnsItem(e.getKey(), e.getValue())).sorted(cmp).collect(Collectors.toList());
  }

  public void addItem(AdresseIP ip, NomMachine nom) throws IOException {
    addItem(ip, nom, true);
  }

  private void addItem(AdresseIP ip, NomMachine nom, boolean save) throws IOException {
    if (ipToName.containsKey(ip)) {
      throw new IllegalArgumentException("ERREUR : L'adresse existe déjà !");
    }
    if (nameToIp.containsKey(nom)) {
      throw new IllegalArgumentException("ERREUR : Le nom de machine existe déjà !");
    }
    ipToName.put(ip, nom);
    nameToIp.put(nom, ip);
    if (save) {
      sauvegarder();
    }
  }
}

