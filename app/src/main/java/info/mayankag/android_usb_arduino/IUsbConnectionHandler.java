package info.mayankag.android_usb_arduino;

public interface IUsbConnectionHandler {

	void onUsbStopped();

	void onErrorLooperRunningAlready();

	void onDeviceNotFound();
}
