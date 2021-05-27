package thread;

import SqlServerData.SqlServerRetrieveData;
import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.Process;
import android.util.Log;
import androidx.lifecycle.ViewModelStoreOwner;

public class UpdateDataHandlerThread extends HandlerThread {

    private static final String TAG = "UpdateDataHandlerThread";

    private ViewModelStoreOwner viewModelStoreOwner;

    public UpdateDataHandlerThread() {
        super("UpdateDataHandlerThread", Process.THREAD_PRIORITY_BACKGROUND);
    }


}
