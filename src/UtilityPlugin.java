package com.davidbriglio.utility;

import android.content.Context;
import android.app.NotificationManager;
import android.app.NotificationChannel;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;
import java.util.List;

public class UtilityPlugin extends CordovaPlugin {
  @Override
  public boolean execute(final String action, final JSONArray args, final CallbackContext callbackContext)
      throws JSONException {
    Context mContext = this.cordova.getActivity().getApplicationContext();

    if (action.equals("getNotificationChannel") && android.os.Build.VERSION.SDK_INT >= 26) {
      NotificationManager manager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
      String channelId = args.getString(0);
      if (channelId != null && !channelId.equals("")) {
        NotificationChannel channel = manager.getNotificationChannel(channelId);
        JSONObject payload = new JSONObject();

        // Put all channel settings into JSON to return to cordova
        payload.put("canBypassDnd", channel.canBypassDnd());
        payload.put("canShowBadge", channel.canShowBadge());
        payload.put("description", channel.getDescription());
        payload.put("group", channel.getGroup());
        payload.put("id", channel.getId());
        payload.put("importance", channel.getImportance());
        payload.put("lightColour", channel.getLightColor());
        payload.put("lockscreenVisibility", channel.getLockscreenVisibility());
        payload.put("name", channel.getName());
        payload.put("sound", channel.getSound());
        payload.put("vibrationPattern", channel.getVibrationPattern());
        payload.put("showsLights", channel.shouldShowLights());
        payload.put("vibration", channel.shouldVibrate());
        payload.put("summary", channel.toString());

        // if (android.os.Build.VERSION.SDK_INT >= 29) {
        // payload.put("canBubble", channel.canBubble());
        // payload.put("userSetImportance", channel.hasUserSetImportance());
        // }

        // if (android.os.Build.VERSION.SDK_INT >= 30) {
        // payload.put("conversationId", channel.getConversationId());
        // payload.put("parentChannelId", channel.getParentChannelId());
        // payload.put("userSetSound", channel.hasUserSetSound());
        // payload.put("importantConversation", channel.isImportantConversation());
        // }

        callbackContext.success(payload);
      } else {
        callbackContext.error("Invalid channel id provided.");
      }
    } else if (action.equals("getNotificationChannels") && android.os.Build.VERSION.SDK_INT >= 26) {
      NotificationManager manager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
      List<NotificationChannel> channels = manager.getNotificationChannels();
      JSONArray payload = new JSONArray();

      for (int i = 0; i < channels.size(); i++) {
        payload.put(channels.get(i).getId());
      }

      callbackContext.success(payload);
    } else {
      callbackContext.error("Invalid plugin action");
    }

    return true;
  }
}