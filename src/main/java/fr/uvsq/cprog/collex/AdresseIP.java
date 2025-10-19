package fr.uvsq.cprog.collex;

/**
 * La classe qui represente IP addresse
 *
 */
public class AdresseIP {
  private String ip;

  // Constructor
  public AdresseIP(String ip) {
    this.ip = ip;
  }

  @Override
  public String toString() {
    return ip;
  }

}
