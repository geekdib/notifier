async function init() {

  firebase.initializeApp({
    apiKey: "AIzaSyBKFrEPBQGxFC2CCiB6vDEsbEax_pks41k",
    projectId: "push-notifier-31bbc",
    messagingSenderId: "108686248814",
    appId: "1:108686248814:web:5928e12cdd3ce5a3aacf7f"
  });
  
const registration = await navigator.serviceWorker.register('sw.js');

await navigator.serviceWorker.ready;

  const messaging = firebase.messaging();
  messaging.usePublicVapidKey('BDWGDT1KtohHpjo_9sYX07OunVDOBn-P0sWX-shY6B4EWeqa_PCbfELnb8T7m1WzwDg_GoH_LLBvw7zoKchiCLU');
  messaging.useServiceWorker(registration);	
  
  try {
    await messaging.requestPermission();
    console.log('requestPermission');
  } catch (e) {
    console.log('Unable to get permission', e);
    return;
  }


  const currentToken = await messaging.getToken();
  console.log(currentToken);
  fetch('/dib-register?token='+currentToken, { method: 'get'});
  showData();

  messaging.onTokenRefresh(async () => {
    console.log('token refreshed');
    const newToken = await messaging.getToken();
    console.log('fetch');
    console.log(currentToken);
    fetch('/dib-register?token='+currentToken, { method: 'get'});
  });
  
}

init();