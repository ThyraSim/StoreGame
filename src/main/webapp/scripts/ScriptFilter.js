$(document).ready(function() {
    $('#btnSearchByName').click(function() {
        console.log("Recherche de nom"); // Log pour indiquer la recherche par nom
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
        console.log("Recherche de genre"); // Log pour indiquer la recherche par genre
        var selectedGenre = $(this).val(); // Récupération de la valeur sélectionnée dans la liste déroulante de genre
        $('div[name="Card-Game"]').hide(); // Masquer tous les jeux
        if (selectedGenre === '') {
            $('div[name="Card-Game"]').show(); // Afficher tous les jeux si aucun genre n'est sélectionné
        } else {
            $('div[name="Card-Game"][genre="' + selectedGenre + '"]').show(); // Afficher les jeux correspondant au genre sélectionné
        }
    });
});