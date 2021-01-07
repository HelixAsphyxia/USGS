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
import java.awt.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
import javax.swing.tree.*;
import org.json.JSONArray;
import org.json.JSONObject;

public class Descarga extends SwingWorker<Void, Integer> {

    DefaultMutableTreeNode title;
    private static HttpURLConnection connection;
    private final String inicio, hasta, order, limite;
    private final String magnitudMIN, magnitudMAX;
    JScrollPane Scroll;
    JTree arbol;
    JPanel panel;
    Proceso proc = new Proceso();
    String json;
    private boolean detenido = false;
    Final gui = new Final();

    public Descarga(String start, String end, String mag, String magMax, String order, String limit, JTree arbol) {
        this.inicio = start;
        this.hasta = end;
        this.magnitudMIN = mag;
        this.order = order;
        this.limite = limit;
        this.arbol = arbol;

        this.magnitudMAX = magMax;

    }

    @Override
    public Void doInBackground() {
        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();
        try {
            URL url = new URL("https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=+" + inicio
                    + "&endtime=" + hasta + "&minmagnitude=" + magnitudMIN + "&maxmagnitude=" + magnitudMAX + "&orderby=" + order + "&limit=" + limite);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();
            if (status > 299) {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();

            }
            json = responseContent.toString();
            DefaultMutableTreeNode e = new DefaultMutableTreeNode("Terremotos");
            JSONObject earthquake = new JSONObject(json);
            JSONArray terre = earthquake.getJSONArray("features");

            for (int i = 0; i < terre.length(); i++) {
                Thread.sleep(100);
                setProgress(100 * (i + 1) / terre.length());
                JSONObject terr = terre.getJSONObject(i);
                JSONObject pro = terr.getJSONObject("properties");

                DefaultMutableTreeNode title = new DefaultMutableTreeNode(proc.getDateString(pro.getLong("time")) + " " + pro.getString("title"));
                DefaultMutableTreeNode Mag = new DefaultMutableTreeNode("Tipo de Magnitud: " + pro.getString("magType"));
                DefaultMutableTreeNode place = new DefaultMutableTreeNode("Ubicación: " + pro.getString("place"));
                DefaultMutableTreeNode date = new DefaultMutableTreeNode("Fecha de Actualización: " + proc.getDateString(pro.getLong("updated")));
                DefaultMutableTreeNode urlt = new DefaultMutableTreeNode("URL: " + pro.getString("url"));

                DefaultTreeModel modelo = new DefaultTreeModel(e);
                modelo.insertNodeInto(place, title, 0);
                modelo.insertNodeInto(Mag, title, 1);
                modelo.insertNodeInto(date, title, 2);
                modelo.insertNodeInto(urlt, title, 3);
                e.add(title);

                arbol.setModel(modelo);
            }

        } catch (MalformedURLException e) {
            System.out.println("Error.");
            JOptionPane.showMessageDialog(null, "Intente de nuevo (Error). " + e.getMessage());
            detenido = true;
            cancel(true);
        } catch (IOException e) {
            System.out.println("Error.");
            JOptionPane.showMessageDialog(null, "Intente de nuevo (Error). " + e.getMessage());
            detenido = true;
            cancel(true);
        } catch (InterruptedException x) {
        }
        return null;
    }

    protected void done() {
        if (!isCancelled() && detenido == false) {

            System.out.println("Se ha completado la descarga.");
            cancel(true);
        } else if (isCancelled()) {
            JOptionPane.showMessageDialog(null, "Se ha cancelado la descarga.");

        }
    }

}
