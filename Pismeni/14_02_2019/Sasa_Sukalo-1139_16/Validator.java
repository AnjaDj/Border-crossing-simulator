
import java.util.*;
import java.util.function.*;

public class Validator {
	public static <T> boolean provjeriSveUslove(T objekat,Predicate<T>[] uslovi) {
		boolean sviUsloviSuIspunjeni = true;
		for(int i=0;i<uslovi.length && sviUsloviSuIspunjeni;i++) {
			sviUsloviSuIspunjeni = uslovi[i].test(objekat);
		}
		return sviUsloviSuIspunjeni;
	}
}