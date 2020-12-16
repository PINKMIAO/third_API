package com.meorient.avaya;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

public class OneX {
	
	 Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
	
    public void call(String num) throws AWTException {
        clipboard.setContents(new StringSelection(num), null);
        callOneX();
    }

    public void callOneX() throws AWTException {
    	Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ALT);
        robot.keyPress(KeyEvent.VK_W);
        robot.keyRelease(KeyEvent.VK_W);
        robot.keyRelease(KeyEvent.VK_ALT);
    }

   
}
