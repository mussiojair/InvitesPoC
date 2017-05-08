package com.jaircardenas.invitespoc;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.appinvite.AppInvite;
import com.google.android.gms.appinvite.AppInviteInvitation;
import com.google.android.gms.appinvite.AppInviteInvitationResult;
import com.google.android.gms.appinvite.AppInviteReferral;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;

import java.util.HashMap;

public class InvitesActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{

    private static final String LOG_TAG = "InvitesActivity";
    private static final int REQUEST_INVITE = 1234;
    private Button invitar;
    private GoogleApiClient mGoogleApiClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invites);


        invitar = (Button) findViewById(R.id.invitarbtn);

        setup();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        Log.d(LOG_TAG, "onActivityResult: requestCode=" + requestCode + ", resultCode=" + resultCode);

        if(requestCode == REQUEST_INVITE){

            if (resultCode == RESULT_OK) {

                String[] ids = AppInviteInvitation.getInvitationIds(resultCode, data);
                for (String id : ids) {

                    Log.d(LOG_TAG, "onActivityResult: sent invitation " + id);

                }
            }
        }
    }

    private void checkIntent(){


    }

    private void setup(){

        checkIntent();


        invitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                HashMap map = new HashMap();
                map.put("infodata", "hacked by mexican warros");


                Intent intent = new AppInviteInvitation.IntentBuilder(getString(R.string.invitation_title))
                        .setMessage(getString(R.string.invitation_msg))
                        // .setDeepLink(Uri.parse(getString(R.string.invitation_deeplink_welcome) + "&datainfo=hackedbymexicanhackers" ))
                        .setDeepLink(Uri.parse("android://enlacejudio.com/welcome/?datainfo=hackedbyme"))
                        // .setCustomImage(Uri.parse(getString(R.string.invitation_image)))
                        // .setCallToActionText(getString(R.string.invitation_cta))
                        .setEmailHtmlContent(getString(R.string.html_template))
                        .setEmailSubject("Instala esta asombrosa app!")
                        .build();

                startActivityForResult(intent, REQUEST_INVITE);
            }
        });


        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(AppInvite.API)
                .enableAutoManage(this, this)
                .build();

        boolean autoLaunchDeepLink = false;


        AppInvite.AppInviteApi.getInvitation(mGoogleApiClient, this, autoLaunchDeepLink)
                .setResultCallback(new ResultCallback<AppInviteInvitationResult>(){

                    @Override
                    public void onResult(@NonNull AppInviteInvitationResult appInviteInvitationResult) {

                        Log.d(LOG_TAG, "getInvitation:onResult:" + appInviteInvitationResult.getStatus());


                        if (appInviteInvitationResult.getStatus().isSuccess()) {



                            // Extract information from the intent
                            Intent intent = appInviteInvitationResult.getInvitationIntent();
                            String deepLink = AppInviteReferral.getDeepLink(intent);
                            String invitationId = AppInviteReferral.getInvitationId(intent);

                            Log.v(LOG_TAG, AppInviteReferral.hasReferral(intent)? "referral: true" : "referral: false" );
                            if(AppInviteReferral.hasReferral(intent)){

                                Log.v(LOG_TAG, "datainfo 2: " + intent.getDataString());

                            }




                            Log.v(LOG_TAG, "datainfo: " + intent.getDataString());

                            Uri uri = Uri.parse(deepLink);

                            if(uri.getQueryParameter("datainfo") != null){
                                Log.v(LOG_TAG, "datainfo: " + uri.getQueryParameter("datainfo"));
                                Toast.makeText(InvitesActivity.this, "datainfo: " + uri.getQueryParameter("datainfo"), Toast.LENGTH_SHORT).show();
                            }


                            Log.v(LOG_TAG, "deepLink: " + deepLink);

                            Log.v(LOG_TAG, "invitationId: " + invitationId);


                            if(deepLink.equals(getString(R.string.invitation_deeplink_content))){

                            }

                            // Because autoLaunchDeepLink = true we don't have to do anything
                            // here, but we could set that to false and manually choose
                            // an Activity to launch to handle the deep link here.
                            // ...
                        }
                    }
                });
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
