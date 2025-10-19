package fr.uvsq.cprog.collex;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.Assert.*;

public class DnsTest {

  private Path tempFile;
  private Dns dns;

  @Before
  public void setup() throws Exception {
    // Create temporary file DNS
    tempFile = Files.createTempFile("dns_test", ".txt");
    dns = new Dns(tempFile);
  }

  @After
  public void teardown() throws Exception {
    // Delete temporary file after the test
    Files.deleteIfExists(tempFile);
  }

  @Test
  public void testAddAndRetrieveItem() throws Exception {
    // Add record
    dns.addItem(new AdresseIP("8.8.8.8"), new NomMachine("google.com"));

    // Check IP
    DnsItem item1 = dns.getItem(new AdresseIP("8.8.8.8"));
    assertEquals("google.com", item1.getNom().getNom());

    // Check Nom
    DnsItem item2 = dns.getItem(new NomMachine("google.com"));
    assertEquals("8.8.8.8", item2.getAdresse().getAdresse());
  }

  @Test
  public void testGetItemsByDomain() throws Exception {
    // Add records
    dns.addItem(new AdresseIP("1.1.1.1"), new NomMachine("a.cloudflare.com"));
    dns.addItem(new AdresseIP("1.0.0.1"), new NomMachine("b.cloudflare.com"));

    // Receive DNS
    List<DnsItem> items = dns.getItems("cloudflare.com", false);
    assertEquals(2, items.size());

    // Check sorting
    assertEquals("a.cloudflare.com", items.get(0).getNom().getNom());
    assertEquals("b.cloudflare.com", items.get(1).getNom().getNom());
  }
}

