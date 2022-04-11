importScripts('https://www.gstatic.com/firebasejs/7.10.0/firebase-app.js');
importScripts('https://www.gstatic.com/firebasejs/7.10.0/firebase-messaging.js');

  firebase.initializeApp({
    apiKey: "AIzaSyBKFrEPBQGxFC2CCiB6vDEsbEax_pks41k",
    projectId: "push-notifier-31bbc",
    messagingSenderId: "108686248814",
    appId: "1:108686248814:web:5928e12cdd3ce5a3aacf7f"
  });

const messaging = firebase.messaging();
messaging.usePublicVapidKey('BDWGDT1KtohHpjo_9sYX07OunVDOBn-P0sWX-shY6B4EWeqa_PCbfELnb8T7m1WzwDg_GoH_LLBvw7zoKchiCLU');





