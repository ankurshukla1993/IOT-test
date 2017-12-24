package com.example.smartlinklib;

import android.app.Activity;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.smartlinklib.SmartLinkManipulator.ConnectCallBack;
import com.ihealth.communication.manager.iHealthDevicesManager;
import java.net.SocketException;
import java.net.UnknownHostException;
import kotlin.text.Typography;

public class MainActivity extends Activity {
    ConnectCallBack callback = new C10722();
    Handler hand = new C10681();
    boolean isconncting = false;
    Button m_startBtn;
    EditText pswd;
    SmartLinkManipulator sm;
    TextView ssid;

    class C10681 extends Handler {
        C10681() {
        }

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    MainActivity.this.m_startBtn.setText("停止链接");
                    return;
                case 2:
                    MainActivity.this.m_startBtn.setText("开始链接");
                    return;
                default:
                    return;
            }
        }
    }

    class C10722 implements ConnectCallBack {

        class C10691 implements Runnable {
            C10691() {
            }

            public void run() {
                Toast.makeText(MainActivity.this, "配置超时", 0).show();
                MainActivity.this.m_startBtn.setText("开始链接");
                MainActivity.this.isconncting = false;
            }
        }

        class C10713 implements Runnable {
            C10713() {
            }

            public void run() {
                Toast.makeText(MainActivity.this, "配置完成", 0).show();
                MainActivity.this.m_startBtn.setText("开始链接");
                MainActivity.this.isconncting = false;
            }
        }

        C10722() {
        }

        public void onConnectTimeOut() {
            MainActivity.this.hand.post(new C10691());
        }

        public void onConnect(final ModuleInfo mi) {
            Log.e("NEWMdule", mi.getModuleIP());
            MainActivity.this.hand.post(new Runnable() {
                public void run() {
                    Toast.makeText(MainActivity.this, "发现设备  " + mi.getMid() + iHealthDevicesManager.IHEALTH_DEVICE_MAC + mi.getMac() + "IP" + mi.getModuleIP(), 0).show();
                }
            });
        }

        public void onConnectOk() {
            MainActivity.this.hand.post(new C10713());
        }
    }

    class C10733 implements OnClickListener {
        C10733() {
        }

        public void onClick(View v) {
            if (MainActivity.this.isconncting) {
                MainActivity.this.sm.StopConnection();
                MainActivity.this.hand.sendEmptyMessage(2);
                MainActivity.this.isconncting = false;
                return;
            }
            MainActivity.this.isconncting = true;
            MainActivity.this.sm = SmartLinkManipulator.getInstence();
            String ss = MainActivity.this.getSSid();
            String ps = MainActivity.this.pswd.getText().toString().trim();
            MainActivity.this.hand.sendEmptyMessage(1);
            try {
                MainActivity.this.sm.setConnection(ss, ps, MainActivity.this);
            } catch (SocketException e) {
                e.printStackTrace();
            } catch (UnknownHostException e2) {
                e2.printStackTrace();
            }
            MainActivity.this.sm.Startconnection(MainActivity.this.callback);
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.m_startBtn = (Button) findViewById(R.id.start);
        this.ssid = (TextView) findViewById(R.id.ssid);
        this.ssid.setText(getSSid());
        this.pswd = (EditText) findViewById(R.id.pswd);
        this.m_startBtn.setOnClickListener(new C10733());
    }

    private String getSSid() {
        WifiManager wm = (WifiManager) getSystemService("wifi");
        if (wm != null) {
            WifiInfo wi = wm.getConnectionInfo();
            if (wi != null) {
                String s = wi.getSSID();
                if (s.length() > 2 && s.charAt(0) == Typography.quote && s.charAt(s.length() - 1) == Typography.quote) {
                    return s.substring(1, s.length() - 1);
                }
                return s;
            }
        }
        return "";
    }
}
