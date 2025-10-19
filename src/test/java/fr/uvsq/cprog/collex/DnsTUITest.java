package fr.uvsq.cprog.collex.tui;

import fr.uvsq.cprog.collex.command.AddItemCommande;
import fr.uvsq.cprog.collex.command.Commande;
import fr.uvsq.cprog.collex.command.GetIpCommande;
import fr.uvsq.cprog.collex.command.GetNameCommande;
import fr.uvsq.cprog.collex.command.ListDomainCommande;
import fr.uvsq.cprog.collex.command.QuitCommande;

import java.util.Scanner;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests unitaires de la classe DnsTUI.
 */
public class DnsTUITest {

  @Test
  public void testDnsTUICommands() {
    // Entrées simulées pour le Scanner
    String input = String.join("\n", "add 8.8.8.8 google.com", "add 1.1.1.1 cloudflare.com",
        "ls cloudflare.com", "8.8.8.8", "google.com", "quit");
    Scanner scanner = new Scanner(input);
    DnsTUI tui = new DnsTUI(scanner);

    // --- Test 1 : add ---
    Commande cmd1 = tui.nextCommande();
    Assert.assertTrue(cmd1 instanceof AddItemCommande);
    AddItemCommande add1 = (AddItemCommande) cmd1;
    System.out.println("> Add 1: " + add1.getIp() + " " + add1.getNom());
    Assert.assertEquals("8.8.8.8", add1.getIp());
    Assert.assertEquals("google.com", add1.getNom());

    // --- Test 2 : add ---
    Commande cmd2 = tui.nextCommande();
    Assert.assertTrue(cmd2 instanceof AddItemCommande);
    AddItemCommande add2 = (AddItemCommande) cmd2;
    System.out.println("> Add 2: " + add2.getIp() + " " + add2.getNom());
    Assert.assertEquals("1.1.1.1", add2.getIp());
    Assert.assertEquals("cloudflare.com", add2.getNom());

    // --- Test 3 : ls ---
    Commande cmd3 = tui.nextCommande();
    Assert.assertTrue(cmd3 instanceof ListDomainCommande);
    ListDomainCommande ls = (ListDomainCommande) cmd3;
    System.out.println("> LS: domain=" + ls.getDomaine() + ", sortByIp=" + ls.isSortByIp());
    Assert.assertEquals("cloudflare.com", ls.getDomaine());
    Assert.assertFalse(ls.isSortByIp());

    // --- Test 4 : IP ---
    Commande cmd4 = tui.nextCommande();
    Assert.assertTrue(cmd4 instanceof GetNameCommande);
    GetNameCommande getName = (GetNameCommande) cmd4;
    System.out.println("> GetName IP=" + getName.getIp());
    Assert.assertEquals("8.8.8.8", getName.getIp());

    // --- Test 5 : nom ---
    Commande cmd5 = tui.nextCommande();
    Assert.assertTrue(cmd5 instanceof GetIpCommande);
    GetIpCommande getIp = (GetIpCommande) cmd5;
    System.out.println("> GetIp name=" + getIp.getNom());
    Assert.assertEquals("google.com", getIp.getNom());

    // --- Test 6 : quit ---
    Commande cmd6 = tui.nextCommande();
    Assert.assertTrue(cmd6 instanceof QuitCommande);
    System.out.println("> Quit command recognized");

    System.out.println("✅ All DnsTUI command parsing tests passed.");
  }
}


