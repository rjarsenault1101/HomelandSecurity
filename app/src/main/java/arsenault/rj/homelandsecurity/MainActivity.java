package arsenault.rj.homelandsecurity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Exceptions.ImproperInputException;

public class MainActivity extends AppCompatActivity {

    private static final int DIMENSION = 8;
    private static final int NUMBER_VALUES = 3;
    private static final String INPUT_PATTERN_STRING = "\\(\\d,\\d,\\d\\)";
    private static final String INPUT_CHECKER_STRING = "(\\(\\d,\\d,\\d\\),)*\\(\\d,\\d,\\d\\)";
    private static final Pattern INPUT_CHECKER_PATTERN = Pattern.compile(INPUT_CHECKER_STRING);
    private static final Pattern INPUT_PATTERN = Pattern.compile(INPUT_PATTERN_STRING);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button enter = (Button) findViewById(R.id.enterButton);
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText input = (EditText) findViewById(R.id.inputBox);
                String array = input.getText().toString();
                try {
                    makeMap(array);
                } catch(ImproperInputException iie) {
                    input.setError("Improper input");
                }
            }
        });

    }
    public static String printArray(int[][][] arr) {
        StringBuilder b = new StringBuilder();
        for(int i = 0; i< DIMENSION; i++) {
            for (int j = 0; j< DIMENSION; j++) {
                b.append("(");
                for (int k = 0; k < NUMBER_VALUES; k++) {
                    b.append(arr[i][j][k]);
                    b.append(k == NUMBER_VALUES-1? "" : ",");
                }
                b.append(i*j < Math.pow(DIMENSION, 2)? "),": ")");
            }
        }
        return b.toString();
    }
    public static int[][][] makeMap(String triplets) throws ImproperInputException {
        int[][][] output = new int[DIMENSION][DIMENSION][NUMBER_VALUES];
        Matcher input = INPUT_CHECKER_PATTERN.matcher(triplets);
        Matcher m = INPUT_PATTERN.matcher(triplets);
        if(!input.matches()) {
            throw new ImproperInputException("Malformed input. Try again");
        }
        int count = 0;
        while(m.find() && count < DIMENSION * DIMENSION) {
            String s = m.group();
            s = s.substring(1, s.indexOf(")"));
            Scanner scan = new Scanner(s);
            scan.useDelimiter(",");
            for(int i = 0; i< NUMBER_VALUES; i++) {
                output[count / DIMENSION][count % DIMENSION][i] =scan.nextInt();
            }
            count++;
        }
        return output;
    }
}
