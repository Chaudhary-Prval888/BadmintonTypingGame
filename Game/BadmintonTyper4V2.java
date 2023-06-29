// Praval Chaudhary
// 5/9/22 - 5/13/22
// BadmintonTyper4.java
// This program is a start of the Game using Java. This sub-program creates the
// animations for the big game.

//imports for all the components used in program
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
import java.awt.Dimension;
import javax.swing.Timer;
import java.io.File;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JTable;

public class BadmintonTyper4V2
{    
    public BadmintonTyper4V2(){}
    
    public static void main(String [] args)
    {
        BadmintonTyper4V2 bt4 = new BadmintonTyper4V2();
        bt4.run();
    }
    
    public void run()
    {
        JFrame frame = new JFrame("Badminton Typer");
        frame.setSize(765, 600);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setLocation(00, 50);
        frame.setResizable(true);
        BadmintonTyperHolder bth = new BadmintonTyperHolder();
        frame.getContentPane().add(bth);
        frame.setVisible(true);
    }
}
// This class holds all the panels of the program and intializes the card layout.
class BadmintonTyperHolder extends JPanel
{
    private BaddyLearnPanel blp;
    private BaddyScoreBoardPanel bsbp;
    
    public BadmintonTyperHolder()
    {
        CardLayout cards = new CardLayout();
        setLayout(cards);
        int fakeScoreVal = 0;
        BaddyStartPanel btsp = new BaddyStartPanel(this, cards);
        BaddyDirectionPanel btdp = new BaddyDirectionPanel(this, cards);
        BaddyPlayPanel bpp = new BaddyPlayPanel(this, cards, fakeScoreVal);
        blp = new BaddyLearnPanel(this, cards, fakeScoreVal);
        bsbp = new BaddyScoreBoardPanel(this, cards, fakeScoreVal);
        
        add(btsp, "StartPage");
        add(btdp, "Directions");
        add(bpp,  "GamePanel");
        add(blp, "LearnPanel");
        add(bsbp, "ScoreBoard");
    }
    
    public BaddyLearnPanel getBsp()
    {
        return blp;
    }
    
    public BaddyScoreBoardPanel getbsbp()
    {
		return bsbp;
	}
}

class BaddyStartPanel extends JPanel
{
    private BadmintonTyperHolder panelCards; //This instance is used to switch
                                            //between the Card Layouts
    private CardLayout cards;// This instance is used to switch between the Card
                        //Layouts
    private JLabel welcome; // This JLabels is used to display the name of the Program
    private JButton start; // These buttons are used to switch between
    private JButton exit; // the different layouts
    private JMenu PanelChooser; // This menu is used to give the user access to
                                //all the panels at once.
    private String pictName; // This string is used to store th name of the file
    private BufferedImage img; // This Buffered image is used to store the loaded image
                              // from the getMyImage method
    
    public BaddyStartPanel(BadmintonTyperHolder panelCardsIn, CardLayout cardsIn)
    {
        //setBackground(new Color( 3, 170, 70));
        panelCards = panelCardsIn;
        cards = cardsIn;
        setLayout(null);
    
        baddyStartComponentPanel bscp = new baddyStartComponentPanel();
        add(bscp);
        bscp.setBounds(0, 0, 745, 555);
    }
    // the class is used to add the buttons and the menu to the Start panel.
    class baddyStartComponentPanel extends JPanel
    {
        
        public baddyStartComponentPanel()
        {
			setLayout(new BorderLayout());
            btButtonPanel bbp = new btButtonPanel();
          
            
            welcome = new JLabel("Badminton Typer", SwingConstants.CENTER);
			welcome.setFont(new Font("Segoe Script", Font.BOLD, 40));
            pictName = "Raquets.jpg";
			getMyImage();
			repaint();
			add(welcome, BorderLayout.NORTH);
        }
        
