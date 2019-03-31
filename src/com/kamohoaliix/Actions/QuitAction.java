package com.kamohoaliix.Actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author      Joshua, Boddy, joshua.boddy@city.ac.uk
 * @version     3.0.0
 * @since       2.0.0
 */
public class QuitAction implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }
}
