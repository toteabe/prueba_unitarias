package org.iesvdm.tddjava.connect4;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.*;
import static org.bson.assertions.Assertions.isTrue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class Connect4TDDSpec {

    /**
     * clase bajo testeo
     */
    private Connect4TDD tested;

    private OutputStream output;

    @BeforeEach
    public void beforeEachTest() {
        output = new ByteArrayOutputStream();

        //Se instancia el juego modificado para acceder a la salida de consola
        tested = new Connect4TDD(new PrintStream(output));
    }

    /*
     * The board is composed by 7 horizontal and 6 vertical empty positions
     */

    @Test
    public void whenTheGameStartsTheBoardIsEmpty() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;

        System.setOut(new PrintStream(outContent));

        Connect4 connect4 = new Connect4();
        connect4.printBoard();

        assertThat(!outContent.toString().contains("R") && !outContent.toString().contains("G")).isTrue();

        System.setOut(originalOut);

    }

    /*
     * Players introduce discs on the top of the columns.
     * Introduced disc drops down the board if the column is empty.
     * Future discs introduced in the same column will stack over previous ones
     */

    @Test
    public void whenDiscOutsideBoardThenRuntimeException() {
        Connect4 connect4 = new Connect4();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;

        System.setOut(new PrintStream(outContent));

        connect4.putDisc(99);

        assertThat(outContent.toString().contains("Column out of bounds")).isTrue();

        System.setOut(originalOut);
    }

    /*  cuando se inserta el primer disco en la columna, la posición es cero*/
    @Test
    public void whenFirstDiscInsertedInColumnThenPositionIsZero() {
        Connect4 connect4 = new Connect4();
        connect4.putDisc(1);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;

        System.setOut(new PrintStream(outContent));

        String tablero = "| | | | | | | |\r\n| | | | | | | |\r\n| | | | | | | |\r\n| | | | | | | |\r\n| | | | | | | |\r\n|R| | | | | | |\r\n";
        connect4.printBoard();



        assertThat(outContent.toString().compareTo(tablero) == 0).isTrue();

        System.setOut(originalOut);

    }

    @Test
    public void whenSecondDiscInsertedInColumnThenPositionIsOne() {
        Connect4 connect4 = new Connect4();
        connect4.putDisc(1);
        connect4.putDisc(1);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;

        System.setOut(new PrintStream(outContent));

        String tablero = "| | | | | | | |\r\n| | | | | | | |\r\n| | | | | | | |\r\n| | | | | | | |\r\n|G| | | | | | |\r\n|R| | | | | | |\r\n";
        connect4.printBoard();



        assertThat(outContent.toString().compareTo(tablero) == 0).isTrue();

        System.setOut(originalOut);



    }

    @Test
    public void whenDiscInsertedThenNumberOfDiscsIncreases() {

        Connect4 connect4 = new Connect4();
        connect4.putDisc(1);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        ByteArrayOutputStream outContent2 = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;

        System.setOut(new PrintStream(outContent));
        connect4.printBoard();

        int firstLastIndex = outContent.toString().indexOf("R");

        connect4.putDisc(1);
        connect4.putDisc(1);
        System.setOut(new PrintStream(outContent2));
        connect4.printBoard();
        int secondLastIndex = outContent2.toString().indexOf("R");


        System.setOut(originalOut);


        assertThat(secondLastIndex < firstLastIndex).isTrue();


    }

    @Test
    public void whenNoMoreRoomInColumnThenRuntimeException() {

        Connect4 connect4 = new Connect4();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;

        System.setOut(new PrintStream(outContent));

        connect4.putDisc(1);
        connect4.putDisc(1);
        connect4.putDisc(1);
        connect4.putDisc(1);
        connect4.putDisc(1);
        connect4.putDisc(1);
        connect4.putDisc(1);

        assertThat(outContent.toString().contains("There's no room for a new disc in this column")).isTrue();

        System.setOut(originalOut);

    }

    /*
     * It is a two-person game so there is one colour for each player.
     * One player uses red ('R'), the other one uses green ('G').
     * Players alternate turns, inserting one disc every time
     */

    /* Compruebo que cuando juega el primer jugador el disco sea rojo y no verde*/
    @Test
    public void whenFirstPlayerPlaysThenDiscColorIsRed() {
        Connect4 connect4 = new Connect4();
        connect4.putDisc(1);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;

        System.setOut(new PrintStream(outContent));

        connect4.printBoard();

        assertThat(outContent.toString().contains("R") && !outContent.toString().contains("G")).isTrue();

        System.setOut(originalOut);
    }

    @Test
    public void whenSecondPlayerPlaysThenDiscColorIsGreen() {
        Connect4 connect4 = new Connect4();
        connect4.putDisc(1);
        connect4.putDisc(1);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;

        System.setOut(new PrintStream(outContent));

        connect4.printBoard();

        assertThat(outContent.toString().indexOf("R") > outContent.toString().indexOf("G")).isTrue();

        System.setOut(originalOut);

    }

    /*
     * We want feedback when either, event or error occur within the game.
     * The output shows the status of the board on every move
     */

    /* cuando se pregunta por el jugador actual el aviso de salida  */
    @Test
    public void whenAskedForCurrentPlayerTheOutputNotice() {

        Connect4 connect4 = new Connect4();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;

        System.setOut(new PrintStream(outContent));

        connect4.putDisc(1);

        assertThat(outContent.toString().contains("Current turn: G")).isTrue();

        System.setOut(originalOut);
    }

    /* cuando se introduce un disco se imprime el tablero */
    @Test
    public void whenADiscIsIntroducedTheBoardIsPrinted() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        Connect4 connect4 = new Connect4();
        System.setOut(new PrintStream(outContent));

        connect4.putDisc(1);
        String tablero = "| | | | | | | |\r\n" + "| | | | | | | |\r\n" + "| | | | | | | |\r\n| | | | | | | |\r\n| | | | | | | |\r\n|R| | | | | | |\r\n";

        assertThat(outContent.toString().contains(tablero)).isTrue();
        System.setOut(originalOut);
    }

    /*
     * When no more discs can be inserted, the game finishes and it is considered a draw
     */

    @Test
    public void whenTheGameStartsItIsNotFinished() {

        Connect4 connect4 = new Connect4();
        connect4.putDisc(1);
        assertThat(!connect4.isFinished()).isTrue();

    }

    /* Cuando no se pueden introducir mas discos y el juego acaba en empate */
    @Test
    public void whenNoDiscCanBeIntroducedTheGamesIsFinished() {
        Connect4 connect4 = new Connect4();
        for(int i=1; i<=Connect4.COLUMNS; i++) {
            for(int j=0; j< Connect4.ROWS; j++) {
                connect4.putDisc(i);
            }
        }

        assertThat(connect4.isFinished()).isTrue();

    }

    /*
     * If a player inserts a disc and connects more than 3 discs of his colour
     * in a straight vertical line then that player wins
     */

    @Test
    public void when4VerticalDiscsAreConnectedThenThatPlayerWins() {
        Connect4 connect4 = new Connect4();
        for (int i=0; i<7; i++) {
            if (i%2 == 0) {
                connect4.putDisc(1);
            } else {
                connect4.putDisc(2);
            }
        }
        assertThat(connect4.isFinished()).isTrue();

    }

    /*
     * If a player inserts a disc and connects more than 3 discs of his colour
     * in a straight horizontal line then that player wins
     */

    @Test
    public void when4HorizontalDiscsAreConnectedThenThatPlayerWins() {
        Connect4 connect4 = new Connect4();
        for (int i=1; i<5; i++) {
            connect4.putDisc(i);
            if (i<4)
                connect4.putDisc(i);
        }
        assertThat(connect4.isFinished()).isTrue();
    }

    /*
     * If a player inserts a disc and connects more than 3 discs of his colour
     * in a straight diagonal line then that player wins
     */

    @Test
    public void when4Diagonal1DiscsAreConnectedThenThatPlayerWins() {
        Connect4 connect4 = new Connect4();
        for(int i=1; i<=4; i++) {
            for(int j=1; j<=Connect4.COLUMNS; j++) {
                connect4.putDisc(j);
            }
        }

        assertThat(connect4.isFinished()).isTrue();

    }

    @Test
    public void when4Diagonal2DiscsAreConnectedThenThatPlayerWins() {

        Connect4 connect4 = new Connect4();
        for(int i=1; i<=4; i++) {
            for(int j=Connect4.COLUMNS; j>=3; j--) {
                    connect4.putDisc(j);
            }
        }

        assertThat(connect4.isFinished()).isTrue();
    }
}