        class btButtonPanel
        {
			public btButtonPanel()
			{
				setLayout(new BorderLayout());
			
				welcome = new JLabel("Badminton Typer", SwingConstants.CENTER);
				welcome.setFont(new Font("Segoe Script", Font.BOLD, 40));
				Font baddyFont = new Font("Segoe Script", Font.PLAIN, 11);
				start = new JButton("Play");
				start.setFont(baddyFont);
				exit = new JButton("Exit");
				exit.setFont(baddyFont);
				btStartPanelButtonHandler btpbh = new btStartPanelButtonHandler();
				start.addActionListener(btpbh);
				exit.addActionListener(btpbh);
				
				JMenuBar bar = new JMenuBar();
				JMenu Menu = new JMenu("Other Panels");
				Menu.setFont(baddyFont);
				JMenuItem Direction = new JMenuItem("Direction Panel");
				Direction.setFont(baddyFont);
				JMenuItem PlayPanel = new JMenuItem("Play Panel");
				PlayPanel.setFont(baddyFont);
				JMenuItem Scoreboard = new JMenuItem("Scoreboard");
				Scoreboard.setFont(baddyFont);
				btMenuHandler btmh = new btMenuHandler();        
				Direction.addActionListener(btmh);
				PlayPanel.addActionListener(btmh);
				Scoreboard.addActionListener(btmh);
				Menu.add(Direction);
				Menu.add(PlayPanel);
				Menu.add(Scoreboard);
				
				bar.add(Menu);
				add(bar, BorderLayout.WEST);
				add(start, BorderLayout.SOUTH);
				add(exit, BorderLayout.EAST);
			}
		}
        public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
				g.drawImage(img, 100, 50, this);
		}
    }
    
    // Method is used to load the image into the program
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
    
    // This class is used to draw the next and back buttons to make the flow of
    // cards possible without these two buttons the start panel would never be able
    // to switch between the different cards inside this program.
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
    
    // This class is used to draw thens to make listen to the menu and is used
    // to switch between the different cards inside this program.
    class btMenuHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent evt)
        {
            String menuAction = null;
            menuAction = evt.getActionCommand();
            if (menuAction.equals("Direction Panel"))
               cards.show(panelCards, "Directions");
            else if (menuAction.equals("Play Panel"))
               cards.show(panelCards, "GamePanel");
            else if (menuAction.equals("Scoreboard"))
               cards.show(panelCards, "ScoreBoard");
        }
    }
}

class BaddyDirectionPanel extends JPanel
{
    private BadmintonTyperHolder panelCards; // This instance is used to switch
                                            // between the Card Layouts
    private CardLayout cards; // This instance is used to switch between the
                            // Card Layouts
    private JLabel label; // The label is used to write the title of the panel.
    private JTextArea directions; // This text area is used to display the
                                //directions to the user.
    
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
    
    // Panel is used to create empty sides on the sides(right and left) of the
    //text area
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
    private BadmintonTyperHolder panelCards; // This instance is used to switch
                                            // between the Card Layouts
    private CardLayout cards; // This instance is used to switch between the Card
                              // Layouts
    private JButton next; // These buttons are used to switch between the different
    private JButton back; // cards inside this program depending on the user input
    
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
            
            if (command.equals("Next"))
                cards.show(panelCards, "GamePanel");
            else if(command.equals("Back"))
                cards.show(panelCards, "StartPage");
        }
    }
}

class BaddyPlayPanel extends JPanel
{
    private BadmintonTyperHolder panelCards; // This instance is used to switch
                                             //between the Card Layouts
    private CardLayout cards; // This instance is used to switch between the Card
                              // Layouts
    private Timer timer; // The timer is used to change the y - values in the
                         //listener so that the circle can fall like an animation
    private int x,y; // These variables are used for drawing the circle using
                     //the paintComponent method
    
    private String [] inputFileNames; // Consists of all the file names being read
                                     // in
    private String [] threeLetterWords; // These are the arrays the words from the
    private String [] fourLetterWords;// files are saved into. The name of the array
    private String [] fiveLetterWords; // corres ponfs with the text file it associat
    private String [] LargeWords; // associates it self with
    
    private String line; // This is used to caputure the next line of the text file
    private String word; // Used to draw the word on top of the circle using the
                         // paintComponent method
    private Font playFont;
    private JLabel Score; // These labels are used to inform the user about the amount
    private JLabel Hearts; // of hearts the user has left and the display the score.
    private JTextField userInput; // This is used to make the user type the word into
                                    // the program
    private int hearts; // This variable is used to keep  track of the amount of
                    // lives the user has left
    private int score; // This field variable is used to increment the score of the user
    private TimerActionListener tmral; // listener for timer
    private int count; // This variable is used to stop the timer after it
                           // has been called 40 times
    
