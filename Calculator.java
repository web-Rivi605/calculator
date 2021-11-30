package com.CoolJavaDev;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Calculator implements ActionListener {

	String[] strings;
	int ind;
	double tmpRes;

	JFrame frame;
	JTextField textField;
	JButton[] numButtons = new JButton[10];
	JButton[] functionButtons = new JButton[8];
	JButton addButton, subButton, multiButton, divButton;
	JButton decButton, equButton, delButton, clrButton;
	JPanel panel;

	Font calcFont = new Font("Ink Free", Font.PLAIN, 28);

	double result = 0;
	char operator;
	ArrayList<Character> operatorsArr = new ArrayList<>();

	Calculator() {
		frame = new JFrame("RKBRO CALCULATOR GUI");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(420, 550);
		frame.setLayout(null);

		textField = new JTextField();
		textField.setBounds(50, 25, 300, 50);
		textField.setFont(calcFont);
		textField.setEditable(false);
		textField.setBorder(BorderFactory.createBevelBorder(1));
		textField.setBackground(Color.yellow);

		addButton = new JButton("+");
		addButton.setBackground(Color.green);
		subButton = new JButton("-");
		subButton.setBackground(Color.green);
		multiButton = new JButton("*");
		multiButton.setBackground(Color.green);
		divButton = new JButton("/");
		divButton.setBackground(Color.green);
		decButton = new JButton(".");
		decButton.setBackground(Color.green);
		equButton = new JButton("=");
		equButton.setBackground(Color.green);
		delButton = new JButton("DEL");
		delButton.setBackground(Color.green);
		clrButton = new JButton("CLEAR");
		clrButton.setBackground(Color.green);

		functionButtons[0] = addButton;
		functionButtons[1] = subButton;
		functionButtons[2] = multiButton;
		functionButtons[3] = divButton;
		functionButtons[4] = decButton;
		functionButtons[5] = equButton;
		functionButtons[6] = delButton;
		functionButtons[7] = clrButton;

		for(int i = 0; i < 8; i++) {
			functionButtons[i].setFocusable(false);
			functionButtons[i].addActionListener(this);
			functionButtons[i].setFont(calcFont);
		}

		for(int i = 0; i < 10; i++) {
			numButtons[i] = new JButton(String.valueOf(i));
			numButtons[i].setBackground(Color.red);
			numButtons[i].setFocusable(false);
			numButtons[i].addActionListener(this);
			numButtons[i].setFont(calcFont);
		}

		delButton.setBounds(50, 430, 145, 50);
		clrButton.setBounds(203, 430, 145, 50);

		panel = new JPanel();
		panel.setBounds(50, 100, 300, 300);
		panel.setLayout(new GridLayout(4, 4));
		panel.setBackground(Color.blue);

		panel.add(numButtons[1]);
		panel.add(numButtons[2]);
		panel.add(numButtons[3]);
		panel.add(addButton);

		panel.add(numButtons[4]);
		panel.add(numButtons[5]);
		panel.add(numButtons[6]);
		panel.add(subButton);

		panel.add(numButtons[7]);
		panel.add(numButtons[8]);
		panel.add(numButtons[9]);
		panel.add(multiButton);

		panel.add(decButton);
		panel.add(numButtons[0]);
		panel.add(equButton);
		panel.add(divButton);

		frame.add(panel);
		frame.add(delButton);
		frame.add(clrButton);
		frame.add(textField);
		frame.setVisible(true);

	}

	public static void main(String[] args) {
		new Calculator();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for(int i = 0; i < 10; i++) {
			if(e.getSource() == numButtons[i]) {
				textField.setText(textField.getText().concat(String.valueOf(i)));
			}
		}

		if(e.getSource() == decButton) {
			textField.setText(textField.getText().concat("."));
		}

		if(e.getSource() == addButton) {

			String num = textField.getText();
			operator = '+';
			operatorsArr.add(operator);
			textField.setText(num + operator);
		}

		if(e.getSource() == subButton) {
			String num = textField.getText();
			operator = '-';
			operatorsArr.add(operator);
			textField.setText(num + operator);
		}

		if(e.getSource() == multiButton) {
			String num = textField.getText();
			operator = '*';
			operatorsArr.add(operator);
			textField.setText(num + operator);
		}

		if(e.getSource() == divButton) {
			String num = textField.getText();
			operator = '/';
			operatorsArr.add(operator);
			textField.setText(num + operator);
		}

		if(e.getSource() == clrButton) {
			textField.setText("");
		}

		if(e.getSource() == delButton) {

			String string = textField.getText();
			textField.setText("");


			for(int i = 0; i < string.length() - 1; i++) {
				textField.setText(textField.getText() + string.charAt(i));
			}
		}

		if(e.getSource() == equButton) {
			String txt = textField.getText();
			ind = 0;

			strings = txt.split("[+\\-]");
			result = getMul(strings[ind]);

			for(char operate : operatorsArr) {
				ind++;
				operator = operate;
				switch(operator) {
					case '+' -> result += getMul(strings[ind]);
					case '-' -> result -= getMul(strings[ind]);
					default -> textField.setText("NO NUM2");
				}

				textField.setText(String.valueOf(result));
			}
			operatorsArr.clear();
		}
	}

	public double getMul(String min) {

		tmpRes = 1;
		int tmpInd;
		tmpInd = 0;

		if(min.contains("*")) {
			String[] mArr = min.split("\\*");
			for(String num : mArr) {
				tmpRes *= Double.parseDouble(mArr[tmpInd]);
				tmpInd++;
			}
		} else {
			tmpRes = Double.parseDouble(min);
		}
		return tmpRes;
	}
}
