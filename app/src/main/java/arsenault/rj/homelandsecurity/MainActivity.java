package arsenault.rj.homelandsecurity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exceptions.ImproperInputException;

public class MainActivity extends AppCompatActivity {

    private static final int WIDTH = 8, HEIGHT = 8;
    private static final int NUMBER_VALUES = 3;
    private static final String INPUT_PATTERN_STRING = "\\(\\d,\\d,\\d\\)";
    private static final String INPUT_CHECKER_STRING = "(\\(\\d,\\d,\\d\\),)*\\(\\d,\\d,\\d\\)";
    private static final Pattern INPUT_CHECKER_PATTERN = Pattern.compile(INPUT_CHECKER_STRING);
    private static final Pattern INPUT_PATTERN = Pattern.compile(INPUT_PATTERN_STRING);
    private int[][] pixels;
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
                    pixels = makeMap(array);
                    Bitmap image = createImage(pixels);
                } catch(ImproperInputException iie) {
                    input.setError("Improper input");
                }
            }
        });

    }
    private Bitmap createImage(int[][] pixels){
        ImageView iv = (ImageView) findViewById(R.id.imageView);


    }
    public static String printArray(int[][][] arr) {
        StringBuilder b = new StringBuilder();
        for(int i = 0; i< WIDTH; i++) {
            for (int j = 0; j< HEIGHT; j++) {
                b.append("(");
                for (int k = 0; k < NUMBER_VALUES; k++) {
                    b.append(arr[i][j][k]);
                    b.append(k == NUMBER_VALUES-1? "" : ",");
                }
                b.append(i*j < WIDTH * HEIGHT? "),": ")");
            }
        }
        return b.toString();
    }
    public static int[][] makeMap(String triplets) throws ImproperInputException {
        int[][] output = new int[WIDTH][HEIGHT];
        Matcher input = INPUT_CHECKER_PATTERN.matcher(triplets);
        Matcher m = INPUT_PATTERN.matcher(triplets);
        if(!input.matches()) {
            throw new ImproperInputException("Malformed input. Try again");
        }
        int count = 0;
        while(m.find() && count < WIDTH * HEIGHT) {
            String s = m.group();
            s = s.substring(1, s.indexOf(")"));
            Scanner scan = new Scanner(s);
            scan.useDelimiter(",");
            int r = scan.nextInt()*32-1;
            int g = scan.nextInt()*32-1;
            int b = scan.nextInt()*32-1;
            output[count / WIDTH][count % HEIGHT] = Color.rgb(r,g,b);

            count++;
        }
        return output;
    }
}
