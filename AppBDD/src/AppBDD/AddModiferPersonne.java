/**
 * 
 */
package AppBDD;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * @author Stagiaire
 *
 */
public class AddModiferPersonne {

	protected Display display = new Display();
	protected Shell shell = new Shell(display);

	/**
	 * @param args
	 */

	protected void formular(String s, Composite c, GridData layout) {
		Label label = new Label(c, SWT.NONE);
		label.setText(s);

		Text text = new Text(c, SWT.None);
		text.setLayoutData(layout);

	}

	protected void DrawTable() {
		GridLayout layout = new GridLayout(1, true);
		shell.setBackground(new Color(150, 150, 150));
		shell.setLayout(layout);
		shell.setText("Add/Modify profile");
		shell.setImage(
				new Image(display, "C:\\Users\\Stagiaire\\eclipse-workspace\\AppBDD\\src\\AppBDD\\notebook.ico"));
		shell.open();

		GridData layoutGrid = new GridData(SWT.FILL, SWT.FILL, true, true, 4, 1);
		Group group1 = new Group(shell, SWT.SHADOW_ETCHED_IN);
		group1.setText("Add/Modify profile");
		group1.setLayout(new GridLayout(4, true));
		group1.setLayoutData(layoutGrid);

		group1.pack();
		formular("First name:", group1, layoutGrid);
		formular("Last name:", group1, layoutGrid);

		Label Birthday = new Label(group1, SWT.NONE);
		Birthday.setLayoutData(layoutGrid);
		Birthday.setText("Birthday:");

		DateTime calendarDropDown = new DateTime(group1, SWT.DROP_DOWN | SWT.CALENDAR_WEEKNUMBERS);

		Label Gender = new Label(group1, SWT.NONE);
		Gender.setText("Genre:");
		Gender.setLayoutData(layoutGrid);

		Button male = new Button(group1, SWT.RADIO);
		male.setText("Male");

		Button female = new Button(group1, SWT.RADIO);
		female.setText("Female");

		Button other = new Button(group1, SWT.RADIO);
		other.setText("Other");

		Button Submit = new Button(group1, SWT.PUSH | SWT.CENTER);
		GridData layoutButton = new GridData(SWT.FILL, SWT.FILL, true, true, 4, 1);
		Submit.setText("Submit");
		Submit.setLayoutData(layoutButton);

	}

}
