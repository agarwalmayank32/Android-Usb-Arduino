package info.mayankag.android_usb_arduino;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;

public class UsbControllerActivity extends Activity {
	private static final int VID =9025;// 0x1a86;//0x2341;
	private static final int PID =67;// 0x7523;//0x0042 for the Arduino Megas
	private static UsbController sUsbController;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        if(sUsbController == null){
	        sUsbController = new UsbController(this, mConnectionHandler, VID, PID);
        }
        SeekBar seekBar = (SeekBar)(findViewById(R.id.seekBar1));
        seekBar.setMax(9);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
			}
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				if(fromUser){
					if(sUsbController != null){
						sUsbController.send((byte)(progress+48));
						//L.e((byte)(progress+48));
					}
				}
			}
		});
        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(sUsbController == null)
                {
                    sUsbController = new UsbController(UsbControllerActivity.this, mConnectionHandler, VID, PID);
                    sUsbController.send((byte)(48));
                }
				else{
					sUsbController.stop();
					sUsbController = new UsbController(UsbControllerActivity.this, mConnectionHandler, VID, PID);
                    sUsbController.send((byte)(48));

				}
			}
		});
        
    }

	private final IUsbConnectionHandler mConnectionHandler = new IUsbConnectionHandler() {
		@Override
		public void onUsbStopped() {
			Log.e(">==<USB Controller>==<", "Usb stopped");
		}
		
		@Override
		public void onErrorLooperRunningAlready() {
			Log.e(">==<USB Controller>==<", "Looper already running!");
		}
		
		@Override
		public void onDeviceNotFound() {
			if(sUsbController != null){
				sUsbController.stop();
				sUsbController = null;
			}
		}
	};
}