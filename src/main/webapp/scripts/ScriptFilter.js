$(document).ready(function () {
    // Fonction pour appliquer tous les filtres
    function appliquerFiltres() {
        var gameNameInput = $('#gameNameInput').val().toLowerCase();
        var selectedGenre = $('#cbGenre').val().toLowerCase();
        var selectedRange = $('#priceFilter option:selected');
        var minPriceRange = selectedRange.attr('minPriceRange');
        var maxPriceRange = selectedRange.attr('maxPriceRange');

        $('div[name="Card-Game"]').each(function () {
            var gameName = $(this).attr('gameName').toLowerCase();
            var genre = $(this).attr('genre').toLowerCase();
            var gamePrice = parseFloat($(this).attr('gamePrice'));

            // Appliquer le filtre par nom
            var filtreNom = gameName.includes(gameNameInput);

            // Appliquer le filtre par genre
            var filtreGenre = selectedGenre === '' || selectedGenre === genre;

            // Appliquer le filtre par prix
            var filtrePrix = gamePrice >= minPriceRange && gamePrice <= maxPriceRange;

            // Afficher ou masquer en fonction des conditions combinées des filtres
            if (filtreNom && filtreGenre && filtrePrix) {
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
    $('#chkAfficherToutJeu').change(function () {
        if (this.checked) {
            $('#actionInput').val('desactiverOwnedFiltre');
        } else {
            $('#actionInput').val('');
        }
        // Soumettre le formulaire
        $('#FormAfficherToutJeu').submit();
    });
});
