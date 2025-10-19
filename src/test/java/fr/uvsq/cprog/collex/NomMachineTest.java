package fr.uvsq.cprog.collex;

import org.junit.Test;
import static org.junit.Assert.*;

public class NomMachineTest {

  @Test
  public void testValidName() {
    NomMachine nm = new NomMachine("www.something.com");
    assertEquals("www.something.com", nm.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidName() {
    new NomMachine("invalid name!!!");
  }

  @Test
  public void testEquals() {
    NomMachine n1 = new NomMachine("test.local");
    NomMachine n2 = new NomMachine("test.local");
    assertEquals(n1, n2);
  }
}
