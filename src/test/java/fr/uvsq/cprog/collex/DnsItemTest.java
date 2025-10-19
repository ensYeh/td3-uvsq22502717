package fr.uvsq.cprog.collex;

import org.junit.Test;
import static org.junit.Assert.*;

public class DnsItemTest {

  @Test
  public void testCreation() {
    AdresseIP ip = new AdresseIP("1.1.1.1");
    NomMachine nm = new NomMachine("cloudflare.com");
    DnsItem item = new DnsItem(ip, nm);
    assertEquals(ip, item.getAdresse());
    assertEquals(nm, item.getNom());
  }

  @Test
  public void testToString() {
    DnsItem item = new DnsItem(new AdresseIP("8.8.8.8"), new NomMachine("google.com"));
    assertTrue(item.toString().contains("google.com"));
  }
}

