var exec = require('cordova/exec');

module.exports = {
  getNotificationChannel: id => new Promise((resolve, reject) => exec(resolve, reject, 'UtilityPlugin', 'getNotificationChannel', [id || ''])),
  getNotificationChannels: () => new Promise((resolve, reject) => exec(resolve, reject, 'UtilityPlugin', 'getNotificationChannels', []))
};