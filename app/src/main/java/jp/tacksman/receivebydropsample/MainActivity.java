package jp.tacksman.receivebydropsample;

import android.content.ClipData;
import android.content.ClipDescription;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = (TextView) findViewById(R.id.output);
        findViewById(R.id.output).setOnDragListener((v, event) -> {
            final int action = event.getAction();
            Log.d("Receive", "Action: " + action);

            // Handles each of the expected events
            switch(action) {

                case DragEvent.ACTION_DRAG_STARTED:
                    if (event.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                        return true;
                    }
                    return false;
                case DragEvent.ACTION_DRAG_ENTERED:
                    return true;
                case DragEvent.ACTION_DRAG_LOCATION:
                    return true;
                case DragEvent.ACTION_DRAG_EXITED:
                    return true;
                case DragEvent.ACTION_DROP:
                    ClipData.Item item = event.getClipData().getItemAt(0);
                    CharSequence receivedText = item.getText();
                    textView.setText(receivedText);

                    Toast.makeText(MainActivity.this, "Dragged data is " + String.valueOf(receivedText), Toast.LENGTH_SHORT).show();
                    return true;
                case DragEvent.ACTION_DRAG_ENDED:
                    if (event.getResult()) {
                        Toast.makeText(MainActivity.this, "The drop was handled.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "The drop didn't work.", Toast.LENGTH_SHORT).show();
                    }
                    return true;
                default:
                    Log.e("DragDrop Example","Unknown action type received by OnDragListener.");
                    break;
            }
            return false;
        });
    }
}
