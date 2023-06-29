// Praval Chaudhary
// 4/11/22 - 4/15/22
// BadmintonTyper1.java
// This program is a start of the Game using Java. This sub-program creates the
// start panel and directions of the big game.

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import javax.swing.JFrame;    
import javax.swing.JPanel;
import java.awt.Image;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.Component;
import javax.swing.SwingConstants;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.image.BufferedImage;


public class BadmintonTyper1
{    
    public BadmintonTyper1(){}
    
    public static void main(String [] args)
    {
        BadmintonTyper1 bt1 = new BadmintonTyper1();
        bt1.run();
    }
    
    public void run()
    {
        JFrame frame = new JFrame("Badminton Typer");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setLocation(00, 50);
        frame.setResizable(true);
        BadmintonTyperHolder bth = new BadmintonTyperHolder();
        frame.getContentPane().add(bth);
        frame.setVisible(true);
    }
}

class BadmintonTyperHolder extends JPanel
{
    public BadmintonTyperHolder()
    {
        CardLayout cards = new CardLayout();
        setLayout(cards);
        
        BaddyStartPanel btsp = new BaddyStartPanel(this, cards);
        BaddyDirectionPanel btdp = new BaddyDirectionPanel(this, cards);
        BaddyPlayPanel bpp = new BaddyPlayPanel(this, cards);
        BaddyScoreBoardPanel bsbp = new BaddyScoreBoardPanel(this, cards);
        
        add(btsp, "StartPage");
        add(btdp, "Directions");
        add(bpp, "GamePanel");
        add(bsbp, "ScoreBoard");
    }
}

class BaddyStartPanel extends JPanel
{
    private BadmintonTyperHolder panelCards;
    private CardLayout cards;
    private JLabel welcome;
    private JButton start;
    private JButton exit;
    private JMenu PanelChooser;
    private String pictName;
    private BufferedImage img;
    private Font baddyFont;
    
    public BaddyStartPanel(BadmintonTyperHolder panelCardsIn, CardLayout cardsIn)
    {
        //setBackground(new Color( 3, 170, 70));
        baddyFont = new Font("Segoe Script", Font.BOLD, 11);
        panelCards = panelCardsIn;
        cards = cardsIn;
        setLayout(new BorderLayout());
        welcome = new JLabel("Badminton Typer", SwingConstants.CENTER);
        welcome.setFont(new Font("Segoe Script", Font.BOLD, 40));
    
        btButtonPanel btbp = new btButtonPanel();
        
        pictName = "Raquets.jpg";
        getMyImage();
        repaint();
        add(welcome, BorderLayout.NORTH);
        add(btbp, BorderLayout.SOUTH);
    }
    
    class btButtonPanel extends JPanel
    {
		
        public btButtonPanel()
        {
            
            start = new JButton("Play");
            start.setFont(baddyFont);
            exit = new JButton("Exit");
            exit.setFont(new Font("Segoe Script", Font.BOLD, 11));
            btStartPanelButtonHandler btpbh = new btStartPanelButtonHandler();
            start.addActionListener(btpbh);
            exit.addActionListener(btpbh);
            
            JMenuBar bar = new JMenuBar();
            JMenu Menu = new JMenu("Other Panels");
            Menu.setFont(new Font("Segoe Script", Font.BOLD, 11));
            JMenuItem Direction = new JMenuItem("Direction Panel");
            Direction.setFont(new Font("Segoe Script", Font.BOLD, 11));
            JMenuItem PlayPanel = new JMenuItem("Play Panel");
            PlayPanel.setFont(new Font("Segoe Script", Font.BOLD, 11));
            JMenuItem Scoreboard = new JMenuItem("Scoreboard");
            Scoreboard.setFont(new Font("Segoe Script", Font.BOLD, 11));
            btMenuHandler btmh = new btMenuHandler();        
            Direction.addActionListener(btmh);
            PlayPanel.addActionListener(btmh);
            Scoreboard.addActionListener(btmh);
            Menu.add(Direction);
            Menu.add(PlayPanel);
            Menu.add(Scoreboard);
            
            
            bar.add(Menu);
            add(bar);
            add(start);
            add(exit);
        }
    }
    
    public void getMyImage()
    {
        try
        {
            img = ImageIO.read(new File(pictName));
        }
        catch (IOException e)
        {
            System.out.printf("\n\nERORR: Cannot find/open file %s", pictName);
            System.exit(2);
        }
    }
    
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
            g.drawImage(img, 100, 50, this);
    }
    
   
    class btStartPanelButtonHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent evt)
        {
            String command = evt.getActionCommand();
            if(command.equals("Play"))
                cards.show(panelCards, "Directions");
			else if(command.equals("Exit"))
				System.exit(1);
        }
    }
    class btMenuHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent evt)
        {
            String menuAction = null;
            menuAction = evt.getActionCommand();
            if(menuAction.equals("Direction Panel"))
                cards.show(panelCards, "Directions");
            else if(menuAction.equals("Play Panel"))
                cards.show(panelCards, "GamePanel");
            else if(menuAction.equals("Scoreboard"))
                cards.show(panelCards, "ScoreBoard");
        }
    }
}

class BaddyDirectionPanel extends JPanel
{
    private BadmintonTyperHolder panelCards;
    private CardLayout cards;
    private JLabel label;
    private JTextArea directions;
    
