/*
 * Title:               GUI Cards Phase 3
 * Files:               Assig5.java
 * Semester:            Summer A, 2019
 * Date:
 *
 * Author:              Roger Terrill
 * Email:               rchicasterrill@csumb.edu
 * Lecturer's Name:     Jesse Cecil, M.S.
 * TA's Name:           Joseph Appleton
 * Lab Section:         CST 338
 */


import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Assig5_Phase3
{
   public static void main(String[] args)
   {
      GameView gameView = new GameView();

      GameModel gameModel = new GameModel();

      GameController gameController = new GameController(gameView, gameModel);
   }
}


