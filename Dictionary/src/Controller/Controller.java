package Controller;
import java.util.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import View.Gui;
import Model.Dictionary;

/**
 * The controller of the application. The controller acts on both the model and the view. It is characterized
 * by an object of type Dictionary and an object of type Gui
 * @author Raluca Bolba
 *
 */
public class Controller 
{
	private Gui gui;
	private Dictionary dictionary;
	
	public Controller(Gui gui, Dictionary dictionary)
	{
		this.gui = gui;
		this.dictionary = dictionary;
		
		gui.addListenerSearchWord(new SearchWordListener());
		gui.addListenerAddWord(new AddWordListener());
		gui.addListenerRemoveWord(new RemoveWordListener());
		gui.addListenerPopulate(new PopulateListener());
		gui.addListenerSave(new SaveListener());
		gui.addListenerCheckConsistance(new CheckConsistanceListener());
		gui.addListenerClickWord(new ClickWordListener());
	}
	
	
	class SearchWordListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			String word = gui.getSearchedWord();
			
			try
			{
				Set<String> list = dictionary.search(word);
				
				if(list.isEmpty())
				{
					throw new Exception();
				}
				
				addWordsToList(list);
			}
			catch(IllegalArgumentException e)
			{
				JOptionPane.showMessageDialog(null, "No matches available. " + e.getMessage());
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null, "No matches available.");
			}
			
		}
	}
	
	class AddWordListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			String word = gui.getAddedWord();
			String synonym = gui.getSynonym();
			try 
			{
				dictionary.addWord(word, synonym);
				JOptionPane.showMessageDialog(null, word + " = " + synonym + " was added to dictionary");
				addWordsToList(dictionary.getKeys());
			} 
			catch (Exception e) 
			{
				JOptionPane.showMessageDialog(null, "The word could not be added " + e.getMessage());
			}
		}
	}
	class RemoveWordListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			String word = gui.getRemovedWord();
			
			try
			{
				dictionary.removeWord(word);
				JOptionPane.showMessageDialog(null, "The word " + word + " was removed.");
				addWordsToList(dictionary.getKeys());
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}
	}
	
	
	class PopulateListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			JFileChooser fc = new JFileChooser();
			int returnVal = fc.showOpenDialog(gui);
			
			if(returnVal == JFileChooser.APPROVE_OPTION)
			{
				File file = fc.getSelectedFile();
				
				String sourceFile = file.getAbsolutePath();
				
				try
				{
					dictionary.populate(sourceFile);
					JOptionPane.showMessageDialog(null, "The dictionary was populated.");
					
				}
				catch (FileNotFoundException e) 
				{
					JOptionPane.showMessageDialog(null, "File not found. " + e.getMessage());
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(null, "Error reading from file. " + e.getMessage());
				}
				
				addWordsToList(dictionary.getKeys());
			}	
		}
		
	}
	
	class SaveListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			JFileChooser fc = new JFileChooser();
			
			int returnVal = fc.showSaveDialog(gui);
			
			if(returnVal == JFileChooser.APPROVE_OPTION)
			{
				dictionary.save(fc.getSelectedFile().getAbsolutePath());
			}
		}
		
	}
	
	class ClickWordListener extends MouseAdapter
	{

		@Override
		public void mouseClicked(MouseEvent arg0) 
		{
			String s = getSynonyms(gui.getSelectedWord());
			gui.setSynonym(s);
		}
	}
	
	class CheckConsistanceListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			if(dictionary.isConsistent())
			{
				JOptionPane.showMessageDialog(null, "The dictionary is consistent");
			}
			else
			{
				JOptionPane.showMessageDialog(null, "The dictionary is not consistent");
			}
		}
		
	}
	public String getSynonyms(String word)
	{
		List<String> synonyms = dictionary.getSynonyms(word);
		
		Iterator<String> i = synonyms.iterator();
		
		String s = "";
		
		while(i.hasNext())
		{
			s += i.next() + "\n";
		}
		
		return s;
	}
	public void addWordsToList(Set<String> set)
	{
		DefaultListModel<String> list = gui.getWordsList();
		
		gui.setSynonym("");
		
		list.setSize(0);
		for(String s : set)
		{
			list.addElement(s);
		}
		
		gui.setWordsList(list);
	}
}
