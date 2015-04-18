package leap;
/**
Author Name: Jeevan Bharat Drave
Email: G1drave@live.com
**/
import java.awt.Desktop;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.JPanel;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Controller.PolicyFlag;

public class Main extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static void main(String args[])
	{
		final HFC_MouseListener mouse=new HFC_MouseListener();
		final Controller controller= new Controller();
		controller.setPolicyFlags(PolicyFlag.POLICY_BACKGROUND_FRAMES);
		
		if(!SystemTray.isSupported())
		{
	        System.out.println("System tray is not supported !!! ");
	        return ;
	    }
		SystemTray system=SystemTray.getSystemTray();
		Image image=Toolkit.getDefaultToolkit().getImage("src/logo/Leap_Logo.gif");
		PopupMenu menu=new PopupMenu();
		MenuItem start=new MenuItem("Start");
		start.addActionListener(new ActionListener() 
		{			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				controller.addListener(mouse);
			}
		});		
		menu.add(start);
		
		
		
		MenuItem stop=new MenuItem("Stop");
		stop.addActionListener(new ActionListener() 
		{			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				// TODO Auto-generated method stub
				controller.removeListener(mouse);
				System.exit(0);
			}
		});
		menu.add(stop);
		
		//TrayIcon icon=new TrayIcon(image, "Hands", menu);
		TrayIcon icon=new TrayIcon(image, "Hands Free Computing", menu);
		icon.setImageAutoSize(true);
		try
		{
			System.out.println("Start");
			system.add(icon);
		}
		
		catch(Exception e)
		{}			
		
		
		try
		{
			System.in.read();
		}
		catch(Exception e)
		{}		
	}
}
