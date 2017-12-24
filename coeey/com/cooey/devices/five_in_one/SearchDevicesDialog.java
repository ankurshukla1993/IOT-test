package com.cooey.devices.five_in_one;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import com.cooey.devices.C0910R;

public abstract class SearchDevicesDialog extends Dialog {
    private Button btnSearchDevices;
    private ListView lvBluetoothDevices = ((ListView) findViewById(C0910R.id.lvBluetoothDevices));
    private ProgressBar pbSearchDevices;

    class C09521 implements OnItemClickListener {
        C09521() {
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
            SearchDevicesDialog.this.onClickDeviceItem(position);
        }
    }

    class C09532 implements OnClickListener {
        C09532() {
        }

        public void onClick(View v) {
            SearchDevicesDialog.this.startSearch();
        }
    }

    public abstract void onClickDeviceItem(int i);

    public abstract void onStartSearch();

    public SearchDevicesDialog(Context context, BluetoothDeviceAdapter adapter) {
        super(context);
        requestWindowFeature(1);
        setContentView(C0910R.layout.devices_dialog);
        this.lvBluetoothDevices.setAdapter(adapter);
        this.lvBluetoothDevices.setOnItemClickListener(new C09521());
        this.pbSearchDevices = (ProgressBar) findViewById(C0910R.id.pbSearchDevices);
        this.btnSearchDevices = (Button) findViewById(C0910R.id.btnSearchDevices);
        this.btnSearchDevices.setOnClickListener(new C09532());
    }

    public void stopSearch() {
        this.pbSearchDevices.setVisibility(8);
        this.btnSearchDevices.setVisibility(0);
    }

    public void startSearch() {
        onStartSearch();
        this.pbSearchDevices.setVisibility(0);
        this.btnSearchDevices.setVisibility(8);
    }
}