    public BaddyPlayPanel(BadmintonTyperHolder panelCardsIn, CardLayout cardsIn, int yIn)
    {
        y = yIn;
        setBackground(Color.BLUE);
        repaint();
        hearts = 5;
        panelCards = panelCardsIn;
        cards = cardsIn;
        
        /* Load words from the files into the lists */
        inputFileNames = new String [] {"3LetterWords.txt",
            "4LetterWords.txt", "5LetterWords.txt", "LargerWords.txt"};
        threeLetterWords = new String [50];
        fourLetterWords = new String [50];
        fiveLetterWords = new String [50];
        LargeWords = new String[50];
        
        line = new String("");
        word = new String("");
        playFont = new Font("Segoe Script", Font.PLAIN, 20);
        setUpTypingWords();
        chooseRandomWord();
        
        setLayout(new BorderLayout());
        BaddySouthPlayPanel bspp = new BaddySouthPlayPanel(panelCards, cards);  
        TimerActionListener tmral = new TimerActionListener();
        timer = new Timer(350, tmral);
        add(bspp);
    }
    // method is used to change the background color of the play panel
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
    }
    
    // This method uses a for loop to load all the words from the different text files
    // into the different arrays by calling a method with a try-catch block
    public void setUpTypingWords()
    {
        
        int index = 0;
        
        for (index = 0; index <= 4; index++)
        {
            if (index == 0)
                loadWordsFromDifferentFiles(inputFileNames[index], threeLetterWords);
            else if (index == 1)
                loadWordsFromDifferentFiles(inputFileNames[index], fourLetterWords);
            else if (index == 2)
                loadWordsFromDifferentFiles(inputFileNames[index], fiveLetterWords);
            else if (index == 3)
                loadWordsFromDifferentFiles(inputFileNames[index], LargeWords);
            
        }
    }
    
    // This method randomly selects a random word to be drawn on top of the circle
    // using the Math.random method
    public void chooseRandomWord()
    {
        int wordPlaySet = 0;
        int randomWord = 0;
        int xRandom = 0;
        xRandom = (int) (Math.random()* 300 + 0);
        x = xRandom;
        wordPlaySet = (int) (Math.random() * 4 + 1);
        randomWord = (int) (Math.random() * 50 + 0);
        if(wordPlaySet == 1)
        {
            word = threeLetterWords[randomWord];
        }
        else if(wordPlaySet == 2)
        {
            word = fourLetterWords[randomWord];
        }
        else if(wordPlaySet == 3)
        {
            word = fiveLetterWords[randomWord];
        }
        else if(wordPlaySet == 4)
        {
            word = LargeWords[randomWord];
        } 
    }
    
    // This method is used to load the words from the different textfiles
    public void loadWordsFromDifferentFiles(String inFileName, String [] listOfWords)
    {
        String fileName = inFileName;
        Scanner inFile = null;
        File inputFile = new File(fileName);
        int count = 0;
        
        try
        {
            inFile = new Scanner(inputFile);
        }
        catch(FileNotFoundException e)
        {
            System.err.printf("ERROR: Cannot %s\n", fileName);
            System.exit(1);
        }        
    
        while(inFile.hasNext())
        {
            line = inFile.nextLine();
            listOfWords[count++] = line;
        }
        inFile.close();
    }
    
    // This method uses the PrintWriter and appending to print the current
    // score into the file
    public void printScoresToFile()
    {
        File ioFile = new File("pastScores.txt");
        PrintWriter outFile = null;
        try
        {
            outFile = new PrintWriter(new FileWriter(ioFile, true));
        }
        catch(IOException e)
        {
            e.printStackTrace();
            System.exit(2);
        }
        outFile.println(score);
        outFile.close();
    }
    
    /* This class uses the timer to increase the y-values of the circle to make
     * it an animation */
    class TimerActionListener implements ActionListener
    {
        
        public TimerActionListener()
        {
            x = 20;
            y = 30;
        }
        
        public void actionPerformed( ActionEvent evt)
        {
            count++;
            if (y < getHeight() - 50)
            {
                y += 10;
                repaint();
            }
            else if (y > getHeight()-50)
            {
                timer.stop();
            }
            
            if (hearts == 0)
            {
               timer.stop();
               printScoresToFile();
               panelCards.getBsp().setTextArea(score);
               panelCards.getbsbp().setScore(score);
               cards.show(panelCards, "LearnPanel");
            }
            else if(count == 41)
            {
                // Rework this code so that we can restart the word
                // after we hit the bottom.
                hearts--;
                Hearts.setText("Number of lives left: " + hearts);
                chooseRandomWord();
                timer.stop();
                cards.show(panelCards, "GamePanel");
                y = 30;
                repaint();
            }
        }
    }
    
    /* This class is used to call all the other panels of this panel.*/
    class BaddySouthPlayPanel extends JPanel
    {    
        private BadmintonTyperHolder panelCards; //This instance is used to
        //switch between the Card Layouts
        private CardLayout cards; //This instance is used to switch between the
                                  //Card Layouts
        
        public BaddySouthPlayPanel(BadmintonTyperHolder panelCardsIn, CardLayout cardsIn)
        {
            setBackground(new Color(110, 255, 170));
            panelCards = panelCardsIn;
            cards = cardsIn;
            setLayout(new BorderLayout());
            BaddyFieldPanel bfp = new BaddyFieldPanel(panelCards, cards);
            BaddyPlayLabels bpl = new BaddyPlayLabels(panelCards, cards);
            
            BaddyPlayShuttle bps = new BaddyPlayShuttle();
            bps.repaint();
            add(bfp, BorderLayout.SOUTH);
            add(bps, BorderLayout.CENTER);
            add(bpl, BorderLayout.EAST);
        }
    }
    
    // This class prints the circle into the panel after the timerListener changes
    // the values using the timer.
    class BaddyPlayShuttle extends JPanel
    {
            
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            setBackground(new Color(118, 208, 255));
            //System.out.println("X: " + x +" Y" + y);
            g.setColor(Color.GREEN);
            g.fillOval(x, y, 50, 50);
            g.setColor(Color.BLACK);
            g.setFont(playFont);
            g.drawString(word, x, y);
        }
    }
    
    
    class BaddyPlayPanelButtons extends JPanel
    {
        // This method is used to draw the next and back buttons to make the flow of
        // cards possible without these two buttons the game panel would never be able
        // to switch between the different cards inside this program.
        public BaddyPlayPanelButtons()
        {
            setLayout(new BorderLayout());
            JButton next = new JButton("Next"); // These buttons are used to
            //switch between the different
            JButton back = new JButton("Back"); // cards inside this program
            //depending on the user input
            BaddyPlayButtonListener bpbl = new BaddyPlayButtonListener();
            next.addActionListener(bpbl);
            back.addActionListener(bpbl);
            add(next, BorderLayout.EAST);
            add(back, BorderLayout.WEST);
        }
    }
    
    // This class implements the ActionListener and listens for the next and back
    // buttons when an action occurs in one of the buttons it is able to change
    // the card that is showing on the screen
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
    // This class is used to intialize the textfield to create a location for the
    // user to input the word that appears on the circle. This class also has an
    // instance of the PlayPanelButtons.
    class BaddyFieldPanel extends JPanel
    {
        private BadmintonTyperHolder panelCards; // This instance is used to switch
                                             //between the Card Layouts
        private CardLayout cards; // This instance is used to switch between the Card
                                  // Layouts
        private BaddyPlayShuttle bps; // instance of BaddyPlayShuttle used to
                                     // the paintComponent method of BaddyPlayShuttle
        
        // Creates the textField for the user to enter the word he/she sees above
        public BaddyFieldPanel(BadmintonTyperHolder panelCardsIn , CardLayout cardsIn)
        {
            panelCards = panelCardsIn;
            cards = cardsIn;
            userInput = new JTextField("Enter the word you see above here", 20);
            Font font = new Font("Segoe Script", Font.PLAIN, 20);
            userInput.setFont(font);
            userInput.setEditable(false);
            userInput.setHorizontalAlignment(SwingConstants.LEFT);
            add(userInput);
            bps = new BaddyPlayShuttle();
            baddyTextFieldListener btfl = new baddyTextFieldListener();
            userInput.addActionListener(btfl);
            BaddyPlayPanelButtons bppb = new BaddyPlayPanelButtons();
            add(bppb);
        }
        
        class baddyTextFieldListener implements ActionListener
        {
            // This method listens to the textfield listener and depending on wheater
            // its right or wrong take the steps to make the game run more smoothly
            public void actionPerformed(ActionEvent evt)
            {
                String command = evt.getActionCommand();
                if(command.equals(word))
                {
                    userInput.setText("");
                    count = 0;
                    score += 10;
                    Score.setText("Your Score is: " + score);
                    chooseRandomWord();
                    timer.stop(); // stop the timer
                    timer.start(); // start the timer for next word
                    cards.show(panelCards, "GamePanel");
                    y = 30;
                    repaint();
                }
                else if(!command.equals(word))
                {
                    userInput.setText("");
                    hearts--;
                    Hearts.setText("Number of lives left: " + hearts);
                    chooseRandomWord();
                    timer.stop();  // stop the timer
                    timer.start(); // start the timer
                    cards.show(panelCards, "GamePanel");
                    y = 30;
                    repaint();
                }
            }
        }
    }
    
    // This class is uses the Label to display important information to the user.
    // This includes the Score, number of lives and play pause buttons.
    class BaddyPlayLabels extends JPanel
    {
        private JLabel Stats; // used to state the title of the east panel with all
                            // the information about the game
        private JButton play; // used to make the circle start falling
        private JButton pause;
        private BadmintonTyperHolder panelCards; // This instance is used to switch
                                             //between the Card Layouts
        private CardLayout cards; // This instance is used to switch between the Card
                              // Layouts
        
        // Method cretes the labels which give the user important information about
        // the game
        public BaddyPlayLabels(BadmintonTyperHolder panelCardsIn, CardLayout cardsIn)
        {
            panelCards = panelCardsIn;
            cards = cardsIn;
            setLayout(new GridLayout(5,1));
            Stats = new JLabel("List of important things");
            Font font1 = new Font("Segoe Script", Font.PLAIN, 20);
            Score = new JLabel("Your Score Is: 0");
            Score.setFont(font1);
            Hearts = new JLabel("Number of Lives left: 5");
            Hearts.setFont(font1);
            add(Stats);
            add(Score);
            add(Hearts);
            play = new JButton("Play");
            pause = new JButton("Pause");
            Font font  = new Font("Segoe Script", Font.PLAIN, 11);
            play.setFont(font);
            TimerButtonListener tmrbl = new TimerButtonListener();
            play.addActionListener(tmrbl);
            pause.addActionListener(tmrbl);
            add(play);
            add(pause);
        }
        
        // This class implements the ActionListener and listens for the next and back
        // buttons when an action occurs in one of the buttons it is able to change
        //the card that is showing on the screen
        class TimerButtonListener extends JPanel implements ActionListener
        {
            private boolean pausePressed;
            
            public TimerButtonListener()
            {
                pausePressed = true;
            }
            
            public void actionPerformed(ActionEvent evt)
            {
                String command  = evt.getActionCommand();
                if(command.equals("Play"))
                {
                    userInput.setEditable(true);
                    timer.start();
                    if(pausePressed)
                    {
                        hearts = 5;
                        score = 0;
                        Hearts.setText("Number of Hearts left: 5");
                        Score.setText("Your Score is: 0");
                    }
                }
                else if(command.equals("Pause"))
                {
                    pausePressed = false;
                    timer.stop();
                    userInput.setEditable(false);
                }
            }
        }
    }
}
 
