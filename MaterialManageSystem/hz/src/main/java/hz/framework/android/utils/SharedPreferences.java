package hz.framework.android.utils;

import android.content.Context;
import android.content.SharedPreferences.Editor;


public class SharedPreferences {

    private static final String SP_NAME = "hz";


    private static SharedPreferences instance = new SharedPreferences();

    public SharedPreferences() {
    }

    private static synchronized void syncInit() {
        if (instance == null) {
            instance = new SharedPreferences();
        }
    }

    public static SharedPreferences getInstance() {
        if (instance == null) {
            syncInit();
        }
        return instance;
    }

    private android.content.SharedPreferences getSp(Context context) {
        return context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
    }

    public int getInt(Context context,String key, int def) {
        try {
            android.content.SharedPreferences sp = getSp(context);
            if (sp != null)
                def = sp.getInt(key, def);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return def;
    }

    public void putInt(Context context,String key, int val) {
        try {
            android.content.SharedPreferences sp = getSp(context);
            if (sp != null) {
                Editor e = sp.edit();
                e.putInt(key, val);
                e.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public long getLong(Context context,String key, long def) {
        try {
            android.content.SharedPreferences sp = getSp(context);
            if (sp != null)
                def = sp.getLong(key, def);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return def;
    }

    public void putLong(Context context,String key, long val) {
        try {
            android.content.SharedPreferences sp = getSp(context);
            if (sp != null) {
                Editor e = sp.edit();
                e.putLong(key, val);
                e.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getString(Context context,String key, String def) {
        try {
            android.content.SharedPreferences sp = getSp(context);
            if (sp != null)
                def = sp.getString(key, def);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return def;
    }

    public void putString(Context context,String key, String val) {
        try {
            android.content.SharedPreferences sp = getSp(context);
            if (sp != null) {
                Editor e = sp.edit();
                e.putString(key, val);
                e.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean getBoolean(Context context,String key, boolean def) {
        try {
            android.content.SharedPreferences sp = getSp(context);
            if (sp != null)
                def = sp.getBoolean(key, def);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return def;
    }

    public void putBoolean(Context context,String key, boolean val) {
        try {
            android.content.SharedPreferences sp = getSp(context);
            if (sp != null) {
                Editor e = sp.edit();
                e.putBoolean(key, val);
                e.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void remove(Context context,String key) {
        try {
            android.content.SharedPreferences sp = getSp(context);
            if (sp != null) {
                Editor e = sp.edit();
                e.remove(key);
                e.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
