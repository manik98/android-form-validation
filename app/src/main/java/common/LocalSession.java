package common;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class LocalSession {
    private SharedPreferences pref;
    private SharedPreferences.Editor prefEditor;

    public LocalSession(Context ctx){
        pref = PreferenceManager.getDefaultSharedPreferences(ctx);
        prefEditor = pref.edit();
    }

    public boolean getLoginStatus(){
        return pref.getBoolean(AppConstants.LOGIN_STATUS,false);
    }
    public boolean getRegistrationStatus(){return pref.getBoolean(AppConstants.REGISTRATION_STATUS,false);}

    public void setLoginStatus(){
        prefEditor.putBoolean(AppConstants.LOGIN_STATUS,true);
        prefEditor.commit();
    }

    public void setRegistrationStatus(){
        prefEditor.putBoolean(AppConstants.REGISTRATION_STATUS,true);
        prefEditor.commit();
    }

    public void logout(){
        //prefEditor.remove(AppConstants.LOGIN_STATUS);
        prefEditor.clear();
        prefEditor.commit();
    }
}
