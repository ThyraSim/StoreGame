

$(document).ready(function () {

    // on applique le filtre dès le départ pour retirer les jeux posséder
    appliquerFiltres();


    var loggedInAccountFlag = $('input[name="loggedInAccountFlag"]').val();


    if (loggedInAccountFlag === "true") {
        $('#ownedGameRow').show();
    } else {
        $('#ownedGameRow').hide();
    }



    // Fonction pour appliquer tous les filtres
    function appliquerFiltres() {

        var gameNameInput = $('#gameNameInput').val().toLowerCase();
        var selectedGenre = $('#cbGenre').val().toLowerCase();
        var selectedRange = $('#priceFilter option:selected');
        var minPriceRange = selectedRange.attr('minPriceRange');
        var maxPriceRange = selectedRange.attr('maxPriceRange');
        var chkShowOwnedGame = $('#chkShowOwnedGame').prop('checked');

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
});
