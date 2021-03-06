package net.sickill.off.common;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

/**
 * @author sickill
 */
public class OffColumnCellRenderer extends JPanel implements ListCellRenderer<OffListElement> {

    private static final Border EMPTY_BORDER = new EmptyBorder(1, 1, 1, 1);

    private JLabel[] labels = {new JLabel(), new JLabel(), new JLabel()};

    public OffColumnCellRenderer() {
        setLayout(new BorderLayout(4, 0));
        setBorder(EMPTY_BORDER);

        for (JLabel l : labels) {
            l.setOpaque(true);
        }

        add(labels[0], BorderLayout.WEST);
        add(labels[1], BorderLayout.CENTER);
        add(labels[2], BorderLayout.EAST);
    }

    @Override
    public Component getListCellRendererComponent(
            JList<? extends OffListElement> list, OffListElement value,
            int index, boolean isSelected, boolean cellHasFocus
    ) {
        if (null == value) {
            return this;
        }
        labels[0].setIcon(value.getIcon());
        labels[1].setText(value.getHTMLHighlightedFileName());
        labels[2].setText(value.getFileRelativeToProjectRoot());

        if (isSelected) {
            for (JLabel l : labels) {
                setBackground(list.getSelectionBackground());
                l.setBackground(list.getSelectionBackground());
                l.setForeground(list.getSelectionForeground());
            }
        } else {
            for (JLabel l : labels) {
                setBackground(list.getBackground());
                l.setBackground(list.getBackground());
                l.setForeground(list.getForeground());
            }

            labels[2].setForeground(Color.GRAY);
        }

        setEnabled(list.isEnabled());
        setFont(list.getFont());
        return this;
    }

}
