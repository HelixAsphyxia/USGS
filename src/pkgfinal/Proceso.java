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
import java.text.SimpleDateFormat;
import com.toedter.calendar.JDateChooser;

public class Proceso {

    public String getFecha(JDateChooser jd) {
        SimpleDateFormat Formato = new SimpleDateFormat("yyyy-MM-dd");
        if (jd.getDate() != null) {
            return Formato.format(jd.getDate());
        } else {
            return null;
        }
    }

    public String getDateString(long timeInMilliseconds) {
        SimpleDateFormat formatter = new SimpleDateFormat("EE', 'dd-MMM-yyyy' a las 'HH:mm:ss");
        return formatter.format(timeInMilliseconds);
    }

}
