/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgfinal;

/**
 *
 * @author valti
 */
/* librerias a usar*/
import com.toedter.calendar.JDateChooser;
import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import javax.swing.*;
import java.text.*;
import java.util.*;
import javax.swing.event.*;
import javax.swing.tree.*;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

/* ejecutable definimos el tamano de la ventana y su accion*/
public class Final extends JFrame {

    public static void main(String[] args) {
        Final app = new Final();
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setSize(1100, 750);
        app.setVisible(true);
        app.setResizable(false);
    }
    /* creamos los elementos visuales que son necesarios en nuestro programa */

    private ButtonGroup Grupo;
    private JRadioButton Magnitude;
    private JRadioButton MagnitudeAsc;
    private JRadioButton None;
    private JRadioButton Time;
    private JLabel Limite;
    private JTextField limite;
    private JRadioButton TimeAsc;
    public JTree Tree;
    private JScrollPane Scroll;
    private javax.swing.JList<String> Lista;
    private javax.swing.JScrollPane Scroll2;
    private JPanel Busqueda, Resultado, Listas;
    private JLabel Logo, Logo2, Logo3, Titulo, Titulo2, Titulo3, Fechas, Fechas2, Mag, Orden, Res, Res1, MagMAX;
    private JButton descargarDatos, cancelarDescarga;
    private JDateChooser fecha1, fecha2;
    private JSlider MagnitudMIN, MagnitudMAX;
    Proceso proc = new Proceso();

