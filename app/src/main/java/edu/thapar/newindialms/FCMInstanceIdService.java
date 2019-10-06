package edu.thapar.newindialms;

import com.google.firebase.iid.FirebaseInstanceId;

/**
 * Created by kamalshree on 10/7/2017.
 */

public class FCMInstanceIdService  {

    String refreshedToken = FirebaseInstanceId.getInstance().getToken();
}