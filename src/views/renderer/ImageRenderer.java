package views.renderer;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import javax.swing.ImageIcon;

public class ImageRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        // Assuming the image name is stored as a String in the cell value
        String imageName = (String) value;

        // Specify the absolute path or a relative path to the image file
        String imagePath = "assets/" + imageName; // Adjust the path based on your folder structure
        
        // Create a file object with the image path
        File imageFile = new File(imagePath);

        // Check if the file exists
        if (imageFile.exists()) {
            System.out.println("Image file exists: " + imageFile.getAbsolutePath());
        } else {
            System.out.println("Image file does not exist: " + imageFile.getAbsolutePath());
        }
        
        // Create the ImageIcon based on the image path
        ImageIcon imageIcon = new ImageIcon(imagePath);

        // Create a JLabel to display the image
        JLabel label = new JLabel();
        label.setIcon(imageIcon);

        // Set the label properties
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);

        // Set the label background and foreground colors based on the table selection
        if (isSelected) {
            label.setBackground(table.getSelectionBackground());
            label.setForeground(table.getSelectionForeground());
        } else {
            label.setBackground(table.getBackground());
            label.setForeground(table.getForeground());
        }

        return label;
    }
  
}
