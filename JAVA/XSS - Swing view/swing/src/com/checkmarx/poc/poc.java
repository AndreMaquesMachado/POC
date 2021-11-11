package com.checkmarx.poc;
/*
import javax.swing.*;
import java.awt.*;

public class poc {
    JTextPane notes = new JTextPane();

    private JPanel jpanel;

    public poc (){
        //
        //notes.setText("<script>alert(1)</script>");
    }

    public static void main(String[] args){
        JFrame frame = new JFrame("poc");
        JTextPane jp = new JTextPane();

        JScrollPane jpanel = new JScrollPane(jp);
        frame.setContentPane(new poc().jpanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        jp.setContentType("text/html");
        jp.setText("hello world");
        //jp.setText("<script>alert(1)</script>");


        JEditorPane editorPane = new JEditorPane();
        editorPane.setEditable(false);
        java.net.URL helpURL = TextSamplerDemo.class.getResource(
                "TextSamplerDemoHelp.html");
        if (helpURL != null) {
            try {
                editorPane.setPage(helpURL);
            } catch (IOException e) {
                System.err.println("Attempted to read a bad URL: " + helpURL);
            }
        } else {
            System.err.println("Couldn't find file: TextSamplerDemoHelp.html");
        }

//Put the editor pane in a scroll pane.
        JScrollPane editorScrollPane = new JScrollPane(editorPane);
        editorScrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        editorScrollPane.setPreferredSize(new Dimension(250, 145));
        editorScrollPane.setMinimumSize(new Dimension(10, 10));
    }
}*/
    import javafx.scene.web.WebEngine;
    import javafx.scene.web.WebView;

    import java.awt.BorderLayout;
        import java.awt.Color;
        import java.awt.Container;
        import javax.swing.JFrame;
        import javax.swing.JScrollPane;
        import javax.swing.JTextPane;
        import javax.swing.text.BadLocationException;
        import javax.swing.text.Document;
        import javax.swing.text.SimpleAttributeSet;
        import javax.swing.text.StyleConstants;
public class poc {
    public static void main(String args[]) throws BadLocationException {
        JFrame frame = new JFrame("JTextPane Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container cp = frame.getContentPane();
        JTextPane pane = new JTextPane();
        SimpleAttributeSet attributeSet = new SimpleAttributeSet();
        StyleConstants.setBold(attributeSet, true);

        // Set the attributes before adding text
        pane.setCharacterAttributes(attributeSet, true);
        pane.setText("\"<script> alert(1) </script>\"");

        attributeSet = new SimpleAttributeSet();
        StyleConstants.setItalic(attributeSet, true);
        StyleConstants.setForeground(attributeSet, Color.red);
        StyleConstants.setBackground(attributeSet, Color.blue);

        Document doc = pane.getStyledDocument();
        doc.insertString(doc.getLength(), "<script> alert(1) </script>", attributeSet);

        attributeSet = new SimpleAttributeSet();
        doc.insertString(doc.getLength(), "World", attributeSet);
        WebView browser = new WebView();
        WebEngine webEngine = browser.getEngine();
        webEngine.setJavaScriptEnabled(true);
        browser.getEngine().loadContent("www.google.com");
        JScrollPane scrollPane = new JScrollPane(pane);
        cp.add(scrollPane, BorderLayout.CENTER);

        frame.setSize(400, 300);
        frame.setVisible(true);



    }
}
