package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    Varasto varasto2;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void lisaysEiYlitaTilavuutta() {
        varasto.lisaaVarastoon(12);
        
        //salso ei saa ylittää varaston tilavuutta
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenLisaysEiMuutaSaldoa() {
        varasto.lisaaVarastoon(8);
        
        varasto.lisaaVarastoon(-2);
        
        //jälkimmäinen lisäys ei muuta varaston saldoa
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void ottaminenTyhjentaaSaldon() {
        varasto.lisaaVarastoon(8);
        
        varasto.otaVarastosta(10);
        
        //saldo nollaantuu, jos varastosta otetaan enemmän kuin siellä on
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenOttaminenPalauttaaNollan() {
        varasto.lisaaVarastoon(8);
        
        double saatuMaara = varasto.otaVarastosta(-2);
        
        assertEquals(0, saatuMaara, vertailuTarkkuus);
    }
    
    @Test
    public void konstruktoriEiHyvaksyNegatiivistaTilavuutta() {
        varasto2 = new Varasto(-2);
        
        assertEquals(0, varasto2.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktoriEiHyvaksyNegatiivistaTilavuutta2() {
        varasto2 = new Varasto(-2, 8);
        
        assertEquals(0, varasto2.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktoriEiHyvaksyNegatiivistaAlkuSaldoa() {
        varasto2 = new Varasto(8, -2);
        
        assertEquals(0, varasto2.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktoriHylkaaYlimaaraisen() {
        varasto2 = new Varasto(5, 10);
        
        assertEquals(5, varasto2.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void uudellaVarastollaOikeaAlkuSaldo() {
        varasto2 = new Varasto(10, 5);
        
        assertEquals(5, varasto2.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void merkkijonoesitysPalauttaaOikein() {
        varasto.lisaaVarastoon(8);
        
        String merkkijono = varasto.toString();
        
        assertEquals("saldo = 8.0, vielä tilaa 2.0", merkkijono);
    }

}