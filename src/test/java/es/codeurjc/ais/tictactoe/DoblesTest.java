package es.codeurjc.ais.tictactoe;
import static org.mockito.Mockito.*;
import static org.mockito.Matchers.argThat;
import static org.mockito.Matchers.eq;

//import static org.hamcrest.CoreMatchers.*;
import org.junit.Before;
import org.junit.Test;
import es.codeurjc.ais.tictactoe.TicTacToeGame.EventType;
import es.codeurjc.ais.tictactoe.TicTacToeGame.WinnerValue;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import org.mockito.ArgumentCaptor;
public class DoblesTest { 

	TicTacToeGame juego;
	Connection conexion1, conexion2;
	Player jugador1, jugador2;

	
	@Before
	public void inicializate(){
		//1-Crear el objeto TICTACTOE
		juego = new TicTacToeGame();
		
		//2-Crear los dobles de los objetos Connection
		conexion1 = mock(Connection.class);
		conexion2 = mock(Connection.class);
		
		//3) Añadir los dobles al objeto TicTacToeGame
		juego.addConnection(conexion1);
		juego.addConnection(conexion2);
		
		//4) Crear los dos jugadores (objetos Player)
		jugador1 = new Player(1, "X", "Juan");
		jugador2 = new Player(2, "O", "Maria");
		
		//5) Añadir los jugadores al objeto TicTacToeGame
		//6) Comprobar que la conexión 1 recibe el evento JOIN_GAME, con ambos jugadores
		//7) Comprobar que la conexión 2 recibe el evento JOIN_GAME, con ambos jugadores
		
		//del jugador1
		reset(conexion1);
		reset(conexion2);
		juego.addPlayer(jugador1);
        verify(conexion1).sendEvent(eq(TicTacToeGame.EventType.JOIN_GAME), argThat(hasItems(jugador1)));
        verify(conexion2).sendEvent(eq(TicTacToeGame.EventType.JOIN_GAME), argThat(hasItems(jugador1)));
        //del jugador2
        reset(conexion1);
		reset(conexion2);
		juego.addPlayer(jugador2);
        verify(conexion1).sendEvent(eq(TicTacToeGame.EventType.JOIN_GAME), argThat(hasItems(jugador1, jugador2)));
        verify(conexion2).sendEvent(eq(TicTacToeGame.EventType.JOIN_GAME), argThat(hasItems(jugador1, jugador2)));
		
        //8) Por turnos, cada jugador va marcando una casilla invocando el método mark de TicTacToeGame, comprobando que el turno cambia
        //9) Al final se comprueba que el juego acaba y que dependiendo de las casillas marcadas
        //uno de los jugadores gana o hay empate
	
	}
	//Caso que gana el jugador1
	@Test 
	public void pruebaGanaJugador1() {
		
		juego.mark(0);
		verify(conexion1).sendEvent(TicTacToeGame.EventType.SET_TURN, jugador2);
		verify(conexion2).sendEvent(TicTacToeGame.EventType.SET_TURN, jugador2);
		reset(conexion1);
		reset(conexion2);
		
		juego.mark(4);
		verify(conexion1).sendEvent(TicTacToeGame.EventType.SET_TURN, jugador1);
		verify(conexion2).sendEvent(TicTacToeGame.EventType.SET_TURN, jugador1);
		reset(conexion1);
		reset(conexion2);
		
		juego.mark(1);
		verify(conexion1).sendEvent(TicTacToeGame.EventType.SET_TURN, jugador2);
		verify(conexion2).sendEvent(TicTacToeGame.EventType.SET_TURN, jugador2);
		reset(conexion1);
		reset(conexion2);
		juego.mark(5);
		verify(conexion1).sendEvent(TicTacToeGame.EventType.SET_TURN, jugador1);
		verify(conexion2).sendEvent(TicTacToeGame.EventType.SET_TURN, jugador1);
		reset(conexion1);
		reset(conexion2);
		
		juego.mark(2);
		//verify(conexion1).sendEvent(TicTacToeGame.EventType.SET_TURN, jugador1);
		//verify(conexion2).sendEvent(TicTacToeGame.EventType.SET_TURN, jugador1);
		
		//Recuperamos los valores pasados como parametros
		ArgumentCaptor<WinnerValue> argument =	ArgumentCaptor.forClass(WinnerValue.class);		
		
		//El juego acaba
        verify(conexion1).sendEvent(eq(EventType.GAME_OVER), argument.capture());

        WinnerValue event = argument.getValue();
        
		int var[] = {0,1,2};
        assertArrayEquals("Las celdas ganadoras deberian ser 0,1,2", event.pos, var);
        
		assertEquals("El jugador 1 deberia haber ganado "+jugador1.getName(), event.player.getId(), jugador1.getId());
	}
	
