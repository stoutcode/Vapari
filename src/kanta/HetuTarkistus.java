/**
 * 
 */
package kanta;

/**
 * Sisältää metodit henkilötunnuksen arpomiseksi ja tarkistamiseksi
 * sekä satunnaisen kokonaisluvun arpomiseksi.
 * @author mikar
 * @version 17 Feb 2020
 *
 */
public class HetuTarkistus {
    
    /** Henkilötunnukseen kelpaavat tarkistusmerkit järjestyksessä */
    public static final String TARKISTUSMERKIT = "0123456789ABCDEFHJKLMNPRSTUVWXY";
    
    /** Kuukausien maksimipituudet */
    //                                1  2  3  4  5  6  7  8  9 10 11 12   
    public static int[] KUUKAUDET = {31,29,31,30,31,30,31,31,30,31,30,31};
    
    /**
     * Palauttaa mikä olisi henkilötunnuksen tarkistusmerkki.
     * Parametrinä tuotava laillista muotoa oleva hetu, josta mahdollisesti
     * tarkistusmerkki puuttuu.
     * @param hetu tutkittava henkilötunnus
     * @return hetun tarkistusmerkki
     * @example
     * <pre name="test">
     *    hetunTarkistusMerkki("121212-222")    === 'N';
     *    hetunTarkistusMerkki("121212-222S")   === 'N';
     *    hetunTarkistusMerkki("121212-222N")   === 'N';
     *    hetunTarkistusMerkki("121212-231Y")   === 'Y';
     *    hetunTarkistusMerkki("311212-2317")   === '7';
     *    hetunTarkistusMerkki("311212-2317XY") === '7'; // vaikka on liikaa merkkejä
     *    hetunTarkistusMerkki("999999-9999XY") === 'F'; // vaikka on pvm väärin
     *    hetunTarkistusMerkki("12121A-222S")   === 'N'; #THROWS NumberFormatException
     *    hetunTarkistusMerkki("12121A-22")     === 'N'; #THROWS StringIndexOutOfBoundsException
     *    hetunTarkistusMerkki("121")           === 'N'; #THROWS StringIndexOutOfBoundsException
     * </pre>
     */
    public static char hetunTarkistusMerkki(String hetu) {
        // Otetaan henkilötunnuksesta alkunumerot ennen viiva merkkiä.
        String alku = hetu.substring(0,6);
        // Otetaan loppunumerot viivan jälkeen.
        String loppu = hetu.substring(7,10);
        // Lyödään hetun alku ja loppu yhteen ja tehdään siitä longluku
        long luku = Long.parseLong(alku+loppu);
        // Otetaan jakojäännös longluvusta jaettuna tarkistusmerkkien määrällä,
        // jotta saadaan joku luku tarkistusmerkkimäärän väliltä.
        int jakojaannos = (int)(luku % 31L);
        // Palautetaan merkki, joka on edellämainitussa kohdassa tarkistusmerkit merkkijonoa.
        return TARKISTUSMERKIT.charAt(jakojaannos);
    }
    
    /**
     * Arvotaan satunnainen henkilötunnus, joka täyttää henkilötunnuksen ehdot
     * @return satunnainen henkilötunnus laillisen mallin mukaisesti
     */
    public static String arvoHetu() {
        String apuHetu = String.format("%02d", rand(1,28)) +
        String.format("%02d", rand(1,12)) +
        String.format("%02d", rand(0,99)) + "-" +
        String.format("%03d", rand(1,999));
        return apuHetu + hetunTarkistusMerkki(apuHetu);
    }
    
    /**
     * Arpoo satunnaisen kokonaisluvun välille [ala, yla]
     * @param ala alaraja arvonnalle
     * @param yla yläraja arvonnalle
     * @return satunnainen kokonaisluku väliltä [ala, yla]
     */
    public static int rand(int ala, int yla) {
        double num = (yla-ala) * Math.random() + ala;
        return (int)Math.round(num);
    }
    
    
    /**
     * Tarkistetaan hetu.
     * @param hetu joka tutkitaan.
     * @return null jos oikein, muuten virhettä kuvaava teksti
     * TODO Jos halutaan vielä tarkemmaksi, niin lisätään merkkien tarkistus alkuosalle
     * (että ovat numeroita) ja merkkien tarkistus loppuosalle (että ovat numeroita lukuunottamatta
     * viimeistä merkkiä). Lisäksi karkausvuoden tarkistus.
     * @example
     * <pre name="test">
     * tarkista("12121")       === "Hetu liian lyhyt"; 
     * tarkista("k")           === "Hetu liian lyhyt"; 
     * tarkista("001212-132N") === "Hetun päivämäärä liian pieni";
     * tarkista("321212-132N") === "Hetun päivämäärä liian suuri";
     * tarkista("300212-132N") === "Hetun päivämäärä liian suuri";
     * tarkista("310412-132N") === "Hetun päivämäärä liian suuri";
     * tarkista("121312-132N") === "Hetun kuukausi liian suuri";
     * tarkista("120012-132N") === "Hetun kuukausi liian pieni";
     * tarkista("121212-12345")=== "Hetu liian pitkä";
     * tarkista("121212-123")  === "Hetu liian lyhyt";
     * tarkista("121212+222N") === "Hetun erotinmerkin pitäis olla -";
     * tarkista("121212-222M") === "Hetun viimeisen merkin pitäis olla N";
     * tarkista("121212-222N") === null;
     * </pre>
     */  
    public static String tarkista(String hetu) {
        int pituus = hetu.length();
        if ( pituus < 11 ) return "Hetu liian lyhyt"; // Tarkistetaan että hetu on oikean mittainen
        if ( pituus > 11 ) return "Hetu liian pitkä"; // Tarkistetaan että hetu on oikean mittainen 
        String pvm = hetu.substring(0,6);
        int pv = Integer.parseInt(pvm.substring(0,2));
        int kk = Integer.parseInt(pvm.substring(2,4));
        if ( kk < 1 )  return "Hetun kuukausi liian pieni"; // Tarkistetaan että kuukausi menee oikein
        if ( 12 < kk ) return "Hetun kuukausi liian suuri"; // Tarkistetaan että kuukausi menee oikein
        int pvmkk = KUUKAUDET[kk-1]; // Kyseisen kuukauden maksimi päivämäärä
        if ( pv < 1 )  return "Hetun päivämäärä liian pieni"; // Tarkistetaan että päivä menee oikein
        if ( pvmkk < pv ) return "Hetun päivämäärä liian suuri"; // Tarkistetaan että päivä menee oikein
        if (hetu.charAt(6) != '-') return "Hetun erotinmerkin pitäis olla -"; // Tarkistetaan että erotinmerkki on oikein
        char merkki = hetunTarkistusMerkki(hetu);
        if ( hetu.charAt(10) != merkki ) return "Hetun viimeisen merkin pitäis olla " + merkki; // Tarkistetaan että viimeinen merkki on oikein
        return null;
    }

}
