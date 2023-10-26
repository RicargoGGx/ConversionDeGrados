package com.example.conversiondegrados;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.example.conversiondegrados.models.Celsius;
import com.example.conversiondegrados.models.Fahrenheit;
import com.example.conversiondegrados.models.Kelvin;


public class MainActivity extends AppCompatActivity {

    private EditText editTextCelsius, editTextKelvin, editTextFahrenheit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextCelsius = findViewById(R.id.editTextCelsius);
        editTextKelvin = findViewById(R.id.editTextKelvin);
        editTextFahrenheit = findViewById(R.id.editTextFahrenheit);

        // Clean Button
        Button buttonClean = findViewById(R.id.buttonClean);
        buttonClean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextCelsius.setText("");
                editTextKelvin.setText("");
                editTextFahrenheit.setText("");
            }
        });

        // Configurar el OnClickListener para el botón de conversión
        Button buttonConvert = findViewById(R.id.buttonConvert);
        buttonConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realizarConversiones();
            }
        });
    }

    private void realizarConversiones() {
        String celsiusStr = editTextCelsius.getText().toString();
        String kelvinStr = editTextKelvin.getText().toString();
        String fahrenheitStr = editTextFahrenheit.getText().toString();

        try {
            if (!celsiusStr.isEmpty()) {
                double celsiusValue = Double.parseDouble(celsiusStr);
                Celsius celsius = new Celsius(celsiusValue, "C");

                Celsius kelvin = celsius.parse(new Kelvin(celsiusValue, "K"));
                Celsius fahrenheit = celsius.parse(new Fahrenheit(celsiusValue, "F"));

                Log.d("Conversion", "Celsius to Kelvin: " + kelvin.getValor());
                Log.d("Conversion", "Celsius to Fahrenheit: " + fahrenheit.getValor());

                editTextKelvin.setText(String.valueOf(kelvin.getValor()));
                editTextFahrenheit.setText(String.valueOf(fahrenheit.getValor()));
            } else if (!kelvinStr.isEmpty()) {
                double kelvinValue = Double.parseDouble(kelvinStr);
                Kelvin kelvin = new Kelvin(kelvinValue, "K");

                Kelvin celsius = kelvin.parse(new Celsius(kelvinValue, "C"));
                Kelvin fahrenheit = kelvin.parse(new Fahrenheit(kelvinValue, "F"));

                Log.d("Conversion", "Kelvin to Celsius: " + celsius.getValor());
                Log.d("Conversion", "Kelvin to Fahrenheit: " + fahrenheit.getValor());

                editTextCelsius.setText(String.valueOf(celsius.getValor()));
                editTextFahrenheit.setText(String.valueOf(fahrenheit.getValor()));
            } else if (!fahrenheitStr.isEmpty()) {
                double fahrenheitValue = Double.parseDouble(fahrenheitStr);
                Fahrenheit fahrenheit = new Fahrenheit(fahrenheitValue, "F");

                Fahrenheit celsius = fahrenheit.parse(new Celsius(fahrenheitValue, "C"));
                Fahrenheit kelvin = fahrenheit.parse(new Kelvin(fahrenheitValue, "K"));

                Log.d("Conversion", "Fahrenheit to Celsius: " + celsius.getValor());
                Log.d("Conversion", "Fahrenheit to Kelvin: " + kelvin.getValor());

                editTextCelsius.setText(String.valueOf(celsius.getValor()));
                editTextKelvin.setText(String.valueOf(kelvin.getValor()));
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            Log.e("Conversion", "Error en la conversión: " + e.getMessage());
        }
    }
}