	//Caso en eque gana el jugador 2
	@Test
	public void pruebaGanaJugador2() {
		
		juego.mark(4);
		verify(conexion1).sendEvent(TicTacToeGame.EventType.SET_TURN, jugador2);
		verify(conexion2).sendEvent(TicTacToeGame.EventType.SET_TURN, jugador2);
		reset(conexion1);
		reset(conexion2);
		
		juego.mark(0);
		verify(conexion1).sendEvent(TicTacToeGame.EventType.SET_TURN, jugador1);
		verify(conexion2).sendEvent(TicTacToeGame.EventType.SET_TURN, jugador1);
		reset(conexion1);
		reset(conexion2);
		
		juego.mark(3);
		verify(conexion1).sendEvent(TicTacToeGame.EventType.SET_TURN, jugador2);
		verify(conexion2).sendEvent(TicTacToeGame.EventType.SET_TURN, jugador2);
		reset(conexion1);
		reset(conexion2);
		juego.mark(1);
		verify(conexion1).sendEvent(TicTacToeGame.EventType.SET_TURN, jugador1);
		verify(conexion2).sendEvent(TicTacToeGame.EventType.SET_TURN, jugador1);
		reset(conexion1);
		reset(conexion2);
		
		juego.mark(6);
		verify(conexion1).sendEvent(TicTacToeGame.EventType.SET_TURN, jugador2);
		verify(conexion2).sendEvent(TicTacToeGame.EventType.SET_TURN, jugador2);
		reset(conexion1);
		reset(conexion2);
		
		juego.mark(2);
		
		ArgumentCaptor<WinnerValue> argument =	ArgumentCaptor.forClass(WinnerValue.class);		
		
		//El juego acaba
        verify(conexion1).sendEvent(eq(EventType.GAME_OVER), argument.capture());

        WinnerValue event = argument.getValue();
        
		int var[] = {0,1,2};
        assertArrayEquals("Las celdas ganadoras deberian ser 0,1,2", event.pos, var);
        
		assertEquals("El jugador 2 deberia haber ganado "+jugador2.getName(), event.player.getId(), jugador2.getId());
	}

	
	//Caso empate
	@Test
	public void pruebaEmpate() {
		juego.mark(1);
		verify(conexion1).sendEvent(TicTacToeGame.EventType.SET_TURN, jugador2);
		verify(conexion2).sendEvent(TicTacToeGame.EventType.SET_TURN, jugador2);
		reset(conexion1);
		reset(conexion2);
		
		juego.mark(4);
		verify(conexion1).sendEvent(TicTacToeGame.EventType.SET_TURN, jugador1);
		verify(conexion2).sendEvent(TicTacToeGame.EventType.SET_TURN, jugador1);
		
		
		juego.mark(3);
		verify(conexion1).sendEvent(TicTacToeGame.EventType.SET_TURN, jugador2);
		verify(conexion2).sendEvent(TicTacToeGame.EventType.SET_TURN, jugador2);
		reset(conexion1);
		reset(conexion2);
		
		juego.mark(0);
		verify(conexion1).sendEvent(TicTacToeGame.EventType.SET_TURN, jugador1);
		verify(conexion2).sendEvent(TicTacToeGame.EventType.SET_TURN, jugador1);
	

		juego.mark(8);
		verify(conexion1).sendEvent(TicTacToeGame.EventType.SET_TURN, jugador2);
		verify(conexion2).sendEvent(TicTacToeGame.EventType.SET_TURN, jugador2);
		reset(conexion1);
		reset(conexion2);
		
		juego.mark(2);
		verify(conexion1).sendEvent(TicTacToeGame.EventType.SET_TURN, jugador1);
		verify(conexion2).sendEvent(TicTacToeGame.EventType.SET_TURN, jugador1);
	
		
		juego.mark(6);
		verify(conexion1).sendEvent(TicTacToeGame.EventType.SET_TURN, jugador2);
		verify(conexion2).sendEvent(TicTacToeGame.EventType.SET_TURN, jugador2);
		reset(conexion1);
		reset(conexion2);
		
		juego.mark(7);
		verify(conexion1).sendEvent(TicTacToeGame.EventType.SET_TURN, jugador1);
		verify(conexion2).sendEvent(TicTacToeGame.EventType.SET_TURN, jugador1);
	
		
		juego.mark(5);

		ArgumentCaptor<WinnerValue> argument =	ArgumentCaptor.forClass(WinnerValue.class);
        verify(conexion1).sendEvent(eq(EventType.GAME_OVER), argument.capture());

        WinnerValue event = argument.getValue();
        
        assertEquals("EL no haber evento significa que hay empate", event, null);
		
	}

		

}
