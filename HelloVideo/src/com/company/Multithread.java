package com.company;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import marvin.gui.MarvinImagePanel;
import marvin.image.MarvinImage;
import marvin.image.MarvinImageMask;
import marvin.io.MarvinImageIO;
import marvin.plugin.MarvinImagePlugin;
import marvin.thread.MarvinThread;
import marvin.thread.MarvinThreadEvent;
import marvin.thread.MarvinThreadListener;
import marvin.util.MarvinPluginLoader;

/**
 * Processing images single and multi threaded.
 * @author Gabriel Ambrósio Archanjo
 */
public class Multithread extends JFrame implements MarvinThreadListener{

    // Interface Components
    private JButton                buttonSingleThread,
            buttonMultiThread,
            buttonResetImage;

    private JLabel                labelPerformance;

    // MarvinImagePanel to display the image
    private MarvinImagePanel     imagePanel;

    // MarvinImage objects used by the app
    private MarvinImage         imageIn, imageOut, originalImage;

    private int                    threadsFinished;
    private long                processStartTime;

    /**
     * Create the user interface and load the images.
     */
    public Multithread(){
        super("Multithread Sample");

        // Buttons
        ButtonHandler buttonHandler = new ButtonHandler();
        buttonSingleThread = new JButton("Single Thread");
        buttonMultiThread = new JButton("Multi Thread");
        buttonResetImage = new JButton("Reset Image");

        buttonSingleThread.addActionListener(buttonHandler);
        buttonMultiThread.addActionListener(buttonHandler);
        buttonResetImage.addActionListener(buttonHandler);

        // Label
        labelPerformance = new JLabel("Performance:");

        // Panels
        JPanel panelIntern = new JPanel();
        panelIntern.add(buttonSingleThread);
        panelIntern.add(buttonMultiThread);
        panelIntern.add(buttonResetImage);

        JPanel panelBottom = new JPanel();
        panelBottom.setLayout(new BorderLayout());

        panelBottom.add(panelIntern, BorderLayout.NORTH);
        panelBottom.add(labelPerformance, BorderLayout.SOUTH);

        imagePanel = new MarvinImagePanel();

        // Container
        Container con = getContentPane();
        con.setLayout(new BorderLayout());
        con.add(imagePanel, BorderLayout.NORTH);
        con.add(panelBottom, BorderLayout.SOUTH);

        // Load Image
        loadImages();

        setSize(originalImage.getWidth()+20,originalImage.getHeight() + 100);
        setVisible(true);
    }

    /**
     *     Load the image processed by the application. Create other two images (imageIn and imageOut)
     *    to be the input and output images. The image panel is set to display the original image.
     */
    private void loadImages(){
        originalImage = MarvinImageIO.loadImage("./res/parrot.jpeg");
        imageIn = new MarvinImage(originalImage.getWidth(), originalImage.getHeight());
        imageOut = new MarvinImage(originalImage.getWidth(), originalImage.getHeight());
        imagePanel.setImage(originalImage);
    }

    public void threadFinished(MarvinThreadEvent e){
        threadsFinished++;
        if(threadsFinished == 2){
            imageOut.update();
            imagePanel.setImage(imageOut);
            labelPerformance.setText("Performance: "+ (System.currentTimeMillis()-processStartTime)+ " milliseconds (Multi Thread)");
            repaint();
        }
    }

    public static void main(String args[]){
        Multithread t = new Multithread();
        t.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void singleThread(){
        processStartTime = System.currentTimeMillis();
        MarvinImagePlugin plgImage = MarvinPluginLoader.loadImagePlugin("org.marvinproject.image.color.grayScale.jar");
        plgImage.process(imageIn, imageOut);
        imageOut.update();
        imagePanel.setImage(imageOut);
        labelPerformance.setText("Performance: "+ (System.currentTimeMillis()-processStartTime)+ " milliseconds (Single Thread)");
        repaint();
    }

    private void multiThread(){
        processStartTime = System.currentTimeMillis();

        // Load Plug-ins
        MarvinImagePlugin plgImage1 = MarvinPluginLoader.loadImagePlugin("org.marvinproject.image.color.grayScale.jar");
        MarvinImagePlugin plgImage2 = MarvinPluginLoader.loadImagePlugin("org.marvinproject.image.color.grayScale.jar");

        // Create masks
        MarvinImageMask mask1 = new MarvinImageMask
                (
                        imageIn.getWidth(),            // width
                        imageIn.getHeight(),            // height
                        0,                    // x-start
                        0,                    // y-start
                        imageIn.getWidth(),            // region´s width
                        imageIn.getHeight()/2            // region´s height
                );

        MarvinImageMask mask2 = new MarvinImageMask
                (
                        imageIn.getWidth(),            // width
                        imageIn.getHeight(),            // height
                        0,                    // x-start
                        imageIn.getHeight()/2,                // y-start
                        imageIn.getWidth(),            // region´s width
                        imageIn.getHeight()/2            // region´s height
                );


        MarvinThread mvThread1 = new MarvinThread(plgImage1, imageIn, imageOut, mask1);
        MarvinThread mvThread2 = new MarvinThread(plgImage2, imageIn, imageOut, mask2);

        mvThread1.addThreadListener(this);
        mvThread2.addThreadListener(this);

        mvThread1.start();
        mvThread2.start();

        threadsFinished = 0;
    }

    private class ButtonHandler implements ActionListener{
        public void actionPerformed(ActionEvent e){
            imageIn = originalImage.clone();
            if(e.getSource() == buttonSingleThread){
                singleThread();
            }
            else if(e.getSource() == buttonMultiThread){
                multiThread();
            }
            else if(e.getSource() == buttonResetImage){
                imagePanel.setImage(originalImage);
            }
        }
    }
}