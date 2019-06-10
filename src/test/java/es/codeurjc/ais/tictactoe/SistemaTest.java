package es.codeurjc.ais.tictactoe;


import static org.junit.Assert.*;
import org.junit.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SistemaTest {

	protected WebDriver driver1;
	protected WebDriver driver2;
	
    private static WebDriverWait wait1;
    private static WebDriverWait wait2;

	@BeforeClass
	public static void setupClass() {
		WebDriverManager.chromedriver().setup();
		WebApp.start();
	}
	@AfterClass
	public static void teardownClass() {
		WebApp.stop();
	}
	@Before
	public void inicializate() {
		driver1 = new ChromeDriver();
		driver2 = new ChromeDriver();
		wait1 = new WebDriverWait(driver1,  10);
		wait2 = new WebDriverWait(driver2,  10);

	}
	@After
	public void teardonw() {
		if(driver1 != null) {
			driver1.quit();
		}
		if(driver2 != null) {
			driver2.quit();
		}
	}
	
	public void login (String nombre1, WebDriver driver) {
		driver.get("http://localhost:8080/");
		driver.findElement(By.id("nickname")).sendKeys(nombre1);
	    driver.findElement(By.id("startBtn")).click();



	}
	//Probamos que la creacion de dos jugadores. 
	@Test
	public void pruebaLogin() {
		login("josep", driver1);
		login("Mary", driver2);
		
	    String aux1 = driver1.findElement(By.id("p1Score")).getText();
	    String aux2 = driver1.findElement(By.id("p2Score")).getText();
	    String aux3 = driver2.findElement(By.id("p1Score")).getText();
	    String aux4 = driver2.findElement(By.id("p2Score")).getText();

	    //System.out.println(""+ aux + aux2);
		assertEquals("El nombre en el driver1 del jug1 deberia ser X josep", "X josep", aux1);
		assertEquals("El nombre en el driver1 del jug2 deberia ser Mary O", "Mary O", aux2);
		assertEquals("El nombre en el driver2 del jug1 deberia ser X josep", "X josep", aux3);
		assertEquals("El nombre en el driver2 del jug2 deberia ser Mary O", "Mary O", aux4);

	}
	
	@Test
	public void pruebaGanaJugador1() {
		login("josep", driver1);
		login("Mary", driver2);
		
        driver1.findElement(By.id("cell-0")).click();
        wait2.until(ExpectedConditions.textToBePresentInElementLocated(By.id("cell-0"), "X"));

        driver2.findElement(By.id("cell-8")).click();
        wait1.until(ExpectedConditions.textToBePresentInElementLocated(By.id("cell-8"), "O"));

        driver1.findElement(By.id("cell-1")).click();
        wait2.until(ExpectedConditions.textToBePresentInElementLocated(By.id("cell-1"), "X"));

        driver2.findElement(By.id("cell-6")).click();
        wait1.until(ExpectedConditions.textToBePresentInElementLocated(By.id("cell-6"), "O"));

        driver1.findElement(By.id("cell-2")).click();
        //wait2.until(ExpectedConditions.textToBePresentInElementLocated(By.id("cell-2"), "X"));

        wait1.until(ExpectedConditions.alertIsPresent());
        wait2.until(ExpectedConditions.alertIsPresent());


        String aux1 = driver1.switchTo().alert().getText();
        String aux2 = driver2.switchTo().alert().getText();
        
        assertEquals("DRIVER1:El ganador deberia ser josep", aux1, "josep wins! Mary looses.");
        assertEquals("Driver2:El ganador deberia ser josep", aux2, "josep wins! Mary looses.");

	}

	@Test 
	public void pruebaGanaJugador2() {
		login("josep", driver1);
		login("Mary", driver2);
		
        driver1.findElement(By.id("cell-3")).click();
        wait2.until(ExpectedConditions.textToBePresentInElementLocated(By.id("cell-3"), "X"));
        
        driver2.findElement(By.id("cell-0")).click();
        wait1.until(ExpectedConditions.textToBePresentInElementLocated(By.id("cell-0"), "O"));

        driver1.findElement(By.id("cell-5")).click();
        wait2.until(ExpectedConditions.textToBePresentInElementLocated(By.id("cell-5"), "X"));

        driver2.findElement(By.id("cell-1")).click();
        wait1.until(ExpectedConditions.textToBePresentInElementLocated(By.id("cell-1"), "O"));
        
        driver1.findElement(By.id("cell-7")).click();
        wait2.until(ExpectedConditions.textToBePresentInElementLocated(By.id("cell-7"), "X"));
        
        driver2.findElement(By.id("cell-2")).click();
        //wait1.until(ExpectedConditions.textToBePresentInElementLocated(By.id("cell-2"), "O"));
        
        wait1.until(ExpectedConditions.alertIsPresent());
        wait2.until(ExpectedConditions.alertIsPresent());


        String aux1 = driver1.switchTo().alert().getText();
        String aux2 = driver2.switchTo().alert().getText();
        
        assertEquals("DRIVER1: El ganador deberia ser Mary", aux1, "Mary wins! josep looses.");
        assertEquals("DRIVER2: El ganador deberia ser Mary", aux2, "Mary wins! josep looses.");

	}

	@Test 
	public void pruebaEmpate() {
		login("josep", driver1);
		login("Mary", driver2);
		

        driver1.findElement(By.id("cell-1")).click();
        wait2.until(ExpectedConditions.textToBePresentInElementLocated(By.id("cell-1"), "X"));
        
        driver2.findElement(By.id("cell-4")).click();
        wait1.until(ExpectedConditions.textToBePresentInElementLocated(By.id("cell-4"), "O"));

        driver1.findElement(By.id("cell-3")).click();
        wait2.until(ExpectedConditions.textToBePresentInElementLocated(By.id("cell-3"), "X"));

        driver2.findElement(By.id("cell-0")).click();
        wait1.until(ExpectedConditions.textToBePresentInElementLocated(By.id("cell-0"), "O"));
        

        driver1.findElement(By.id("cell-8")).click();
        wait2.until(ExpectedConditions.textToBePresentInElementLocated(By.id("cell-8"), "X"));
        
        driver2.findElement(By.id("cell-2")).click();
        wait1.until(ExpectedConditions.textToBePresentInElementLocated(By.id("cell-2"), "O"));

        driver1.findElement(By.id("cell-6")).click();
        wait2.until(ExpectedConditions.textToBePresentInElementLocated(By.id("cell-6"), "X"));

        driver2.findElement(By.id("cell-7")).click();
        wait1.until(ExpectedConditions.textToBePresentInElementLocated(By.id("cell-7"), "O"));
        
        driver1.findElement(By.id("cell-5")).click();

        wait1.until(ExpectedConditions.alertIsPresent());
        wait2.until(ExpectedConditions.alertIsPresent());


        String aux1 = driver1.switchTo().alert().getText();
        String aux2 = driver2.switchTo().alert().getText();
        
        assertEquals("DRIVER1: Deberia haber empate", aux1, "Draw!");
        assertEquals("DRIVER2: Deberia haber empate", aux2, "Draw!");
		
	}
}
