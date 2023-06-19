$(document).ready(function() {
    $('#btnSearchByName').click(function() {

        var gameNameInput = $('#gameNameInput').val().toLowerCase(); // Récupération de la valeur du champ de recherche par nom
        $('div[name="Card-Game"]').each(function() { // Parcours de chaque élément de div avec l'attribut name="Card-Game"
            var gameName = $(this).attr('gameName').toLowerCase(); // Récupération du nom du jeu en cours de vérification
            if (gameName.includes(gameNameInput)) { // Vérification si le nom du jeu correspond à la recherche
                $(this).show(); // Afficher le jeu correspondant
            } else {
                $(this).hide(); // Masquer le jeu s'il ne correspond pas à la recherche
            }
        });
    });

    $('#genre').change(function() {

        var selectedGenre = $(this).val(); // Récupération de la valeur sélectionnée dans la liste déroulante de genre
        $('div[name="Card-Game"]').hide(); // Masquer tous les jeux
        if (selectedGenre === '') {
            $('div[name="Card-Game"]').show(); // Afficher tous les jeux si aucun genre n'est sélectionné
        } else {
            $('div[name="Card-Game"][genre="' + selectedGenre + '"]').show(); // Afficher les jeux correspondant au genre sélectionné
        }
    });
});

$(document).ready(function() {
    $('#priceFilter').change(function() {
        // Récupérer la plage de prix sélectionnée
        var selectedRange = $('#priceFilter option:selected');
        var minPriceRange = selectedRange.attr('minPriceRange');
        var maxPriceRange = selectedRange.attr('maxPriceRange');



        // Effectuer la recherche en fonction de la plage de prix sélectionnée
        $('div[name="Card-Game"]').each(function() {
            var gamePrice = parseFloat($(this).attr('gamePrice'));
            if (gamePrice >= minPriceRange && gamePrice <= maxPriceRange) {
                $(this).show(); // Afficher l'élément s'il correspond à la plage de prix
            } else {
                $(this).hide(); // Masquer l'élément s'il ne correspond pas à la plage de prix
            }
        });
    });
});

