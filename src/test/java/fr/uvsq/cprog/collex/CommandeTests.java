package fr.uvsq.cprog.collex.command;

import fr.uvsq.cprog.collex.Dns;
import fr.uvsq.cprog.collex.AdresseIP;
import fr.uvsq.cprog.collex.NomMachine;
import fr.uvsq.cprog.collex.DnsItem;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.Assert.*;

/** Tests unitaires pour les commandes DNS. */
public class CommandeTests {

  private Dns dns;
  private Path tempFile;

  @Before
  public void setUp() throws Exception {
    // Создаём временный файл для базы DNS
    tempFile = Files.createTempFile("dns_test", ".txt");
    dns = new Dns(tempFile);
  }

  @Test
  public void testAddItemCommande() throws Exception {
    Commande addCmd = new AddItemCommande("8.8.8.8", "google.com");
    String result = addCmd.execute(dns);

    DnsItem item = dns.getItem(new AdresseIP("8.8.8.8"));
    assertEquals("google.com", item.getNom().getNom());
    assertEquals("Ajout réussi.", result); // <-- исправлено на актуальное сообщение
  }


  @Test
  public void testGetIpCommande() throws Exception {
    dns.addItem(new AdresseIP("1.1.1.1"), new NomMachine("cloudflare.com"));
    Commande getIpCmd = new GetIpCommande("cloudflare.com");

    String result = getIpCmd.execute(dns);
    assertEquals("1.1.1.1", result);
  }

  @Test
  public void testGetNameCommande() throws Exception {
    dns.addItem(new AdresseIP("1.0.0.1"), new NomMachine("one.one.one.one"));
    Commande getNameCmd = new GetNameCommande("1.0.0.1");

    String result = getNameCmd.execute(dns);
    assertEquals("one.one.one.one", result);
  }

  @Test
  public void testListDomainCommande() throws Exception {
    dns.addItem(new AdresseIP("1.1.1.1"), new NomMachine("a.cloudflare.com"));
    dns.addItem(new AdresseIP("1.0.0.1"), new NomMachine("b.cloudflare.com"));

    Commande listCmd = new ListDomainCommande("cloudflare.com", false);
    String result = listCmd.execute(dns);

    assertTrue(result.contains("a.cloudflare.com"));
    assertTrue(result.contains("b.cloudflare.com"));
  }

  // Удаление временного файла после тестов
  @org.junit.After
  public void tearDown() throws Exception {
    Files.deleteIfExists(tempFile);
  }
}
