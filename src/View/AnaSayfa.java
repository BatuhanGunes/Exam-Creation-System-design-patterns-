package View;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import View.DersIslemleri;
import View.KategoriIslemleri;
import View.SinavOlustur;
import View.SoruIslemleri;
import View.Zorluk;
import javax.swing.JDesktopPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.beans.PropertyVetoException;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;

public class AnaSayfa extends JFrame {

	
	//private PresenterAnaSayfa _PAnaSayfa;
	
	private JPanel contentPane;


	/**
	 * Create the frame.
	 */
	
	public AnaSayfa() {

		//PresenterAna Sayfa ile baðlantý için nesnesini oluþturduk
		//_PAnaSayfa = new PresenterAnaSayfa();
		
		
		setTitle("Sinav Olusturucu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 0, 1024, 768);
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(SystemColor.scrollbar);
		desktopPane.setBounds(0, 0, 1008, 729);
		contentPane.add(desktopPane);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1010, 22);
		desktopPane.add(menuBar);
		
		
		
		
		//------------------------------ Menü item Dosya ----------------------------------
		
		JMenu mnDosya = new JMenu("Dosya");
		menuBar.add(mnDosya);
		
		JMenuItem MExit = new JMenuItem("\u00C7\u0131k\u0131\u015F");
		MExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
			}
		});
		mnDosya.add(MExit);
		
		
		
		
		
		//------------------------------ Menü item Ders iþlemleri ----------------------------------
		
		JMenu mnDers_islemleri = new JMenu("Ders i\u015Flemleri");
		menuBar.add(mnDers_islemleri);
		
		JMenuItem MDersIslemleri = new JMenuItem("Ders i\u015Flemleri");
		MDersIslemleri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				DersIslemleri de = DersIslemleri.getObj();
				if(!de.isVisible()) {
				desktopPane.add(de);
				de.setVisible(true);
				}
				try {
					de.setSelected(true);
				} catch (PropertyVetoException e) {
					e.printStackTrace();
				}
			
			}
		});
		mnDers_islemleri.add(MDersIslemleri);
		
		JMenuItem MKategoriIslemleri = new JMenuItem("Kategori i\u015Flemleri");
		MKategoriIslemleri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				KategoriIslemleri ka = KategoriIslemleri.getObj();
				if(!ka.isVisible()) {
				desktopPane.add(ka);
				ka.setVisible(true);
				}
				try {
					ka.setSelected(true);
				} catch (PropertyVetoException eeeeee) {
					eeeeee.printStackTrace();
				}
				
			}
		});
		mnDers_islemleri.add(MKategoriIslemleri);
		
		
		
		
		//------------------------------ Menü item Soru iþlemleri ----------------------------------
		
		JMenu mnSoru_islemleri = new JMenu("Soru i\u015Flemleri");
		menuBar.add(mnSoru_islemleri);
		
		JMenuItem MSoruIslemleri = new JMenuItem("Soru i\u015Flemleri");
		MSoruIslemleri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				SoruIslemleri sl = SoruIslemleri.getObj();
				if(!sl.isVisible()) {
				desktopPane.add(sl);
				sl.setVisible(true);
				}
				try {
					sl.setSelected(true);
				} catch (PropertyVetoException eee) {
					eee.printStackTrace();
				}
				
			}
		});
		mnSoru_islemleri.add(MSoruIslemleri);
		
		JMenuItem MZorlukÝslemleri = new JMenuItem("Zorluk i\u015Flemleri");
		MZorlukÝslemleri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Zorluk z = Zorluk.getObj();
				if(!z.isVisible()) {
				desktopPane.add(z);
				z.setVisible(true);
				}
				try {
					z.setSelected(true);
				} catch (PropertyVetoException eeee) {
					eeee.printStackTrace();
				}

			}
		});
		mnSoru_islemleri.add(MZorlukÝslemleri);
		
		
		
		
		
		//------------------------------ Menü item Sýnav iþlemleri ----------------------------------
		
		JMenu mnSinav_islemleri = new JMenu("S\u0131nav i\u015Flemleri");
		menuBar.add(mnSinav_islemleri);
		
		JMenuItem MSinavOlustur = new JMenuItem("S\u0131nav Olu\u015Ftur");
		MSinavOlustur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				SinavOlustur so = SinavOlustur.getObj();
				if(!so.isVisible()) {
				desktopPane.add(so);
				so.setVisible(true);
				}
				try {
					so.setSelected(true);
				} catch (PropertyVetoException eeeee) {
					eeeee.printStackTrace();
				}
				
			}
		});
		mnSinav_islemleri.add(MSinavOlustur);
		
		
		//------------------------------ Menü item Öðretmen iþlemleri ----------------------------------
		
		JMenu mnretmenIlemleri = new JMenu("\u00D6\u011Fretmen i\u015Flemleri");
		menuBar.add(mnretmenIlemleri);
		
		JMenuItem mnýtmGzetmenIlemleri = new JMenuItem("G\u00F6zetmen i\u015Flemleri");
		mnýtmGzetmenIlemleri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Gozetmen G = Gozetmen.getObj();
				if(!G.isVisible()) {
				desktopPane.add(G);
				G.setVisible(true);
				}
				try {
					G.setSelected(true);
				} catch (PropertyVetoException eeee) {
					eeee.printStackTrace();
				}
			}
		});
		mnretmenIlemleri.add(mnýtmGzetmenIlemleri);
		
		JMenuItem mnýtmretimyesiIlemleri = new JMenuItem("\u00D6\u011Fretim \u00DCyesi i\u015Flemleri");
		mnýtmretimyesiIlemleri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				OgretimUyesi Uye = OgretimUyesi.getObj();
				if(!Uye.isVisible()) {
				desktopPane.add(Uye);
				Uye.setVisible(true);
				}
				try {
					Uye.setSelected(true);
				} catch (PropertyVetoException eeee) {
					eeee.printStackTrace();
				}
				
			}
		});
		mnretmenIlemleri.add(mnýtmretimyesiIlemleri);
		
		//------------------------------ Menü item Derslik iþlemleri ----------------------------------
		
		JMenu mnDerslikIlemleri = new JMenu("Derslik i\u015Flemleri");
		menuBar.add(mnDerslikIlemleri);
		
		JMenuItem mnýtmDerslikIlemleri = new JMenuItem("Derslik i\u015Flemleri");
		mnýtmDerslikIlemleri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Derslik Ders = Derslik.getObj();
				if(!Ders.isVisible()) {
				desktopPane.add(Ders);
				Ders.setVisible(true);
				}
				try {
					Ders.setSelected(true);
				} catch (PropertyVetoException eeee) {
					eeee.printStackTrace();
				}
			}
		});
		mnDerslikIlemleri.add(mnýtmDerslikIlemleri);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(200, 200, 200), new Color(240, 240, 240), new Color(200, 200, 200), new Color(240, 240, 240)));
		panel.setBounds(10, 35, 986, 670);
		desktopPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblTitle = new JLabel("SORU BANKASI");
		lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 50));
		lblTitle.setBounds(311, 13, 436, 74);
		panel.add(lblTitle);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(12, 85, 962, 2);
		panel.add(separator);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, SystemColor.controlShadow, SystemColor.control, SystemColor.control, SystemColor.controlShadow));
		panel_1.setBounds(22, 101, 450, 556);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblHazirlayanlar = new JLabel("Haz\u0131rlayanlar");
		lblHazirlayanlar.setFont(new Font("Times New Roman", Font.PLAIN, 40));
		lblHazirlayanlar.setHorizontalAlignment(SwingConstants.CENTER);
		lblHazirlayanlar.setBounds(12, 13, 426, 58);
		panel_1.add(lblHazirlayanlar);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(12, 13, 426, 2);
		panel_1.add(separator_3);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(12, 69, 426, 2);
		panel_1.add(separator_4);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new BevelBorder(BevelBorder.LOWERED, SystemColor.controlShadow, SystemColor.control, SystemColor.control, SystemColor.controlShadow));
		panel_3.setBounds(12, 84, 426, 460);
		panel_1.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel label_5 = new JLabel("Batuhan G\u00DCNE\u015E");
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_5.setBounds(10, 70, 245, 58);
		panel_3.add(label_5);
		
		JLabel label_6 = new JLabel("Emre Okan DI\u015EKAYA");
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_6.setBounds(10, 154, 245, 58);
		panel_3.add(label_6);
		
		JLabel label_7 = new JLabel("Dursun DURAK");
		label_7.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_7.setBounds(10, 240, 245, 58);
		panel_3.add(label_7);
		
		JLabel label_8 = new JLabel("M. E. Berkay KOCAO\u011ELU");
		label_8.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_8.setBounds(10, 326, 245, 58);
		panel_3.add(label_8);
		
		JSeparator separator_11 = new JSeparator();
		separator_11.setBounds(0, 311, 426, 2);
		panel_3.add(separator_11);
		
		JSeparator separator_12 = new JSeparator();
		separator_12.setBounds(0, 225, 426, 2);
		panel_3.add(separator_12);
		
		JSeparator separator_13 = new JSeparator();
		separator_13.setBounds(0, 139, 426, 2);
		panel_3.add(separator_13);
		
		JSeparator separator_14 = new JSeparator();
		separator_14.setBounds(0, 70, 426, 2);
		panel_3.add(separator_14);
		
		JSeparator separator_15 = new JSeparator();
		separator_15.setBounds(0, 397, 426, 2);
		panel_3.add(separator_15);
		
		JLabel label_4 = new JLabel("201513171055");
		label_4.setBounds(267, 92, 120, 16);
		panel_3.add(label_4);
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel label_3 = new JLabel("201513171022");
		label_3.setBounds(267, 176, 120, 16);
		panel_3.add(label_3);
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel label_2 = new JLabel("201513171054");
		label_2.setBounds(267, 262, 120, 16);
		panel_3.add(label_2);
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel label_1 = new JLabel("201513171070");
		label_1.setBounds(267, 348, 120, 16);
		panel_3.add(label_1);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new BevelBorder(BevelBorder.LOWERED, SystemColor.controlShadow, new Color(240, 240, 240), new Color(240, 240, 240), SystemColor.controlShadow));
		panel_2.setBounds(524, 100, 450, 557);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNedir = new JLabel("Nedir ?");
		lblNedir.setHorizontalAlignment(SwingConstants.CENTER);
		lblNedir.setFont(new Font("Times New Roman", Font.PLAIN, 40));
		lblNedir.setBounds(12, 13, 426, 58);
		panel_2.add(lblNedir);
		
		JSeparator separator_5 = new JSeparator();
		separator_5.setBounds(12, 13, 426, 2);
		panel_2.add(separator_5);
		
		JSeparator separator_6 = new JSeparator();
		separator_6.setBounds(12, 69, 426, 2);
		panel_2.add(separator_6);
		
		JTextArea txtrSoruBankasUygulamas = new JTextArea();
		txtrSoruBankasUygulamas.setWrapStyleWord(true);
		txtrSoruBankasUygulamas.setEditable(false);
		txtrSoruBankasUygulamas.setBackground(SystemColor.control);
		txtrSoruBankasUygulamas.setFont(new Font("Monospaced", Font.PLAIN, 16));
		txtrSoruBankasUygulamas.setLineWrap(true);
		txtrSoruBankasUygulamas.setText("     Soru Bankas\u0131 uygulamas\u0131, lise m\u00FCfredat\u0131n\u0131 kapsayan konular\u0131n farkl\u0131 t\u00FCrlerde sorular\u0131n\u0131 i\u00E7ermektedir. Mevcut dersler g\u00FCncellenebilir veya \u00E7\u0131kart\u0131labilir, istenilirse yeni dersler eklenebilir. Sorular\u0131 g\u00F6r\u00FCnt\u00FCleyebilip \u00FCzerilerinde de\u011Fi\u015Ftirme yap\u0131labilir. Do\u011Fru yanl\u0131\u015F, klasik ve \u00E7oktan se\u00E7meli olmak \u00FCzere 3 adet soru tipi vard\u0131r. \u0130stenilirse bunlar\u0131n d\u0131\u015F\u0131nda da soru tipi eklenebilir veya varolan soru tipleri de\u011Fi\u015Ftirilebilir.\r\n    S\u0131nav i\u015Flemlerinden s\u0131nav olu\u015Ftur i\u015Flemi ile istenilen derse istenilen zorlukta \u00E7e\u015Fitli tipte sorular eklenerek dilenilen bir s\u0131nav ka\u011F\u0131d\u0131 olu\u015Fturulabilir. Olu\u015Fturulan s\u0131nav, yazd\u0131r diyerek \u00E7\u0131kart\u0131labilir.\r\n");
		txtrSoruBankasUygulamas.setBounds(12, 84, 426, 460);
		panel_2.add(txtrSoruBankasUygulamas);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBackground(SystemColor.window);
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(484, 100, 30, 557);
		panel.add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBounds(511, 100, 1, 557);
		panel.add(separator_2);
	}
}
