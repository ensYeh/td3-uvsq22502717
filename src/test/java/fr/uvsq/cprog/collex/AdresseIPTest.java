package fr.uvsq.cprog.collex;

import org.junit.Test;
import static org.junit.Assert.*;

public class AdresseIPTest {

  @Test
  public void testValidIp() {
    AdresseIP ip = new AdresseIP("192.168.1.1");
    assertEquals("192.168.1.1", ip.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidIp() {
    new AdresseIP("999.0.0.1");
  }

  @Test
  public void testEqualsAndHashCode() {
    AdresseIP ip1 = new AdresseIP("8.8.8.8");
    AdresseIP ip2 = new AdresseIP("8.8.8.8");
    assertEquals(ip1, ip2);
    assertEquals(ip1.hashCode(), ip2.hashCode());
  }
}

