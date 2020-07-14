# cordova-plugin-android-utility

This plugin is built as a general tool utility to perform relatively small tasks on the native android side.

---

Contents:

- [Setup](#Setup)
- [Methods](#methods)
  - [getNotificationChannels()](#getNotificationChannels)
  - [getNotificationChannel()](#getNotificationChannel)
- [Questions](#Questions?)
- [License](#License)

## Setup

```shell
cordova plugin add cordova-plugin-android-utility

# or

cordova plugin add https://github.com/DavidBriglio/cordova-plugin-android-utility
```

---

## Methods

### getNotificationChannels

`Usage: cordova.plugins.android.utility.getNotificationChannels()`

This method will return a promise with an array of notification channel id strings.

Example Usage:

```javascript

cordova.plugins.android.utility.getNotificationChannels()
  .then(channels => {
    // List all channels
    for (let i in channels) {
      console.log(`Channel ${i}: ${channels[i]}`)
    }
  })
  .catch(message => console.log('Error: ' + message))

```

### getNotificationChannel

`Usage: cordova.plugins.android.utility.getNotificationChannel([string: channel id])`

This method will return a promise with all information about the specified channel id.

[Android Documentation](https://developer.android.com/reference/android/app/NotificationChannel)

Returns Promise with:
| Index | Type | Description |
|-------|------|-------------|
| canBypassDnd | Boolean | True if the notification bypasses do not disturb. |
| canShowBadge | Boolean | True if the notification will show a badge when active. |
| description | String | Description of the channel. |
| group | String | Channel group. |
| id | String | Channel ID. |
| importance | Integer | Integer representing channel importance. Refer to android documentation for value representations. |
| lightColour | Integer | Integer representation of the light colour shown when `showsLights` is true. |
| lockscreenVisibility | Integer | Integer value representing visibility of the notification on the lock screen. Refer to the android documentation for value representations. |
| name | String | Name of the channel. |
| sound | String | URI of the sound being used. |
| vibrationPattern | Array | Vibration pattern array. |
| showsLights | Boolean | Can show LED light when notification is active. |
| vibration | Boolean | True if notification will cause a vibration. |
| summary | String | The `toString` method result of the channel. |

Example Usage:

```javascript

cordova.plugins.android.utility.getNotificationChannel('TEST_CHANNEL_1')
  .then(channel => {
    // Show some of the channel information
    console.log('This channel is: ' + channel.name)
    console.log('This channel has vibration: ' + (channel.vibration ? 'Enabled' : 'Disabled'))
    console.log('This channel uses the sound: ' + channel.sound)
  })
  .catch(message => console.log('Error: ' + message))

```

---

## Questions?

Please feel free to open an issue or make a pull request!

---

## License

MIT - Please see the LICENSE file for details.

---

David Briglio (2020)
