package arsenault.rj.homelandsecurity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by The Smarticus 2017 on 2017-11-11.
 */

public class TextListener extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        //Toast.makeText(context, "MESSAGE RECEIVED",Toast.LENGTH_SHORT).show();
        SmsMessage[] msgs = null;
        String str = "";

        if (bundle != null) {
            // Retrieve the SMS Messages received
            Object[] pdus = (Object[]) bundle.get("pdus");
            msgs = new SmsMessage[pdus.length];


            for (int i=0; i < msgs.length; i++) {
                StringBuilder b = new StringBuilder();
                // Convert Object array
                msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                // Sender's phone number
                b.append( msgs[i].getOriginatingAddress());
                //this is going to be a delimiter in a scanner
                b.append(",");
                //fetch the feckin message
                b.append(msgs[i].getMessageBody().toString());
            }
        }
    }
}
