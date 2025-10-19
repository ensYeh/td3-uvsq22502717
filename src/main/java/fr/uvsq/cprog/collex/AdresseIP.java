package fr.uvsq.cprog.collex;

/**
 * La classe qui represente IP addresse
 *
 */


/** Représente une adresse IP au format IPv4. */
public class AdresseIP {
  private final String adresse;

  public AdresseIP(String adresse) {
    if (!adresse
        .matches("^(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\." + "(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\."
            + "(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\." + "(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)$")) {
      throw new IllegalArgumentException("Adresse IP invalide : " + adresse);
    }
    this.adresse = adresse;
  }

  public String getAdresse() {
    return adresse;
  }

  @Override
  public String toString() {
    return adresse;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof AdresseIP)) {
      return false;
    }
    return adresse.equals(((AdresseIP) o).adresse);
  }

  @Override
  public int hashCode() {
    return adresse.hashCode();
  }
}

