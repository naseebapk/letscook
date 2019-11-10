package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import edmt.dev.edmtdevcognitivevision.Contract.AnalysisResult;
import edmt.dev.edmtdevcognitivevision.Contract.Caption;
import edmt.dev.edmtdevcognitivevision.Rest.VisionServiceException;
import edmt.dev.edmtdevcognitivevision.VisionServiceClient;
import edmt.dev.edmtdevcognitivevision.VisionServiceRestClient;


public class
MainActivity extends AppCompatActivity {
    String st;
    ImageView imageview;
    Button buttonprocess;
    //Button button;
    TextView textview;
    private final String API_KEY = "3cdb6f76aff94c1380ec781511d94390";
    private final String API_LINK = "https://naseebapk.cognitiveservices.azure.com/vision/v1.0";
    VisionServiceClient visionServiceClient=new VisionServiceRestClient(API_KEY,API_LINK);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageview = (ImageView) findViewById(R.id.image);
       // button=(Button)findViewById(R.id.button);
        buttonprocess = (Button) findViewById(R.id.buttonprocess);
        textview = (TextView) findViewById(R.id.result);
        final Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.tomato);
        imageview.setImageBitmap(bitmap);
        buttonprocess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                final ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
                AsyncTask<InputStream, String, String> visionTask = new AsyncTask<InputStream, String, String>() {


                    ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);

                    @Override
                    protected void onPreExecute() {
                        progressDialog.show();
                    }

                    @Override
                    protected String doInBackground(InputStream... inputStreams) {


                        try {
                            publishProgress("Recognizing......");
                            String[] features = {"Description"};
                            String[] details = {};
                            AnalysisResult result = visionServiceClient.analyzeImage(inputStreams[0], features, details);
                            String jsonresult = new Gson().toJson(result);
                            return jsonresult;


                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (VisionServiceException e) {
                            e.printStackTrace();
                        }

                        return "";
                    }

                    @Override
                    protected void onPostExecute(String s) {
                        if(TextUtils.isEmpty(s))
                        {
                            Toast.makeText(MainActivity.this,"empty result", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            progressDialog.dismiss();
                            AnalysisResult result = new Gson().fromJson(s, AnalysisResult.class);
                            StringBuilder result_Text = new StringBuilder();
                            for (Caption caption : result.description.captions)
                                result_Text.append(caption.text);
                            textview.setText(result_Text.toString());


                        }
                    }

                    @Override
                    protected void onProgressUpdate(String... values) {
                        progressDialog.setMessage(values[0]);
                    }
                };
                visionTask.execute(inputStream);


            }
        });

    }


//
    }






//                    @Override
//                    protected String doInBackground(InputStream... inputStreams) {
//                        try {
//                            publishProgress("Recognizing......");
//                            String[] features = {"Description"};
//                            String[] details = {};
//                            AnalysisResult result = visionServiceClient.analyzeImage(inputStreams[0], features, details);
//                            String jsonresult = new Gson().toJson(result);
//                            return jsonresult;
//
//
//                        } catch (VisionServiceException e) {
//                            e.printStackTrace();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//

//                    //progressDialog.dismiss();
//                    //AnalysisResult result=new Gson().to
//
//                };
//            }
//
//
//        });
//        }
//
//


