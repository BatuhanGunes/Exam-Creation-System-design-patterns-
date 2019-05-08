package Main;

import java.awt.EventQueue;

import View.AnaSayfa;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AnaSayfa frame = new AnaSayfa();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
