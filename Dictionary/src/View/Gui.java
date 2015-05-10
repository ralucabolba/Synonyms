package View;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * The graphic user interface of the application
 * @author Raluca Bolba
 *
 */
public class Gui extends JFrame 
{
	private static final long serialVersionUID = 1L;

	private JFrame mainWindow;
	
	private JTextArea textArea;
	
	private JTextField searchField;
	private JTextField addField;
	private JTextField synonymField;
	private JTextField removeField;
	
	private JButton searchButton;
	private JButton addButton;
	private JButton removeButton;
	
	private JMenuBar menuBar;
	
	private JMenu options;
	
	private JMenuItem save;
	private JMenuItem populate;
	private JMenuItem checkConsistance;
	
	private JList<String> listView;
	
	private DefaultListModel<String> listWords;
	
	public Gui()
	{
		mainWindow = this;
		mainWindow.setTitle("Dictionary");
		
		JDesktopPane mainPanel = new JDesktopPane();
	 	
		menuBar = new JMenuBar();
		
		options = new JMenu("Options");
		options.setMnemonic(KeyEvent.VK_O);
		
		menuBar.add(options);
		
		save = new JMenuItem("Save dictionary");
		populate = new JMenuItem("Populate dictionary");
		checkConsistance = new JMenuItem("Check consistance");
		
		options.add(save);
		options.add(populate);
		options.add(checkConsistance);
		
		mainWindow.setJMenuBar(menuBar);
		
	 	JLabel welcomeText = new JLabel("English Synonyms Dictionary");
	 	welcomeText.setFont(new Font("Georgia", 0, 40));
	 	welcomeText.setBounds(400, 20, 600, 100);
	 	mainPanel.add(welcomeText);
	 	
	 	JDesktopPane leftPane = new JDesktopPane();
	 	leftPane.setBackground(new Color(255, 255, 255, 150));
	 	leftPane.setBounds(100, 150, 500, 500);
	 	mainPanel.add(leftPane);
	 	
	 	JLabel optionLabel = new JLabel("Please choose an option : ");
	 	optionLabel.setFont(new Font("Georgia", 0, 16));
	 	optionLabel.setBounds(150, 10, 200, 25);
	 	leftPane.add(optionLabel);
	 	
	 	JLabel searchLabel = new JLabel("Search word : ");
	 	searchLabel.setFont(new Font("Georgia", 0, 14));
	 	searchLabel.setBounds(10, 60, 100, 25);
	 	leftPane.add(searchLabel);
	 	
	 	searchField = new JTextField();
	 	searchField.setBounds(150, 60, 150, 25);
	 	leftPane.add(searchField);
	 	
	 	searchButton = new JButton("Search");
	 	searchButton.setBounds(350, 60, 100, 25);
	 	leftPane.add(searchButton);
	 	
	 	JLabel addLabel = new JLabel("Add word : ");
	 	addLabel.setFont(new Font("Georgia", 0, 14));
	 	addLabel.setBounds(10, 150, 100, 25);
	 	leftPane.add(addLabel);
	 	
	 	addField = new JTextField();
	 	addField.setBounds(150, 150, 150, 25);
	 	leftPane.add(addField);
	 	
	 	addButton = new JButton("Add word");
	 	addButton.setBounds(350, 150, 100, 25);
	 	leftPane.add(addButton);
	 	
	 	JLabel synonymLabel = new JLabel("Insert synonym : ");
	 	synonymLabel.setFont(new Font("Georgia", 0, 14));
	 	synonymLabel.setBounds(10, 180, 110, 25);
	 	leftPane.add(synonymLabel);
	 	
	 	synonymField = new JTextField();
	 	synonymField.setBounds(150, 180, 150, 25);
	 	leftPane.add(synonymField);
	 	
	 	JLabel removeLabel = new JLabel("Remove word : ");
	 	removeLabel.setFont(new Font("Georgia", 0, 14));
	 	removeLabel.setBounds(10, 270, 100, 25);
	 	leftPane.add(removeLabel);
	 	
	 	removeField = new JTextField();
	 	removeField.setBounds(150, 270, 150, 25);
	 	leftPane.add(removeField);
	 	
	 	removeButton = new JButton("Remove");
	 	removeButton.setBounds(350, 270, 100, 25);
	 	leftPane.add(removeButton);
	 	
	 	JDesktopPane rightPane = new JDesktopPane();
	 	rightPane.setBackground(new Color(255, 255, 255, 150));
	 	rightPane.setBounds(750, 150, 500, 500);
	 	mainPanel.add(rightPane);
	 	
	 	listWords = new DefaultListModel<String>();
	 	listView = new JList<String>(listWords);
	 	
	 	JScrollPane scrollList = new JScrollPane(listView);
	 	scrollList.setBounds(100, 40, 300, 200);
	 	scrollList.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    scrollList.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	    rightPane.add(scrollList);
	 	
	 	textArea = new JTextArea();
	 	textArea.setBounds(100, 260, 300, 200);
	 	textArea.setEditable(false);
	 	
	 	JScrollPane scroll = new JScrollPane(textArea);
	 	scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	    scroll.setBounds(100, 260, 300, 200);
	 	rightPane.add(scroll);
	 	
	 	JLabel image = new JLabel();
		image.setIcon(new javax.swing.ImageIcon(getClass().getResource("dictionary.jpg")));
	 	image.setBounds(0, 0, 1366, 768);
	 	mainPanel.add(image);
	 	
	 	mainWindow.add(mainPanel);
	 	mainWindow.setSize(1366, 768);
	 	mainWindow.setVisible(true);
	 	mainWindow.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public String getSearchedWord()
	{
		return this.searchField.getText();
	}
	
	public String getAddedWord()
	{
		return this.addField.getText();
	}
	
	public String getSynonym()
	{
		return this.synonymField.getText();
	}
	
	public String getRemovedWord()
	{
		return this.removeField.getText();
	}
	
	public String getSelectedWord()
	{
		return this.listView.getSelectedValue().toString();
	}
	
	public void setSynonym(String synonyms)
	{
		this.textArea.setText(synonyms);
	}
	
	public DefaultListModel<String> getWordsList()
	{
		return this.listWords;
	}
	public void setWordsList(DefaultListModel<String> list)
	{
		this.listWords = list;
	}
	
	public void addListenerSearchWord(ActionListener act)
	{
		searchButton.addActionListener(act);
	}
	
	public void addListenerAddWord(ActionListener act)
	{
		addButton.addActionListener(act);
	}
	
	public void addListenerRemoveWord(ActionListener act)
	{
		removeButton.addActionListener(act);
	}
	
	public void addListenerPopulate(ActionListener act)
	{
		populate.addActionListener(act);
	}
	public void addListenerSave(ActionListener act)
	{
		save.addActionListener(act);
	}
	
	public void addListenerCheckConsistance(ActionListener act)
	{
		checkConsistance.addActionListener(act);
	}
	public void addListenerClickWord(MouseAdapter act)
	{
		listView.addMouseListener(act);
	}
}
