package com.yf.kp.utility;

import com.yf.kp.service.TabelAgamaService;
import com.yf.kp.service.TabelBayarBiayaTambahanService;
import com.yf.kp.service.TabelBayarSppService;
import com.yf.kp.service.TabelBiayaTambahanService;
import com.yf.kp.service.TabelBulanService;
import com.yf.kp.service.TabelCountService;
import com.yf.kp.service.TabelKelasService;
import com.yf.kp.service.TabelKonfigService;
import com.yf.kp.service.TabelPenggunaService;
import com.yf.kp.service.TabelSiswaService;
import com.yf.kp.service.TabelSppService;
import com.yf.kp.service.TabelWaliKelasService;
import com.yf.kp.serviceImpl.TabelAgamaServiceImpl;
import com.yf.kp.serviceImpl.TabelBayarBiayaTambahanServiceImpl;
import com.yf.kp.serviceImpl.TabelBayarSppServiceImpl;
import com.yf.kp.serviceImpl.TabelBiayaTambahanServiceImpl;
import com.yf.kp.serviceImpl.TabelBulanServiceImpl;
import com.yf.kp.serviceImpl.TabelCountServiceImpl;
import com.yf.kp.serviceImpl.TabelKelasServiceImpl;
import com.yf.kp.serviceImpl.TabelKonfigServiceImpl;
import com.yf.kp.serviceImpl.TabelPenggunaServiceImpl;
import com.yf.kp.serviceImpl.TabelSiswaServiceImpl;
import com.yf.kp.serviceImpl.TabelSppServiceImpl;
import com.yf.kp.serviceImpl.TabelWaliKelasServiceImpl;

/**
 *
 * @author BlackCode
 */
public final class InjectHelper {

    private static TabelAgamaService tabelAgamaService;
    private static TabelBayarBiayaTambahanService tabelBayarBiayaTambahanService;
    private static TabelBayarSppService tabelBayarSppService;
    private static TabelBiayaTambahanService tabelBiayaTambahanService;
    private static TabelBulanService tabelBulanService;
    private static TabelCountService tabelCountService;
    private static TabelKelasService tabelKelasService;
    private static TabelKonfigService tabelKonfigService;
    private static TabelPenggunaService tabelPenggunaService;
    private static TabelSiswaService tabelSiswaService;
    private static TabelSppService tabelSppService;
    private static TabelWaliKelasService tabelWaliKelasService;

    public static TabelAgamaService getTabelAgamaService() {
        if (tabelAgamaService == null) {
            tabelAgamaService = new TabelAgamaServiceImpl();
        }
        return tabelAgamaService;
    }

    public static TabelBayarBiayaTambahanService getTabelBayarBiayaTambahanService() {
        if (tabelBayarBiayaTambahanService == null) {
            tabelBayarBiayaTambahanService = new TabelBayarBiayaTambahanServiceImpl();
        }
        return tabelBayarBiayaTambahanService;
    }

    public static TabelBayarSppService getTabelBayarSppService() {
        if (tabelBayarSppService == null) {
            tabelBayarSppService = new TabelBayarSppServiceImpl();
        }
        return tabelBayarSppService;
    }

    public static TabelBiayaTambahanService getTabelBiayaTambahanService() {
        if (tabelBiayaTambahanService == null) {
            tabelBiayaTambahanService = new TabelBiayaTambahanServiceImpl();
        }
        return tabelBiayaTambahanService;
    }

    public static TabelBulanService getTabelBulanService() {
        if (tabelBulanService == null) {
            tabelBulanService = new TabelBulanServiceImpl();
        }
        return tabelBulanService;
    }

    public static TabelCountService getTabelCountService() {
        if (tabelCountService == null) {
            tabelCountService = new TabelCountServiceImpl();
        }
        return tabelCountService;
    }

    public static TabelKelasService getTabelKelasService() {
        if (tabelKelasService == null) {
            tabelKelasService = new TabelKelasServiceImpl();
        }
        return tabelKelasService;
    }

    public static TabelKonfigService getTabelKonfigService() {
        if (tabelKonfigService == null) {
            tabelKonfigService = new TabelKonfigServiceImpl();
        }
        return tabelKonfigService;
    }

    public static TabelPenggunaService getTabelPenggunaService() {
        if (tabelPenggunaService == null) {
            tabelPenggunaService = new TabelPenggunaServiceImpl();
        }
        return tabelPenggunaService;
    }
    
    public static TabelSiswaService getTabelSiswaService(){
        if(tabelSiswaService == null){
            tabelSiswaService = new TabelSiswaServiceImpl();
        }
        return tabelSiswaService;
    }
    
    public static TabelSppService getTabelSppService(){
        if(tabelSppService == null){
            tabelSppService = new TabelSppServiceImpl();
        }
        return tabelSppService;
    }
    
    public static TabelWaliKelasService getTabelWaliKelasService(){
        if(tabelWaliKelasService == null){
            tabelWaliKelasService = new TabelWaliKelasServiceImpl();
        }
        return tabelWaliKelasService;
    }

}
