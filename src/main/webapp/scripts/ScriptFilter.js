

$(document).ready(function () {
    console.log("initialisation")


// recuperer le boolean du flag si le compte est connecte ou non

    var loggedInAccountFlag = $('input[name="loggedInAccountFlag"]').val();

// Si connecter a in compte on va afficher le checkbox pour afficher les jeux posséder.
    if (loggedInAccountFlag === "true") {
        $('#filterAllGameRow').show();
    } else {
        $('#filterAllGameRow').hide();
    }



    // Fonction pour appliquer tous les filtres
    function appliquerFiltres() {

        // on recupere les differentes valeur de la section des filtres

        var gameNameInput = $('#gameNameInput').val().toLowerCase();  //filtre du nom
        var selectedGenre = $('#cbGenre').val().toLowerCase();         //filtre du genre
        var selectedRange = $('#priceFilter option:selected');          //filtre du prix
        var minPriceRange = selectedRange.attr('minPriceRange');
        var maxPriceRange = selectedRange.attr('maxPriceRange');
        var chkShowOwnedGame = $('#chkShowOwnedGame').prop('checked'); // filtre des jeux possedé


        //Pour conserver les choix de filtre saisi par l'utilisateur, on va les conserver dans un localstorage, ainsi
        // après un rechargement de page ( achat par exemple) les sélections de filtre seront conserver
        localStorage.setItem('gameNameFilter', gameNameInput);
        localStorage.setItem('genreFilterPosition', $('#cbGenre').prop('selectedIndex'));
        localStorage.setItem('priceFilter', $('#priceFilter').val());
        localStorage.setItem('showOwnedGameFilter', chkShowOwnedGame);

        //Pour chaque carte de jeu ("Card-Game") on récupère les attributs pour pouvoir appliquer les filtres.
        $('div[name="Card-Game"]').each(function () {
            var gameName = $(this).attr('gameName').toLowerCase();
            var genre = $(this).attr('genre').toLowerCase();
            var gamePrice = parseFloat($(this).attr('gamePrice'));
            var gameOwned =  ($(this).attr('gameOwned'));

            // Appliquer le filtre par nom
            var filtreNom = gameName.includes(gameNameInput);

            // Appliquer le filtre par genre
            var filtreGenre = selectedGenre === '' || selectedGenre === genre;

            // Appliquer le filtre par prix
            var filtrePrix = gamePrice >= minPriceRange && gamePrice <= maxPriceRange;

            // Appliquer le filtre pour jeu

            var filtreOwned = (gameOwned == 'false' && chkShowOwnedGame==false )|| chkShowOwnedGame==true;

            // Afficher ou masquer en fonction des conditions combinées des filtres
            if (filtreNom && filtreGenre && filtrePrix && filtreOwned) {
                $(this).show();
            } else {
                $(this).hide();
            }
        });
    }

    // On met à jour les valeurs de filtre selon les valeurs du localstorage respectif
    var storedGameNameFilter = localStorage.getItem('gameNameFilter');
    if (storedGameNameFilter != null) {
        $('#gameNameInput').val(storedGameNameFilter);
    }

    var storedGenreFilter = localStorage.getItem('genreFilterPosition');
    if (storedGenreFilter != null) {
        $('#cbGenre').prop('selectedIndex', storedGenreFilter);
    }

    var storedPriceFilter = localStorage.getItem('priceFilter');
    if (storedPriceFilter != null) {
        $('#priceFilter').val(storedPriceFilter);
    }

    var storedShowOwnedGameFilter = localStorage.getItem('showOwnedGameFilter');
    if (storedShowOwnedGameFilter != null && storedShowOwnedGameFilter === 'true') {
        $('#chkShowOwnedGame').prop('checked', true);

    }
    //appliquer le filtre au départ
    appliquerFiltres()

    // Gestionnaire d'événement pour le filtre par nom
    $('#btnSearchByName').click(function () {
        appliquerFiltres();
    });

    // Gestionnaire d'événement pour le filtre par genre
    $('#cbGenre').change(function () {
        appliquerFiltres();
    });

    // Gestionnaire d'événement pour le filtre par prix
    $('#priceFilter').change(function () {
        appliquerFiltres();
    });

    // Gestionnaire d'événement pour la case à cocher "Afficher Tout Jeu"
    $('#chkShowOwnedGame').change(function () {
            appliquerFiltres();
    });

    //Gestionnaire d'événement si on clique sur le bouton "Reset filter"
    $('#btnResetFilter').click(function () {
        console.log("click reset filter")
        resetFilter();
        appliquerFiltres();
    });

});

//Fonction pour remettre les valeurs des filtres par défaut
function resetFilter(){

    $('#gameNameInput').val("");
    $('#cbGenre option:first').prop('selected', true);
    $('#priceFilter option:first').prop('selected', true);
    $('#chkShowOwnedGame').prop('checked', false);

}
