package fr.uvsq.cprog.collex;

import static org.junit.Assert.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Test;

public class DnsTest {

  private Path tempFile() {
    // For tests utiliser null, 
    return Paths.get("dns_test.txt");
  }

  @Test
  public void testAddAndRetrieveItem() throws Exception {
    Dns dns = new Dns(tempFile());
    dns.addItem(new AdresseIP("8.8.8.8"), new NomMachine("google.com"));

    DnsItem item1 = dns.getItem(new AdresseIP("8.8.8.8"));
    assertEquals("google.com", item1.getNom().getNom());

    DnsItem item2 = dns.getItem(new NomMachine("google.com"));
    assertEquals("8.8.8.8", item2.getAdresse().getAdresse());
  }

  @Test
  public void testGetItemsByDomain() throws Exception {
    Dns dns = new Dns(tempFile());
    dns.addItem(new AdresseIP("1.1.1.1"), new NomMachine("a.cloudflare.com"));
    dns.addItem(new AdresseIP("1.0.0.1"), new NomMachine("b.cloudflare.com"));

    List<DnsItem> items = dns.getItems("cloudflare.com", true);
    assertEquals(2, items.size());
  }
}
