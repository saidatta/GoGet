package app.request;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.EditText;

import java.util.Calendar;

/**
 * Created with IntelliJ IDEA.
 * User: mkeats
 * Date: 5/16/14
 * Time: 1:25 AM
 * To change this template use File | Settings | File Templates.
 */
public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    //from Android API dev page
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        DateFormat DateFormat = new DateFormat();
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }
    
    @Override
    public void onTimeSet(android.widget.TimePicker view, int hourOfDay, int minute) {
        //EditText inputTime = (EditText) getView().findViewById(R.id.text_time);
        EditText inputTime = (EditText) view.findViewById(R.id.text_time);
        String time = Integer.toString(hourOfDay) + ":" + Integer.toString(minute);
        inputTime.setText(time);

    }
}
