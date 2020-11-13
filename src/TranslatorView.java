import javax.swing.*;
import java.awt.*;

public class TranslatorView extends JFrame implements TranslatorV{

    private JTextField english,french,status;
    private JLabel e,f;
    private JButton add,practice;

    public TranslatorView(){
        Translator trans = new Translator();
        trans.addView(this);
        Controller con = new Controller(trans,this);

        Container pane = getContentPane();
        pane.setLayout(new GridLayout(4,2));

        this.e = new JLabel("English");
        this.f = new JLabel("French");
        this.add = new JButton("Add");
        this.practice = new JButton("Practice");
        this.english = new JTextField();
        this.french = new JTextField();
        this.status = new JTextField();

        e.setPreferredSize(new Dimension(120, 40));
        f.setPreferredSize(new Dimension(120, 40));
        add.setPreferredSize(new Dimension(120, 40));
        practice.setPreferredSize(new Dimension(120, 40));
        english.setPreferredSize(new Dimension(120, 40));
        french.setPreferredSize(new Dimension(120, 40));
        status.setPreferredSize(new Dimension(120, 40));

        pane.add(e);
        pane.add(f);
        pane.add(english);
        pane.add(french);
        pane.add(add);
        pane.add(practice);
        pane.add(status);

        english.addActionListener(con);
        english.setActionCommand("english");
        french.addActionListener(con);
        french.setActionCommand("french");
        add.addActionListener(con);
        add.setActionCommand("add");
        practice.addActionListener(con);
        practice.setActionCommand("practice");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);

    }
    public String getEnglishInput(){
        return this.english.getText();
    }
    public String getFrenchInput(){
        return this.french.getText();
    }
    public void clear(){
        this.english.setText("");
        this.french.setText("");
    }
    @Override
    public void handleInputUpdate(TranslatorEvent e) {
        Translator trans = (Translator)e.getSource();
        Translator.Status s = trans.getStatus();
        if(s== Translator.Status.AddEntry)
             this.status.setText("entry added!");
        else if (s== Translator.Status.CHECK){
            this.english.setText(trans.getEnglish());
        }
        else if(s == Translator.Status.RIGHT){
            this.status.setText("Well done");
        }
        else if(s == Translator.Status.WRONG){
            this.status.setText("Wrong guess again");
        }
        else
            this.status.setText("");

    }


    public static void main(String[] args) {
        // TODO Auto-generated method stub
        TranslatorView view = new TranslatorView();

    }
}
