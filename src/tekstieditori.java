import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.awt.event.InputEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class tekstieditori extends JFrame {

	private static JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu mnTiedosto;
	private JMenu mnMuokkaa;
	private JMenu mnTietoja;
	private JMenuItem mntmAvaa;
	private JMenuItem mntmTallenna;
	private JMenuItem mntmLopeta;
	private JMenuItem mntmSulje;
	private JMenuItem mntmEtsi;
	private JMenuItem mntmKorvaa;
	private JMenuItem mntmTietojaOhjelmasta;
	private JToolBar toolBar;
	private JButton button;
	private JButton button_1;
	private JButton button_2;
	private JButton button_3;
	private static JEditorPane editorPane;
	private JButton button_4;
	
	public static void avaaTiedosto() { //avaa tiedoston valintaikkunan
		
		JFileChooser valintaikkuna = new JFileChooser();
		valintaikkuna.showOpenDialog(null);				
		String rivi ="";
		String uusiTiedosto = valintaikkuna.getSelectedFile().getAbsolutePath();
		
		try {
			
			Scanner lukija = null;
			File tiedosto = new File(uusiTiedosto);
			lukija = new Scanner(tiedosto);
			
			while (lukija.hasNextLine()) {
				rivi += lukija.nextLine()+"\n";
				System.out.println(rivi);
			}
			lukija.close();
		} catch (FileNotFoundException p) {
			System.out.println("Tiedostoa ei löydy..");
		}
		
		editorPane.setText(rivi);		
	}
	public static void tallennaTiedosto() { //Tallentaa tiedoston
		
		// Tallenusikkuna
		
		JFileChooser valintaikkuna = new JFileChooser();
		valintaikkuna.showSaveDialog(null);
		
		String uusiTiedosto = valintaikkuna.getSelectedFile().getAbsolutePath();
		System.out.println("Kirjoitettava tiedosto: " + uusiTiedosto);
		
		// Tallennuskoodi
		try { 
			
			PrintWriter writer = new PrintWriter(uusiTiedosto);
			String sisalto = editorPane.getText();
			
			writer.println(sisalto);
			
			// Vakiotoimet
			writer.flush();
			writer.close();
			
		} catch (Exception e1) {
			System.out.println("Tiedoston tallennuksessa tapahtui virhe!");
			e1.printStackTrace();
		}
		
	}
	public static void etsiTiedostosta() { // Etsii sanoja tiedostosta
		
		String haettava = JOptionPane.showInputDialog(null, "Etsi sana: ");
		String sisalto = editorPane.getText();
		sisalto = sisalto.toLowerCase();
		int indeksi = sisalto.indexOf(haettava);
		System.out.println("Indeksi: " + indeksi);
		
		editorPane.setSelectionColor(Color.yellow);
		editorPane.setSelectionStart(indeksi);
		editorPane.setSelectionEnd(indeksi + haettava.length());;
	}
	public static void korvaaSana() { // Korvaa sanoja tiedostossa
		
		String korvattava = JOptionPane.showInputDialog(contentPane, "Mikä sana korvataan?");
		String korvaus = JOptionPane.showInputDialog(contentPane, "Millä sanalla korvataan?");
		String sisalto = editorPane.getText();
		String replaceString = sisalto.replaceAll(korvattava, korvaus);
		editorPane.setText(replaceString);
	}
	public static void showAbout() { // Näyttää tietoja ohjelmasta
		JOptionPane.showMessageDialog(contentPane, "Version 1.0.0 \nMatias Kohanevic \nhttps://github.com/BuluMatziel/KT3", "Tietoja ohjelmasta", JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					tekstieditori frame = new tekstieditori();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
	}

	/**
	 * Create the frame.
	 */
	public tekstieditori() {
		setTitle("Oma tekstieditori");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnTiedosto = new JMenu("Tiedosto");
		menuBar.add(mnTiedosto);
		
		mntmAvaa = new JMenuItem("Avaa");
		mntmAvaa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				avaaTiedosto();				
			}
		});
		mntmAvaa.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		mntmAvaa.setIcon(new ImageIcon(tekstieditori.class.getResource("/javax/swing/plaf/metal/icons/ocean/directory.gif")));
		mnTiedosto.add(mntmAvaa);
		
		mntmTallenna = new JMenuItem("Tallenna");
		mntmTallenna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tallennaTiedosto();
			}
		});
		mntmTallenna.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		mntmTallenna.setIcon(new ImageIcon(tekstieditori.class.getResource("/javax/swing/plaf/metal/icons/ocean/floppy.gif")));
		mnTiedosto.add(mntmTallenna);
		
		mntmLopeta = new JMenuItem("Lopeta");
		mntmLopeta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);//Lopettaa ohjelman
			}
		});
		mntmLopeta.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
		mntmLopeta.setIcon(new ImageIcon(tekstieditori.class.getResource("/javax/swing/plaf/metal/icons/ocean/close.gif")));
		mnTiedosto.add(mntmLopeta);
		
		mntmSulje = new JMenuItem("Sulje");
		mnTiedosto.add(mntmSulje);
		
		mnMuokkaa = new JMenu("Muokkaa");
		menuBar.add(mnMuokkaa);
		
		mntmEtsi = new JMenuItem("Etsi");
		mntmEtsi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				etsiTiedostosta();
			}
		});
		mntmEtsi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_MASK));
		mntmEtsi.setIcon(new ImageIcon(tekstieditori.class.getResource("/javax/swing/plaf/metal/icons/ocean/expanded.gif")));
		mnMuokkaa.add(mntmEtsi);
		
		mntmKorvaa = new JMenuItem("Korvaa");
		mntmKorvaa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				korvaaSana();
			}
		});
		mntmKorvaa.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_K, InputEvent.CTRL_MASK));
		mntmKorvaa.setIcon(new ImageIcon(tekstieditori.class.getResource("/com/sun/javafx/scene/web/skin/Cut_16x16_JFX.png")));
		mnMuokkaa.add(mntmKorvaa);
		
		mnTietoja = new JMenu("Tietoja");
		menuBar.add(mnTietoja);
		
		mntmTietojaOhjelmasta = new JMenuItem("Tietoja ohjelmasta");
		mntmTietojaOhjelmasta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showAbout();
			}
		});
		mntmTietojaOhjelmasta.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_MASK));
		mntmTietojaOhjelmasta.setIcon(new ImageIcon(tekstieditori.class.getResource("/com/sun/java/swing/plaf/windows/icons/Inform.gif")));
		mnTietoja.add(mntmTietojaOhjelmasta);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		toolBar = new JToolBar();
		contentPane.add(toolBar, BorderLayout.NORTH);
		
		button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editorPane.setText("");
			}
		});
		button.setIcon(new ImageIcon(tekstieditori.class.getResource("/javax/swing/plaf/metal/icons/ocean/file.gif")));
		toolBar.add(button);
		
		button_1 = new JButton("");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				avaaTiedosto();
			}
		});
		button_1.setIcon(new ImageIcon(tekstieditori.class.getResource("/com/sun/java/swing/plaf/windows/icons/Directory.gif")));
		toolBar.add(button_1);
		
		button_2 = new JButton("");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tallennaTiedosto();
			}
		});
		button_2.setIcon(new ImageIcon(tekstieditori.class.getResource("/javax/swing/plaf/metal/icons/ocean/floppy.gif")));
		toolBar.add(button_2);
		
		button_3 = new JButton("");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				korvaaSana();
			}
		});
		button_3.setIcon(new ImageIcon(tekstieditori.class.getResource("/com/sun/javafx/scene/web/skin/Cut_16x16_JFX.png")));
		toolBar.add(button_3);
		
		button_4 = new JButton("");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showAbout();
			}
		});
		button_4.setIcon(new ImageIcon(tekstieditori.class.getResource("/com/sun/java/swing/plaf/motif/icons/Inform.gif")));
		toolBar.add(button_4);
		
		editorPane = new JEditorPane();
		contentPane.add(editorPane, BorderLayout.CENTER);
	}

}
