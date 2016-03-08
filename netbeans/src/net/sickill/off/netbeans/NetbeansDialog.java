package net.sickill.off.netbeans;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.KeyboardFocusManager;
import net.sickill.off.common.IDE;
import net.sickill.off.common.OffDialog;
import net.sickill.off.common.Settings;
import net.sickill.off.common.OffPanel;
import org.openide.windows.WindowManager;

/**
 * @author sickill
 */
public class NetbeansDialog extends OffDialog {

    static NetbeansDialog instance;
    static IDE ide;
    static OffPanel off;

    public static NetbeansDialog getInstance() {
        if (instance == null) {
            instance = new NetbeansDialog();
        }

        return instance;
    }

    public NetbeansDialog() {
        super(WindowManager.getDefault().getMainWindow(), "Open File Fast");

        if (off == null) {
            ide = new NetbeansIDE();
            off = new OffPanel(ide, settings, NetbeansProject.getInstance());
        }

        ide.setDialog(this);
        getContentPane().add(off, BorderLayout.CENTER);
    }

    public void showDialog() {
        //try to use monitor, where the input focus is
        Component focusOwner = KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner();
        this.setLocationRelativeTo(focusOwner);
        
        this.setVisible(true);
        off.focusOnDefaultComponent();
    }

    public void closeDialog() {
        dispose();
        instance = null;
    }

    @Override
    protected Settings getSettings() {
        return NetbeansSettings.getInstance();
    }

}
