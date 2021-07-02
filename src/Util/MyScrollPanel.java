package Util;

import javax.swing.*;

public class MyScrollPanel {
    /**
     * @param jScrollPane1
     * @param vertical     0-1 theo phương thẳng đứng
     * @param horizontal   0-1 theo phương ngang
     */
    public static void scroll(JScrollPane jScrollPane1, Double vertical, Double horizontal) {

        Double giatrithang = vertical * jScrollPane1.getVerticalScrollBar().getMaximum();
        Double giatringang = horizontal * jScrollPane1.getHorizontalScrollBar().getMaximum();
        jScrollPane1.getVerticalScrollBar().setValue(MyConvert.parseDoubleToInt(giatrithang));
        jScrollPane1.getHorizontalScrollBar().setValue(MyConvert.parseDoubleToInt(giatringang));

        jScrollPane1.revalidate();
        jScrollPane1.repaint();
    }
}
