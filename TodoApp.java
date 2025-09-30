package com.elevatelabs.tasks.task6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class TodoApp {

	// Swing components as fields so we can access them in event handlers
	private JFrame frame;
	private JTextField taskInputField;
	private DefaultListModel<String> listModel;
	private JList<String> taskList;

	public TodoApp() {
		// Step 1: Create the main window
		frame = new JFrame("My To-Do App");
		frame.setSize(400, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Step 2: Create a panel for the top part (input + button)
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());

		// Step 3: Create the text field where user will type tasks
		taskInputField = new JTextField();
		topPanel.add(taskInputField, BorderLayout.CENTER);

		// Step 4: Create an "Add Task" button
		JButton addButton = new JButton("Add Task");
		topPanel.add(addButton, BorderLayout.EAST);

		// Step 5: Create the list model and the visual JList
		listModel = new DefaultListModel<>();
		taskList = new JList<>(listModel); // Displays list on screen
		JScrollPane scrollPane = new JScrollPane(taskList); // Add scroll bar if list is long

		// Step 6: Create a "Delete Task" button below the list
		JButton deleteButton = new JButton("Delete Selected Task");

		// Step 7: Add components to the frame
		frame.setLayout(new BorderLayout());
		frame.add(topPanel, BorderLayout.NORTH);
		frame.add(scrollPane, BorderLayout.CENTER);
		frame.add(deleteButton, BorderLayout.SOUTH);

		// Step 8: Add event listeners (what happens when buttons clicked)
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String task = taskInputField.getText().trim();
				if (!task.isEmpty()) {

					listModel.addElement(task);

					taskInputField.setText("");
				} else {
					JOptionPane.showMessageDialog(frame, "Please enter a task!");
				}
			}
		});

		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedIndex = taskList.getSelectedIndex();
				if (selectedIndex != -1) {
					
					listModel.remove(selectedIndex);
				} else {
					JOptionPane.showMessageDialog(frame, "Please select a task to delete!");
				}
			}
		});

		// Step 9: Show the frame (window) on screen
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		// Swing programs should start on the Event Dispatch Thread
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new TodoApp(); 
			}
		});
	}
}
