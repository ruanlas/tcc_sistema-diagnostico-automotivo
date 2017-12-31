package app;

import java.awt.EventQueue;

import view.ScreenMonitor;

public class AppStart {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScreenMonitor window = new ScreenMonitor();
					window.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
