/**
 * 
 */
package AppBDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * @author Stagiaire
 *
 */
public class Authentify {

	private static Display display = new Display();
	private static Shell shell = new Shell(display);

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		DrawAuthentify();

		shell.pack();
		while (!display.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}

	public static void TryConnect(String username, String dbPassword, Label label)/** METHOD RETARDE */
	{

		try {

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/personnedonnees", username,
					dbPassword);
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("show databases;");
			display.dispose();
			ApplicationBDD app = new ApplicationBDD();
			app.open(con);
		} catch (Exception e) {
			label.setText("Acces denied");
			label.getParent().layout();
			System.out.println(e);
		}
	}

	private static void DrawAuthentify() {
		GridLayout layout = new GridLayout(1, true);
		shell.setLayout(layout);
		shell.setText("Authentication");
		shell.setImage(new Image(display,
				"C:\\Users\\Stagiaire\\eclipse-workspace\\AppBDD\\src\\AppBDD\\kgpg_key1_kopete.ico"));
		shell.open();

		GridData layoutGrid = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);

		shell.setBackground(new Color(150, 150, 150));
		Group group1 = new Group(shell, SWT.SHADOW_ETCHED_IN);
		group1.setText("Authentication");
		group1.setLayout(new GridLayout(1, true));
		group1.setLayoutData(layoutGrid);

		group1.pack();

		Label labelStatus = new Label(group1, SWT.None);
		labelStatus.setText("");

		Label labelLogin = new Label(group1, SWT.NONE);
		labelLogin.setText("Login:");

		Text Login = new Text(group1, SWT.None);
		Login.setLayoutData(layoutGrid);
		Login.setBackground(new Color(255, 255, 255));

		Label labelPassword = new Label(group1, SWT.NONE);
		labelPassword.setText("¨Password:");

		Text Password = new Text(group1, SWT.BORDER | SWT.PASSWORD);
		Password.setLayoutData(layoutGrid);

		Button Submit = new Button(group1, SWT.PUSH | SWT.CENTER | SWT.FILL);
		Submit.setLayoutData(layoutGrid);
		Submit.setText("Submit");
		shell.pack();

		Submit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TryConnect(Login.getText(), Password.getText(), labelStatus);
			}
		});
	}
}
