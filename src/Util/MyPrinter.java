package Util;

/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */


import dichvu.DichVuDAO;
import dichvu.ThueDichVu;
import hoadon.HoaDon;
import hoadon.HoaDonDAO;
import khachhang.KhachHang;
import khachhang.KhachHangDAO;
import nhanvien.NhanVienDAO;
import phong.DanhSachPhongDAO;
import phong.PhongDAO;
import phong.ThuePhong;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.ImageObserver;
import java.awt.print.*;
import java.text.AttributedCharacterIterator;
import java.util.ArrayList;
import java.util.Date;

public class MyPrinter extends Graphics implements Printable {


    private KhachHang khachhang;
    private HoaDon hoadon;
    private Date date =new Date(System.currentTimeMillis());
    private ArrayList<ThuePhong> listThuePhong;
    private ArrayList<ThueDichVu> listThueDichVu;
    private  String noidung="";
    public int print(Graphics g, PageFormat pf, int page) throws
            PrinterException {

        if (page > 0) { /* We have only one page, and 'page' is zero-based */
            return NO_SUCH_PAGE;
        }

        /* User (0,0) is typically outside the imageable area, so we must
         * translate by the X and Y values in the PageFormat to avoid clipping
         */

        Graphics2D g2d = (Graphics2D)g;
        
        g2d.translate(pf.getImageableX(), pf.getImageableY());

        /* Now we perform our rendering */
        //drawString(g,noidung,100,100);
        //g.drawString(noidung, 100, 100);
        Integer x=0;
        for (String line : noidung.split("\n")) {
            x+=15;
            g.drawString(line, 100, 100 + x);
        }


        /* tell the caller that this page is part of the printed document */
        return PAGE_EXISTS;
    }

    public void  printMyContent(HoaDon hd)
    {
/*

        hoadon=hd; listThueDichVu=new DichVuDAO().qu; listThuePhong=listtp;
        khachhang= new KhachHangDAO().queryKHbyHD(hoadon);
        noidung+="KHÁCH SẠN ÁNH DƯƠNG\n" +
                "Khu phố 6 phường Linh Trung, thành phố Thủ Đức\n"+
                "BIÊN NHẬN THANH TOÁN THUÊ KHÁCH SẠN\n\n" +
                "Mã KH: "+khachhang.getMAKH() +"\n" +
                "Tên KH: "+khachhang.getTENKH()+"\n" +
                "Ngày hoá đơn: "+date+"\n" +
                "------------------------------- \n" ;
        for (ThuePhong tp:listThuePhong)
        {
            noidung+="MAPH: "+tp.getMAPH()+" \n" +
                    "Số người: "+tp.getSONGUOITHUE() +"\n" +
                    "Đơn giá: "+(new DanhSachPhongDAO().queryDSPbyTP(tp)).getDONGIA()+
            "\nSố ngày thuê "+new PhongDAO().querySoNgayThueByTP(tp)+
            "\nTiền phòng: "+ tp.getTIEN()+
                    "\n";
        }
        noidung+="TỔNG TIỀN PHÒNG: " + MyConvert.parseObjToString(new PhongDAO().queryTongTienTPByHD(hoadon))+
                "\n" ;
        noidung+="------------------------------- \n" ;
        for (ThueDichVu tdv:listThueDichVu)
        {
            noidung+="MAPH: "+tdv.getMADV()+" \n" +
                    "Số người: "+new DichVuDAO().queryDVByTDV(tdv).getTENDV() +"\n" +
                    "Đơn giá: "+(new DichVuDAO().queryDVByTDV(tdv)).getGIADV()+
                    "\nSố ngày thuê "+new DichVuDAO().soNgayThueDVbyTDV(tdv)+
                    "\nTiền phòng: "+ tdv.getTIEN()+
                    "\n";
        }
        noidung+="TỔNG TIỀN DỊCH VỤ: " + MyConvert.parseObjToString(new DichVuDAO().queryTongTienDVByHD(hoadon))+ "\n";
        noidung+="------------------------------- \n" ;
        noidung+="TỔNG THÀNH TIỀN: "+hoadon.getTHANHTIEN()+"\n";
        noidung+="------------------------------- \n" ;
        noidung+="Nhân viên: "+(new NhanVienDAO().queryNVbyHD(hoadon)).getHOTEN()+ "\n";
        noidung+="------------------------------- \n" ;
        noidung+="Nhóm sinh viên hệ thống thông tin \n" ;
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(this);
        boolean ok = job.printDialog();
        if (ok) {
            try {
                job.print();
            } catch (PrinterException ex) {

            }
        }*/
        return;
    }


    @Override
    public Graphics create() {
        return null;
    }

    @Override
    public void translate(int x, int y) {

    }

    @Override
    public Color getColor() {
        return null;
    }

    @Override
    public void setColor(Color c) {

    }

    @Override
    public void setPaintMode() {

    }

    @Override
    public void setXORMode(Color c1) {

    }

    @Override
    public Font getFont() {
        return null;
    }

    @Override
    public void setFont(Font font) {

    }

    @Override
    public FontMetrics getFontMetrics(Font f) {
        return null;
    }

    @Override
    public Rectangle getClipBounds() {
        return null;
    }

    @Override
    public void clipRect(int x, int y, int width, int height) {

    }

    @Override
    public void setClip(int x, int y, int width, int height) {

    }

    @Override
    public Shape getClip() {
        return null;
    }

    @Override
    public void setClip(Shape clip) {

    }

    @Override
    public void copyArea(int x, int y, int width, int height, int dx, int dy) {

    }

    @Override
    public void drawLine(int x1, int y1, int x2, int y2) {

    }

    @Override
    public void fillRect(int x, int y, int width, int height) {

    }

    @Override
    public void clearRect(int x, int y, int width, int height) {

    }

    @Override
    public void drawRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {

    }

    @Override
    public void fillRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {

    }

    @Override
    public void drawOval(int x, int y, int width, int height) {

    }

    @Override
    public void fillOval(int x, int y, int width, int height) {

    }

    @Override
    public void drawArc(int x, int y, int width, int height, int startAngle, int arcAngle) {

    }

    @Override
    public void fillArc(int x, int y, int width, int height, int startAngle, int arcAngle) {

    }

    @Override
    public void drawPolyline(int[] xPoints, int[] yPoints, int nPoints) {

    }

    @Override
    public void drawPolygon(int[] xPoints, int[] yPoints, int nPoints) {

    }

    @Override
    public void fillPolygon(int[] xPoints, int[] yPoints, int nPoints) {

    }

    @Override
    public void drawString(String str, int x, int y) {

    }

    @Override
    public void drawString(AttributedCharacterIterator iterator, int x, int y) {

    }

    @Override
    public boolean drawImage(Image img, int x, int y, ImageObserver observer) {
        return false;
    }

    @Override
    public boolean drawImage(Image img, int x, int y, int width, int height, ImageObserver observer) {
        return false;
    }

    @Override
    public boolean drawImage(Image img, int x, int y, Color bgcolor, ImageObserver observer) {
        return false;
    }

    @Override
    public boolean drawImage(Image img, int x, int y, int width, int height, Color bgcolor, ImageObserver observer) {
        return false;
    }

    @Override
    public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, ImageObserver observer) {
        return false;
    }

    @Override
    public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, Color bgcolor, ImageObserver observer) {
        return false;
    }

    @Override
    public void dispose() {

    }
}
