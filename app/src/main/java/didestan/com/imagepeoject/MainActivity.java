package didestan.com.imagepeoject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;

import okhttp3.MediaType;

public class MainActivity extends AppCompatActivity {


    TextView error_txt;
    ImageView avatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            SSLSocketFactory customSocketFactory = new TLSSocketFactory();

            HttpsURLConnection.setDefaultSSLSocketFactory(customSocketFactory);

        }catch (KeyManagementException e){
           e.printStackTrace();
        } catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }


        avatar = (ImageView) findViewById(R.id.avatar);
        error_txt =  (TextView) findViewById(R.id.error_log);


        Glide.with(this).load("https://media.didestan.com/v1/image/w256h144/5bfbada9d833cs6ruNVbEtu78AOIycqb2.jpg")
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, com.bumptech.glide.request.target.Target<GlideDrawable> target, boolean isFirstResource) {
                        error_txt.setText(e.getMessage());
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, com.bumptech.glide.request.target.Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        return false;
                    }

                }).into(avatar);
    }

}