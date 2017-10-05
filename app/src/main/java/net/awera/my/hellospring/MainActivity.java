package net.awera.my.hellospring;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class MainActivity extends AppCompatActivity {

    private static final String URL_AWERA_GET = "http://ec2-18-220-181-193.us-east-2.compute.amazonaws.com:8080/awera-service-1.0-SNAPSHOT/helloAwera/v1";
    private static final String URL_AWERA_POST = "http://ec2-18-220-181-193.us-east-2.compute.amazonaws.com:8080/awera-service-1.0-SNAPSHOT/helloAwera/sms";
    //private static final String URL_AWERA_POST = "http://localhost:8080/helloAwera/sms";
    private static final String URL_SAMPLE = "http://rest-service.guides.spring.io/aweraMessage";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addListenerOnServiceButton();
        //addListnerOnSendButton();
    }

    private void addListenerOnServiceButton() {

        Button button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                new RequestStatusTask().execute();
            }
        });
    }

    private class RequestStatusTask extends AsyncTask<Void,Void,AweraStatus> {
        @Override
        protected AweraStatus doInBackground(Void... params) {
            try {
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                AweraStatus aweraStatus = restTemplate.getForObject(URL_AWERA_GET, AweraStatus.class);
                return aweraStatus;

                /*HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                MultiValueMap<String,String> headers = new LinkedMultiValueMap<String,String>();
                headers.add("Content-Type","application/json");
                AweraMessage message = new AweraMessage("","+16414513138", "23847297502", "luv u");
                HttpEntity<AweraMessage> entity = new HttpEntity<AweraMessage>(message, headers);
                ResponseEntity<String> response = restTemplate.postForEntity(URL_AWERA_POST,entity,String.class);
                return null;*/
            } catch (Exception e) {
                //Log.e("MainActivity", e.getMessage(), e);
                Log.d("MainActivity", e.getMessage(), e);
            }

            return null;
        }
        @Override
        protected void onPostExecute(AweraStatus aweraStatus) {
            TextView statusIdText = (TextView) findViewById(R.id.content_value);
            statusIdText.setText(aweraStatus.getAweraStatus());

        }
    }


    /*private class SendMessageTask extends AsyncTask<Void,Void,AweraMessage> {
        @Override
        protected AweraMessage doInBackground(Void... params) {
            try {
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                AweraMessage message = new AweraMessage("+16414513138", "23847297502", "luv u");
                AweraMessage aweraMessage = restTemplate.postForObject(URL_AWERA_POST,message,AweraMessage.class);
                //AweraMessage aweraMessage = restTemplate.getForObject(URL_AWERA_GET, AweraMessage.class);
                return aweraMessage;
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }

            return null;
        }
        @Override
        protected void onPostExecute(AweraStatus aweraStatus) {
            TextView statusIdText = (TextView) findViewById(R.id.content_value);
            statusIdText.setText(aweraStatus.getAweraStatus());

        }
    }*/
}
