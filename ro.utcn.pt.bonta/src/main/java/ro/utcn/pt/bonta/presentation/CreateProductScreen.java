package ro.utcn.pt.bonta.presentation;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ro.utcn.pt.bonta.model.Product;

public class CreateProductScreen {
	public JFrame mainFrame;
	private JPanel controlPanel;

	private JTextField idTextField;
	private JTextField nameTextField;
	private JTextField priceTextField;
	private JTextField quantityTextField;

	private JButton cancelButton;
	private JButton createButton;

	private AppInterfaceButtonEvents buttonEventHandler;

	private ArrayList<Product> productsList;

	public CreateProductScreen(AppInterfaceButtonEvents buttonEventHandler) {
		this.buttonEventHandler = buttonEventHandler;
	}

	public void prepareComponents(ArrayList<Product> productsList) {
		this.productsList = productsList;
		this.prepareGUI();
		this.placeElements();
	}

	private void prepareGUI() {
		mainFrame = new JFrame("Create new product");
		mainFrame.setSize(500, 400);
		mainFrame.setLayout(new GridLayout(1, 1));
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
		});

		controlPanel = new JPanel();
		controlPanel.setLayout(new GridLayout(6, 1));

		mainFrame.add(controlPanel);
		mainFrame.setVisible(false);
	}

	/*
	 * placeButtons(): Add elements on the controlPanel
	 */
	private void placeElements() {

		idTextField = new JTextField("Id");
		nameTextField = new JTextField("Name");
		priceTextField = new JTextField("Price");
		quantityTextField = new JTextField("Quantity");

		cancelButton = new JButton("Cancel");
		createButton = new JButton("Create product");

		controlPanel.add(idTextField);
		controlPanel.add(nameTextField);
		controlPanel.add(priceTextField);
		controlPanel.add(quantityTextField);
		controlPanel.add(createButton);
		controlPanel.add(cancelButton);

		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonEventHandler.nothingHappenedOnUI();
				mainFrame.setVisible(false);
			}
		});

		createButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int errorNumber = 0;
				if (nameTextField.getText().length() == 0) {
					errorNumber = 5;
				}
				if (!(quantityTextField.getText().matches("[0-9]+") && quantityTextField.getText().length() > 0)
						|| (Double.parseDouble(quantityTextField.getText())) < 0) {
					errorNumber = 2;
				}
				if (!(priceTextField.getText().matches("^([0-9]*[1-9][0-9]*(\\.[0-9]+)?|[0]+\\.[0-9]*[1-9][0-9]*)$")
						&& priceTextField.getText().length() > 0) || (Integer.parseInt(priceTextField.getText())) < 0) {
					errorNumber = 3;
				}
				if (!(idTextField.getText().matches("[0-9]+") && idTextField.getText().length() > 0)) {
					errorNumber = 4;
				}

				int size = productsList.size();
				int index;
				for (index = 0; index < size; ++index) {
					if (productsList.get(index).getId().equals(idTextField.getText())) {
						errorNumber = 1;
					}
				}
				System.out.println(errorNumber);
				if (errorNumber == 0) {
					buttonEventHandler.newProductCreated(idTextField.getText(), nameTextField.getText(),
							Double.parseDouble(priceTextField.getText()),
							Integer.parseInt(quantityTextField.getText()));
					mainFrame.setVisible(false);
				} else {
					String errorMessage;
					switch (errorNumber) {
					case 1:
						errorMessage = "Product with ID:" + idTextField.getText() + " already exists!";
						break;
					case 2:
						errorMessage = "Quantity should be numeric and positive!";
						break;
					case 3:
						errorMessage = "Price should be numeric and positive!";
						break;
					case 4:
						errorMessage = "ID should be numeric!";
						break;
					case 5:
						errorMessage = "Name should not be blank!";
						break;
					default:
						errorMessage = "Error";
						break;
					}
					JOptionPane.showMessageDialog(mainFrame, errorMessage);
				}
			}
		});
	}
}
