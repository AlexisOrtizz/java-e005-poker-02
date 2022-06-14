package Ejercicio006.Ejercicio2.Poker;

import java.util.ArrayList;
import java.util.List;

import Ejercicio006.Carta;
import Ejercicio006.Poker;

public class MyClass {
	
	// este es el único método que tienen que desarrollar
	// para hacerlo, pueden hacer uso de otras clases (si es necesario),
	// pero la corrección del ejercicio será automática en base a este
	// método que está acá
	
	public String ganadores(List<Carta[]> jugadas)
	{
		/* inicio del calculo de la mejor jugada */
		Poker jugada = new Poker();
		List<Carta []> mejorJugada = new ArrayList<Carta[]>();
		int indice;
		int max = -1; // indicador de la mejor jugada en numerico
		
		System.out.println("Cantidad de jugadas: " + jugadas.size());
		
		// recorrer todas las jugadas
		for (Carta[] mano : jugadas) {
			indice = 0;
			String auxMano = jugada.analizarJugada(mano);
			
			/* buscar la jugada de poker y darle un valor */
			for(String jug : jugada.JUGADAS) {
				if(jug.equals(auxMano)) {
					// si la mano es mejor limpia y agrega a las mejores jugadas
					// si la mano es la misma que la mejor jugada, agrega a la lista
					if(indice > max) {
						max = indice;
						mejorJugada.clear();
						mejorJugada.add(mano);
					} else if (indice == max) {
						mejorJugada.add(mano);
					}
					break;
				} else {
					indice++;
				}
			}
			
			System.out.println("JUGADA ======================");
			for (Carta c : mano) {
				System.out.println(c.valorPalo());
			}

		}
		
		//System.out.println("\n========== MEJORES JUGADA ==========");
		List<Carta []> mejorJugadaFinal = new ArrayList<Carta[]>();
		max = -1;
		Carta auxCarMax = null;
		
		for (Carta [] mano : mejorJugada) {
			Carta auxCar = null;
			/* hallar la carta mayor de la mano */
			for(Carta c : mano) {
				// validar A como 14
				if(auxCar == null) {
					auxCar = c;
				} else if(jugada.esMayor(c, auxCar)) {
					auxCar = c;
				}
				
				//System.out.println(c.valorPalo());
			}
			
			System.out.println(">> " + auxCar.valorPalo());
			/* validar empates con carta alta */
			if(mejorJugada.size() > 1) { // si hay mas de una mano, desempatar.
				if(auxCarMax == null || jugada.esMayor(auxCar, auxCarMax)) {
					auxCarMax = auxCar;
					mejorJugadaFinal.clear();
					mejorJugadaFinal.add(mano);
				} else if(auxCar.valor.equals(auxCarMax.valor)) {
					System.out.println("igual");
					mejorJugadaFinal.add(mano);
				}
			} else {
				mejorJugadaFinal = mejorJugada;
			}
		}
		
		System.out.println("\n========== JUGADA/s GANADOR/es ==========");
		for(Carta [] mano : mejorJugadaFinal) {
			for(Carta c : mano) {
				System.out.println(c.valorPalo());
			}
			System.out.println("");
		}
		
		
		return mejorJugadaFinal.size() + "";
	}
	
	public static void main(String args[]) {
		MyClass mc = new MyClass();
		List<Carta[]> jugadas = new ArrayList<Carta[]>();
		
		Carta[] m1 = new Carta[5];
		m1[0] = new Carta("9H");
		m1[1] = new Carta("9D");
		m1[2] = new Carta("TH");
		m1[3] = new Carta("TC");
		m1[4] = new Carta("6S");
		
		Carta[] m2 = new Carta[5];
		m2[0] = new Carta("AH");
		m2[1] = new Carta("KD");
		m2[2] = new Carta("QH");
		m2[3] = new Carta("3C");
		m2[4] = new Carta("3S");
		
		Carta[] m3 = new Carta[5];
		m3[0] = new Carta("TH");
		m3[1] = new Carta("TD");
		m3[2] = new Carta("9H");
		m3[3] = new Carta("9C");
		m3[4] = new Carta("AS");
		
		jugadas.add(m1);
		jugadas.add(m2);
		jugadas.add(m3);
		
		String ganadores = mc.ganadores(jugadas);
		System.out.println("Ganadores = " + ganadores);
	}
}
