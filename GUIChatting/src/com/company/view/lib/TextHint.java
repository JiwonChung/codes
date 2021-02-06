package com.company.view.lib;

import java.awt.*;

import java.awt.event.FocusEvent;

import java.awt.event.FocusListener;

import java.beans.PropertyChangeEvent;

import java.beans.PropertyChangeListener;


import javax.swing.*;

import javax.swing.event.DocumentEvent;

import javax.swing.event.DocumentListener;



public class TextHint implements FocusListener, DocumentListener, PropertyChangeListener {

    private final JTextField textField;

    private boolean isEmpty;

    private Color hintColor;

    private Color foregroundColor;

    private final String hintText;



    public TextHint(final JTextField textField, String hintText) {

        super();

        this.textField = textField;

        this.hintText = hintText;

        this.hintColor = Color.LIGHT_GRAY;

        textField.addFocusListener(this);

        registerListeners();

        updateState();

        if (!this.textField.hasFocus()) {

            focusLost(null);

        }

    }
    public TextHint(final JPasswordField textField, String hintText) {

        super();

        this.textField = textField;

        this.hintText = hintText;

        this.hintColor = Color.LIGHT_GRAY;

        textField.addFocusListener(this);

        registerListeners();

        updateState();

        if (!this.textField.hasFocus()) {

            focusLost(null);

        }

    }



    public void delete() {

        unregisterListeners();

        textField.removeFocusListener(this);

    }



    private void registerListeners() {

        textField.getDocument().addDocumentListener(this);

        textField.addPropertyChangeListener("foreground", this);

    }



    private void unregisterListeners() {

        textField.getDocument().removeDocumentListener(this);

        textField.removePropertyChangeListener("foreground", this);

    }



    public Color getGhostColor() {

        return hintColor;

    }



    public void setGhostColor(Color hintColor) {

        this.hintColor = hintColor;

    }



    private void updateState() {

        isEmpty = textField.getText().length() == 0;

        foregroundColor = textField.getForeground();

    }



    @Override

    public void focusGained(FocusEvent e) {

        if (isEmpty) {

            unregisterListeners();

            try {

                textField.setText("");

                textField.setForeground(foregroundColor);

            } finally {

                registerListeners();

            }

        }



    }



    @Override

    public void focusLost(FocusEvent e) {

        if (isEmpty) {

            unregisterListeners();

            try {

                textField.setText(hintText);

                textField.setForeground(hintColor);

            } finally {

                registerListeners();

            }

        }

    }



    @Override

    public void propertyChange(PropertyChangeEvent evt) {

        updateState();

    }



    @Override

    public void changedUpdate(DocumentEvent e) {

        updateState();

    }



    @Override

    public void insertUpdate(DocumentEvent e) {

        updateState();

    }



    @Override

    public void removeUpdate(DocumentEvent e) {

        updateState();

    }

}



