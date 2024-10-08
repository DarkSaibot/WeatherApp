package src;

import org.json.simple.JSONObject;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WeatherAppGui extends JFrame {
    private JSONObject weatherData;

    public WeatherAppGui(){

        super("Previsão Do Tempo");


        setDefaultCloseOperation(EXIT_ON_CLOSE);

        getContentPane().setBackground(new Color(173, 216, 230));


        setSize(450, 650);


        setLocationRelativeTo(null);


        setLayout(null);


        setResizable(false);

        addGuiComponents();
    }

    private void addGuiComponents(){

        JTextField searchTextField = new JTextField();


        searchTextField.setBounds(15, 15, 351, 45);


        searchTextField.setFont(new Font("Dialog", Font.PLAIN, 24));

        add(searchTextField);


        JLabel weatherConditionImage = new JLabel(loadImage("src/assets/cloudy.png"));
        weatherConditionImage.setBounds(0, 125, 450, 217);
        add(weatherConditionImage);


        JLabel temperatureText = new JLabel("10 C");
        temperatureText.setBounds(0, 350, 450, 54);
        temperatureText.setFont(new Font("Dialog", Font.BOLD, 48));


        temperatureText.setHorizontalAlignment(SwingConstants.CENTER);
        add(temperatureText);


        JLabel weatherConditionDesc = new JLabel("Nublado");
        weatherConditionDesc.setBounds(0, 405, 450, 36);
        weatherConditionDesc.setFont(new Font("Dialog", Font.PLAIN, 32));
        weatherConditionDesc.setHorizontalAlignment(SwingConstants.CENTER);
        add(weatherConditionDesc);


        JLabel humidityImage = new JLabel(loadImage("src/assets/humidity.png"));
        humidityImage.setBounds(15, 500, 74, 66);
        add(humidityImage);


        JLabel humidityText = new JLabel("<html><b>Umidade</b> 100%</html>");
        humidityText.setBounds(90, 500, 85, 55);
        humidityText.setFont(new Font("Dialog", Font.PLAIN, 16));
        add(humidityText);


        JLabel windspeedImage = new JLabel(loadImage("src/assets/windspeed.png"));
        windspeedImage.setBounds(220, 500, 74, 66);
        add(windspeedImage);


        JLabel windspeedText = new JLabel("<html><b>Força Do Vento</b> 15km/h</html>");
        windspeedText.setBounds(310, 500, 85, 55);
        windspeedText.setFont(new Font("Dialog", Font.PLAIN, 16));
        add(windspeedText);


        JButton searchButton = new JButton(loadImage("src/assets/search.png"));


        searchButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        searchButton.setBounds(375, 13, 47, 45);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String userInput = searchTextField.getText();


                if(userInput.replaceAll("\\s", "").length() <= 0){
                    return;
                }

                weatherData = WeatherApp.getWeatherData(userInput);


                String weatherCondition = (String) weatherData.get("weather_condition");


                switch(weatherCondition){
                    case "Céu Limpo":
                        weatherConditionImage.setIcon(loadImage("src/assets/clear.png"));
                        break;
                    case "Nublado":
                        weatherConditionImage.setIcon(loadImage("src/assets/cloudy.png"));
                        break;
                    case "Chuvoso":
                        weatherConditionImage.setIcon(loadImage("src/assets/rain.png"));
                        break;
                    case "Nevando":
                        weatherConditionImage.setIcon(loadImage("src/assets/snow.pngImage"));
                        break;
                }


                double temperature = (double) weatherData.get("temperature");
                temperatureText.setText(temperature + " C");

                weatherConditionDesc.setText(weatherCondition);

                long humidity = (long) weatherData.get("humidity");
                humidityText.setText("<html><b>Umidade</b> " + humidity + " %</html>");


                double windspeed = (double) weatherData.get("windspeed");
                windspeedText.setText("<html><b>Força Do Vento</b> " + windspeed + "km/h</html>");
            }
        });
        add(searchButton);
    }


    private ImageIcon loadImage(String resourcePath){
        try{

            BufferedImage image = ImageIO.read(new File(resourcePath));


            return new ImageIcon(image);
        }catch(IOException e){
            e.printStackTrace();
        }

        System.out.println("Não foi possível encontrar o recurso.");
        return null;
    }
}








