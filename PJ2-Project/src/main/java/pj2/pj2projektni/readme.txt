PT :
   - Provjerava se da li se na kraju staze nalazi vozilo i da li tekuci objekat klase PT ima mogucnost procesiranja te
     vrste vozila. Ako da, onda se vozilo uklanja sa staze, radi se notify() da je posljednja pozicija na stazi slobodna

   - Vrsi se procesiranje vozila na nacin
        1.da se svi putnici sa neispravnim pasosima SERIJALIZUJU
        2.provjerava se da li vozac ima neispravan pasos.
            2.1.ako vozac ima neispravan pasos tada se cijelo vozilo SERIJALIZUJE sa svim svojim putnicima. Nema izbacivanja
                niti 1 putnika.
            2.2.ako vozac ima ispravan pasos tada se iz vozila izbacuju svi sporni putnici a samo vozilo nastavlja dalje svoje
                kretanje.

