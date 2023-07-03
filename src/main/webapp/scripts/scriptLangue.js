// script.js
function setLanguage(lang) {
    var currentUrl = window.location.href;
    var separator = (currentUrl.indexOf('?') === -1) ? '?' : '&';

    // Construire la nouvelle URL avec le paramètre de langue
    var newUrl;
    if (currentUrl.indexOf('lang=') !== -1) {
        newUrl = currentUrl.replace(/(lang=)[^\&]+/, '$1' + lang);
    } else {
        newUrl = currentUrl + separator + 'lang=' + lang;
    }

    // Rediriger vers la nouvelle URL
    window.location.href = newUrl;
}