package api.modules.Restapiclient;


import org.json.JSONObject;

abstract class EventListener
{
    public static interface Callback
    {
        public void onEvent(JSONObject jsonObject);
    }
}
