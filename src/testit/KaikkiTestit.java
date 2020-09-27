/**
 * 
 */
package testit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * @author mikar
 * @version 23 Mar 2020
 *
 */
@RunWith(Suite.class)
@SuiteClasses({
    vapari.test.HenkiloTest.class,
    vapari.test.HenkilotTest.class,
    vapari.test.LisatiedotTest.class,
    vapari.test.LisatietoTest.class,
    vapari.test.TapahtumaTest.class,
    vapari.test.TapahtumatTest.class,
    vapari.test.ToiminnotTest.class,
    vapari.test.ToimintoTest.class,
    vapari.test.VapariTest.class,
    kanta.test.HetuTarkistusTest.class
    })
public class KaikkiTestit {

    //

}
