import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {
  private Translator trans;
  private TranslatorView view;
  private String english;
  private String french;


  public Controller(Translator trans,TranslatorView view){
      this.trans = trans;
      this.view = view;

  }
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command == "english") {
          english = view.getEnglishInput();
      }
        else if (command == "french"&&((trans.getStatus()== Translator.Status.INITIAL)||(trans.getStatus()== Translator.Status.AddEntry))){
          french = view.getFrenchInput();
        }
        else if (command == "add"){
          trans.add(english,french);
        }
        else if (command == "practice"){
          view.clear();
          trans.randomWord();
      }
        else if (command == "french" && ((trans.getStatus() == Translator.Status.CHECK)|| (trans.getStatus() == Translator.Status.WRONG)|| (trans.getStatus() == Translator.Status.RIGHT))){
          french = view.getFrenchInput();
          trans.checkCorrect(french);
        }

      }




        }