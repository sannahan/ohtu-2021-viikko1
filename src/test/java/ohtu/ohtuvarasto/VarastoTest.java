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
    Varasto varastoAlkusaldolla;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
        varastoAlkusaldolla = new Varasto(10, 1);
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
    public void varastoonEiListätäEnempääKunMahtuu() {
        varasto.lisaaVarastoon(11);
        
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void varastostaEiVoiOttaaEnempääKuinSielläOn() {
        varasto.lisaaVarastoon(1);
        
        double maara = varasto.otaVarastosta(2);
        
        assertEquals(1, maara, vertailuTarkkuus);
    }
    
    @Test
    public void tulostusToimii() {
        varasto.lisaaVarastoon(1);
        
        assertEquals("saldo = 1.0, vielä tilaa 9.0", varasto.toString());
    }
    
    @Test
    public void konstruktoriAlkusaldollaLuoVarastonYhdellä() {
        assertEquals(1, varastoAlkusaldolla.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void uudellaVarastollaAlkusaldollaOikeaTilavuus() {
        assertEquals(10, varastoAlkusaldolla.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void varastoonNegatiivisenLisääminenEiTuotaMuutoksiaSaldoon() {
        varasto.lisaaVarastoon(-1);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void varastostaNegatiivisenOttaminenEiTuotaMuutoksiaSaldooon() {
        varasto.otaVarastosta(-1);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenTilavuusLuoKäyttökelvottomanVaraston() {
        Varasto varastoNollatilavuudella = new Varasto(-1);
        Varasto varastoNollatilavuudellaJaAlkusaldolla = new Varasto(-1, 0);
        
        assertEquals(0, varastoNollatilavuudella.getTilavuus(), vertailuTarkkuus);
        assertEquals(0, varastoNollatilavuudellaJaAlkusaldolla.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenSaldoLuoTyhjänVaraston() {
        Varasto varastoTyhja = new Varasto(1, -1);
        
        assertEquals(0, varastoTyhja.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void liianIsoSaldoValuuYli() {
        Varasto varastoTaysi = new Varasto(1, 2);
        
        assertEquals(1, varastoTaysi.getSaldo(), vertailuTarkkuus);
    }
}