// This panel is used to make the user learn how to type faster. It will be
// used for giving the user a couple of links for him/her to be able to learn
// how to type faster.
class BaddyLearnPanel extends JPanel
{
    private BadmintonTyperHolder panelCards; // This instance is used to switch
                                            //between the Card Layouts
    private CardLayout cards; // This instance is used to switch between the Card
                              // Layouts
    private JButton next; // These buttons are used to switch between the different
    private JButton back; // cards inside this program depending on the user input
    private int score;
    private JLabel gameOver;
    
    public BaddyLearnPanel(BadmintonTyperHolder panelCardsIn, CardLayout cardsIn, int scoreIn)
    {
        setLayout(new BorderLayout());
        panelCards = panelCardsIn;
        cards = cardsIn;
        panelCards = panelCardsIn;
        cards = cardsIn;
        score  = scoreIn;
        setLayout(new BorderLayout());
        next = new JButton("Next");
        back = new JButton("Back");
        Font large = new Font("Segoe Script", Font.BOLD, 30);
        String gameOverStr = new String("Game Over                        Your Score is: "+ scoreIn);
        gameOver = new JLabel(gameOverStr);
        cards.show(panelCards, "LearnPanel");
        gameOver.setFont(large);
        Font trump = new Font("Segoe Script", Font.PLAIN, 20);
        JTextArea learn = new JTextArea("To type faster you should sit in an up"+
                " straight and make sure your eyes are on the screen at"+
                " all TIMES. Keep you fingers on the home row at all times"+
                ". Keep your head straight into the screen and type like a pro."+
                "Make sure you are sitting on a chair and your computer should "+
                "be on a hard surface(ie desk). To practice in more depth you can do some" +
                "lessons in Typing Club (Link to typing club copy and paste" +
                " into broswer https://www.typingclub.com/login.html).");
        learn.setFont(trump);
        learn.setEditable(false);
        learn.setLineWrap(true);
        learn.setWrapStyleWord(true);
        //learn.setHorizontalAlignment(CENTER);
        BaddyLearnButtonListener blbl = new BaddyLearnButtonListener();
        next.addActionListener(blbl);
        back.addActionListener(blbl);
        //BaddyLearnLabels bll = new BaddyLearnLabels();
        
        add(next, BorderLayout.EAST);
        add(back, BorderLayout.WEST);
        add(gameOver, BorderLayout.NORTH);
        add(learn, BorderLayout.CENTER);
    }
    
