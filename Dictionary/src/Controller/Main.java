package Controller;
import View.Gui;
import Model.Dictionary;

public class Main 
{
	public static void main(String[] args)
	{
		Gui gui = new Gui();
		Dictionary dictionary = new Dictionary();
		@SuppressWarnings("unused")
		Controller controller  = new Controller(gui, dictionary);
	}
}
