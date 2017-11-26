const badwords = require('badwords-list')

const functions = require('firebase-functions');
const admin = require('firebase-admin');
admin.initializeApp(functions.config().firebase);

const MESSAGE_NOTIFICATION_TOPIC = 'message';
const POSITIVE_WORDS = ['friendly', 'lovely', 'nice']

exports.onUpdateFeedSendNotification = functions.database.ref('/messages/{messageId}').onWrite(event => {
    const message = event.data.val();
    console.log(`Received: ${message.text}`);

    if(!message.sanatized) {
        console.log('Sanatize input...')
        message.text = message.text.toLowercase().replace(badwords.regex, getRandomPositiveWord());
        message.sanatized = true;
    }

    const saveToDatabasePromise = event.data.adminRef.update(message);
    const sendNotificationPromise = sendNotification(message);

    return Promise.all([saveToDatabasePromise, sendNotificationPromise]);
});

const sendNotification = message => {
    const payload = {
        notification: {
            title: message.name,
            body: message.text
        }
    };

    return admin.messaging().sendToTopic(MESSAGE_NOTIFICATION_TOPIC, payload);
}

const getRandomPositiveWord = () => POSITIVE_WORDS[Math.floor(Math.random()*POSITIVE_WORDS.length)];