    public void setTextArea(int scoreIn)
    {
        score = scoreIn;
        gameOver.setText("Game Over                        Your Score is: "+ score);
    }
    
    // This class implements the ActionListener and listens for the next and back
    // buttons when an action occurs in one of the buttons it is able to change
    //the card that is showing on the screen
    class BaddyLearnButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent evt)
        {
            // The buttons added below isn't actually the "game panel" they
            // are just added for maneuverability between the cards
            String command = evt.getActionCommand();
            if(command.equals("Next"))
                cards.show(panelCards, "ScoreBoard");
            else if(command.equals("Back"))
                cards.show(panelCards, "GamePanel");
        }
    }
}

class BaddyScoreBoardPanel extends JPanel
{
    private BadmintonTyperHolder panelCards; // This instance is used to switch
                                             //between the Card Layouts
    private CardLayout cards; // This instance is used to switch between the Card
                              // Layouts
    private JButton next; // These buttons are used to switch between the different
    private JButton back; // cards inside this program depending on the user input
    private JTable table;
    private String [] data;
    private String [] namesOfPeople;
    private boolean tfEntered;
    private int score;
    
    public BaddyScoreBoardPanel(BadmintonTyperHolder panelCardsIn, CardLayout cardsIn, int scoreIn)
    {
		tfEntered = false;
        panelCards = panelCardsIn;
        cards = cardsIn;
        panelCards = panelCardsIn;
        cards = cardsIn;
        setLayout(new BorderLayout());
        next = new JButton("Return To Game");
        back = new JButton("Back");
        JLabel scoreBoard = new JLabel("Scoreboard:");
        JTextField nametf = new JTextField("Enter your name here to see the scoreboard");
        baddyNameTfListener ntfl = new baddyNameTfListener();
        nametf.addActionListener(ntfl);
        add(nametf, BorderLayout.SOUTH);
        scoreBoard.setFont(new Font("Dancing Script", Font.PLAIN, 30));
        
        table = new JTable(10, 2);
        table.setValueAt("Name", 0, 0);
        table.setValueAt("Score", 0, 1);
        table.setValueAt("Computer", 1,0);
        table.setValueAt("10000", 1,1);
        table.setValueAt("Praval", 2, 0);
        table.setValueAt("1000", 2,1);
        table.setValueAt("Bob", 3,0);
        table.setValueAt("170", 3,1);
        table.setValueAt("Sentinel", 4, 0);
        table.setValueAt("1800", 4,1);
        table.setFont(new Font("Dancing Script", Font.PLAIN, 16));
        
        BaddyScoreBoardButtonListener bsbbl = new BaddyScoreBoardButtonListener();
        next.addActionListener(bsbbl);
        back.addActionListener(bsbbl);
        add(next, BorderLayout.EAST);
        add(back, BorderLayout.WEST);
		add(table, BorderLayout.CENTER);
        add(scoreBoard, BorderLayout.NORTH);
    }
    
    public void setScore(int scoreIn)
    {
		score = scoreIn;
	}
    
    class baddyNameTfListener implements ActionListener
    {
		private int count;
		
		public baddyNameTfListener()
		{
			count = 4;
		}
		
		public void actionPerformed(ActionEvent evt)
		{
			String name = evt.getActionCommand();
			count++;
			table.setValueAt(name, count, 0);
			table.setValueAt(score, count, 1);
		}
	}
    
    class BaddyScoreBoardButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent evt)
        {
            // The buttons added below isn't actually the "game panel" they
            // are just added for maneuverability between the cards
            String command = evt.getActionCommand();
            if(command.equals("Return To Game"))
            {
                BadmintonTyperHolder bth = new BadmintonTyperHolder();
                cards.show(panelCards, "StartPage");
            }
            else if(command.equals("Back"))
                cards.show(panelCards, "LearnPanel");
        }
    }
}

