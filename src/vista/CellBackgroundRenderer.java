package vista;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class CellBackgroundRenderer implements TableCellRenderer
{
  private TableCellRenderer delegate;

  public CellBackgroundRenderer(TableCellRenderer defaultRenderer)
  {
    this.delegate = defaultRenderer;
  }

  /**
   * Comprueba si en la celda hay un conflicto y configura los colores seg�n el caso
   */
  public Component getTableCellRendererComponent(JTable table, Object value, 
                           boolean isSelected, boolean hasFocus, int row, int column) 
  {
    Component c = delegate.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    
    if (((String)value).contains("-")){
    	c.setBackground(Color.RED);
    	c.setForeground(Color.WHITE);
    }
    else{
    	c.setBackground(Color.WHITE);
    	c.setForeground(Color.BLACK);
    }
    return c;
  }
} 