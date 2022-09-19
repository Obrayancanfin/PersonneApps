/**
 * 
 */
package AppBDD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

/**
 * @author Stagiaire
 *
 */
public class ApplicationBDD {

	protected Display display = new Display();
	protected Shell shell = new Shell(display);
	final Image image = display.getSystemImage(SWT.ICON_INFORMATION);

	/**
	 * 
	 * @param con
	 * @throws SQLException
	 */
	public void DrawTable(Connection con) throws SQLException {

		GridLayout layout = new GridLayout(1, true);
		shell.setLayout(layout);
		shell.setText("Authentication");
		shell.setImage(new Image(display, "C:\\Users\\Stagiaire\\eclipse-workspace\\AppBDD\\src\\AppBDD\\profil.ico"));

		Table table = new Table(shell, SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION | SWT.NO_SCROLL);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		GridData data = new GridData(SWT.FILL, SWT.FILL, true, true);
		table.setLayoutData(data);
		String[] titles = { "Nom", "Prenom", "Date de naissance", "Genre", "Actions" };
		for (String title : titles) {
			TableColumn column = new TableColumn(table, SWT.ABORT);
			column.setText(title);
		}

		for (int i = 0; i < titles.length; i++) {
			table.getColumn(i).pack();
		}

		String querry = "SELECT psn_id,psn_first_name,psn_last_name,psn_date_naissance,psn_genre  FROM personne p";
		Statement stmt = con.createStatement();
		try {
			ResultSet rs = stmt.executeQuery(querry);
			while (rs.next()) {
				TableItem item = new TableItem(table, SWT.FILL);

				Composite buttonPane = new Composite(table, SWT.NONE);
				buttonPane.setLayout(new FillLayout());

				Button removeButton = new Button(buttonPane, SWT.NONE);
				removeButton.setImage(
						new Image(display, "C:\\Users\\Stagiaire\\eclipse-workspace\\AppBDD\\src\\AppBDD\\delete.ico"));
				removeButton.pack();
				removeButton.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
					}
				});

				Button editeButton = new Button(buttonPane, SWT.NONE);
				editeButton.setImage(
						new Image(display, "C:\\Users\\Stagiaire\\eclipse-workspace\\AppBDD\\src\\AppBDD\\editer.png"));
				editeButton.pack();

				TableEditor editor = new TableEditor(table);
				editor.grabHorizontal = true;
				editor.grabVertical = true;
				editor.setEditor(buttonPane, item, 4);
				editor.layout();
				buttonPane.pack();

				item.setText(0, rs.getString("psn_first_name"));
				item.setText(1, rs.getString("psn_last_name"));
				item.setText(2, rs.getDate("psn_date_naissance").toString());
				item.setText(3, rs.getString("psn_genre"));

			}
		} catch (

		Exception e) {
			e.printStackTrace();

		}
		table.pack();
		shell.pack();
		shell.open();

	}

	public void open(Connection con) throws SQLException {
		Display display = Display.getDefault();
		shell = new Shell(display);

		DrawTable(con);

		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}

		display.dispose();

	}
}