    public Final() {
        super("Buscador de terremotos mundiales. ");
        setIconImage(new ImageIcon("src/images/USGS.jpg").getImage());
        JTabbedPane panelFichas = new JTabbedPane();

        //1//
        Busqueda = new JPanel();
        fecha1 = new JDateChooser();
        fecha2 = new JDateChooser();
        Titulo = new JLabel();
        Logo = new JLabel();
        MagnitudMIN = new JSlider(0, 10, 0);
        MagnitudMAX = new JSlider(0, 10, 10);
        Fechas = new JLabel();
        Fechas2 = new JLabel();
        Mag = new JLabel();
        MagMAX = new JLabel();
        Grupo = new ButtonGroup();
        Time = new JRadioButton();
        TimeAsc = new JRadioButton();
        Magnitude = new JRadioButton();
        MagnitudeAsc = new JRadioButton();
        None = new JRadioButton();
        Orden = new JLabel();
        cancelarDescarga = new JButton();
        descargarDatos = new JButton();
        limite = new JTextField();
        Limite = new JLabel();

        Busqueda.setBackground(new Color(255, 253, 181));
        Busqueda.setLayout(new AbsoluteLayout());

        fecha1.setBackground(new Color(255, 253, 181));
        Busqueda.add(fecha1, new AbsoluteConstraints(170, 200, 320, -1));

        fecha2.setBackground(new Color(255, 253, 181));
        Busqueda.add(fecha2, new AbsoluteConstraints(530, 200, 320, -1));

        Titulo.setFont(new Font("Times New Roman", 1, 50));
        Titulo.setForeground(new Color(20, 80, 80));
        Titulo.setText("USS Geological Survey (USGS).");
        Busqueda.add(Titulo, new AbsoluteConstraints(200, 70, -1, -1));

        MagnitudMIN.setBackground(new Color(255, 253, 181));
        MagnitudMIN.setPaintTicks(true);
        MagnitudMIN.setPaintLabels(true);
        MagnitudMIN.setMajorTickSpacing(1);
        Busqueda.add(MagnitudMIN, new AbsoluteConstraints(260, 380, 516, -1));

        MagnitudMAX.setBackground(new Color(255, 253, 181));
        MagnitudMAX.setPaintTicks(true);
        MagnitudMAX.setPaintLabels(true);
        MagnitudMAX.setMajorTickSpacing(1);

        MagMAX.setFont(new Font("Times New Roman", 1, 18));
        MagMAX.setForeground(new Color(0, 0, 0));
        MagMAX.setText("Magnitud minima:");

        Fechas.setFont(new Font("Times New Roman", 1, 18));
        Fechas.setForeground(new Color(0, 0, 0));
        Fechas.setText("Especifique las fechas usando los iconos de calendario: ");

        Fechas2.setFont(new Font("Times New Roman", 1, 18));
        Fechas2.setForeground(new Color(0, 0, 0));
        Fechas2.setText("");

        Mag.setFont(new Font("Times New Roman", 1, 18));
        Mag.setForeground(new Color(0, 0, 0));
        Mag.setText("Magnitud maxima:");

        Time.setBackground(new Color(255, 255, 255));
        Time.setFont(new Font("Times New Roman", 1, 14));
        Time.setForeground(new Color(0, 0, 0));
        Time.setText("time");

        Grupo.add(Time);

        TimeAsc.setBackground(new Color(255, 255, 255));
        TimeAsc.setFont(new Font("Times New Roman", 1, 14));
        TimeAsc.setForeground(new Color(0, 0, 0));
        TimeAsc.setText("time-asc");

        Grupo.add(TimeAsc);

        Magnitude.setBackground(new Color(255, 255, 255));
        Magnitude.setFont(new Font("Times New Roman", 1, 14));
        Magnitude.setForeground(new Color(0, 0, 0));
        Magnitude.setText("magnitud");

        Grupo.add(Magnitude);

        MagnitudeAsc.setBackground(new Color(255, 255, 255));
        MagnitudeAsc.setFont(new Font("Times New Roman", 1, 14));
        MagnitudeAsc.setForeground(new Color(0, 0, 0));
        MagnitudeAsc.setText("magnitud-asc");

        Grupo.add(MagnitudeAsc);

        None.setBackground(new Color(255, 255, 255));
        None.setFont(new Font("Times New Roman", 1, 14));
        None.setForeground(new Color(0, 0, 0));
        None.setText("none");

        Grupo.add(None);

        Orden.setFont(new Font("Times New Roman", 1, 18));
        Orden.setForeground(new Color(0, 0, 0));
        Orden.setText("Ordenar por:");

        cancelarDescarga.setIcon(new ImageIcon("src/images/Cancelar.png"));
        cancelarDescarga.setBorder(null);
        cancelarDescarga.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cancelarDescarga.setSelectedIcon(new ImageIcon("src/images/Cancelar.png"));

        Busqueda.add(cancelarDescarga, new AbsoluteConstraints(800, 450, -1, -1));
        Busqueda.add(Orden, new AbsoluteConstraints(150, 470, -1, -1));
        Busqueda.add(descargarDatos, new AbsoluteConstraints(800, 500, -1, -1));
        Busqueda.add(None, new AbsoluteConstraints(700, 470, -1, -1));
        Busqueda.add(MagnitudeAsc, new AbsoluteConstraints(570, 470, -1, -1));
        Busqueda.add(Magnitude, new AbsoluteConstraints(460, 470, -1, -1));
        Busqueda.add(TimeAsc, new AbsoluteConstraints(360, 470, -1, -1));
        Busqueda.add(Time, new AbsoluteConstraints(280, 470, -1, -1));
        Busqueda.add(Mag, new AbsoluteConstraints(440, 250, -1, -1));
        Busqueda.add(Fechas2, new AbsoluteConstraints(510, 203, -1, -1));
        Busqueda.add(Fechas, new AbsoluteConstraints(295, 155, -1, -1));
        Busqueda.add(MagMAX, new AbsoluteConstraints(440, 350, -1, -1));
        Busqueda.add(MagnitudMAX, new AbsoluteConstraints(260, 280, 510, -1));

        descargarDatos.setIcon(new ImageIcon("src/images/Descargar.png"));
        descargarDatos.setBorder(null);
        descargarDatos.setCursor(new Cursor(Cursor.HAND_CURSOR));
        descargarDatos.setSelectedIcon(new ImageIcon("src/images/Descargar.png"));

        descargarDatos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    descargarDatosActionPerformed(evt);
                } catch (Exception x) {

                }
            }
        });

        limite.setBackground(new Color(255, 255, 255));
        limite.setFont(new Font("Times New Roman", 0, 12));
        limite.setForeground(new Color(0, 0, 0));
        limite.setText("");
        limite.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent evt) {
                limiteKeyTyped(evt);
            }
        });
        Busqueda.add(limite, new AbsoluteConstraints(530, 550, 240, 23));

        Limite.setFont(new Font("Times New Roman", 1, 18));
        Limite.setForeground(new Color(0, 0, 0));
        Limite.setText("Especifique cantidad maxima de datos a mostrar: ");
        Busqueda.add(Limite, new AbsoluteConstraints(130, 550, -1, -1));

        panelFichas.addTab("Busqueda", null, Busqueda, "Defina en esta ventana los valores de busqueda. ");

        //Parte 2
        Resultado = new JPanel();
        Logo2 = new JLabel();
        Titulo2 = new JLabel();
        Res = new JLabel();
        DefaultMutableTreeNode e = new DefaultMutableTreeNode("Espera...");
        DefaultTreeModel limpio = new DefaultTreeModel(e);
        Tree = new JTree(limpio);
        Scroll = new JScrollPane();

        Res.setFont(new Font("Times New Roman", 1, 18));
        Res.setForeground(new Color(0, 0, 0));
        Res.setText("Resultados de la búsqueda: ");
        Resultado.add(Res, new AbsoluteConstraints(392, 160, -1, -1));

        Resultado.setBackground(new Color(255, 253, 181));
        Resultado.setLayout(new AbsoluteLayout());

        Logo2.setIcon(new ImageIcon("src/images/1.png"));
        Resultado.add(Logo2, new AbsoluteConstraints(100, 57, -1, -1));

        Titulo2.setFont(new Font("Times New Roman", 1, 50));
        Titulo2.setForeground(new Color(255, 96, 9));
        Titulo2.setText("USS Geological Survey (USGS).");
        Resultado.add(Titulo2, new AbsoluteConstraints(200, 70, -1, -1));

        Tree.setBackground(new Color(255, 253, 181));
        Tree.addTreeSelectionListener(new TreeSelectionListener() {
            public void valueChanged(TreeSelectionEvent e) {
                String s = "";
                DefaultMutableTreeNode nodoSeleccionado;
                nodoSeleccionado = (DefaultMutableTreeNode) Tree.getLastSelectedPathComponent();

                DefaultListModel listado = new DefaultListModel();
                TreePath rutaSeleccionada = e.getPath();
                Object[] nodos = rutaSeleccionada.getPath();

                for (int i = 0; i < nodos.length; i++) {
                    Object nodo = nodos[i];
                    s += nodo.toString() + " / ";
                    s += "\n";
                    listado.addElement(s);
                }

                Lista.setModel(listado);
            }
        });
        Scroll.setViewportView(Tree);
        Resultado.add(Scroll, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 201, 800, -1));
        panelFichas.addTab("Resultados. ", null, Resultado, "Muestra los resultados obtenidos");

        //Pagina 3
        Listas = new JPanel();
        Logo3 = new JLabel();
        Titulo3 = new JLabel();
        Res1 = new JLabel();
        Scroll2 = new JScrollPane();
        Lista = new javax.swing.JList<>();

        Listas.setBackground(new java.awt.Color(255, 253, 181));
        Listas.setLayout(new AbsoluteLayout());

        Logo3.setIcon(new ImageIcon("src/images/1.png"));
        Listas.add(Logo3, new AbsoluteConstraints(100, 57, -1, -1));

        Titulo3.setFont(new Font("Times New Roman", 1, 50));
        Titulo3.setForeground(new Color(255, 96, 9));
        Titulo3.setText("USS Geological Survey (USGS).");
        Listas.add(Titulo3, new AbsoluteConstraints(200, 70, -1, -1));

        Res1.setFont(new Font("Times New Roman", 1, 18));
        Res1.setForeground(new Color(0, 0, 0));
        Res1.setText("Datos seleccionados: ");
        Listas.add(Res1, new AbsoluteConstraints(392, 160, -1, -1));

        Lista.setBackground(new Color(255, 253, 181));
        Lista.setFont(new Font("Times New Roman", 1, 14));
        Lista.setForeground(new Color(0, 0, 0));
        Scroll2.setViewportView(Lista);

        Listas.add(Scroll2, new AbsoluteConstraints(40, 197, 890, 370));
        panelFichas.addTab("Datos seleccionados", null, Listas, "Muestra las rutas de los datos seleccionados. ");
        add(panelFichas);

    }

    private void limiteKeyTyped(KeyEvent evt) {
        char validar = evt.getKeyChar();
        if (Character.isLetter(validar)) {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "No se permiten datos decimales o derivados.");
        }
    }

    private void descargarDatosActionPerformed(ActionEvent evt) throws Exception {
        SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = sdformat.parse(proc.getFecha(fecha1));
        Date date2 = sdformat.parse(proc.getFecha(fecha2));
        if (MagnitudMIN.getValue() > MagnitudMAX.getValue()) {
            JOptionPane.showMessageDialog(null, "El valor de la Mag-min (" + MagnitudMIN.getValue()
                    + ") no puede ser mayor a la Mag-max(" + MagnitudMAX.getValue() + ")");
        } else if (proc.getFecha(fecha1).equals("null") && proc.getFecha(fecha2).equals("null")) {
            JOptionPane.showMessageDialog(null, "Ingrese fechas reales.");
        } else if (date1.after(date2)) {
            JOptionPane.showMessageDialog(null, "La fecha " + proc.getFecha(fecha1) + " es mayor y  no puede ser después de la fecha " + proc.getFecha(fecha2) + " no tiene sentido vaya.");
        } else if (limite.getText().equals("0")) {
            JOptionPane.showMessageDialog(null, "0 no es un numero valido");
        } else {

            DefaultMutableTreeNode e = new DefaultMutableTreeNode("Esperando resultados...");
            DefaultTreeModel limpio = new DefaultTreeModel(e);
            Tree.setModel(limpio);
            DefaultListModel listaLimpia = new DefaultListModel();
            listaLimpia.addElement("");
            Lista.setModel(listaLimpia);
            String ordenamiento = "";
            if (Time.isSelected()) {
                ordenamiento = "time";
            } else if (TimeAsc.isSelected()) {
                ordenamiento = "time-asc";
            } else if (Magnitude.isSelected()) {
                ordenamiento = "magnitude";
            } else if (MagnitudeAsc.isSelected()) {
                ordenamiento = "magnitude-asc";
            } else if (None.isSelected()) {
                ordenamiento = "";
            } else {
                ordenamiento = "";
            }
            System.out.println(proc.getFecha(fecha1) + " A \n" + proc.getFecha(fecha2) + " mag-min de \n" + String.valueOf(MagnitudMIN.getValue())
                    + " mag-max \n" + String.valueOf(MagnitudMAX.getValue()) + " con el tipo de orden: \n" + ordenamiento + " con un maximo de datos: \n" + limite.getText());

            Descarga dw = new Descarga(proc.getFecha(fecha1), proc.getFecha(fecha2), String.valueOf(MagnitudMIN.getValue()),
                    String.valueOf(MagnitudMAX.getValue()), ordenamiento, limite.getText(), Tree);

            dw.addPropertyChangeListener(
                    new PropertyChangeListener() {
                public void propertyChange(PropertyChangeEvent e) {
                    if (e.getPropertyName().equals("progress")) {
                        int Valornuevo = (Integer) e.getNewValue();

                    }
                }
            });
            dw.execute();

            cancelarDescarga.addActionListener(
                    new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    dw.cancel(true);
                }
            });
        }
    }

}
