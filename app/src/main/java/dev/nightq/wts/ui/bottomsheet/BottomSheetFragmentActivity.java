package dev.nightq.wts.ui.bottomsheet;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.flipboard.bottomsheet.BottomSheetLayout;
import com.flipboard.bottomsheet.commons.ImagePickerSheetView;

import dev.nightq.wts.R;

/**
 * Activity demonstrating the use of {@link ImagePickerSheetView}
 */
public final class BottomSheetFragmentActivity extends AppCompatActivity {

    protected BottomSheetLayout bottomSheetLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet_fragment);
        bottomSheetLayout = (BottomSheetLayout) findViewById(R.id.bottomsheet);
        findViewById(R.id.bottomsheet_fragment_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MyFragment().show(getSupportFragmentManager(), R.id.bottomsheet);
            }
        });
    }
}
