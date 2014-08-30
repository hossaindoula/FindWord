package ui;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import dict.FindWord;

public class FindWordUi {
	private JFrame mainFrame;
	private JLabel headerLabel;
	private JPanel inputPanel;
	private JLabel namelabel;
	private JTextField sourceWord;
	private JButton findButton;
	DefaultListModel foundWords = new DefaultListModel();
	JList wordList;
	private JLabel developedBy =new JLabel("Developed By shanavas.m2@gmail.com",JLabel.CENTER);
	
	public void gui(){
		mainFrame = new JFrame("Find Word");
	    mainFrame.setSize(400,400);
	     Container pane = mainFrame.getContentPane();
	    BoxLayout mainLayout = new BoxLayout(pane, BoxLayout.Y_AXIS);
	    mainFrame.setLayout(mainLayout);
	    mainFrame.setIconImage(Toolkit.getDefaultToolkit().createImage("icon.ico"));

	    headerLabel = new JLabel("Found Words",JLabel.CENTER );
	    headerLabel.setSize(100, 100);
	    headerLabel.setVerticalAlignment(JLabel.TOP);
	    
	    mainFrame.addWindowListener(new WindowAdapter() {
	         public void windowClosing(WindowEvent windowEvent){
		        System.exit(0);
	         }        
	      });
	    
	    inputPanel = new JPanel();
	    FlowLayout inputLayout = new FlowLayout();
	    inputLayout.setVgap(10);
	    inputPanel.setLayout(inputLayout);
	    
	    namelabel= new JLabel("Source Word: ", JLabel.LEFT);
	    sourceWord = new JTextField(10);
	    
	    
	    wordList = new JList(foundWords);
	    wordList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    final JScrollPane wordListScrollPane = new JScrollPane(wordList);
	    wordListScrollPane.setAlignmentY(JComponent.TOP_ALIGNMENT);
	    
	    
	    findButton = new JButton("Find");
	    findButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {     
	            String data = sourceWord.getText();
	            if(data == null || data=="" || data.matches("^\\s*$")){
	            	return;
	            }
	            List<String> list = new ArrayList<String>();
	            list = new FindWord().find(data);
	            foundWords.clear();
	            if(list.isEmpty()){
	            	headerLabel.setText("No Words Found");
		            mainFrame.add(headerLabel);
	            }else{
	            	headerLabel.setText("Found Words");
		            mainFrame.add(headerLabel);
		            for(String str: list){
		            	foundWords.addElement(str);
		            }
		    	    mainFrame.add(wordListScrollPane);
		    	    
		            if(list.size()>10){
		            	wordList.setVisibleRowCount(list.size());
		            }else{
		            	wordList.setVisibleRowCount(10);
		            }
		         }
	            mainFrame.remove(developedBy);
	            mainFrame.add(developedBy);
	            mainFrame.setVisible(true);
	         }
	      }); 
	    

	    
	    
	    inputPanel.add(namelabel);
	    inputPanel.add(sourceWord);
	    inputPanel.add(findButton);
	    Color color = new Color(255,0,0);
	    developedBy.setForeground(color);
	    developedBy.setFont(new Font("Comic Sans MS",Font.PLAIN,12));
	    developedBy.setSize(300, 70);

	    mainFrame.add(inputPanel);
	    mainFrame.add(developedBy);
	    mainFrame.setVisible(true);
	    
	}
	public static void main(String[] args) {
	    FindWordUi findWordUi = new FindWordUi();
	    findWordUi.gui();
	}

}