    public BaddyDirectionPanel(BadmintonTyperHolder panelCardsIn, CardLayout cardsIn)
    {
        panelCards = panelCardsIn;
        cards = cardsIn;
        setLayout(new BorderLayout());
        
        label = new JLabel("A message from the developer:", SwingConstants.CENTER);
        label.setFont(new Font("Segoe Script", Font.BOLD, 20));
        
        directions = new JTextArea("The next panel will contain the game." +
            " A shuttle with a word will be dropping in front of you and your job is to "+
            "write the word into the area provided within the next 10 seconds" +
            ". When you correctly type the word you will be awarded with 10 "+
            "points but when you type the word incorrectly you will lose a "+
            "live (you have 5 lives at the start). The game ends when you" +
            " lose all of your lives As you progress in the game" +
            " the amount of time given to input the words will decrease."+ 
            " Be careful, have fun, and enjoy the game!");
        Font tafont = new Font("Segoe Script", Font.PLAIN, 20);
        directions.setFont(tafont);
        Color taColor = new Color( 3, 170, 70);
        directions.setLineWrap(true);
        directions.setWrapStyleWord(true);
        directions.setForeground(taColor);
        EmptyWestPanel ewp = new EmptyWestPanel();
        EmptyWestPanel eep = new EmptyWestPanel();
        BaddySouthButtonPanel bsbp = new BaddySouthButtonPanel(panelCardsIn, cards);
        add(label, BorderLayout.NORTH);
        add(directions, BorderLayout.CENTER);
        add(bsbp, BorderLayout.SOUTH);
        add(ewp, BorderLayout.WEST);
        add(eep, BorderLayout.EAST);
    }
    
    class EmptyWestPanel extends JPanel
    {
        public EmptyWestPanel()
        {
            JLabel blank = new JLabel("                        ");
            add(blank);
        }
    }
    
}

class BaddySouthButtonPanel extends JPanel
{
    private BadmintonTyperHolder panelCards;
    private CardLayout cards;
    private JButton next;
    private JButton back;
    public BaddySouthButtonPanel(BadmintonTyperHolder panelCardsIn, CardLayout cardsIn)
    {
        panelCards = panelCardsIn;
        cards = cardsIn;
        
        next = new JButton("Back");
        back = new JButton("Next");
        
        BaddySouthPanelButtonListener bspbl = new BaddySouthPanelButtonListener();
        next.addActionListener(bspbl);
        back.addActionListener(bspbl);
        
        add(next, BorderLayout.EAST);
        add(back, BorderLayout.WEST);
    }
    
    class BaddySouthPanelButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent evt)
        {
            String command = evt.getActionCommand();
            if(command.equals("Next"))
                cards.show(panelCards, "GamePanel");
            else if(command.equals("Back"))
                cards.show(panelCards, "StartPage");
        }
    }
}

class BaddyPlayPanel extends JPanel
{
    private BadmintonTyperHolder panelCards;
    private CardLayout cards;
    private JButton next;
    private JButton back;
    
    
    public BaddyPlayPanel(BadmintonTyperHolder panelCardsIn, CardLayout cardsIn)
    {
        setBackground(new Color(110, 255, 170));
        panelCards = panelCardsIn;
        cards = cardsIn;
        BaddyStartPanel bsp = new BaddyStartPanel(panelCards, cards);
        bsp.repaint();
        setLayout(new BorderLayout());
        next = new JButton("Next");
        back = new JButton("Back");
        BaddyPlayButtonListener bpbl = new BaddyPlayButtonListener();
        next.addActionListener(bpbl);
        back.addActionListener(bpbl);
        add(next, BorderLayout.EAST);
        add(back, BorderLayout.WEST);
        
    }
    
    class BaddyPlayButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent evt)
        {
            // The buttons added below isn't actually the "game panel" they
            // are just added for maneuverability between the cards
            String command = evt.getActionCommand();
            if(command.equals("Next"))
                cards.show(panelCards, "ScoreBoard");
            else if(command.equals("Back"))
                cards.show(panelCards, "Directions");
        }
    }
}

class BaddyScoreBoardPanel extends JPanel
{
    private BadmintonTyperHolder panelCards;
    private CardLayout cards;
    private JButton next;
    private JButton back;
    
    public BaddyScoreBoardPanel(BadmintonTyperHolder panelCardsIn, CardLayout cardsIn)
    {
        panelCards = panelCardsIn;
        cards = cardsIn;
        panelCards = panelCardsIn;
        cards = cardsIn;
        BaddyStartPanel bsp = new BaddyStartPanel(panelCards, cards);
        bsp.repaint();
        setLayout(new BorderLayout());
        next = new JButton("Play again");
        back = new JButton("Back");
        BaddyScoreBoardButtonListener bsbbl = new BaddyScoreBoardButtonListener();
        next.addActionListener(bsbbl);
        back.addActionListener(bsbbl);
        add(next, BorderLayout.EAST);
        add(back, BorderLayout.WEST);
    }
    
    class BaddyScoreBoardButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent evt)
        {
            // The buttons added below isn't actually the "game panel" they
            // are just added for maneuverability between the cards
            String command = evt.getActionCommand();
            if(command.equals("Play again"))
                cards.show(panelCards, "StartPage");
            else if(command.equals("Back"))
                cards.show(panelCards, "Directions");
        }
    }
}
