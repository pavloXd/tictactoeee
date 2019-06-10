package es.codeurjc.ais.tictactoe;

import static org.junit.Assert.assertArrayEquals;

//Pruebas UNITARIAS de la clase BOARD

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import es.codeurjc.ais.tictactoe.TicTacToeGame.Cell;

public class BoardTest {

	Board tablero;
	
	@Before 
	public void inicializate() {
		tablero = new Board();

	}
	
	private void asignarCelda(String valor, int cellId) {
		Cell celda = tablero.getCell(cellId);
		celda.value = valor;
	}
	
	private void tableroEmpate() {
		tablero.enableAll();
		
		asignarCelda("O", 0);
		asignarCelda("X", 1);
		asignarCelda("O", 2);
		asignarCelda("X", 3);
		asignarCelda("O", 4);
		asignarCelda("X", 5);
		asignarCelda("X", 6);
		asignarCelda("O", 7);
		asignarCelda("X", 8);
	}
	private void tableroGanador() {
		tablero.enableAll();

		asignarCelda("X",0);
		asignarCelda("O",4);
		asignarCelda("X",1);
		asignarCelda("O",6);
		asignarCelda("X",2);
	}
	//Test de empate
	@Test
	public void pruebaCheckEmpate(){
		tableroEmpate();
		
		 
		assertEquals("El tablero esta empatado", tablero.checkDraw(), true);
	}
	
	//Probamos que compruebe bien la cuando alguien gana
	@Test
	public void pruebaCheckGana() {

		tableroGanador();
		assertEquals("El tablero esta ganado por alguien", tablero.checkDraw(), false);
	}
	
	//Comprueba que la linea que extrae es la correcta
	@Test
	public void pruebaCellsIfWinnerGanador() {
		tableroGanador();
		int var[] = {0,1,2};
		
		//assertTrue("El tablero ha detectado la fila ganadora", Arrays.equals(tablero.getCellsIfWinner("X"), var));
		assertArrayEquals("El tablero ha detectado la fila ganadora", tablero.getCellsIfWinner("X"), var);

		
	}
	
	//Comprueba que si no hay ninguna linea ganadora, devuelve un null
	@Test
	public void pruebaCellsIfWinnerNOGanador() {
		tableroEmpate();

		assertEquals("NO hay ningun ganador, el resultado es null", tablero.getCellsIfWinner("X"), null);
	}
}
