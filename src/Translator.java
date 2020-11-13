import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Translator {
    private String english;
    private String french;
    private boolean guessRight;
    private HashMap<String,String> glossary;
    public enum Status{AddEntry,CHECK,INITIAL,RIGHT,WRONG};
    private Status s = Status.INITIAL;
    //private String randomword;

    private List<TranslatorV> translatorview;

    public Translator(){
        glossary = new HashMap<>();
        translatorview = new ArrayList<TranslatorV>();
    }
    public void clear(){
        this.english = null;
        this.french = null;
    }
    public void add(String e,String f){
        glossary.put(e,f);
        s = Status.AddEntry;
        System.out.println(glossary.toString());
        for(TranslatorV view : translatorview){
            view.handleInputUpdate(new TranslatorEvent(this));
        }
    }
    public boolean checkCorrect(String f){

        if(french.equals(f)){
            guessRight = true;
            s =  Status.RIGHT;
            System.out.println("Well done");
        }
        else{
            guessRight = false;
            s = Status.WRONG;
            System.out.println("Wrong guess again");
        }
        for(TranslatorV view : translatorview){
            view.handleInputUpdate((new TranslatorEvent(this)));
        }
        return guessRight;
    }
    public Status getStatus(){
        return this.s;
    }
    public void randomWord(){
        this.s = Status.CHECK;
        List list = new ArrayList(glossary.keySet());
       int i = glossary.size();
       int random = (int)Math.floor(Math.random()*i);
       this.english = (String) list.get(random);
       this.french = glossary.get(english);
       System.out.println(english);
        for(TranslatorV view : translatorview){
            view.handleInputUpdate((new TranslatorEvent(this)));
        }


    }
    public String getEnglish(){
        return this.english;
    }
    public void addView(TranslatorV view){
        translatorview.add(view);
    }
    public void removeView(TranslatorV view){
        translatorview.remove(view);
    }
}
